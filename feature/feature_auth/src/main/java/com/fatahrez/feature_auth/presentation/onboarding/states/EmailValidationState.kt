package com.fatahrez.feature_auth.presentation.onboarding.states

data class EmailValidationState(
    val email: String = "",
    val emailError: String? = null
)
