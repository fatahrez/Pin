package com.fatahrez.feature_auth.data.remote.dto.responses

import com.fatahrez.feature_auth.domain.models.requests.EmailRequest
import com.fatahrez.feature_auth.domain.models.responses.EmailResponse

data class EmailResponseDTO(
    val message: Boolean
) {
    fun toEmailResponse(): EmailResponse {
        return EmailResponse(
            message = message
        )
    }
}
