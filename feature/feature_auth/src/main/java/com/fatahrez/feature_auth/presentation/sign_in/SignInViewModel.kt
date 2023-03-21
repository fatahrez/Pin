package com.fatahrez.feature_auth.presentation.sign_in

import android.content.SharedPreferences
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fatahrez.common.util.ResultWrapper
import com.fatahrez.feature_auth.domain.models.requests.SignInRequest
import com.fatahrez.feature_auth.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    val sharedPreferences: SharedPreferences
): ViewModel() {

    private val _state = mutableStateOf(SignInState())
    val state: State<SignInState> get() = _state

    fun signIn(signInRequest: SignInRequest) {
        viewModelScope.launch {
            authRepository.postSignIn(signInRequest)
                .collect { result ->
                    when(result) {
                        is ResultWrapper.Loading -> {
                            _state.value = state.value.copy(
                                signInResponse = null,
                                isLoading = true,
                                error = null
                            )
                        }
                        is ResultWrapper.Success -> {
                            _state.value = state.value.copy(
                                signInResponse = result.value,
                                isLoading = false,
                                error = null
                            )
                        }
                        is ResultWrapper.NetworkError -> {
                            _state.value = state.value.copy(
                                signInResponse = null,
                                isLoading = false,
                                error = "Unable to make request, check your internet connection."
                            )
                        }
                        is ResultWrapper.GenericError -> {
                            _state.value = state.value.copy(
                                signInResponse = null,
                                isLoading = false,
                                error = result.error?.message
                            )
                        }
                    }
                }
        }
    }

}