package enric.domenech.app2u.ui.screens.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import enric.domenech.app2u.data.repositories.RepositoryImpl
import enric.domenech.app2u.domain.models.Result
import kotlinx.coroutines.launch

class DetailViewModel(
    private val dataId: Int,
    private val repository: RepositoryImpl
) : ViewModel() {

    var state by mutableStateOf(UiState())
        private set

    var data by mutableStateOf(UiData())
        private set

    init {
        viewModelScope.launch {
            state = UiState(isLoading = true)
            val dataList = repository.getCachedData()
            val dataToShow = dataList.find { it.id == dataId }
            data = UiData(data = dataToShow)
            state = UiState(isLoading = false)
        }
    }

    data class UiState(
        val isLoading: Boolean = false,
    )

    data class UiData(
        val data: Result? = null,
    )
}