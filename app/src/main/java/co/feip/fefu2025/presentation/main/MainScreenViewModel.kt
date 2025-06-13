package co.feip.fefu2025.presentation.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.feip.fefu2025.domain.entities.Anime
import co.feip.fefu2025.domain.usecases.GetAnimeListUseCase
import co.feip.fefu2025.presentation.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val useCase: GetAnimeListUseCase
) : ViewModel() {

    var uiState by mutableStateOf<UiState<List<Anime>>>(UiState.Loading)
        private set

    init {
        loadAnimeList()
    }

    fun loadAnimeList() {
        viewModelScope.launch {
            uiState = UiState.Loading
            try {
                val data = useCase.exec()
                uiState = UiState.Success(data)
            } catch (e: Exception) {
                uiState = UiState.Error(e.message ?: "Неизвестная ошибка")
            }
        }
    }
}

