package enric.domenech.app2u.data.realmDB

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class RealmResult : RealmObject {
    @PrimaryKey
    var id: Int = 0
    var avatar: String? = null
    var description: String = ""
    var email: String = ""
    var facebook: String? = null
    var firstName: String = ""
    var guid: String = ""
    var image: String = ""
    var instagram: String? = null
    var isRemoved: Boolean = false
    var lastName: String = ""
    var webpage: String? = null
    var isFavorite: Boolean = false
    var imageByteArray: ByteArray? = null
}