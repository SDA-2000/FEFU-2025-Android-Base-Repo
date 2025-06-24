package co.feip.fefu2025.presentation.recomendations

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.feip.fefu2025.domain.entities.Anime
import co.feip.fefu2025.domain.usecases.GetRecommendationsUseCase
import co.feip.fefu2025.presentation.details.AnimeScreenViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class RecomendationsScreenViewModel @Inject constructor() : ViewModel() {
    private val _recs = mutableStateOf<List<Anime>>(emptyList())

    fun setRecs(list: List<Anime>) {
        _recs.value = list
    }

    fun getRecs(): List<Anime> = _recs.value
}