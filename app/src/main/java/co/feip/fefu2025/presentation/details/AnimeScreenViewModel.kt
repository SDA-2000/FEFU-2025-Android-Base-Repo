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
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimeScreenViewModel @Inject constructor(
    private val getAnimeByIdUseCase: GetAnimeByIdUseCase
) : ViewModel() {

    var anime by mutableStateOf<Anime?>(null)
        private set

    var recommendations by mutableStateOf<List<Anime>>(emptyList())
        private set

    var isLoading by mutableStateOf(true)
        private set

    fun loadAnimeData(id: Int) {
        viewModelScope.launch {
            isLoading = true

            anime = getAnimeByIdUseCase.exec(id)

            val recIds = anime?.Recomendations ?: emptyList()
            recommendations = recIds.mapNotNull { recId ->
                getAnimeByIdUseCase.exec(recId)
            }

            isLoading = false
        }
    }
}
