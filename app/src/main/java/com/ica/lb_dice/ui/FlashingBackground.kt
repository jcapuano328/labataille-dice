package com.ica.lb_dice.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun FlashingBackground(
    modifier: Modifier = Modifier,
    trigger: Any,
    flashColor: Color = Color.Yellow,
    baseColor: Color = Color.Transparent,
    animationSpecIn: TweenSpec<Color> = tween(durationMillis = 300), // Time to reach flash color
    holdDurationMillis: Long = 500, // How long to stay on flash color
    animationSpecOut: TweenSpec<Color> = tween(durationMillis = 300), // Time to return to base
    onFlashStart: () -> Unit = {},
    onFlashFull: () -> Unit = {},
    onFlashComplete: () -> Unit = {},
    content: @Composable () -> Unit
) {
    var previousTrigger by remember { mutableStateOf(trigger) }
    var actualColor by remember { mutableStateOf(baseColor) } // The color currently being animated
    var animationTarget by remember { mutableStateOf(baseColor) }

    LaunchedEffect(trigger) {
        if (trigger != previousTrigger) {
            previousTrigger = trigger

            onFlashStart()
            // Phase 1: Animate to Flash Color
            animationTarget = flashColor
            delay(animationSpecIn.durationMillis.toLong()) // Wait for animation to flashColor

            // Phase 2: Hold Flash Color
            // No change in animationTarget, actualColor should be flashColor now
            delay(holdDurationMillis)
            onFlashFull()

            // Phase 3: Animate back to Base Color
            animationTarget = baseColor
            delay(animationSpecOut.durationMillis.toLong()) // Wait for animation back to baseColor

            onFlashComplete()
        }
    }

    actualColor = animateColorAsState(
        targetValue = animationTarget,
        animationSpec = when (animationTarget) {
            flashColor -> animationSpecIn
            else -> animationSpecOut // Covers baseColor or initial state
        },
        label = "flashingColorTransition"
    ).value

    Box(
        modifier = modifier.background(actualColor)
    ) {
        content()
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFlashingBackground() {
    var eventCount by remember { mutableStateOf(0) }

    Column {
        Button(onClick = { eventCount++ }) {
            Text("Trigger Flash")
        }

        Spacer(Modifier.height(16.dp))

        FlashingBackground(
            trigger = eventCount,
            flashColor = Color.Magenta,
            baseColor = MaterialTheme.colorScheme.surfaceVariant,
            animationSpecIn = tween(durationMillis = 5000), // Takes 5s to become Magenta
            holdDurationMillis = 2000, // Stays Magenta for 2s
            animationSpecOut = tween(durationMillis = 1000), // Takes 1s to go back to surfaceVariant
            onFlashComplete = {
                // Optional: Do something when the flash cycle is done
                println("Flash for Text complete!")
            }
        ) {
            // Content to be wrapped by the flashing background
            Text(
                text = "This content will flash!",
                modifier = Modifier.padding(16.dp)
            )
        }

        Spacer(Modifier.height(16.dp))

        // Another instance with different content or settings
        FlashingBackground(
            trigger = eventCount,
            //flashColor = Color.Magenta,
            baseColor = MaterialTheme.colorScheme.surfaceVariant,
            animationSpecIn = tween(durationMillis = 300),
            holdDurationMillis = 200,
            animationSpecOut = tween(durationMillis = 300), // Takes 1s to go back to surfaceVariant
            onFlashComplete = {
                // Optional: Do something when the flash cycle is done
                println("Flash for Icon complete!")
            }
        ) {
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = "Star Icon",
                modifier = Modifier.size(48.dp).padding(8.dp)
            )
        }
    }
}
