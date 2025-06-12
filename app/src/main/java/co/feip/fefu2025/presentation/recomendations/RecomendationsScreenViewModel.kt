package co.feip.fefu2025.presentation.recomendations

import androidx.lifecycle.ViewModel
import co.feip.fefu2025.domain.entities.Anime

class RecomendationsScreenViewModel : ViewModel() {
    private var _recommendations: List<Anime>? = null
    val recommendations: List<Anime>?
        get() = _recommendations

    fun setRecommendations(list: List<Anime>) {
        _recommendations = list
    }
}