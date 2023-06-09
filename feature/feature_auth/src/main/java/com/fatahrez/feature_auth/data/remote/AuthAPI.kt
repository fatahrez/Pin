package com.fatahrez.feature_auth.data.remote

import com.fatahrez.feature_auth.data.remote.dto.requests.EmailRequestDTO
import com.fatahrez.feature_auth.data.remote.dto.requests.ProfileRequestDTO
import com.fatahrez.feature_auth.data.remote.dto.requests.SignInRequestDTO
import com.fatahrez.feature_auth.data.remote.dto.requests.SignUpRequestDTO
import com.fatahrez.feature_auth.data.remote.dto.responses.*
import com.fatahrez.feature_auth.domain.models.requests.EmailRequest
import com.fatahrez.feature_auth.domain.models.requests.SignInRequest
import com.fatahrez.feature_auth.domain.models.requests.SignUpRequest
import com.fatahrez.feature_auth.domain.models.responses.CountryResponse
import com.fatahrez.feature_auth.domain.models.responses.EmailResponse
import com.fatahrez.feature_auth.domain.models.responses.SignUpResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface AuthAPI {

    @POST("auth/email/")
    suspend fun postEmail(
        @Body emailRequestDTO: EmailRequestDTO
    ): EmailResponseDTO

    @POST("auth/jwt/create/")
    suspend fun postSignIn(
        @Body signInRequestDTO: SignInRequestDTO
    ): SignInResponseDTO

    @POST("auth/users/")
    suspend fun postRegisterUser(
        @Body signUpRequestDTO: SignUpRequestDTO
    ): SignUpResponseDTO

    @PATCH("profile/update/{username}/")
    suspend fun postProfile(
        @Path("username") username: String,
        @Body profileRequestDTO: ProfileRequestDTO
    ): ProfileResponseDTO

    @GET("profile/countries/")
    suspend fun getCountries(): List<CountryResponseDTO>

}