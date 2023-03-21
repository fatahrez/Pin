package com.fatahrez.feature_auth.domain.models.responses

data class SignUpResponse(
    val username: String,
    val firstName: String,
    val email:String,
    val id: String,
    val token: Token
)

data class Token(
    val access: String,
    val refresh: String
)