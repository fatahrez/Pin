package com.fatahrez.feature_auth.domain.models.requests

import com.fatahrez.feature_auth.data.remote.dto.requests.SignUpRequestDTO

data class SignUpRequest(
    val username: String,
    val email: String,
    val firstName: String,
    val password: String,
    val rePassword: String
) {
    fun toSignUpRequestDTO(): SignUpRequestDTO {
        return SignUpRequestDTO(
            username = username,
            email = email,
            firstName = firstName,
            password = password,
            rePassword = rePassword
        )
    }
}
