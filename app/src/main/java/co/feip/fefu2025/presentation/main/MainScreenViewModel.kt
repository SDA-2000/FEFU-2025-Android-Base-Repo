package co.feip.fefu2025.presentation.main

import androidx.lifecycle.ViewModel
import co.feip.fefu2025.domain.entities.Anime
import co.feip.fefu2025.domain.usecases.GetAnimeListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(private val useCase: GetAnimeListUseCase) : ViewModel() {


    fun GetAnimeList() : List<Anime>
    {
        return useCase.exec()
    }
}