package co.feip.fefu2025.presentation.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.feip.fefu2025.domain.entities.Anime
import co.feip.fefu2025.domain.usecases.GetAnimeByIdUseCase
import co.feip.fefu2025.domain.usecases.GetRecommendationsUseCase
import co.feip.fefu2025.presentation.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimeScreenViewModel @Inject constructor(
    private val getAnimeByIdUseCase: GetAnimeByIdUseCase,
    private val getRecommendationsUseCase: GetRecommendationsUseCase
) : ViewModel() {

    var animeState by mutableStateOf<UiState<Anime>>(UiState.Loading)
        private set

    var recsState by mutableStateOf<List<Anime>>(emptyList())
        private set

    private var currentAnimeId: Int? = null

    fun loadAnimeData(id: Int?) {
        currentAnimeId = id

        viewModelScope.launch {
            animeState = UiState.Loading
            recsState = emptyList()

            try {
                val anime = getAnimeByIdUseCase.exec(id)
                    ?: throw Exception("Аниме не найдено")

                if (currentAnimeId != id) return@launch

                animeState = UiState.Success(anime)

                val recs = getRecommendationsUseCase.exec(id!!)
                recsState = recs

            } catch (e: Exception) {
                if (currentAnimeId == id) {
                    animeState = UiState.Error(e.message ?: "Ошибка")
                }
            }
        }
    }
    fun retry(id: Int?) {
        loadAnimeData(id)
    }
}