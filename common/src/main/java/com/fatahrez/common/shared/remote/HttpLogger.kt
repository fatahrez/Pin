package com.fatahrez.common.shared.remote

import okhttp3.logging.HttpLoggingInterceptor

object HttpLogger {
    fun create(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
}