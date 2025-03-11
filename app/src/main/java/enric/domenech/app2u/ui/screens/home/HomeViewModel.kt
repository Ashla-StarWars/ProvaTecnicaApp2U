package enric.domenech.app2u.ui.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import enric.domenech.app2u.data.repositories.RepositoryImpl
import enric.domenech.app2u.domain.models.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: RepositoryImpl
) : ViewModel() {

    var state by mutableStateOf(UiState())
        private set

    val dataState: StateFlow<List<Result>> = repository.dataState

    init {
        viewModelScope.launch(Dispatchers.IO) {
            state = UiState(isLoading = true)
            println("Fetching data from server...")
            repository.fetchDataFromServer()
            state = UiState(isLoading = false, onSucces = true)
        }
    }

    fun getCachedData(): List<Result> {
        return repository.getCachedData()
    }

    data class UiState(
        val isLoading: Boolean = false,
        val onSucces: Boolean = false,
    )
}