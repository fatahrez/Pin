package com.fatahrez.feature_auth.presentation.onboarding

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.MutatePriority
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.*
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.fatahrez.common.util.verticalFadingEdge
import kotlin.random.Random
import com.fatahrez.feature_auth.R
import com.fatahrez.feature_auth.presentation.destinations.SignUpScreenDestination
import com.fatahrez.feature_auth.presentation.onboarding.model.StaggeredListItem
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.delay

@OptIn(ExperimentalFoundationApi::class)
@RootNavGraph(start = true)
@Destination
@Composable
fun Onboarding(
    navigator: DestinationsNavigator
) {
    // Get screen height to give Lazy Grid an adaptive height
    val configuration = LocalConfiguration.current
    val height = configuration.screenHeightDp.dp

    val viewModel : EmailViewModel = hiltViewModel()

    val lazyListState = rememberLazyStaggeredGridState()
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val drawableImageList = intArrayOf(
            R.drawable.onboarding_image,
            R.drawable.onboarding_image2,
            R.drawable.onboarding_image3,
            R.drawable.onboarding_image4,
            R.drawable.onboarding_image5,
            R.drawable.onboarding_image6,
            R.drawable.onboarding_image7,
            R.drawable.onboarding_image8,
        )
        val images = (0..14).map {
            StaggeredListItem(
                height = Random.nextInt(100, 180).dp,
                image = drawableImageList.random()
            )
        }
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(3),
            modifier = Modifier
                .fillMaxWidth()
                .height(height / 2.65f)
                .verticalFadingEdge(
                    lazyListState,
                    height / 2.65f
                ),
            contentPadding = PaddingValues(
                vertical = 4.dp,
                horizontal = 16.dp
            ),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            state = lazyListState
        ) {
            items(images) { item ->
                RandomImageBox(item)
                LaunchedEffect(key1 = Unit) {
                    autoScroll(lazyListState)
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        WelcomeSection()
        AuthSection(viewModel, navigator)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthSection(viewModel: EmailViewModel, navigator: DestinationsNavigator) {
    val inputValue = remember { mutableStateOf(TextFieldValue()) }

    val state = viewModel.state.value
    if(state.isLoading) {
        Log.i("TAG", "AuthSection: loading")
    } else if (state.errors != null){
        Log.e("TAG", "AuthSection: ${state.errors}")
    } else {
        if (state.emailResponse != null) {
            if (state.emailResponse.message) {
                Log.i("TAG", "AuthSection: login")
            } else {
                navigator
                    .navigate(SignUpScreenDestination(email = inputValue.value.text))
            }
        }
    }

    Column(
        Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = inputValue.value,
            onValueChange = { inputValue.value = it },
            placeholder = {
                Text(
                    text = "Email address",
                    modifier = Modifier
                        .padding(start = 16.dp),
                    style = MaterialTheme.typography.titleMedium
                )
            },
            modifier = Modifier
                .padding(horizontal = 36.dp, vertical = 18.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(50)),
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                autoCorrect = false,
                keyboardType = KeyboardType.Email
            ),
            textStyle = MaterialTheme.typography.bodyLarge,
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                if(isSystemInDarkTheme()) Color.White else Color.Black,
                containerColor = if(isSystemInDarkTheme()) Color.DarkGray else Color(0xFFF0F0F0),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
        )

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .padding(horizontal = 36.dp, vertical = 0.dp)
                .clip(RoundedCornerShape(50)),
            colors = ButtonDefaults.buttonColors(contentColor = Color.White),
            onClick = {
                viewModel.getEmailStatus(inputValue.value.text)
            }
        ) {
            Text(
                text = "Continue",
                style = MaterialTheme.typography.bodyLarge
            )
        }

        Spacer(modifier = Modifier.height(28.dp))

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .padding(horizontal = 36.dp, vertical = 0.dp)
                .clip(RoundedCornerShape(50)),
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.White,
                containerColor = Color.Blue
            ),
            onClick = { /*TODO*/ }
        ) {
            Row(
                horizontalArrangement = Arrangement.Start
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.facebook),
                    contentDescription = "facebook icon",
                    modifier = Modifier
                        .size(24.dp)
                )
                Spacer(modifier = Modifier.width(24.dp))
                Text(
                    text = "Continue with Facebook",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }

        Spacer(modifier = Modifier.height(4.dp))

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .padding(horizontal = 36.dp, vertical = 0.dp)
                .clip(RoundedCornerShape(50)),
            colors = ButtonDefaults.buttonColors(
                contentColor = if (isSystemInDarkTheme()) Color.White else Color.Black,
                containerColor = if (isSystemInDarkTheme()) Color.DarkGray else Color.LightGray
            ),
            onClick = { /*TODO*/ }
        ) {
            Row(
                horizontalArrangement = Arrangement.Start
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.google),
                    contentDescription = "google icon",
                    modifier = Modifier
                        .size(24.dp)
                )
                Spacer(modifier = Modifier.width(24.dp))
                Text(
                    text = "Continue with Google",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}

@Composable
fun WelcomeSection() {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        AsyncImage(
            model = R.drawable.pinterest_logo,
            contentDescription = "Pinterest Logo",
            modifier = Modifier
                .size(88.dp)
                .offset(y = (-10).dp)
                .align(Alignment.CenterHorizontally)
        )
        Text(
            text = "Welcome to Pinterest",
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
private tailrec suspend fun autoScroll(lazyListState: LazyStaggeredGridState) {
    delay(DELAY_BETWEEN_SCROLL_MS)
    lazyListState.scroll(MutatePriority.PreventUserInput) {
        scrollBy(SCROLL_DX)
    }
    delay(DELAY_BETWEEN_SCROLL_MS)

    autoScroll(lazyListState)
}

@Composable
fun RandomImageBox(item: StaggeredListItem) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(item.height)
    ) {
        AsyncImage(
            model = item.image,
            contentDescription = "Image",
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(10.dp)),
            contentScale = ContentScale.Crop
        )
    }
}

private const val DELAY_BETWEEN_SCROLL_MS = 50L
private const val SCROLL_DX = 1f