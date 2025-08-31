package com.ica.lb_dice.help

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ica.lb_dice.help.components.HelpSection
import com.ica.lb_dice.help.components.MoraleResultsHelpSection
import com.ica.lb_dice.help.components.RollDiceHelpSection

@Composable
fun MoraleHelpContent() {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
        .fillMaxSize()
        .padding(2.dp)) {
        /*
        Text("Morale Screen Help",
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
            color = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = MaterialTheme.colorScheme.primary,
                    shape = MaterialTheme.shapes.medium
                )
        )
        Spacer(Modifier.height(8.dp))
        */
        LazyColumn(
            modifier = Modifier
                .padding(2.dp)
                .fillMaxWidth()
        ) {
            // Section 1
            item {
                HelpSection(title = "Roll Dice") {
                    RollDiceHelpSection(
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
            }
            // Section 2
            item {
                HelpSection(title = "Morale Results") {
                    MoraleResultsHelpSection(
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMoraleHelpContent() {
    MoraleHelpContent(
    )
}
