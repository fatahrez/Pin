package com.fatahrez.feature_auth.presentation.sign_up.states

import com.fatahrez.feature_auth.domain.models.responses.ProfileResponse

data class ProfileState(
    val profileResponse: ProfileResponse? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
