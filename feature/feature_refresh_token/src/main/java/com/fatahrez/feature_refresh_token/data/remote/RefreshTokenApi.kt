package com.fatahrez.feature_refresh_token.data.remote

import com.fatahrez.feature_refresh_token.data.remote.dto.TokenDTO
import retrofit2.http.Field
import retrofit2.http.POST

interface RefreshTokenApi {

    @POST("auth/jwt/refresh/")
    suspend fun refreshToken(@Field("refresh") token: String): TokenDTO

}