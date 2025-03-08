package com.ica.lb_dice.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun ResultsSection(combatResults: @Composable () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color.Gray)
            .padding(8.dp)
    ) {
        Text("Results", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(8.dp))
        combatResults()
        Spacer(modifier = Modifier.height(8.dp))
        LeaderLossDisplay()
        Spacer(modifier = Modifier.height(8.dp))
        MoraleResultsTable()
    }
}

@Composable
fun LeaderLossDisplay() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = "Leader Loss", style = MaterialTheme.typography.titleMedium)
        // Placeholder for Leader Loss information
        Text(text = "Leader: No Loss")
    }
}

@Composable
fun MoraleResultsTable() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = "Morale", style = MaterialTheme.typography.titleMedium, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
        Row(Modifier.fillMaxWidth()) {
            TableCell(text = "Value", modifier = Modifier.weight(0.5f))
            TableCell(text = "Result", modifier = Modifier.weight(0.5f))
        }
        // Placeholder data
        val moraleResults = listOf(
            Pair("1", "Pass"),
            Pair("2", "Fail"),
            Pair("3", "Pass")
        )
        moraleResults.forEach { result ->
            Row(Modifier.fillMaxWidth()) {
                TableCell(text = result.first, modifier = Modifier.weight(0.5f))
                TableCell(text = result.second, modifier = Modifier.weight(0.5f))
            }
        }
    }
}

@Composable
fun TableCell(text: String, modifier: Modifier) {
    Text(
        text = text,
        modifier = modifier
            .border(1.dp, Color.Gray)
            .padding(8.dp),
        textAlign = TextAlign.Center
    )
}