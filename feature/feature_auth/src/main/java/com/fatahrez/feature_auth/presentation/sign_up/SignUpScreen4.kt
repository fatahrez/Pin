package com.fatahrez.feature_auth.presentation.sign_up

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.fatahrez.common.util.Constants
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun SignUpScreen4(
    name: String,
    navigator: DestinationsNavigator
) {
    val viewModel: SignUpViewModel = hiltViewModel()

}