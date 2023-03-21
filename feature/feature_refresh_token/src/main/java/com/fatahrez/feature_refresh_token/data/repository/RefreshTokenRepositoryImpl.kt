package com.fatahrez.feature_refresh_token.data.repository

import com.fatahrez.feature_refresh_token.data.remote.RefreshTokenApi
import com.fatahrez.feature_refresh_token.domain.model.Token
import com.fatahrez.feature_refresh_token.domain.repository.RefreshTokenRepository
import javax.inject.Inject

class RefreshTokenRepositoryImpl @Inject constructor(
    private val refreshTokenApi: RefreshTokenApi
): RefreshTokenRepository {
    override suspend fun refreshToken(token: String): Token {
        return refreshTokenApi.refreshToken(token).toToken()
    }
}