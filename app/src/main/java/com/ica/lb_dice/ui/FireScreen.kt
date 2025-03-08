package com.ica.lb_dice.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun FireScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        DiceSection()
        Spacer(modifier = Modifier.height(16.dp))
        ResultsSection() {
            FireResultsTable()
        }
    }
}

@Composable
fun DiceSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color.Gray)
            .padding(8.dp)
    ) {
        Text("Dice", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            DiceSet(
                diceColors = listOf(Color.Red, Color.White),
                modifierButtonsColor = Color.Blue
            )
            DiceSet(
                diceColors = listOf(Color.Blue, Color.Yellow, Color.Green),
                modifierButtonsColor = Color.Blue,
                hasRollButton = true,
            )
            DiceSet(
                diceColors = listOf(Color.Black, Color.Black),
                modifierButtonsColor = Color.Magenta
            )
        }
    }
}
@Composable
fun FireResultsTable() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = "Fire Results", style = MaterialTheme.typography.titleMedium)
        Row(Modifier.fillMaxWidth()) {
            TableCell(text = "Odds", modifier = Modifier.weight(0.5f))
            TableCell(text = "Result", modifier = Modifier.weight(0.5f))
        }
        // Placeholder data
        val fireResults = listOf(
            Pair("1:1", "Result 1"),
            Pair("1:2", "Result 2"),
            Pair("1:3", "Result 3"),
            Pair("1:4", "Result 4")
        )
        fireResults.forEach { result ->
            Row(Modifier.fillMaxWidth()) {
                TableCell(text = result.first, modifier = Modifier.weight(0.5f))
                TableCell(text = result.second, modifier = Modifier.weight(0.5f))
            }
        }
    }
}