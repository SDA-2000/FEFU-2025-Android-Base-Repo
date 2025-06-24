package co.feip.fefu2025.data.remote.api

import co.feip.fefu2025.data.remote.dto.AnimeDto
import co.feip.fefu2025.data.remote.dto.RecommendationDto
import co.feip.fefu2025.data.remote.response.JikanResponse
import co.feip.fefu2025.data.remote.response.JikanSingleResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AnimeApi {
    @GET("anime")
    suspend fun getAnimeList(@Query("q") query: String? = null) : JikanResponse<AnimeDto>

    @GET("anime/{id}")
    suspend fun getAnimeById(@Path("id") id: Int?): JikanSingleResponse<AnimeDto>

    @GET("anime/{id}/recommendations")
    suspend fun getRecommendations(@Path("id") id: Int?) : JikanResponse<RecommendationDto>
}