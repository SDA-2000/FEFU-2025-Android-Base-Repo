package co.feip.fefu2025.presentation.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.feip.fefu2025.domain.entities.Anime
import co.feip.fefu2025.domain.usecases.GetAnimeByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AnimeScreenViewModel @Inject constructor(private val getAnimeByIdUseCase: GetAnimeByIdUseCase) : ViewModel() {
    private val _anime =  MutableLiveData<Anime?>()
    private val _recomendation = MutableLiveData<List<Anime?>>()
    val anime : LiveData<Anime?> = _anime

    fun LoadAnimeById(id : Int) : Anime?
    {
        _anime.value = getAnimeByIdUseCase.exec(id)
        return anime.value
    }

    fun LoadRecsById(anime_recs_ids : List<Int>): List<Anime?>
    {
        var temp : Anime?
        val temp1 = mutableListOf<Anime?>()
        for(item in anime_recs_ids)
        {
            temp = getAnimeByIdUseCase.exec(item)
            temp1.add(temp)
        }
        _recomendation.value = temp1
        return temp1
    }



}