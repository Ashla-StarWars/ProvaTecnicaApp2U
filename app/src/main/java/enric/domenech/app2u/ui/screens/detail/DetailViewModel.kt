package enric.domenech.app2u.ui.screens.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import enric.domenech.app2u.data.repositories.NetworkRepositoryImpl
import enric.domenech.app2u.data.repositories.RealmRepositoryImpl
import enric.domenech.app2u.domain.models.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailViewModel(
    private val dataId: Int,
    private val networkRepository: NetworkRepositoryImpl,
    private val realmRepository: RealmRepositoryImpl
) : ViewModel() {

    private var state by mutableStateOf(UiState())

    private val _dataState = MutableStateFlow<Result?>(null)
    val dataState: StateFlow<Result?> = _dataState

    private val _isDataLoaded = MutableStateFlow(false)

    /**
     * Inicializa el ViewModel cargando los datos del resultado específico
     * basado en el ID proporcionado
     */
    init {
        viewModelScope.launch(Dispatchers.IO) {
            if (!_isDataLoaded.value) {
                loadDetailData()
            }
        }
    }

    private fun loadDetailData() {
        state = UiState(isLoading = true)
        if(_dataState.value == null) {
            val result = realmRepository.findResultById(dataId)
            if (result != null) {
                _dataState.value = result
            } else {
                val dataList = networkRepository.getCachedData()
                val dataToShow = dataList.find { it.id == dataId }
                _dataState.value = dataToShow
            }
        }
        _isDataLoaded.value = true
        state = UiState(isLoading = false)
    }

    /**
     * Alterna el estado de favorito de un resultado y lo persiste en Realm
     * @param idResult ID del resultado a modificar
     */
    fun toggleFavorite(idResult: Int) {
        viewModelScope.launch {
            _dataState.value = _dataState.value?.copy(isFavorite = !_dataState.value!!.isFavorite!!)
            withContext(Dispatchers.IO) {
                realmRepository.toggleFavorite(idResult)
                networkRepository.updateFavoriteStatus(idResult, _dataState.value!!.isFavorite!!)
            }
        }
    }

    data class UiState(
        val isLoading: Boolean = false,
    )

}