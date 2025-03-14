package enric.domenech.app2u.ui.screens.home

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import enric.domenech.app2u.data.network.NetworkMonitor
import enric.domenech.app2u.data.realmDB.PendingLike
import enric.domenech.app2u.data.realmDB.RealmResult
import enric.domenech.app2u.data.repositories.RepositoryImpl
import enric.domenech.app2u.domain.models.Result
import io.realm.kotlin.Realm
import io.realm.kotlin.UpdatePolicy
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream

class HomeViewModel(
    private val repository: RepositoryImpl,
    private val networkMonitor: NetworkMonitor,
    private val realm: Realm
) : ViewModel() {

    private var state by mutableStateOf(UiState())

    private val _dataState = MutableStateFlow<List<Result>>(emptyList())
    val dataState: StateFlow<List<Result>> = _dataState

    init {
        // Inicializa el ViewModel cargando datos desde cache o servidor
        viewModelScope.launch(Dispatchers.IO) {
            // Código de inicialización...
            state = UiState(isLoading = true)
            val cachedData = getCachedData()
            if (cachedData.isEmpty()) {
                println("Fetching data from server...")
                repository.fetchDataFromServer()
                repository.dataState.collect { results ->
                    _dataState.value = results
                    if (results.isNotEmpty()) {
                        saveDataToRealm(mapResultsToRealmResults(results))
                    }
                }
            } else {
                println("Loading data from cache...")
                _dataState.value = cachedData
                repository.updateData(cachedData)
            }
            state = UiState(isLoading = false, onSucces = true)
        }
    }

    /**
     * Obtiene los datos almacenados en la base de datos local Realm
     * @return Lista de resultados desde la caché
     */
    private suspend fun getCachedData(): List<Result> {
        return withContext(Dispatchers.IO) {
            val realmResults = realm.query(RealmResult::class).find()
            realmResults.map { realmResult ->
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
        }
    }

    /**
     * Guarda los datos en la base de datos local Realm
     * @param data Lista de objetos RealmResult para guardar
     */
    private fun saveDataToRealm(data: List<RealmResult>) {
        viewModelScope.launch {
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
    }

    /**
     * Convierte los objetos Result del dominio a objetos RealmResult para la persistencia
     * @param results Lista de objetos Result
     * @return Lista de objetos RealmResult
     */
    private fun mapResultsToRealmResults(results: List<Result>): List<RealmResult> {
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
     * Alterna el estado de favorito de un resultado y lo persiste
     * @param idResult ID del resultado a modificar
     */
    fun toggleFavorite(idResult: Int) {
        viewModelScope.launch {
            val updatedList = _dataState.value.map { result ->
                if (result.id == idResult) {
                    result.copy(isFavorite = !result.isFavorite!!)
                } else {
                    result
                }
            }
            _dataState.value = updatedList
            withContext(Dispatchers.IO) {
                try {
                    realm.write {
                        val result = query(clazz = RealmResult::class).find().firstOrNull { it.id == idResult }
                        result?.let {
                            it.isFavorite = !it.isFavorite
                        }
                    }
                    _dataState.value = getCachedData()
                } catch (e: Exception) {
                    println("Error al cambiar estado de favorito: ${e.message}")
                }
                if (!networkMonitor.isConnected.value) {
                    savePendingLike(idResult)
                } else {
                    sendLikeToServer(idResult)
                }
            }
        }
    }

    /**
     * Guarda un "like" pendiente cuando no hay conexión a internet
     * @param idResult ID del resultado para el like
     */
    private fun savePendingLike(idResult: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                println("Saveing like for id: $idResult")
                realm.write {
                    val pendingLike = PendingLike().apply {
                        this.idResult = idResult
                    }
                    copyToRealm(pendingLike, updatePolicy = UpdatePolicy.ALL)
                }
            }
        }
    }

    /**
     * Envía un "like" al servidor y elimina el pendiente si existe
     * @param idResult ID del resultado para el like
     */
    private fun sendLikeToServer(idResult: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                // Mock sending like to server
                println("Sending like for id: $idResult")
                // Remove pending like from Realm
                realm.write {
                    val pendingLike = query(clazz = PendingLike::class).find().firstOrNull { it.idResult == idResult }
                    pendingLike?.let {
                        delete(pendingLike)
                    }
                }
                state = UiState(isLoading = false, onSucces = true)
            }
        }
    }

    /**
     * Sincroniza todos los "likes" pendientes cuando se recupera la conexión
     */
    fun syncPendingLikes() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val pendingLikes = realm.query(PendingLike::class).find()
                println("Found ${pendingLikes.size} pending likes to sync")
                pendingLikes.forEach { pendingLike ->
                    sendLikeToServer(pendingLike.idResult)
                }
            }
        }
    }

    data class UiState(
        val isLoading: Boolean = false,
        val onSucces: Boolean = false,
    )
}