package com.fatahrez.feature_auth.presentation.sign_in

import com.fatahrez.feature_auth.domain.models.responses.SignInResponse

data class SignInState(
    val signInResponse: SignInResponse? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
