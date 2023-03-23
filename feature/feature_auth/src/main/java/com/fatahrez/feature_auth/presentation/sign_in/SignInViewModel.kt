package com.fatahrez.feature_auth.presentation.sign_in

import android.content.SharedPreferences
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fatahrez.common.util.ResultWrapper
import com.fatahrez.feature_auth.domain.models.requests.SignInRequest
import com.fatahrez.feature_auth.domain.repository.AuthRepository
import com.fatahrez.feature_auth.domain.use_case.ValidateEmail
import com.fatahrez.feature_auth.domain.use_case.ValidatePassword
import com.fatahrez.feature_auth.presentation.onboarding.EmailViewModel.ValidationEvent
import com.fatahrez.feature_auth.presentation.onboarding.states.EmailValidationState
import com.fatahrez.feature_auth.presentation.sign_in.events.SignInFormEvent
import com.fatahrez.feature_auth.presentation.sign_in.states.SignInState
import com.fatahrez.feature_auth.presentation.sign_in.states.SignInValidationState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    val sharedPreferences: SharedPreferences,
    private val validateEmail: ValidateEmail,
    private val validatePassword: ValidatePassword
): ViewModel() {

    private val _state = mutableStateOf(SignInState())
    val state: State<SignInState> get() = _state

    var signInValidationState by mutableStateOf(SignInValidationState())

    private var validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    fun onEvent(event: SignInFormEvent) {
        when(event) {
            is SignInFormEvent.EmailChanged -> {
                signInValidationState = signInValidationState.copy(
                    email = event.email
                )
            }
            is SignInFormEvent.PasswordChanged -> {
                signInValidationState = signInValidationState.copy(
                    password = event.password
                )
            }
            is SignInFormEvent.Submit -> {
                submitData()
            }
        }
    }

    private fun submitData() {
        val emailResult = validateEmail(signInValidationState.email)
        val passwordResult = validatePassword(signInValidationState.password)

        val hasError = listOf(
            emailResult,
            passwordResult
        ).any { !it.successful }

        if (hasError) {
            signInValidationState = signInValidationState.copy(
                emailError = emailResult.errorMessage
            )
            signInValidationState = signInValidationState.copy(
                passwordError = passwordResult.errorMessage
            )
            return
        }
        viewModelScope.launch {
            validationEventChannel.send(ValidationEvent.Success)
        }
    }

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