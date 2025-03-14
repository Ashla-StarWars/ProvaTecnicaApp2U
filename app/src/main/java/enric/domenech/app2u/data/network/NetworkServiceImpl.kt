package enric.domenech.app2u.data.network

import android.graphics.BitmapFactory
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import enric.domenech.app2u.domain.models.Response
import enric.domenech.app2u.domain.models.Result
import enric.domenech.app2u.domain.repositories.NetworkService
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.put
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.client.statement.readRawBytes
import io.ktor.client.statement.request
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json

/**
 * NetworkServiceImpl
 *
 * Implementación de la interfaz NetworkService que maneja todas las comunicaciones
 * con el servidor remoto de la aplicación.
 *
 * Esta clase es responsable de:
 * - Obtener datos desde el API REST
 * - Convertir respuestas JSON a modelos de dominio
 * - Descargar y procesar imágenes desde URLs remotas
 * - Manejar errores de red y conversión de datos
 *
 * Utiliza Ktor Client para las peticiones HTTP y kotlinx.serialization
 * para deserializar las respuestas JSON.
 */

class NetworkServiceImpl(
    private val json: Json,
    private val client: HttpClient
) : NetworkService {

    // Cache para almacenar los resultados y evitar llamadas repetidas
    private var cachedResults: List<Result> = emptyList()

    /**
     * Obtiene la lista de resultados desde el endpoint principal.
     * Para cada resultado, también descarga y asocia su imagen correspondiente.
     */
    override suspend fun fetchDataFromServer(): List<Result> {

        // Cache para evitar llamadas repetidas innecesarias
        if (cachedResults.isNotEmpty()) {
            println("Returning cached results")
            return cachedResults
        }

        return try {
            withContext(Dispatchers.IO) {
                val response: HttpResponse = client.get("/api/photographer/")
                println("${response.request.method.value} ${response.request.url}")

                if (response.status.value !in 200..299) {
                    println("Error: HTTP ${response.status}")
                    return@withContext emptyList()
                }

                val responseData: Response = json.decodeFromString(response.bodyAsText())
                println("Received ${responseData.results.size} results")

                responseData.results.forEach {
                    println("Processing result with id: ${it.id}")
                    it.imageBitmap = fetchImageAsBitmap(it.image)
                }
                responseData.results
            }
        } catch (e: Exception) {
            println("Error fetching first page: ${e.message}")
            emptyList()
        }
    }

    /**
     * Descarga una imagen desde una URL y la convierte a formato ImageBitmap
     * para su uso directo en la UI con Jetpack Compose.
     */
    override suspend fun fetchImageAsBitmap(imageUrl: String): ImageBitmap? {
        val byteArray = fetchImage(imageUrl)
        return if (byteArray.isNotEmpty()) {
            val bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
            bitmap?.asImageBitmap()
        } else {
            null
        }
    }

    /**
     * Mock de una petición al servidor para actualizar el estado de favorito
     * de un objeto Result.
     */
    override suspend fun sendLikeToServer(idResult: Int, isFavorite: Boolean): Boolean {
        println("Sending like to server for result with id: $idResult")
        try {
            return withContext(Dispatchers.IO) {
                val response: HttpResponse = client.put("/api/photographer/$idResult/&isFavorite=$isFavorite")
                println("${response.request.method.value} ${response.request.url}")

                if (response.status.value !in 200..299) {
                    println("Error: HTTP ${response.status}")
                    false
                } else {
                    println("Like sent successfully")
                    true
                }
            }
        } catch (e: Exception) {
            println("Error sending like to server: ${e.message}")
            return false
        }
    }

    /**
     * función para paginación de resultados.
     * Pendiente de implementar.
     */
    override suspend fun fetchNextPage(page: String): List<Result> {
        TODO("Not yet implemented")
    }

    /**
     * Descarga una imagen desde una URL y la devuelve como array de bytes.
     * Maneja los errores de red y devuelve un array vacío en caso de fallo.
     */
    override suspend fun fetchImage(imageUrl: String): ByteArray {
        return try {
            withContext(Dispatchers.IO) {
                val response: HttpResponse = client.get(imageUrl)
                println("${response.request.method.value} ${response.request.url}")

                if (response.status.value !in 200..299) {
                    println("Error: HTTP ${response.status}")
                    return@withContext ByteArray(0)
                }

                response.readRawBytes()
            }
        } catch (e: Exception) {
            println("Error fetching image: ${e.message}")
            ByteArray(0)
        }
    }



}