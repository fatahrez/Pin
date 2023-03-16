package com.fatahrez.feature_auth.presentation.sign_up

import com.fatahrez.feature_auth.domain.models.responses.SignUpResponse

data class SignUpState(
    val signUpResponse: SignUpResponse? = null,
    val isLoading: Boolean = false
)
