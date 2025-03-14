package enric.domenech.app2u.data.repositories


import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.asImageBitmap
import enric.domenech.app2u.data.network.NetworkServiceImpl
import enric.domenech.app2u.data.realmObjects.PendingLike
import enric.domenech.app2u.data.realmObjects.RealmResult
import enric.domenech.app2u.domain.models.Result
import enric.domenech.app2u.domain.repositories.RealmRepository
import io.realm.kotlin.Realm
import io.realm.kotlin.UpdatePolicy
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream

class RealmRepositoryImpl(
    private val realm: Realm,
    private val networkService: NetworkServiceImpl
) : RealmRepository {

    private val _dataState = MutableStateFlow<List<Result>>(emptyList())
    val dataState: StateFlow<List<Result>> get() = _dataState

    override fun findResultById(id: Int): Result? {
        realm.query(RealmResult::class).find().firstOrNull { it.id == id }.let {
            if (it != null) {
                return Result(
                    id = it.id,
                    avatar = it.avatar,
                    description = it.description,
                    email = it.email,
                    facebook = it.facebook,
                    firstName = it.firstName,
                    guid = it.guid,
                    image = it.image,
                    instagram = it.instagram,
                    isRemoved = it.isRemoved,
                    lastName = it.lastName,
                    webpage = it.webpage,
                    isFavorite = it.isFavorite,
                    imageBitmap = it.imageByteArray?.let { image -> byteArrayToImageBitmap(image) }
                )
            } else {
                return null
            }
        }
    }

    /**
     * Guarda los datos en la base de datos local Realm
     * @param data Lista de objetos RealmResult para guardar
     */
    override suspend fun saveDataToRealm(data: List<RealmResult>) {
        withContext(Dispatchers.IO) {
            realm.write {
                data.forEach {
                    val result = RealmResult().apply {
                        id = it.id
                        avatar = it.avatar
                        description = it.description
                        email = it.email
                        facebook = it.facebook
                        firstName = it.firstName
                        guid = it.guid
                        image = it.image
                        instagram = it.instagram
                        isRemoved = it.isRemoved
                        lastName = it.lastName
                        webpage = it.webpage
                        isFavorite = it.isFavorite
                        imageByteArray = it.imageByteArray
                    }
                    copyToRealm(result, updatePolicy = UpdatePolicy.ALL)
                }
            }
        }
    }


    /**
     * Obtiene los datos almacenados en la base de datos local Realm
     * @return Lista de resultados desde la caché
     */
    override suspend fun getCachedData(): List<Result> {
        return withContext(Dispatchers.IO) {
            val realmResults = realm.query(RealmResult::class).find()
            val results = realmResults.map { realmResult ->
                Result(
                    id = realmResult.id,
                    avatar = realmResult.avatar,
                    description = realmResult.description,
                    email = realmResult.email,
                    facebook = realmResult.facebook,
                    firstName = realmResult.firstName,
                    guid = realmResult.guid,
                    image = realmResult.image,
                    instagram = realmResult.instagram,
                    isRemoved = realmResult.isRemoved,
                    lastName = realmResult.lastName,
                    webpage = realmResult.webpage,
                    isFavorite = realmResult.isFavorite,
                    imageBitmap = byteArrayToImageBitmap(realmResult.imageByteArray!!)
                )
            }
            // Update the internal state with the retrieved data
            _dataState.value = results
            results
        }
    }

    override suspend fun toggleFavorite(id: Int) {
        withContext(Dispatchers.IO) {
            try {
                realm.write {
                    val result =
                        query(clazz = RealmResult::class).find().firstOrNull { it.id == id }
                    result?.let {
                        it.isFavorite = !it.isFavorite
                    }
                }
                // Update the in-memory state
                _dataState.value = _dataState.value.map { result ->
                    if (result.id == id) {
                        result.copy(isFavorite = !result.isFavorite!!)
                    } else {
                        result
                    }
                }
            } catch (e: Exception) {
                println("Error al cambiar estado de favorito: ${e.message}")
            }
        }
    }

    /**
     * Guarda un "like" pendiente cuando no hay conexión a internet
     * @param idResult ID del resultado para el like
     */
    override suspend fun savePendingLike(idResult: Int, isFavorite: Boolean) {
        withContext(Dispatchers.IO) {
            println("Saveing like for id: $idResult")
            realm.write {
                val pendingLike = PendingLike().apply {
                    this.idResult = idResult
                    this.isFavorite = isFavorite
                }
                copyToRealm(pendingLike, updatePolicy = UpdatePolicy.ALL)
            }
            // Make sure in-memory state matches
            if (_dataState.value.any { it.id == idResult && it.isFavorite != isFavorite }) {
                _dataState.value = _dataState.value.map { result ->
                    if (result.id == idResult) {
                        result.copy(isFavorite = isFavorite)
                    } else {
                        result
                    }
                }
            }
        }
    }

    /**
     * Envía un "like" al servidor y elimina el pendiente si existe
     * @param idResult ID del resultado para el like
     */
    override suspend fun sendLikeToServer(idResult: Int, isFavorite: Boolean) {
        withContext(Dispatchers.IO) {
            // Mock sending like to server
            networkService.sendLikeToServer(idResult, isFavorite)
            // Remove pending like from Realm
            realm.write {
                val pendingLike =
                    query(clazz = PendingLike::class).find().firstOrNull { it.idResult == idResult }
                pendingLike?.let {
                    delete(pendingLike)
                }
            }
            // Ensure in-memory state is consistent
            if (_dataState.value.any { it.id == idResult && it.isFavorite != isFavorite }) {
                _dataState.value = _dataState.value.map { result ->
                    if (result.id == idResult) {
                        result.copy(isFavorite = isFavorite)
                    } else {
                        result
                    }
                }
            }
        }
    }

    /**
     * Sincroniza todos los "likes" pendientes cuando se recupera la conexión
     */
    override suspend fun syncPendingLikes() {
        withContext(Dispatchers.IO) {
            val pendingLikes = realm.query(PendingLike::class).find()
            println("Found ${pendingLikes.size} pending likes to sync")
            pendingLikes.forEach { pendingLike ->
                sendLikeToServer(pendingLike.idResult, pendingLike.isFavorite)
            }
        }
    }


    /**
     * Convierte los objetos Result del dominio a objetos RealmResult para la persistencia
     * @param results Lista de objetos Result
     * @return Lista de objetos RealmResult
     */
    override fun mapResultsToRealmResults(results: List<Result>): List<RealmResult> {
        return results.map { result ->
            RealmResult().apply {
                id = result.id
                avatar = result.avatar
                description = result.description
                email = result.email
                facebook = result.facebook
                firstName = result.firstName
                guid = result.guid
                image = result.image
                instagram = result.instagram
                isRemoved = result.isRemoved
                lastName = result.lastName
                webpage = result.webpage
                isFavorite = result.isFavorite!!
                imageByteArray = imageBitmapToByteArray(result.imageBitmap!!)
            }
        }
    }

    /**
     * Convierte un array de bytes a ImageBitmap para mostrar en UI
     * @param byteArray Array de bytes a convertir
     * @return ImageBitmap resultante o null
     */
    private fun byteArrayToImageBitmap(byteArray: ByteArray): ImageBitmap? {
        val bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
        return bitmap?.asImageBitmap()
    }

    /**
     * Convierte un ImageBitmap a un array de bytes para almacenamiento
     * @param imageBitmap La imagen a convertir
     * @return Array de bytes de la imagen
     */
    private fun imageBitmapToByteArray(imageBitmap: ImageBitmap): ByteArray {
        val bitmap = imageBitmap.asAndroidBitmap()
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
        return stream.toByteArray()
    }
}