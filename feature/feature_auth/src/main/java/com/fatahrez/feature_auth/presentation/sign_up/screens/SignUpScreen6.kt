package com.fatahrez.feature_auth.presentation.sign_up.screens

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.fatahrez.feature_auth.domain.models.requests.ProfileRequest
import com.fatahrez.feature_auth.presentation.sign_up.SignUpViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun SignUpScreen6(
    username: String,
    age: String,
    gender: String,
    navigator: DestinationsNavigator
) {
    val viewModel: SignUpViewModel = hiltViewModel()
    val countriesState = viewModel.countriesState.value

    var mSelectedText by remember { mutableStateOf("Mexico") }
    var mExpanded by remember { mutableStateOf(false) }
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
                    text = "What is your country or region?",
                    style = MaterialTheme.typography.bodyLarge
                )

                Text(
                    text = "This helps us find you more relevant content.\n" +
                            "We won't show it on your profile.",
                    style = MaterialTheme.typography.bodyLarge
                )
                if (countriesState.isLoading) {
                    Log.i("TAG", "SignUpScreen6: loading...")
                } else if (countriesState.error != null) {
                    Log.e("TAG", "SignUpScreen6: ${countriesState.error}")
                } else {
                    Log.i("TAG", "SignUpScreen6: ${countriesState.countryResponse}")
                }

                val icon = if (mExpanded)
                    Icons.Filled.KeyboardArrowUp
                else
                    Icons.Filled.KeyboardArrowDown

                OutlinedTextField(
                    value = mSelectedText,
                    onValueChange = { mSelectedText = it },
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
                    ),
                    trailingIcon = {
                        Icon(icon,"contentDescription",
                            Modifier.clickable { mExpanded = !mExpanded })
                    }
                )
                DropdownMenu(
                    expanded = mExpanded,
                    onDismissRequest = { mExpanded = false },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    countriesState.countryResponse.forEach { country ->
                        DropdownMenuItem(text = {
                            Text(text = country.name)
                        }, onClick = {
                            mSelectedText = country.name
                            mExpanded = false
                        })
                    }
                }
            }
        }

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(56.dp),
            onClick = {
                val profileRequest = ProfileRequest(
                    age.toInt(),
                    gender,
                    mSelectedText
                )
                viewModel.postProfile(
                    username,
                    profileRequest
                )
            },
            colors = ButtonDefaults.buttonColors(
                contentColor = if (isSystemInDarkTheme()) Color.White else Color.Black
            )
        ) {
            Text(text = "Next")
        }
    }

}