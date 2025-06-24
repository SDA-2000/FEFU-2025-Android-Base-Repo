package co.feip.fefu2025.data.remote.dto
import com.google.gson.annotations.SerializedName

data class AnimeDto(
    @SerializedName("mal_id") val id: Int,
    @SerializedName("title") val name: String,
    @SerializedName("images") val images: ImagesDto,
    @SerializedName("genres") val genres: List<GenreDto>,
    @SerializedName("year") val year: Int?,
    @SerializedName("synopsis") val description: String?,
    @SerializedName("score") val score: Double,
    @SerializedName("episodes") val episodes: Int?
)