package com.fatahrez.feature_auth.domain.models.requests

import com.fatahrez.feature_auth.data.remote.dto.requests.SignInRequestDTO

data class SignInRequest(
    val email: String,
    val password: String
) {
    fun toSignInRequestDTO(): SignInRequestDTO {
        return SignInRequestDTO(
            email = email,
            password = password
        )
    }
}
