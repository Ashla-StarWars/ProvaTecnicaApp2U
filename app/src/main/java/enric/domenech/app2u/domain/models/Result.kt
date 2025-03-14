package enric.domenech.app2u.domain.models

import androidx.compose.ui.graphics.ImageBitmap
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Result
 *
 * Modelo de datos serializable que representa un perfil o resultado
 * obtenido desde la API del servidor.
 *
 * Esta clase contiene toda la información asociada a un perfil, incluyendo:
 * - Datos personales del usuario (nombre, apellido, email)
 * - Enlaces a redes sociales (Facebook, Instagram)
 * - URLs de imágenes asociadas (avatar, imagen principal)
 * - Información adicional (descripción, página web)
 * - Estado de favorito y datos de imagen procesados para UI
 *
 * Se utiliza tanto para la deserialización de respuestas JSON mediante kotlinx.serialization
 * como para el transporte de datos entre las diferentes capas de la aplicación.
 * Los campos marcados como variables (var) permiten almacenar estado temporal
 * como la imagen procesada o el estado de favorito.
 */
@Serializable
data class Result(
    val avatar: String?,                                // URL de la imagen de avatar (puede ser null)
    val description: String,                            // Descripción o biografía del perfil
    val email: String,                                  // Correo electrónico de contacto
    val facebook: String?,                              // Enlace a perfil de Facebook (puede ser null)
    @SerialName("first_name") val firstName: String,    // Nombre
    val guid: String,                                   // Identificador único global
    val id: Int,                                        // Identificador numérico único
    val image: String,                                  // URL de la imagen principal
    val instagram: String?,                             // Enlace a perfil de Instagram (puede ser null)
    @SerialName("is_removed") val isRemoved: Boolean,   // Indica si el elemento ha sido eliminado
    @SerialName("last_name") val lastName: String,      // Apellido
    val webpage: String?,                               // URL de página web personal (puede ser null)
    var isFavorite: Boolean? = false,                   // Estado de favorito, modificable
    var imageBitmap: ImageBitmap? = null                // Imagen procesada para mostrar en UI
)