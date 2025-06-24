package co.feip.fefu2025.domain.usecases

import co.feip.fefu2025.domain.entities.Anime
import co.feip.fefu2025.domain.repositories.AnimeRepository
import javax.inject.Inject

class GetRecommendationByIdUseCase @Inject constructor(private val repository: AnimeRepository) {
    suspend fun exec(id: Int?): Anime{
        return repository.GetRecommendationById(id)
    }
}