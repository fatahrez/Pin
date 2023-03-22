package com.fatahrez.feature_auth.presentation.onboarding

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fatahrez.common.util.ResultWrapper
import com.fatahrez.feature_auth.domain.models.requests.EmailRequest
import com.fatahrez.feature_auth.domain.repository.AuthRepository
import com.fatahrez.feature_auth.domain.use_case.ValidateEmail
import com.fatahrez.feature_auth.presentation.onboarding.events.EmailFormEvent
import com.fatahrez.feature_auth.presentation.onboarding.states.EmailState
import com.fatahrez.feature_auth.presentation.onboarding.states.EmailValidationState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EmailViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val validateEmail: ValidateEmail
): ViewModel(){

    private val _state = mutableStateOf(EmailState())
    val state: State<EmailState> get() = _state

    var emailValidationState by mutableStateOf(EmailValidationState())

    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()
    fun onEvent(event: EmailFormEvent) {
        when(event) {
            is EmailFormEvent.EmailChanged -> {
                emailValidationState = emailValidationState.copy(
                    email = event.email
                )
            }
            is EmailFormEvent.Submit -> {
                submitData()
            }
        }
    }

    private fun submitData() {
        val emailResult = validateEmail(emailValidationState.email)

        val hasError = listOf(
            emailResult
        ).any { !it.successful }

        if(hasError) {
            emailValidationState = emailValidationState.copy(
                emailError = emailResult.errorMessage
            )
            return
        }
        viewModelScope.launch {
            validationEventChannel.send(ValidationEvent.Success)
        }
    }

    fun getEmailStatus(email: String) {
        viewModelScope.launch {
            val emailRequest = EmailRequest(email)
            authRepository.postEmail(emailRequest)
                .collect { result ->
                    when(result) {
                        is ResultWrapper.Success -> {
                            _state.value = state.value.copy(
                                emailResponse = result.value,
                                isLoading = false,
                                errors = null
                            )
                        }
                        is ResultWrapper.Loading -> {
                            _state.value = state.value.copy(
                                emailResponse = null,
                                isLoading = true,
                                errors = null
                            )
                        }
                        is ResultWrapper.GenericError -> {
                            _state.value = state.value.copy(
                                emailResponse = null,
                                isLoading = false,
                                errors = result.error?.message
                            )
                        }
                        is ResultWrapper.NetworkError -> {
                            _state.value = state.value.copy(
                                emailResponse = null,
                                isLoading = false,
                                errors = "Unable to get data, Check your internet connection."
                            )
                        }
                    }
                }
        }
    }

    sealed class ValidationEvent {
        object Success: ValidationEvent()
    }
}