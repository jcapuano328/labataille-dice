package com.ica.lb_dice.ui

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import com.ica.lb_dice.R

@Composable
fun DiceIcon(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.dice),
        contentDescription = "Dice",
        modifier = modifier,
        colorFilter = ColorFilter.tint(Color.Red)
    )
}