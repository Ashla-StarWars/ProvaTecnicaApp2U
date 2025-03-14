package enric.domenech.app2u.ui.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import enric.domenech.app2u.data.network.NetworkMonitor
import enric.domenech.app2u.data.repositories.NetworkRepositoryImpl
import enric.domenech.app2u.data.repositories.RealmRepositoryImpl
import enric.domenech.app2u.domain.models.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(
    private val networkMonitor: NetworkMonitor,
    private val networkRepository: NetworkRepositoryImpl,
    private val realmRepository: RealmRepositoryImpl,
) : ViewModel() {

    var state by mutableStateOf(UiState())
        private set

    private val _dataState = MutableStateFlow<List<Result>>(emptyList())
    val dataState: StateFlow<List<Result>> = _dataState

    init {
        println("HomeViewModel initialized, checking for data...")
        // Load data on initialization
        viewModelScope.launch(Dispatchers.IO) {
            loadData()
        }
    }

    private suspend fun loadData() {
        // Only show loading indicator if no data is available in ViewModel or Repository
        if (_dataState.value.isEmpty()) {
            state = UiState(isLoading = true)

            // First check if repository already has data in its dataState
            val repoData = realmRepository.dataState.value
            if (repoData.isNotEmpty()) {
                println("Using existing data from repository state")
                _dataState.value = repoData
                networkRepository.updateData(repoData)
            } else {
                // Repository state is empty, need to load from database or server
                val cachedData = realmRepository.getCachedData()
                if (cachedData.isNotEmpty()) {
                    println("Loading data from cache...")
                    _dataState.value = cachedData
                    networkRepository.updateData(cachedData)
                } else {
                    println("Fetching data from server...")
                    networkRepository.fetchDataFromServer()

                    try {
                        val results = networkRepository.dataState.first { it.isNotEmpty() }
                        _dataState.value = results

                        viewModelScope.launch(Dispatchers.IO) {
                            realmRepository.saveDataToRealm(
                                realmRepository.mapResultsToRealmResults(results)
                            )
                        }
                    } catch (e: Exception) {
                        println("Error fetching data: ${e.message}")
                    }
                }
            }
        } else {
            println("Data already loaded in ViewModel, using existing data")
        }

        state = UiState(isLoading = false, onSucces = true)
    }

    /**
     * Alterna el estado de favorito de un resultado y lo persiste
     * @param idResult ID del resultado a modificar
     */
    fun toggleFavorite(idResult: Int) {
        viewModelScope.launch {
            _dataState.value = _dataState.value.map { result ->
                if (result.id == idResult) {
                    result.copy(isFavorite = !result.isFavorite!!)
                } else {
                    result
                }
            }
            withContext(Dispatchers.IO) {
                try {
                    realmRepository.toggleFavorite(idResult)
                } catch (e: Exception) {
                    println("Error al cambiar estado de favorito: ${e.message}")
                }
                if (!networkMonitor.isConnected.value) {
                    realmRepository.savePendingLike(
                        idResult,
                        _dataState.value.first { it.id == idResult }.isFavorite!!
                    )
                } else {
                    realmRepository.sendLikeToServer(
                        idResult,
                        _dataState.value.first { it.id == idResult }.isFavorite!!
                    )
                    state = UiState(isLoading = false, onSucces = true)
                }
            }
        }
    }

    data class UiState(
        val isLoading: Boolean = false,
        val onSucces: Boolean = false,
    )
}