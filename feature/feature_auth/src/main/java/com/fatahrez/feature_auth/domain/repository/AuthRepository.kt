package com.fatahrez.feature_auth.domain.repository

import com.fatahrez.common.util.ResultWrapper
import com.fatahrez.feature_auth.domain.models.requests.SignUpRequest
import com.fatahrez.feature_auth.domain.models.responses.SignUpResponse
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    suspend fun postRegisterUser(
        signUpRequest: SignUpRequest
    ): Flow<ResultWrapper<SignUpResponse>>

}