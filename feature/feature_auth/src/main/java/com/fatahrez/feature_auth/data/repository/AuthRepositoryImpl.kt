package com.fatahrez.feature_auth.data.repository

import com.fatahrez.common.util.ResultWrapper
import com.fatahrez.common.util.safeApiCall
import com.fatahrez.feature_auth.data.remote.AuthAPI
import com.fatahrez.feature_auth.domain.models.requests.SignUpRequest
import com.fatahrez.feature_auth.domain.models.responses.SignUpResponse
import com.fatahrez.feature_auth.domain.repository.AuthRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow

class AuthRepositoryImpl(
    private val authAPI: AuthAPI,
    private val ioDispatchers: CoroutineDispatcher = Dispatchers.IO
): AuthRepository {
    override suspend fun postRegisterUser(signUpRequest: SignUpRequest): Flow<ResultWrapper<SignUpResponse>>
    = safeApiCall(ioDispatchers) {
        authAPI.postRegisterUser(signUpRequest.toSignUpRequestDTO()).toSignUpResponse()
    }
}