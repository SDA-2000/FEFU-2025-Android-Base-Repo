package co.feip.fefu2025.domain.repositories

import co.feip.fefu2025.domain.entities.Anime

interface AnimeRepository {
    fun GetAnimeList() : List<Anime>
    fun GetAnimeById(id: Int): Anime?
}