package co.feip.fefu2025.domain.usecases

import android.util.Log
import co.feip.fefu2025.domain.entities.Anime
import co.feip.fefu2025.domain.repositories.AnimeRepository
import javax.inject.Inject

class GetAnimeListUseCase @Inject constructor(private val animeRepository: AnimeRepository) {
    suspend fun exec(): List<Anime>
    {
        return animeRepository.GetAnimeList()
    }

    suspend fun exec(page: Int, limit: Int) : List<Anime>{
        return animeRepository.GetAnimeList(page, limit)
    }
}