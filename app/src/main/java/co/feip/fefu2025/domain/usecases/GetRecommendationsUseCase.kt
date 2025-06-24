package co.feip.fefu2025.domain.usecases

import co.feip.fefu2025.domain.entities.Anime
import co.feip.fefu2025.domain.repositories.AnimeRepository
import javax.inject.Inject

class GetRecommendationsUseCase @Inject constructor(
    private val repository: AnimeRepository,
    private val getRecommendationByIdUseCase: GetRecommendationByIdUseCase
) {
    suspend fun exec(id: Int): List<Anime> {
        val recsIds = repository.getRecommendations(id).take(10)
        val animeList = mutableListOf<Anime>()

        for(recId in recsIds){
            val anime = getRecommendationByIdUseCase.exec(recId)
            animeList.add(anime)
        }
        return animeList
    }
}