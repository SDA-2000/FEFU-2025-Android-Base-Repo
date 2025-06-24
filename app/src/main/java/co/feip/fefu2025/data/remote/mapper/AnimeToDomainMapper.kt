package co.feip.fefu2025.data.remote.mapper

import co.feip.fefu2025.data.remote.dto.AnimeDto
import co.feip.fefu2025.domain.entities.Anime

fun AnimeDto.toDomain(recommendations: List<Int> = emptyList()): Anime {
    return Anime(
        id = id,
        name = name,
        genres = genres.map { it.name },
        year = year?: 0,
        imageUrl = images.jpg.image_url,
        description = description,
        score = score,
        episodes = episodes?: 0,
        recommendations = recommendations
    )
}