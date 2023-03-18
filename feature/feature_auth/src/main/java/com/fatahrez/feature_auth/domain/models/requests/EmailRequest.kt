package com.fatahrez.feature_auth.domain.models.requests

import com.fatahrez.feature_auth.data.remote.dto.requests.EmailRequestDTO

data class EmailRequest(
    val email: String
) {
    fun toEmailRequestDTO(): EmailRequestDTO {
        return EmailRequestDTO(
            email = email
        )
    }
}