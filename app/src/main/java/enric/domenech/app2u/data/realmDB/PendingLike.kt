package enric.domenech.app2u.data.realmDB

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

/**
 * PendingLike
 *
 * Modelo de datos para Realm que representa un "like" pendiente de sincronización.
 *
 * Cuando un usuario marca un elemento como favorito sin conexión a internet,
 * se almacena su ID en esta entidad para su posterior sincronización con el servidor
 * una vez que se reestablezca la conectividad.
 *
 * Esta clase trabaja en conjunto con NetworkMonitor, que se encarga de detectar
 * cuando la conectividad se recupera para iniciar el proceso de sincronización.
 */
class PendingLike : RealmObject {
    @PrimaryKey var idResult: Int = 0
}