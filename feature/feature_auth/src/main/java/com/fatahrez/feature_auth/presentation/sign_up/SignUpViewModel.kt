package com.fatahrez.feature_auth.presentation.sign_up

import android.content.SharedPreferences
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fatahrez.common.util.ResultWrapper
import com.fatahrez.feature_auth.domain.models.requests.ProfileRequest
import com.fatahrez.feature_auth.domain.models.requests.SignUpRequest
import com.fatahrez.feature_auth.domain.repository.AuthRepository
import com.fatahrez.feature_auth.presentation.sign_up.states.CountriesState
import com.fatahrez.feature_auth.presentation.sign_up.states.ProfileState
import com.fatahrez.feature_auth.presentation.sign_up.states.SignUpState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    val sharedPreferences: SharedPreferences
): ViewModel() {

    private val _state = mutableStateOf(SignUpState())
    val state: State<SignUpState> get() = _state

    private val _profileState = mutableStateOf(ProfileState())
    val profileState: State<ProfileState> get() = _profileState

    private val _countriesState = mutableStateOf(CountriesState())
    val countriesState: State<CountriesState> get() = _countriesState

    init {
        getCountries()
    }

    fun signUpUser(signUpRequest: SignUpRequest) {
        viewModelScope.launch {
            authRepository.postRegisterUser(signUpRequest).collect { result ->
                when(result) {
                    is ResultWrapper.Loading -> {
                        _state.value = state.value.copy(
                            signUpResponse = null,
                            isLoading = true,
                            errors = null
                        )
                    }
                    is ResultWrapper.Success -> {
                        _state.value = state.value.copy(
                            signUpResponse = result.value,
                            isLoading = false,
                            errors = null
                        )
                    }
                    is ResultWrapper.NetworkError -> {
                        _state.value = state.value.copy(
                            signUpResponse = null,
                            isLoading = false,
                            errors = "Unable make request, check your internet connection."
                        )
                    }
                    is ResultWrapper.GenericError -> {
                        _state.value = state.value.copy(
                            signUpResponse = null,
                            isLoading = false,
                            errors = result.error?.message
                        )
                    }
                }
            }
        }
    }

    fun getCountries() {
        viewModelScope.launch {
            authRepository.getCountries()
                .collect { result ->
                    when(result) {
                        is ResultWrapper.Loading -> {
                            _countriesState.value = countriesState.value.copy(
                                countryResponse = emptyList(),
                                isLoading = true,
                                error = null
                            )
                        }
                        is ResultWrapper.Success -> {
                            _countriesState.value = countriesState.value.copy(
                                countryResponse = result.value,
                                isLoading = false,
                                error = null
                            )
                        }
                        is ResultWrapper.NetworkError -> {
                            _countriesState.value = countriesState.value.copy(
                                countryResponse = emptyList(),
                                isLoading = false,
                                error = "Unable make request, check your Internet connection."
                            )
                        }
                        is ResultWrapper.GenericError -> {
                            _countriesState.value = countriesState.value.copy(
                                countryResponse = emptyList(),
                                isLoading = false,
                                error = result.error?.message
                            )
                        }
                    }
                }
        }
    }

    fun postProfile(username: String, profileRequest: ProfileRequest) {
        viewModelScope.launch {
            authRepository.postProfile(username, profileRequest)
                .collect { result ->
                    when(result) {
                        is ResultWrapper.Loading -> {
                            _profileState.value = profileState.value.copy(
                                profileResponse = null,
                                isLoading = true,
                                error = null
                            )
                        }
                        is ResultWrapper.Success -> {
                            _profileState.value = profileState.value.copy(
                                profileResponse = result.value,
                                isLoading = false,
                                error = null
                            )
                        }
                        is ResultWrapper.GenericError -> {
                            _profileState.value = profileState.value.copy(
                                profileResponse = null,
                                isLoading = false,
                                error = result.error?.message
                            )
                        }
                        is ResultWrapper.NetworkError -> {
                            _profileState.value = profileState.value.copy(
                                profileResponse = null,
                                isLoading = false,
                                error = "Unable to make request, check your internet connection."
                            )
                        }
                    }
                }
        }
    }
}