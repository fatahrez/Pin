package com.fatahrez.feature_refresh_token.domain.repository

import com.fatahrez.feature_refresh_token.domain.model.Token

interface RefreshTokenRepository {

    suspend fun refreshToken(token: String): Token

}