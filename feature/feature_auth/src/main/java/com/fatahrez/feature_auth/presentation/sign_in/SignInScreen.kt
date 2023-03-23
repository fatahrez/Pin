package com.fatahrez.feature_auth.presentation.sign_in

import android.util.Log
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.core.content.edit
import androidx.hilt.navigation.compose.hiltViewModel
import com.fatahrez.common.util.Constants
import com.fatahrez.feature_auth.domain.models.requests.SignInRequest
import com.fatahrez.feature_auth.presentation.onboarding.EmailViewModel
import com.fatahrez.feature_auth.presentation.sign_in.events.SignInFormEvent
import com.ramcosta.composedestinations.annotation.Destination

@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun SignInScreen(
    email: String
) {
    val viewModel: SignInViewModel = hiltViewModel()
    val state = viewModel.state.value
    val signInValidationState = viewModel.signInValidationState

    val context = LocalContext.current

    LaunchedEffect(key1 = context) {
        viewModel.onEvent(SignInFormEvent.EmailChanged(email))
        viewModel.validationEvents.collect { event ->
            when(event) {
                is EmailViewModel.ValidationEvent.Success -> {
                    val emailAddress = viewModel.signInValidationState.email
                    val password = viewModel.signInValidationState.password
                    val signInRequest = SignInRequest(
                        emailAddress,
                        password
                    )
                    viewModel.signIn(signInRequest)
                }
            }
        }
    }

    Column {
        Row() {
            Text(text = "Log in")
        }

        OutlinedTextField(
            value = signInValidationState.email,
            onValueChange = {
                viewModel.onEvent(SignInFormEvent.EmailChanged(it))
            },
            isError = signInValidationState.emailError != null,
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 18.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(30)),
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                autoCorrect = false,
                keyboardType = KeyboardType.Email
            ),
            textStyle = MaterialTheme.typography.bodyLarge,
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                if(isSystemInDarkTheme()) Color.White else Color.Black,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
        )
        if (signInValidationState.emailError != null) {
            Text(
                text = signInValidationState.emailError,
                color = MaterialTheme.colorScheme.error
            )
        }

        OutlinedTextField(
            value = signInValidationState.password,
            onValueChange = {
                viewModel.onEvent(SignInFormEvent.PasswordChanged(it))
            },
            isError = signInValidationState.passwordError != null,
            placeholder = {
                Text(
                    text = "Password",
                    modifier = Modifier
                        .padding(start = 16.dp),
                    style = MaterialTheme.typography.titleMedium
                )
            },
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 18.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(30)),
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                autoCorrect = false,
                keyboardType = KeyboardType.Password
            ),
            visualTransformation = PasswordVisualTransformation(),
            textStyle = MaterialTheme.typography.bodyLarge,
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                if(isSystemInDarkTheme()) Color.White else Color.Black,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
        )
        if (signInValidationState.passwordError != null) {
            Text(
                text = signInValidationState.passwordError,
                color = MaterialTheme.colorScheme.error
            )
        }

        if (state.isLoading) {
            Log.i("TAG", "SignInScreen: loading...")
        } else if (state.error != null) {
            Log.e("TAG", "SignInScreen: ${state.error}", )
        } else {
            if (state.signInResponse != null) {
                viewModel.sharedPreferences.edit {
                    viewModel.sharedPreferences.edit {
                        putString(Constants.ACCESS_TOKEN, state.signInResponse.access)
                        putString(Constants.REFRESH_TOKEN, state.signInResponse.refresh)
                    }
                }
            }
        }

        Button(onClick = {
            viewModel.onEvent(SignInFormEvent.Submit)
        }) {
            Text(text = "Log in")
        }
    }
    
}