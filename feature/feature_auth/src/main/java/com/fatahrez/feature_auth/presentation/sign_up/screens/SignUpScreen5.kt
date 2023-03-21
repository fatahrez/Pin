package com.fatahrez.feature_auth.presentation.sign_up

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.fatahrez.feature_auth.presentation.destinations.SignUpScreen6Destination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun SignUpScreen5(
    username: String,
    age: String,
    navigator: DestinationsNavigator
) {

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
                    text = "What is your gender?",
                    style = MaterialTheme.typography.bodyLarge
                )

                Text(
                    text = "This helps us find you more relevant content. \n" +
                            "We wont show it on your profile.",
                    style = MaterialTheme.typography.bodyLarge
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .height(56.dp),
                    onClick = {
                        navigator.navigate(
                            SignUpScreen6Destination(
                                username = username,
                                age = age,
                                gender = "Female"
                            )
                        )
                    },
                    colors = ButtonDefaults.buttonColors
                        (contentColor = if (isSystemInDarkTheme()) Color.White else Color.Black,
                        containerColor = if (isSystemInDarkTheme()) Color.DarkGray else Color.LightGray
                    )
                ) {
                    Text(text = "Female")
                }

                Spacer(modifier = Modifier.height(4.dp))

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .height(56.dp),
                    onClick = {
                        navigator.navigate(
                            SignUpScreen6Destination(
                                username = username,
                                age = age,
                                gender = "Male"
                            )
                        )
                    },
                    colors = ButtonDefaults.buttonColors
                        (contentColor = if (isSystemInDarkTheme()) Color.White else Color.Black,
                        containerColor = if (isSystemInDarkTheme()) Color.DarkGray else Color.LightGray
                    )
                ) {
                    Text(text = "Male")
                }

                Spacer(modifier = Modifier.height(4.dp))

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .height(56.dp),
                    onClick = {
                        navigator.navigate(
                            SignUpScreen6Destination(
                                username = username,
                                age = age,
                                gender = "Other"
                            )
                        )
                    },
                    colors = ButtonDefaults.buttonColors
                        (contentColor = if (isSystemInDarkTheme()) Color.White else Color.Black,
                        containerColor = if (isSystemInDarkTheme()) Color.DarkGray else Color.LightGray
                    )
                ) {
                    Text(text = "Specify another gender")
                }
            }
        }
    }

}