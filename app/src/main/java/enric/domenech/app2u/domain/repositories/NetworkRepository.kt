package enric.domenech.app2u.domain.repositories

import enric.domenech.app2u.domain.models.Result

/**
 * NetworkRepository
 *
 * Interfaz que define las operaciones de acceso a datos para la aplicación,
 * actuando como punto de entrada único entre la capa de datos y la capa de lógica de negocio.
 *
 * Esta interfaz es responsable de:
 * - Abstraer la fuente de los datos (remota, local, etc.)
 * - Gestionar la obtención de datos desde el servidor
 * - Mantener un caché de datos para su acceso eficiente
 * - Proporcionar funcións para actualizar los datos almacenados
 * - Manejar la paginación de resultados para grandes conjuntos de datos
 *
 * Su implementación concreta (NetworkRepositoryImpl) coordina la comunicación con
 * el servicio de red (NetworkService) y con almacenamiento local,
 * proporcionando una API limpia y coherente al resto de la aplicación.
 */
interface NetworkRepository {
    /**
     * Obtiene datos del servidor remoto y actualiza el estado interno.
     */
    suspend fun fetchDataFromServer()

    /**
     * Obtiene la siguiente página de datos según el parámetro especificado.
     * @param page Identificador o URL de la página a obtener.
     */
    suspend fun fetchNextPage(page: String)

    /**
     * Mock de una petición al servidor para actualizar el estado de favorito
     * de un objeto Result.
     * @param id ID del objeto a actualizar
     * @param isFavorite Nuevo estado de favorito
     * @return Boolean que indica si la operación fue exitosa
     */
    suspend fun updateFavoriteStatus(id: Int, isFavorite: Boolean)

    /**
     * Devuelve los datos actualmente almacenados en caché.
     * @return Lista de resultados almacenados localmente.
     */
    fun getCachedData() : List<Result>

    /**
     * Actualiza manualmente los datos almacenados en el repositorio.
     * @param data Nueva lista de resultados para actualizar el caché.
     */
    fun updateData(data: List<Result>)


}