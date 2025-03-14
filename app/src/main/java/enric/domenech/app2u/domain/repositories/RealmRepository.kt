package enric.domenech.app2u.domain.repositories

import enric.domenech.app2u.data.realmObjects.RealmResult
import enric.domenech.app2u.domain.models.Result

/**
 * RealmRepository
 *
 * Interfaz que define las operaciones de acceso a datos para la aplicación,
 * actuando como punto de entrada único entre la capa de datos y la capa de lógica de negocio.
 *
 * Esta interfaz es responsable de:
 * - Abstraer la fuente de los datos (remota, local, etc.)
 * - Gestionar la obtención de datos desde la base de datos local
 * - Mantener un caché de datos para su acceso eficiente
 * - Proporcionar funcións para actualizar los datos almacenados
 * - Manejar la sincronización de datos con el servidor remoto
 *
 * Su implementación concreta (RealmRepositoryImpl) coordina la comunicación con
 * la base de datos local (Realm) y con el servicio de red (NetworkService),
 * proporcionando una API limpia y coherente al resto de la aplicación.
 */
interface RealmRepository {

    /**
     * Obtiene un resultado por su ID desde la base de datos local.
     * @param id Identificador del resultado a obtener.
     * @return Objeto Result con la información del perfil o null si no se encuentra.
     */
    fun findResultById(id: Int): Result?

    /**
     * Mapea una lista de objetos Result a objetos RealmResult para su almacenamiento en la base de datos.
     * @param results Lista de objetos Result a mapear.
     * @return Lista de objetos RealmResult con la información de los perfiles.
     */
    fun mapResultsToRealmResults(results: List<Result>): List<RealmResult>

    /**
     * Almacena los datos en caché en la base de datos local.
     * @param data Lista de objetos RealmResult con la información de los perfiles.
     */
    suspend fun saveDataToRealm(data: List<RealmResult>)

    /**
     * Obtiene los datos almacenados en caché desde la base de datos local.
     * @return Lista de objetos Result con la información de los perfiles.
     */
    suspend fun getCachedData(): List<Result>

    /**
     * Marca un objeto Result como favorito en la base de datos local.
     * @param id, Identificador del objeto a marcar.
     */
    suspend fun toggleFavorite(id: Int)

    /**
     * Obtiene los likes pendientes de sincronizar con el servidor remoto.
     * @return Lista de objetos RealmResult con los likes pendientes.
     */
    suspend fun savePendingLike(idResult: Int, isFavorite: Boolean)

    /**
     * Sincroniza los likes pendientes con el servidor remoto.
     */
    suspend fun sendLikeToServer(idResult: Int, isFavorite: Boolean)

    /**
     * Sincroniza los likes pendientes con el servidor remoto.
     */
    suspend fun syncPendingLikes()
}