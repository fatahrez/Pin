package com.fatahrez.feature_auth.presentation.onboarding

import com.fatahrez.feature_auth.domain.models.responses.EmailResponse

data class EmailState(
    val emailResponse: EmailResponse? = null,
    val isLoading: Boolean = false,
    val errors: String? = null
)
