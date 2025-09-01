package com.ica.lb_dice.features.fire

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ica.lb_dice.features.help.components.HelpSection
import com.ica.lb_dice.features.help.components.CombatResultsHelpSection
import com.ica.lb_dice.features.help.components.LeaderCasualtyResultsHelpSection
import com.ica.lb_dice.features.help.components.MoraleResultsHelpSection
import com.ica.lb_dice.features.help.components.RollDiceHelpSection

@Composable
fun FireCombatHelpContent() {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
        .fillMaxSize()
        .padding(2.dp)) {
        LazyColumn(
            modifier = Modifier
                .padding(4.dp)
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
                HelpSection(title = "Combat Results") {
                    CombatResultsHelpSection(
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
            }

            // Section 3
            item {
                HelpSection(title = "Morale Results") {
                    MoraleResultsHelpSection(
                        modifier = Modifier
                            .fillMaxWidth(),
                        details = ", in the event the combat result calls for a morale check."
                    )
                }
            }

            // Section 4
            item {
                HelpSection(title = "Leader Casualty Results") {
                    LeaderCasualtyResultsHelpSection(
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
fun PreviewFireCombatHelpContent() {
    FireCombatHelpContent(
    )
}
