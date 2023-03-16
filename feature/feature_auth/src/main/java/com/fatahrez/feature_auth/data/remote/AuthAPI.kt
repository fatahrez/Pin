package com.fatahrez.feature_auth.data.remote

import com.fatahrez.feature_auth.domain.models.requests.SignUpRequest
import com.fatahrez.feature_auth.domain.models.responses.SignUpResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthAPI {

    @POST("auth/users")
    suspend fun postRegisterUser(
        @Body signUpRequest: SignUpRequest
    ): SignUpResponse

}