package co.feip.fefu2025.presentation.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.feip.fefu2025.domain.entities.Anime
import co.feip.fefu2025.domain.usecases.GetAnimeListUseCase
import co.feip.fefu2025.presentation.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.compose.runtime.State



@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val useCase: GetAnimeListUseCase
) : ViewModel() {

    private var currentPage = 1
    private var endReached = false
    private var isLoading = false

    private val _uiState = mutableStateOf<UiState<List<Anime>>>(UiState.Loading)
    val uiState: State<UiState<List<Anime>>> get() = _uiState

    private var cachedList = mutableListOf<Anime>()

    init {
        loadNextPage()
    }

    fun loadNextPage() {
        if (isLoading || endReached) return

        isLoading = true
        if (cachedList.isEmpty()) _uiState.value = UiState.Loading

        viewModelScope.launch {
            try {
                val newItems = useCase.exec(currentPage, limit = 10)
                if (newItems.isEmpty()) {
                    endReached = true
                } else {
                    cachedList.addAll(newItems)
                    _uiState.value = UiState.Success(cachedList.toList())
                    currentPage++
                }
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "Неизвестная ошибка")
            } finally {
                isLoading = false
            }
        }
    }

    fun retry() {
        _uiState.value = UiState.Loading
        loadNextPage()
    }
}


