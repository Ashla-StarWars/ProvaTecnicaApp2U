package enric.domenech.app2u.domain.repositories

import enric.domenech.app2u.domain.models.Result

interface NetworkService {
    suspend fun fetchDataFromServer() : List<Result>
    suspend fun fetchNextPage(page: Int) : List<Result>
}