package enric.domenech.app2u.domain.repositories

import enric.domenech.app2u.data.realmObjects.RealmResult
import enric.domenech.app2u.domain.models.Result

interface RealmRepository {
    fun findResultById(id: Int): Result?
    fun mapResultsToRealmResults(results: List<Result>): List<RealmResult>
    suspend fun saveDataToRealm(data: List<RealmResult>)
    suspend fun getCachedData(): List<Result>
    suspend fun toggleFavorite(id: Int)
    suspend fun savePendingLike(idResult: Int, isFavorite: Boolean)
    suspend fun sendLikeToServer(idResult: Int, isFavorite: Boolean)
    suspend fun syncPendingLikes()
}