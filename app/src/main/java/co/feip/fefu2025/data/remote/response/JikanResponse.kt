package co.feip.fefu2025.data.remote.response

import com.google.gson.annotations.SerializedName

data class JikanResponse<T>(
    @SerializedName("data") val data: List<T>
)