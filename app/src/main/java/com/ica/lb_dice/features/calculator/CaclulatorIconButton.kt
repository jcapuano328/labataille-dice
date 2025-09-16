package com.ica.lb_dice.features.calculator

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp

@Composable
fun CalculatorIconButton(
    imageResId: Int, // drawable resource ID
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    width: Dp? = null,
    height: Dp? = null,
    contentDescription: String? = null,
    scale: Float = 1f,
    resizeMode: ContentScale = ContentScale.FillBounds // equivalent to 'contain'
) {
    var layoutSize by remember { mutableStateOf(IntSize.Zero) }

    val finalWidth = width ?: (layoutSize.width.takeIf { it != 0 }?.dp?.times(scale) ?: 32.dp)
    val finalHeight = height ?: (layoutSize.height.takeIf { it != 0 }?.dp?.times(scale) ?: 32.dp)

    Box(
        modifier = modifier
            .onGloballyPositioned { coordinates ->
                layoutSize = coordinates.size
            }
            .clickable { onClick() }
            .size(width = finalWidth, height = finalHeight)
    ) {
        Image(
            painter = painterResource(id = imageResId),
            contentDescription = contentDescription,
            contentScale = resizeMode,
            modifier = Modifier.fillMaxSize()
        )
    }
}

