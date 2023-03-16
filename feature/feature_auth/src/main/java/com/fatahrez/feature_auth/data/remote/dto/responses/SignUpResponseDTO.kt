package com.fatahrez.feature_auth.data.remote.dto.responses

import com.fatahrez.feature_auth.domain.models.responses.SignUpResponse
import com.google.gson.annotations.SerializedName

data class SignUpResponseDTO(
    val username: String,
    @SerializedName("first_name")
    val firstName: String,
    val email:String,
    val pkid: Int
) {
    fun toSignUpResponse(): SignUpResponse {
        return SignUpResponse(
            username = username,
            firstName = firstName,
            email = email,
            pkid = pkid
        )
    }
}
