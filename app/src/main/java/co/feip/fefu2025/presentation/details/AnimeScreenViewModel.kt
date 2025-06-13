package co.feip.fefu2025.presentation.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.feip.fefu2025.domain.entities.Anime
import co.feip.fefu2025.domain.usecases.GetAnimeByIdUseCase
import co.feip.fefu2025.presentation.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimeScreenViewModel @Inject constructor(
    private val getAnimeByIdUseCase: GetAnimeByIdUseCase
) : ViewModel() {

    var animeState by mutableStateOf<UiState<Anime>>(UiState.Loading)
        private set

    var recsState by mutableStateOf<List<Anime>>(emptyList())
        private set

    private var currentAnimeId: Int? = null

    fun loadAnimeData(id: Int) {
        currentAnimeId = id

        viewModelScope.launch {
            animeState = UiState.Loading
            recsState = emptyList()

            try {
                val anime = getAnimeByIdUseCase.exec(id)
                    ?: throw Exception("Аниме не найдено")

                if (currentAnimeId != id) return@launch

                animeState = UiState.Success(anime)

                val recs = coroutineScope {
                    anime.Recomendations.map { recId ->
                        async {
                            try {
                                getAnimeByIdUseCase.exec(recId)
                            } catch (e: Exception) {
                                null
                            }
                        }
                    }.awaitAll().filterNotNull()
                }


                if (currentAnimeId == id) {
                    recsState = recs
                }

            } catch (e: Exception) {
                if (currentAnimeId == id) {
                    animeState = UiState.Error(e.message ?: "Ошибка")
                }
            }
        }
    }

    fun retry(id: Int) {
        loadAnimeData(id)
    }
}