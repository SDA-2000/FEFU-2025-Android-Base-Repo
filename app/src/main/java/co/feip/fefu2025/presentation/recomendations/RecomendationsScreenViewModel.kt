package co.feip.fefu2025.presentation.recomendations

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.feip.fefu2025.domain.entities.Anime
import co.feip.fefu2025.domain.usecases.GetRecommendationsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.launch
import androidx.compose.runtime.State

@HiltViewModel
class RecomendationsScreenViewModel @Inject constructor(
    private val getRecommendationsUseCase: GetRecommendationsUseCase
) : ViewModel() {

    private val _recs = mutableStateOf<List<Anime>>(emptyList())
    val recs: State<List<Anime>> = _recs

    private var currentPage = 1
    private var isLoading = false
    private var endReached = false

    private var currentAnimeId: Int? = null

    fun loadInitial(id: Int?) {
        if (currentAnimeId != id) {
            currentAnimeId = id
            currentPage = 1
            _recs.value = emptyList()
            endReached = false
            loadNextPage()
        }
    }

    fun loadNextPage() {
        if (isLoading || endReached || currentAnimeId == null) return

        isLoading = true
        viewModelScope.launch {
            val newRecs = getRecommendationsUseCase.exec(currentAnimeId, currentPage)
            if (newRecs.isNotEmpty()) {
                _recs.value = _recs.value + newRecs
                currentPage++
            } else {
                endReached = true
            }
            isLoading = false
        }
    }
}