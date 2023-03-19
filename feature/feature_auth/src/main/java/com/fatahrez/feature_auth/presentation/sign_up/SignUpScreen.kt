package com.fatahrez.feature_auth.presentation.sign_up

import android.util.Log
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.fatahrez.feature_auth.domain.models.requests.SignUpRequest

@Composable
fun SignUpScreen() {
    val viewModel: SignUpViewModel = hiltViewModel()

}