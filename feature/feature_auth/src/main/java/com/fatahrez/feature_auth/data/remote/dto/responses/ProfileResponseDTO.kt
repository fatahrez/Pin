package com.fatahrez.feature_auth.data.remote.dto.responses

import com.fatahrez.feature_auth.domain.models.responses.ProfileResponse

data class ProfileResponseDTO(
    val age: Int,
    val gender: String,
    val country: String
) {
    fun toProfileResponse(): ProfileResponse {
        return ProfileResponse(
            age = age,
            gender = gender,
            country = country
        )
    }
}
