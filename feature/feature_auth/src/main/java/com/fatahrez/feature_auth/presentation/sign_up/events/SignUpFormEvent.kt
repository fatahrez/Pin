package com.fatahrez.feature_auth.presentation.sign_up.events

sealed class SignUpFormEvent {
    data class PasswordChanged(val password: String): SignUpFormEvent()

    data class NameChanged(val name: String): SignUpFormEvent()

    object Submit: SignUpFormEvent()
}
