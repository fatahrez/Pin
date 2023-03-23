package com.fatahrez.feature_auth.presentation.sign_up.states

data class NameValidationState(
    val name: String = "",
    val nameError: String? = null
)
