package enric.domenech.app2u.domain.models

import kotlinx.serialization.Serializable

/**
 * Response
 *
 * Modelo de datos serializable que representa la estructura de respuesta
 * recibida desde la API del servidor.
 *
 * Esta clase encapsula una respuesta paginada, conteniendo:
 * - Metadatos de paginación (conteo total, enlaces a páginas siguiente/anterior)
 * - La colección de resultados de la consulta actual
 * - Información temporal de la respuesta
 *
 * Se utiliza junto con la biblioteca kotlinx.serialization para deserializar
 * automáticamente las respuestas JSON recibidas a través de NetworkServiceImpl.
 */
@Serializable
data class Response(
    val count: Int,             // Número total de elementos disponibles en la API
    val next: String?,          // URL para obtener la siguiente página de resultados (null si es la última)
    val previous: String?,      // URL para obtener la página anterior de resultados (null si es la primera)
    val results: List<Result>,  // Lista de resultados para la página actual
    val timestamp: String       // Marca temporal de cuándo se generó la respuesta
)