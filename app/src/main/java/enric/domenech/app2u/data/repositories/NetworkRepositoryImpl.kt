package enric.domenech.app2u.data.repositories

import enric.domenech.app2u.data.network.NetworkServiceImpl
import enric.domenech.app2u.domain.models.Result
import enric.domenech.app2u.domain.repositories.NetworkRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * NetworkRepositoryImpl
 *
 * Implementación del repositorio que actúa como intermediario entre la fuente de datos
 * remota (NetworkServiceImpl) y la lógica de negocio de la aplicación.
 *
 * Esta clase es responsable de:
 * - Obtener datos del servidor a través de NetworkServiceImpl
 * - Almacenar en caché los resultados recibidos en un StateFlow
 * - Proporcionar acceso a los datos almacenados en caché
 * - Gestionar la actualización de los datos cuando sea necesario
 *
 * Implementa la interfaz NetworkRepository para garantizar una correcta separación de
 * responsabilidades y facilitar las pruebas unitarias.
 */
class NetworkRepositoryImpl(
    private val conn: NetworkServiceImpl
) : NetworkRepository {

    // StateFlow para almacenar y exponer los datos actuales de la aplicación
    private val _dataState = MutableStateFlow<List<Result>>(emptyList())
    val dataState: StateFlow<List<Result>> get() = _dataState

    // StateFlow para almacenar y exponer la respuesta de la peticion al servidor
    private val _responseState = MutableStateFlow(false)

    /**
     * Obtiene datos del servidor remoto y actualiza el estado interno
     * con los resultados obtenidos.
     */
    override suspend fun fetchDataFromServer() {
        _dataState.value = conn.fetchDataFromServer()
    }

    /**
     * función para obtener la siguiente página de resultados.
     * Pendiente de implementación.
     */
    override suspend fun fetchNextPage(page: String) {
        TODO("Not yet implemented")
    }

    /**
     * Actualiza manualmente el estado del repositorio con nuevos datos.
     * Útil para actualizar el estado después de operaciones locales.
     */
    override fun updateData(data: List<Result>) {
        _dataState.value = data
    }

    /**
     * Devuelve los datos actualmente almacenados en caché sin
     * realizar una nueva petición al servidor.
     */
    override fun getCachedData(): List<Result> {
        return _dataState.value
    }

    /**
     * Mock de una petición al servidor para actualizar el estado de favorito
     * de un objeto Result.
     */
    override suspend fun updateFavoriteStatus(id: Int, isFavorite: Boolean) {
        _responseState.value = conn.sendLikeToServer(id, isFavorite)
    }

}