package enric.domenech.app2u.ui.screens.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import enric.domenech.app2u.data.realmDB.RealmResult
import enric.domenech.app2u.data.repositories.RepositoryImpl
import enric.domenech.app2u.domain.models.Result
import io.realm.kotlin.Realm
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailViewModel(
    private val dataId: Int,
    private val repository: RepositoryImpl,
    private val realm: Realm
) : ViewModel() {

    var state by mutableStateOf(UiState())
        private set

    private val _dataState = MutableStateFlow<Result?>(null)
    val dataState: StateFlow<Result?> = _dataState

    /**
     * Inicializa el ViewModel cargando los datos del resultado específico
     * basado en el ID proporcionado
     */
    init {
        viewModelScope.launch {
            state = UiState(isLoading = true)
            val dataList = repository.getCachedData()
            val dataToShow = dataList.find { it.id == dataId }
            _dataState.value = dataToShow
            state = UiState(isLoading = false)
        }
    }

    /**
     * Alterna el estado de favorito de un resultado y lo persiste en Realm
     * @param idResult ID del resultado a modificar
     */
    fun toggleFavorite(idResult: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    realm.write {
                        val result = query(clazz = RealmResult::class).find().firstOrNull { it.id == idResult }
                        result?.let {
                            it.isFavorite = !it.isFavorite
                        }
                    }
                } catch (e: Exception) {
                    println("Error al cambiar estado de favorito: ${e.message}")
                }
            }
        }
    }

    data class UiState(
        val isLoading: Boolean = false,
    )

}