package enric.domenech.app2u.domain.repositories

import enric.domenech.app2u.domain.models.Result

interface Repository {
    suspend fun fetchDataFromServer()
    suspend fun fetchNextPage(page: Int)

    fun getCachedData() : List<Result>
    fun updateData(data: List<Result>)
}