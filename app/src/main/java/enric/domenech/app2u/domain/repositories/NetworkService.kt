package enric.domenech.app2u.domain.repositories

import androidx.compose.ui.graphics.ImageBitmap
import enric.domenech.app2u.domain.models.Result

/**
 * NetworkService
 *
 * Interfaz que define las operaciones de red para obtener datos del servidor remoto.
 *
 * Esta interfaz es responsable de:
 * - Realizar peticiones HTTP al servidor para obtener datos de perfiles
 * - Manejar la paginación de resultados
 * - Descargar y procesar imágenes desde URLs remotas
 *
 * El componente que implementa esta interfaz (NetworkServiceImpl) se encarga de
 * gestionar la comunicación con la API, deserializar las respuestas JSON a objetos Kotlin,
 * y convertir los datos binarios de imágenes a formatos utilizables por la UI.
 *
 * La separación mediante esta interfaz permite simular fácilmente las respuestas
 * del servidor durante las pruebas unitarias.
 */
interface NetworkService {
    /**
     * Obtiene la lista inicial de resultados desde el servidor.
     * @return Lista de objetos Result con la información de perfiles.
     */
    suspend fun fetchDataFromServer() : List<Result>

    /**
     * Obtiene la siguiente página de resultados mediante paginación.
     * @param page URL o identificador de la página a obtener.
     * @return Lista de nuevos resultados correspondientes a la página solicitada.
     */
    suspend fun fetchNextPage(page: String) : List<Result>

    /**
     * Descarga una imagen desde una URL remota como array de bytes.
     * @param imageUrl URL de la imagen a descargar.
     * @return Datos binarios de la imagen descargada.
     */
    suspend fun fetchImage(imageUrl: String) : ByteArray

    /**
     * Descarga y convierte una imagen remota al formato ImageBitmap para su uso directo en la UI.
     * @param imageUrl URL de la imagen a descargar.
     * @return Imagen convertida a ImageBitmap o null si ocurre algún error.
     */
    suspend fun fetchImageAsBitmap(imageUrl: String): ImageBitmap?

    /**
     * Mock de una petición al servidor para actualizar el estado de favorito
     * de un objeto Result.
     */
    suspend fun sendLikeToServer(idResult: Int, isFavorite: Boolean) : Boolean
}