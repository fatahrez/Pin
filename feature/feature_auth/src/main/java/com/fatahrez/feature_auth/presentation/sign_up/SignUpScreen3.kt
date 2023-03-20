package com.fatahrez.feature_auth.presentation.sign_up

import android.content.SharedPreferences
import android.util.Log
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.core.content.edit
import androidx.hilt.navigation.compose.hiltViewModel
import com.fatahrez.common.util.Constants
import com.fatahrez.feature_auth.domain.models.requests.SignUpRequest
import com.fatahrez.feature_auth.presentation.destinations.SignUpScreen4Destination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import javax.inject.Inject

@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun SignUpScreen3(
    email: String,
    password: String,
    name: String,
    navigator: DestinationsNavigator
) {
    val viewModel: SignUpViewModel = hiltViewModel()

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxSize()
    ) {
        val inputValue = remember { mutableStateOf(TextFieldValue()) }
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Pick a username",
                    style = MaterialTheme.typography.bodyLarge
                )

                OutlinedTextField(
                    value = inputValue.value,
                    onValueChange = { inputValue.value = it },
                    placeholder = {
                        Text(
                            text = "Username",
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
            }
        }

        val state = viewModel.state.value
        if (state.isLoading) {
            Log.i("TAG", "SignUpScreen3: loading")
        } else if (state.errors != null) {
            Log.e("TAG", "SignUpScreen3: errors")
        } else {
            if (state.signUpResponse != null) {
                sharedPreferences.edit {
                    sharedPreferences.edit {
                        putString(Constants.ACCESS_TOKEN, state.signUpResponse.token.access)
                        putString(Constants.REFRESH_TOKEN, state.signUpResponse.token.refresh)
                    }
                }
                navigator.navigate(
                    SignUpScreen4Destination(
                        name = name,
                    )
                )
            }
        }

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(56.dp),
            onClick = {
                val user = SignUpRequest(
                    username = inputValue.value.text,
                    email = email,
                    firstName = name,
                    password = password
                )
                viewModel.signUpUser(user)
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
@Inject lateinit var sharedPreferences: SharedPreferences