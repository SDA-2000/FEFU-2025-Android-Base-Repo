package co.feip.fefu2025.domain.usecases

import co.feip.fefu2025.domain.entities.Anime
import co.feip.fefu2025.domain.repositories.AnimeRepository
import javax.inject.Inject

class GetAnimeByIdUseCase @Inject constructor(private val animeRepository: AnimeRepository) {
    suspend fun exec(id: Int) : Anime?{
        return animeRepository.GetAnimeById(id)
    }

    suspend fun execFast(id : Int) : Anime? {
        return animeRepository.GetAnimeByIdFast(id)
    }
}