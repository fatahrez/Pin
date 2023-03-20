package com.fatahrez.feature_auth.domain.repository

import com.fatahrez.common.util.ResultWrapper
import com.fatahrez.feature_auth.domain.models.requests.EmailRequest
import com.fatahrez.feature_auth.domain.models.requests.ProfileRequest
import com.fatahrez.feature_auth.domain.models.requests.SignInRequest
import com.fatahrez.feature_auth.domain.models.requests.SignUpRequest
import com.fatahrez.feature_auth.domain.models.responses.EmailResponse
import com.fatahrez.feature_auth.domain.models.responses.ProfileResponse
import com.fatahrez.feature_auth.domain.models.responses.SignInResponse
import com.fatahrez.feature_auth.domain.models.responses.SignUpResponse
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    suspend fun postEmail(
        emailRequest: EmailRequest
    ): Flow<ResultWrapper<EmailResponse>>

    suspend fun postSignIn(
        signInRequest: SignInRequest
    ): Flow<ResultWrapper<SignInResponse>>

    suspend fun postRegisterUser(
        signUpRequest: SignUpRequest
    ): Flow<ResultWrapper<SignUpResponse>>

    suspend fun postProfile(
        profileRequest: ProfileRequest
    ): Flow<ResultWrapper<ProfileResponse>>
}