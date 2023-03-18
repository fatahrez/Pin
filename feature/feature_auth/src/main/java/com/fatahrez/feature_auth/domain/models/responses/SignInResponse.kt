package com.fatahrez.feature_auth.domain.models.responses

data class SignInResponse(
    val refresh: String,
    val access: String
)
