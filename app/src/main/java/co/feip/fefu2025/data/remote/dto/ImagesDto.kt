package co.feip.fefu2025.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ImagesDto(
    @SerializedName("jpg") val jpg: JpgImageDto
)