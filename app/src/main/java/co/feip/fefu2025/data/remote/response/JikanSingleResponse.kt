package co.feip.fefu2025.data.remote.response

import com.google.gson.annotations.SerializedName

data class JikanSingleResponse<T>(
    @SerializedName("data") val data: T
)