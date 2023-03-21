package com.fatahrez.feature_auth.presentation.onboarding

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fatahrez.common.util.ResultWrapper
import com.fatahrez.feature_auth.domain.models.requests.EmailRequest
import com.fatahrez.feature_auth.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EmailViewModel @Inject constructor(
    private val authRepository: AuthRepository
): ViewModel(){

    private val _state = mutableStateOf(EmailState())
    val state: State<EmailState> get() = _state

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
}