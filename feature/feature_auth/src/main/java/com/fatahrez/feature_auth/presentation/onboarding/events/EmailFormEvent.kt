package com.fatahrez.feature_auth.presentation.onboarding.events

sealed class EmailFormEvent {
    data class EmailChanged(val email: String): EmailFormEvent()

    object Submit: EmailFormEvent()
}
