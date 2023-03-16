package com.fatahrez.feature_auth.domain.models.responses

data class SignUpResponse(
    val username: String,
    val firstName: String,
    val email:String,
    val pkid: Int
)
