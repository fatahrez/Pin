package com.fatahrez.feature_auth.data.remote.dto.responses

import com.fatahrez.feature_auth.domain.models.responses.CountryResponse
import com.google.gson.annotations.SerializedName

data class CountryResponseDTO(
    val name: String,
    @SerializedName("short_code")
    val shortCode: String
) {
    fun toCountryResponse(): CountryResponse {
        return CountryResponse(
            name = name,
            shortCode = shortCode
        )
    }
}
