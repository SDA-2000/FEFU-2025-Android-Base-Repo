package co.feip.fefu2025.data.repositories

import co.feip.fefu2025.data.remote.api.AnimeApi
import co.feip.fefu2025.data.remote.mapper.toDomain
import co.feip.fefu2025.domain.entities.Anime
import co.feip.fefu2025.domain.repositories.AnimeRepository
import kotlinx.coroutines.delay
import javax.inject.Inject

class AnimeRepositoryImpl @Inject constructor( private val api: AnimeApi) : AnimeRepository  {
    override suspend fun GetAnimeList(): List<Anime> {
        return api.getAnimeList().data.map { it.toDomain()}
    }

    override suspend fun GetAnimeById(id: Int?): Anime {
        val recommendations = getRecommendations(id)
        return api.getAnimeById(id).data.toDomain(recommendations)
    }

    override suspend fun GetRecommendationById(id: Int?): Anime {
        delay(670)
        return api.getAnimeById(id).data.toDomain(emptyList())
    }

    override suspend fun searchAnimeByName(query: String): List<Anime> {
        return api.getAnimeList(query).data.map {it.toDomain()}
    }

    override suspend fun getRecommendations(id: Int?): List<Int> {
        return api.getRecommendations(id).data.map { it.entry.id }
    }
}