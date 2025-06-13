package co.feip.fefu2025.presentation.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.feip.fefu2025.domain.entities.Anime
import co.feip.fefu2025.domain.usecases.GetAnimeListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val useCase: GetAnimeListUseCase
) : ViewModel() {

    var animeList by mutableStateOf<List<Anime>>(emptyList())
        private set

    var isLoading by mutableStateOf(true)
        private set

    init {
        loadAnimeList()
    }

    private fun loadAnimeList() {
        viewModelScope.launch {
            isLoading = true
            animeList = useCase.exec()
            isLoading = false
        }
    }
}
