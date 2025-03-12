package enric.domenech.app2u.data.repositories

import enric.domenech.app2u.data.network.NetworkServiceImpl
import enric.domenech.app2u.domain.models.Result
import enric.domenech.app2u.domain.repositories.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class RepositoryImpl(
    private val conn: NetworkServiceImpl,
) : Repository {
    private val _dataState = MutableStateFlow<List<Result>>(emptyList())
    val dataState: StateFlow<List<Result>> get() = _dataState

//    private val _currentPage = MutableStateFlow(1)
//    val currentPage: StateFlow<Int> get() = _currentPage

    override suspend fun fetchDataFromServer() {
        _dataState.value = conn.fetchDataFromServer()
    }

    override suspend fun fetchNextPage(page: Int) {
        TODO("Not yet implemented")
    }

    override fun updateData(data: List<Result>) {
        _dataState.value = data
    }

    override fun getCachedData(): List<Result> {
        return _dataState.value
    }

}