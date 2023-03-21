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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.fatahrez.feature_auth.domain.models.requests.SignInRequest
import com.ramcosta.composedestinations.annotation.Destination

@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun SignInScreen(
    email: String
) {
    val viewModel: SignInViewModel = hiltViewModel()
    val state = viewModel.state.value
    
    Column {
        Row() {
            Text(text = "Log in")
        }
        val emailInputValue = remember {
            mutableStateOf(TextFieldValue(email))
        }

        val passwordInputValue = remember {
            mutableStateOf(TextFieldValue())
        }

        OutlinedTextField(
            value = emailInputValue.value,
            onValueChange = { emailInputValue.value = it },
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

        OutlinedTextField(
            value = passwordInputValue.value,
            onValueChange = { passwordInputValue.value = it },
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

        if (state.isLoading) {
            Log.i("TAG", "SignInScreen: loading...")
        } else if (state.error != null) {
            Log.e("TAG", "SignInScreen: ${state.error}", )
        } else {
            if (state.signInResponse != null) {
                Log.i("TAG", "SignInScreen: ${state.signInResponse}")
            }
        }

        Button(onClick = {
            val signInRequest = SignInRequest(
                emailInputValue.value.text,
                passwordInputValue.value.text
            )
            viewModel.signIn(signInRequest)
        }) {
            Text(text = "Log in")
        }
    }
    
}