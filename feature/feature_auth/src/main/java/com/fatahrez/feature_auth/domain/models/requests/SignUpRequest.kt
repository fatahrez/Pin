package com.fatahrez.feature_auth.domain.models.requests

data class SignUpRequest(
    val username: String,
    val email: String,
    val firstName: String,
    val password: String,
    val rePassword: String
)
