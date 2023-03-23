package com.fatahrez.feature_auth.presentation.sign_in.events

sealed class SignInFormEvent {

    data class EmailChanged(val email: String): SignInFormEvent()

    data class PasswordChanged(val password: String): SignInFormEvent()

    object Submit: SignInFormEvent()

}

