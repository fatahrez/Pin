package com.fatahrez.feature_auth.presentation.sign_up.states

data class PasswordValidationState(
    val password: String = "",
    val passwordError: String? = null
)
