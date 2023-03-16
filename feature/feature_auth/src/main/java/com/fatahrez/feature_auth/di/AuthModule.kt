package com.fatahrez.feature_auth.di

import com.fatahrez.common.shared.remote.HttpClient
import com.fatahrez.common.shared.remote.HttpLogger
import com.fatahrez.common.util.Constants
import com.fatahrez.feature_auth.data.remote.AuthAPI
import com.fatahrez.feature_auth.data.repository.AuthRepositoryImpl
import com.fatahrez.feature_auth.domain.repository.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    @Singleton
    fun providesAuthRepository(
        authAPI: AuthAPI
    ): AuthRepository {
        return AuthRepositoryImpl(authAPI)
    }

    @Provides
    fun providesLoggingInterceptor(): HttpLoggingInterceptor = HttpLogger.create()

    @Provides
    fun providesOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return HttpClient.setupOkHttpClient(
            httpLoggingInterceptor
        )
    }

    @Provides
    @Singleton
    fun providesAuthApi(okHttpClient: OkHttpClient): AuthAPI {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(AuthAPI::class.java)
    }

    @Provides
    fun providesIODispatchers(): CoroutineDispatcher  {
        return Dispatchers.IO
    }
}