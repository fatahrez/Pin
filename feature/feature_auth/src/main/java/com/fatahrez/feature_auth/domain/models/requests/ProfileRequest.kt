package com.fatahrez.feature_auth.domain.models.requests

import com.fatahrez.feature_auth.data.remote.dto.requests.ProfileRequestDTO

data class ProfileRequest(
    val age: Int? = null,
    val gender: String? = null,
    val country: String? = null
) {
    fun toProfileRequestDTO(): ProfileRequestDTO {
        return ProfileRequestDTO(
            age = age,
            gender = gender,
            country = country
        )
    }
}
