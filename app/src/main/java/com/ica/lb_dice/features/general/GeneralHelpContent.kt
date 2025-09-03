package com.ica.lb_dice.features.general

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ica.lb_dice.features.help.BulletPoint
import com.ica.lb_dice.features.help.HelpSection
import com.ica.lb_dice.features.help.components.RollDiceHelpSection

@Composable
fun GeneralHelpContent() {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
        .fillMaxSize()
        .padding(2.dp)) {
        /*
        Text("General Dice Screen Help",
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
                HelpSection(title = "Dice Results") {
                    BulletPoint(text = "3 sets of dice to be used for miscellaneous tasks.")
                    BulletPoint(text = "Tap a die to increase its value by 1.")
                    BulletPoint(text = "The modifier buttons are used to adjust the dice directly above.")
                    BulletPoint(text = "Tap a button to adjust the dice by the value of the button.")
                    Spacer(Modifier.height(16.dp))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewGeneralHelpContent() {
    GeneralHelpContent(
    )
}
