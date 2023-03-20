package com.fatahrez.feature_auth.domain.models.requests

data class ProfileRequest(
    val age: Int? = null,
    val gender: String? = null,
    val country: String? = null
)
