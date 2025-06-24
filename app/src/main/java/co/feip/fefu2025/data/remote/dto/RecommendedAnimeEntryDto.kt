package co.feip.fefu2025.data.remote.dto

import com.google.gson.annotations.SerializedName

data class RecommendedAnimeEntryDto(
    @SerializedName("mal_id") val id: Int
)