package com.fatahrez.feature_auth.data.remote.dto.requests

import com.google.gson.annotations.SerializedName

data class SignUpRequestDTO(
    val username: String,
    val email: String,
    @SerializedName("first_name")
    val firstName: String,
    val password: String,
    @SerializedName("re_password")
    val rePassword: String
)
