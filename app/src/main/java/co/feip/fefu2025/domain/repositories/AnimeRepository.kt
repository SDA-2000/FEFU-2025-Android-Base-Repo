package co.feip.fefu2025.domain.repositories

import co.feip.fefu2025.domain.entities.Anime

interface AnimeRepository {
     suspend fun GetAnimeList() : List<Anime>
     suspend fun GetAnimeById(id: Int): Anime?
}