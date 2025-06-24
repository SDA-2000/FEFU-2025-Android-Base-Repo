package co.feip.fefu2025.domain.usecases

import co.feip.fefu2025.domain.entities.Anime
import co.feip.fefu2025.domain.repositories.AnimeRepository
import javax.inject.Inject

class SearchAnimeByNameUseCase @Inject constructor(private val animeRepository: AnimeRepository) {
    suspend fun exec(query: String, page: Int): List<Anime> {
        return animeRepository.searchAnimeByName(query, page, limit = 20)
    }
}