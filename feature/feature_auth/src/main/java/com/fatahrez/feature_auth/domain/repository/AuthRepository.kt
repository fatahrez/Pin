package com.fatahrez.feature_auth.domain.repository

import com.fatahrez.feature_auth.domain.models.requests.SignUpRequest
import com.fatahrez.feature_auth.domain.models.responses.SignUpResponse

interface AuthRepository {

    suspend fun postRegisterUser(
        signUpRequest: SignUpRequest
    ): SignUpResponse

}