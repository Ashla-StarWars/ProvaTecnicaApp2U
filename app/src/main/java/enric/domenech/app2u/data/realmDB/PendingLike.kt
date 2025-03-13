package enric.domenech.app2u.data.realmDB

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class PendingLike : RealmObject {
    @PrimaryKey var idResult: Int = 0
}