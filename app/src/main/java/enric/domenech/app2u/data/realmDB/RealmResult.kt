package enric.domenech.app2u.data.realmDB

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

/**
 * RealmResult
 *
 * Modelo de datos para Realm que representa un resultado o perfil de usuario
 * almacenado localmente en la base de datos.
 *
 * Esta clase contiene toda la información asociada a un perfil, incluyendo:
 * - Datos personales del usuario (nombre, apellido, email)
 * - Enlaces a redes sociales (Facebook, Instagram)
 * - Información de contacto y web
 * - Estado de favorito para funciónalidades offline
 * - Referencias a imágenes (URL y datos binarios almacenados)
 *
 * Se utiliza para persistir localmente los datos obtenidos del servidor,
 * permitiendo acceso offline y la gestión del estado de favoritos.
 */
class RealmResult : RealmObject {
    @PrimaryKey
    var id: Int = 0                         // Identificador único del resultado
    var avatar: String? = null              // URL de la imagen de avatar
    var description: String = ""            // Descripción o biografía
    var email: String = ""                  // Correo electrónico de contacto
    var facebook: String? = null            // Enlace a perfil de Facebook
    var firstName: String = ""              // Nombre
    var guid: String = ""                   // Identificador único global
    var image: String = ""                  // URL de la imagen principal
    var instagram: String? = null           // Enlace a perfil de Instagram
    var isRemoved: Boolean = false          // Indica si el elemento ha sido eliminado
    var lastName: String = ""               // Apellido
    var webpage: String? = null             // URL de página web personal
    var isFavorite: Boolean = false         // Indica si el usuario lo ha marcado como favorito
    var imageByteArray: ByteArray? = null   // Datos binarios de la imagen almacenada localmente
}