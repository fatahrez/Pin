package com.fatahrez.common.shared.remote

import android.content.SharedPreferences
import com.fatahrez.common.util.Constants
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val sharedPreferences: SharedPreferences
): Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = sharedPreferences.getString(Constants.ACCESS_TOKEN, null)
        val request = chain.request()

        return if (token.isNullOrEmpty()) {
            chain.proceed(request)
        } else {
            val newRequest = request.newBuilder().header(
                "Authorization", "Bearer $token"
            ).build()
            chain.proceed(newRequest)
        }
    }


}