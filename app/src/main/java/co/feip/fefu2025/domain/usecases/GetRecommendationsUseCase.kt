package co.feip.fefu2025.domain.usecases

import co.feip.fefu2025.domain.entities.Anime
import co.feip.fefu2025.domain.repositories.AnimeRepository
import javax.inject.Inject

class GetRecommendationsUseCase @Inject constructor(
    private val repository: AnimeRepository,
    private val getRecommendationByIdUseCase: GetRecommendationByIdUseCase
) {
    suspend fun exec(id: Int?, page: Int = 1, pageSize: Int = 10): List<Anime> {
        val recsIds = repository.getRecommendations(id)
        val paginatedIds = recsIds.drop((page - 1) * pageSize).take(pageSize)

        return paginatedIds.map { recId ->
            getRecommendationByIdUseCase.exec(recId)
        }
    }
}