package com.ica.lb_dice.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

data class DieConfig(
    val dieColor: Color = Color.Transparent,
    val dotColor: Color = Color.Transparent,
    val sides: Int = 6
)

@Composable
fun Die(
    modifier: Modifier = Modifier,
    dieNumber: Int,
    onDieClicked: (Int) -> Unit,
    sides: Int,
    dieColor: Color,
    dotColor: Color,
    dieValue: Int,
) {
    Box(
        modifier = modifier
            //.background(dieColor)
            .aspectRatio(1f)
            .clickable { onDieClicked(dieNumber) },
        contentAlignment = Alignment.Center,
    ) {
        when (sides) {
            0 -> DieRendererIcon(
                iconResId = dieValue,
                modifier = Modifier.fillMaxSize()   // << here
            )

            6 -> DieRendererD6(
                value = dieValue,
                dieColor = dieColor,
                dotColor = dotColor,
                modifier = Modifier.fillMaxSize()   // << here
            )

            8 -> DieRendererD8(
                value = dieValue,
                dieColor = dieColor,
                textColor = dotColor,
                modifier = Modifier.fillMaxSize()   // << and here
            )

            10 -> DieRendererD10(
                value = dieValue,
                dieColor = dieColor,
                textColor = dotColor,
                modifier = Modifier.fillMaxSize()
            )

            else -> Text("Unsupported number of sides: $sides")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDie() {
    var value by remember { mutableStateOf(6) }
    Row(
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Box(modifier = Modifier.size(65.dp)) {
            Die(
                //modifier = Modifier.size(65.dp),
                dieNumber = 1,
                onDieClicked = { die -> value = die },
                sides = 6,
                dieColor = Color.Blue,
                dotColor = Color.White,
                dieValue = value
            )
        }

        Box(modifier = Modifier.size(65.dp)) {
            Die(
                //modifier = Modifier.size(65.dp),
                dieNumber = 2,
                onDieClicked = { die -> value = die },
                sides = 8,
                dieColor = Color.Red,
                dotColor = Color.White,
                dieValue = value
            )
        }

        Box(modifier = Modifier.size(65.dp)) {
            Die(
                //modifier = Modifier.size(65.dp),
                dieNumber = 3,
                onDieClicked = { die -> value = die },
                sides = 10,
                dieColor = Color.Green,
                dotColor = Color.White,
                dieValue = value
            )
        }
    }
}