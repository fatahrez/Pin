package com.fatahrez.feature_auth.domain.models.responses

import com.google.gson.annotations.SerializedName

data class SignUpResponse(
    val username: String,
    val firstName: String,
    val email:String,
    val id: String
)
