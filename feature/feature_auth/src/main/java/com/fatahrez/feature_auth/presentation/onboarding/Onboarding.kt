package com.fatahrez.feature_auth.presentation.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.MutatePriority
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.fatahrez.common.util.verticalFadingEdge
import kotlin.random.Random
import com.fatahrez.feature_auth.R
import kotlinx.coroutines.delay

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Onboarding() {
    // Get screen height to give Lazy Grid an adaptive height
    val configuration = LocalConfiguration.current
    val height = configuration.screenHeightDp.dp

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
        val images = (0..7).map {
            StaggeredListItem(
                height = Random.nextInt(130, 220).dp,
                image = drawableImageList.random()
            )
        }
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            modifier = Modifier
                .fillMaxWidth()
                .height(height / 3)
                .verticalFadingEdge(
                    lazyListState,
                    height/3
                ),
            contentPadding = PaddingValues(4.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            state = lazyListState
        ) {
            items(images) { item ->
                RandomColorBox(item)
                LaunchedEffect(key1 = Unit) {
                    autoScroll(lazyListState)
                }
            }
        }
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
fun RandomColorBox(item: StaggeredListItem) {
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