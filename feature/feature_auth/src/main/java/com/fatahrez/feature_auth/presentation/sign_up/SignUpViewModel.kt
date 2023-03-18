package com.fatahrez.feature_auth.presentation.sign_up

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fatahrez.common.util.ResultWrapper
import com.fatahrez.feature_auth.domain.models.requests.SignUpRequest
import com.fatahrez.feature_auth.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authRepository: AuthRepository
): ViewModel() {

    private val _state = mutableStateOf(SignUpState())
    val state: State<SignUpState> get() = _state

    fun signUpUser(signUpRequest: SignUpRequest) {
        viewModelScope.launch {
            authRepository.postRegisterUser(signUpRequest).collect { result ->
                when(result) {
                    is ResultWrapper.Loading -> {
                        _state.value = state.value.copy(
                            signUpResponse = null,
                            isLoading = true
                        )
                    }
                    is ResultWrapper.Success -> {
                        _state.value = state.value.copy(
                            signUpResponse = result.value,
                            isLoading = false
                        )
                    }
                    is ResultWrapper.NetworkError -> {
                        _state.value = state.value.copy(
                            signUpResponse = null,
                            isLoading = false
                        )
                    }
                    is ResultWrapper.GenericError -> {
                        _state.value = state.value.copy(
                            signUpResponse = null,
                            isLoading = false
                        )
                    }
                }
            }
        }
    }

}