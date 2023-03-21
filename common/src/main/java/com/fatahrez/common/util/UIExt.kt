package com.fatahrez.common.util

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.unit.Dp

@OptIn(ExperimentalFoundationApi::class)
fun Modifier.verticalFadingEdge(
    lazyListState: LazyStaggeredGridState,
    length: Dp,
    edgeColor: Color? = null,
) = composed(
    debugInspectorInfo {
        name = "length"
        value = length
    }
) {
    val color = edgeColor ?: MaterialTheme.colorScheme.surface

    drawWithContent {
        val topFadingEdgeStrength by derivedStateOf {
            lazyListState.layoutInfo.run {
                val firstItem = visibleItemsInfo.first()
                when {
                    visibleItemsInfo.size in 0..1 -> 0f
                    firstItem.index > 0 -> 1f // Added
                    firstItem.offset.y == viewportStartOffset -> 0f
                    firstItem.offset.y < viewportStartOffset -> firstItem.run {
                        kotlin.math.abs(offset.y) / size.height.toFloat()
                    }
                    else -> 1f
                }
            }.coerceAtMost(1f) * length.value
        }
        val bottomFadingEdgeStrength by derivedStateOf {
            lazyListState.layoutInfo.run {
                val lastItem = visibleItemsInfo.first()
                when {
                    visibleItemsInfo.size in 0..1 -> 0f
                    lastItem.index < totalItemsCount - 1 -> 1f // Added
                    lastItem.offset.y + lastItem.size.height <= viewportEndOffset -> 0f // added the <=
                    lastItem.offset.y + lastItem.size.height > viewportEndOffset -> lastItem.run {
                        (size.height - (viewportEndOffset - offset.y)) / size.height.toFloat()  // Fixed the percentage computation
                    }
                    else -> 1f
                }
            }.coerceAtMost(1f) * length.value
        }

        drawContent()

        drawRect(
            brush = Brush.verticalGradient(
                colors = listOf(
                    color,
                    Color.Transparent,
                ),
                startY = 0f,
                endY = topFadingEdgeStrength,
            ),
            size = Size(
                this.size.width,
                topFadingEdgeStrength
            ),
        )

        drawRect(
            brush = Brush.verticalGradient(
                colors = listOf(
                    Color.Transparent,
                    color,
                ),
                startY = size.height - bottomFadingEdgeStrength,
                endY = size.height,
            ),
            topLeft = Offset(x = 0f, y = size.height - bottomFadingEdgeStrength),
        )
    }
}