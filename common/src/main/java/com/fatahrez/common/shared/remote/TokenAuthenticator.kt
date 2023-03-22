package com.fatahrez.common.shared.remote

import android.content.SharedPreferences
import android.util.Log
import androidx.core.content.edit
import com.fatahrez.common.util.Constants
import com.fatahrez.feature_refresh_token.data.repository.RefreshTokenRepositoryImpl
import com.fatahrez.feature_refresh_token.domain.model.Token
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject
import javax.inject.Provider

class TokenAuthenticator @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val repositoryProvider: Provider<RefreshTokenRepositoryImpl>,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
): Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        Log.i("TAG", "authenticate: here")
        // exclude login url or email url from TokenAuthenticator
        if (response.request.url.toString() == Constants.BASE_URL+"auth/jwt/create/") {
            return null
        }
        if (response.request.url.toString() == Constants.BASE_URL+"auth/email/") {
            return null
        }
        return runBlocking {
            if (response.code == 401) {
                val refreshToken = sharedPreferences.getString(Constants.REFRESH_TOKEN,"")
                val updatedAccessToken = refreshToken?.let { refreshAccessToken(it).access }
                sharedPreferences.edit {
                    putString(Constants.ACCESS_TOKEN, updatedAccessToken)
                }
                response.request.newBuilder()
                    .header("Authenticator", "Bearer $updatedAccessToken")
                    .build()
            } else {
                null
            }
        }
    }

    private suspend fun refreshAccessToken(refreshToken: String):
            Token = withContext(ioDispatcher) {
        val repository = repositoryProvider.get()
        repository.refreshToken(refreshToken)
    }
}