package com.fatahrez.feature_auth.presentation.sign_in.states

data class SignInValidationState(
    val email: String = "",
    val emailError: String? = null,
    val password: String = "",
    val passwordError: String? = null
)
