package com.fatahrez.feature_auth.data.remote.dto.responses

import com.google.gson.annotations.SerializedName

data class SignUpResponseDTO(
    val username: String,
    @SerializedName("first_name")
    val firstName: String,
    val email:String,
    val pkid: Int
)
