package com.fatahrez.feature_auth.presentation.sign_up.states

import com.fatahrez.feature_auth.domain.models.responses.CountryResponse

data class CountriesState(
    val countryResponse: List<CountryResponse> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
