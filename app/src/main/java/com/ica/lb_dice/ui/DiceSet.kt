package com.ica.lb_dice.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun DiceSet(
    diceColors: List<Color>,
    modifierButtonsColor: Color,
    hasRollButton: Boolean = false,
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row {
            diceColors.forEach { color ->
                Die(color = color)
            }
        }
        if (hasRollButton) {
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Dice Roll")
            }
        } else {
            DiceModifierRow(buttonsColor = modifierButtonsColor)
        }
    }
}

@Composable
fun Die(color: Color) {
    Box(
        modifier = Modifier
            .size(40.dp)
            .border(1.dp, Color.Black)
            .background(color),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "6")
    }
}

@Composable
fun DiceModifierRow(buttonsColor: Color) {
    Row {
        listOf("-6", "-3", "-1", "+1", "+3", "+6").forEach { modifier ->
            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(containerColor = buttonsColor),
                modifier = Modifier.padding(2.dp)
            ) {
                Text(text = modifier, color = Color.White)
            }
        }
    }
}