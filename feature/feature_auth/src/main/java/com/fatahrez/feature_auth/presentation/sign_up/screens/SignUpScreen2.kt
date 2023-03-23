package com.fatahrez.feature_auth.presentation.sign_up.screens

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.fatahrez.feature_auth.presentation.destinations.SignUpScreen2Destination
import com.fatahrez.feature_auth.presentation.destinations.SignUpScreen3Destination
import com.fatahrez.feature_auth.presentation.onboarding.EmailViewModel
import com.fatahrez.feature_auth.presentation.sign_up.SignUpViewModel
import com.fatahrez.feature_auth.presentation.sign_up.events.SignUpFormEvent
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun SignUpScreen2(
    email: String,
    password: String,
    navigator: DestinationsNavigator
) {
    val viewModel: SignUpViewModel = hiltViewModel()
    val state = viewModel.nameValidationState
    val context = LocalContext.current

    LaunchedEffect(key1 = context) {
        viewModel.nameValidationEvents.collect { event ->
            when(event) {
                is EmailViewModel.ValidationEvent.Success -> {
                    navigator.navigate(
                        SignUpScreen3Destination(
                            email = email,
                            password = password,
                            name = state.name,
                        )
                    )
                }
            }
        }
    }

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxSize()
    ) {

        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "What's your name?",
                    style = MaterialTheme.typography.bodyLarge
                )

                OutlinedTextField(
                    value = state.name,
                    onValueChange = {
                        viewModel.onEvent(SignUpFormEvent.NameChanged(it))
                    },
                    isError = state.nameError != null,
                    placeholder = {
                        Text(
                            text = "Full name",
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
                        keyboardType = KeyboardType.Text
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
                if (state.nameError != null) {
                    Text(
                        text = state.nameError,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
        }

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(56.dp),
            onClick = {
                viewModel.onNameEvent(SignUpFormEvent.Submit)
            },
            colors = ButtonDefaults.buttonColors
                (contentColor = if (isSystemInDarkTheme()) Color.White else Color.Black,
                containerColor = if (isSystemInDarkTheme()) Color.DarkGray else Color.LightGray
            )
        ) {
            Text(text = "Next")
        }
    }

}