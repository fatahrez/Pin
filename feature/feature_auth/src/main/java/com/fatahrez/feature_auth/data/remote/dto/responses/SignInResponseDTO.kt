package com.fatahrez.feature_auth.data.remote.dto.responses

import com.fatahrez.feature_auth.domain.models.responses.SignInResponse

data class SignInResponseDTO(
    val refresh: String,
    val access: String
) {
    fun toSignInResponse(): SignInResponse {
        return SignInResponse(
            refresh = refresh,
            access = access
        )
    }
}
