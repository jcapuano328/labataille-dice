package com.ica.lb_dice.ui

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.withTransform

/**
 * Draw an [image] at [x],[y] scaled to [width]×[height].
 * This works even if the dstSize overload isn't available.
 */
fun DrawScope.drawImageScaled(
    image: ImageBitmap,
    x: Float,
    y: Float,
    width: Float,
    height: Float
) {
    val scaleX = width / image.width.toFloat()
    val scaleY = height / image.height.toFloat()

    withTransform({
        translate(x, y)
        scale(scaleX, scaleY)
    }) {
        drawImage(image, topLeft = Offset.Zero)
    }
}

fun DrawScope.drawImageAspectFit(
    image: ImageBitmap,
    x: Float,
    y: Float,
    maxWidth: Float,
    maxHeight: Float
) {
    val scale = minOf(
        maxWidth / image.width.toFloat(),
        maxHeight / image.height.toFloat()
    )
    val drawWidth = image.width * scale
    val drawHeight = image.height * scale

    val offsetX = x + (maxWidth - drawWidth) / 2f
    val offsetY = y + (maxHeight - drawHeight) / 2f

    withTransform({
        translate(offsetX, offsetY)
        scale(scale, scale)
    }) {
        drawImage(image, topLeft = Offset.Zero)
    }
}
