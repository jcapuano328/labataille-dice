package com.ica.lb_dice.features.melee

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ica.lb_dice.R
import com.ica.lb_dice.features.help.BulletPoint
import com.ica.lb_dice.features.help.components.CombatResultsHelpSection
import com.ica.lb_dice.features.help.HelpSection
import com.ica.lb_dice.features.help.components.LeaderCasualtyResultsHelpSection
import com.ica.lb_dice.features.help.components.MoraleResultsHelpSection
import com.ica.lb_dice.features.help.components.OddsHelpSection
import com.ica.lb_dice.features.help.components.RollDiceHelpSection
import com.ica.lb_dice.ui.Die
import com.ica.lb_dice.ui.ModifierButtonsRow
import com.ica.lb_dice.ui.PngIcon

@Composable
fun MeleeCombatHelpContent() {
    var selectedTabIndex by remember { mutableStateOf(0) }
    val tabTitles = listOf("Odds", "Assault", "Melee")

    Column(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
    ) {
        HelpSection(title = "Roll Dice") {
            RollDiceHelpSection(
                modifier = Modifier
                    .fillMaxWidth()
            )
        }

        TabRow(selectedTabIndex = selectedTabIndex) {
            tabTitles.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index },
                    text = { Text(text = title, fontSize = 12.sp) }
                )
            }
        }
        LazyColumn(
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth()
        ) {
            item {
                when (selectedTabIndex) {
                    0 -> OddsHelpSection()
                    1 -> PreMeleeMoraleCheckHelpSection()
                    2 -> MeleeCombatHelpSection()
                }

            }
        }
    }
}

@Composable
fun PreMeleeMoraleCheckHelpSection() {
    Column(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
    ) {
        // Section 1
        HelpSection(title = "Morale Dice") {
            Column(Modifier.padding(16.dp)) {
                // morale dice
                Row() {
                    Die(dieNumber = 1,
                        modifier = Modifier
                            .padding(1.dp)
                            .size(40.dp)
                        ,
                        onDieClicked = { },
                        backgroundColor = Color.Black,
                        dotColor = Color.White,
                        dieValue = 1
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Die(dieNumber = 2,
                        modifier = Modifier
                            .padding(1.dp)
                            .size(40.dp)
                        ,
                        onDieClicked = { },
                        backgroundColor = Color.Black,
                        dotColor = Color.Red,
                        dieValue = 1
                    )
                }
                BulletPoint(text = "The morale dice are used to determine the morale results.")
                BulletPoint(text = "Tap a die to increase its value by 1.")
                BulletPoint(text = "Attacker dice on the left, Defender dice on the right.")
                Spacer(Modifier.height(12.dp))

                // attacker modifier buttons
                ModifierButtonsRow(
                    label = "Morale",
                    foregroundColor = Color.White,
                    backgroundColor = Color.Blue,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                    ,
                    onModifierButtonClicked = { }
                )
                BulletPoint(text = "The Attacker modifier buttons are used to adjust the dice.")
                BulletPoint(text = "Tap a button to adjust the dice by the value of the button.")
                Spacer(Modifier.height(12.dp))

                // defender modifier buttons
                ModifierButtonsRow(
                    label = "Morale",
                    foregroundColor = Color.White,
                    backgroundColor = Color(0xFFB200FF), // purple
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                    ,
                    onModifierButtonClicked = { }
                )
                BulletPoint(text = "The Defender modifier buttons are used to adjust the dice.")
                BulletPoint(text = "Tap a button to adjust the dice by the value of the button.")
                Spacer(Modifier.height(16.dp))
            }
        }
        HelpSection(title = "Morale Results") {
            Column(Modifier.padding(16.dp)) {
                // results explanation
                BulletPoint(text = "The morale results are a list of the possible outcomes based on the dice.")
                BulletPoint(text = "Each result includes a morale value and a pass or fail indicator.")
                BulletPoint(text = "For quick reference, the list includes estimates of outcomes for common morale modifiers.")
                Spacer(Modifier.height(16.dp))
            }
        }
    }
}

@Composable
fun MeleeCombatHelpSection() {
    Column(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
    ) {
        HelpSection(title = "Combat Results") {
            CombatResultsHelpSection(
                modifier = Modifier
                    .fillMaxWidth()
            )
        }

        HelpSection(title = "Morale Results") {
            MoraleResultsHelpSection(
                modifier = Modifier
                    .fillMaxWidth(),
                details = ", in the event the combat result calls for a morale check"
            )
        }

        HelpSection(title = "Leader Casualty Results") {
            LeaderCasualtyResultsHelpSection(
                modifier = Modifier
                    .fillMaxWidth()
                ,melee = true
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewMeleeCombatHelpContent() {
    MeleeCombatHelpContent(
    )
}
