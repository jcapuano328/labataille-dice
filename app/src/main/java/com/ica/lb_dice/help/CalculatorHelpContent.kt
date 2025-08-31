package com.ica.lb_dice.help

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ica.lb_dice.help.components.BulletPoint
import com.ica.lb_dice.help.components.HelpSection
import com.ica.lb_dice.help.components.RollDiceHelpSection
import com.ica.lb_dice.ui.PngIcon
import com.ica.lb_dice.ui.ResultImage

@Composable
fun CalculatorHelpContent() {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
        .fillMaxSize()
        .padding(2.dp)) {
        Card(modifier = Modifier.padding(2.dp)) {
            Text(
                "This is a specialized calculator to determine the proportional strength of a unit.",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(8.dp)
            )
        }
        Spacer(Modifier.height(16.dp))

        LazyColumn(
            modifier = Modifier
                .padding(2.dp)
                .fillMaxWidth()
        ) {
            // Section 1
            item {
                HelpSection(title = "Procedure") {
                    BulletPoint(text = "Enter Size, Loss, and Strength using the number pad to calculate the value.")
                    BulletPoint(text = "Apply the optional strength modifier to the value.")
                    BulletPoint(text = "Choose the PLUS button to accumulate the current value to the result.")
                    BulletPoint(text = "Calculate additional values as necessary, accumulating the total using the PLUS button.")
                    BulletPoint(text = "Add final result to the Attack or Defend value by choosing the appropriate button.")
                    Spacer(Modifier.height(16.dp))
                }
            }

            // Section 2
            item {
                HelpSection(title = "SIZE, LOSS, STR") {
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        PngIcon(com.ica.lb_dice.R.drawable.calc_size, "SIZE", modifier = Modifier.size(40.dp))
                        PngIcon(com.ica.lb_dice.R.drawable.calc_loss, "LOSS", modifier = Modifier.size(40.dp))
                        PngIcon(com.ica.lb_dice.R.drawable.calc_strength, "STR", modifier = Modifier.size(40.dp))
                    }
                    BulletPoint(text = "SIZE: Total increments of the unit.")
                    BulletPoint(text = "LOSS: Total increments lost from the unit.")
                    BulletPoint(text = "STR:  Raw combat strength of the unit.")
                    Spacer(Modifier.height(16.dp))
                }
            }

            // Section 3
            item {
                HelpSection(title = "1/3, 1/2, 3/2, 2") {
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        PngIcon(com.ica.lb_dice.R.drawable.calc_third, "1/3", modifier = Modifier.size(40.dp))
                        PngIcon(com.ica.lb_dice.R.drawable.calc_half, "1/2", modifier = Modifier.size(40.dp))
                        PngIcon(com.ica.lb_dice.R.drawable.calc_three_halves, "3/2", modifier = Modifier.size(40.dp))
                        PngIcon(com.ica.lb_dice.R.drawable.calc_twice, "2", modifier = Modifier.size(40.dp))
                    }
                    BulletPoint(text = "Quick action multipliers applied to the result.")
                    BulletPoint(text = "Multipliers correspond to standard adjustments to strength.")
                    Spacer(Modifier.height(16.dp))
                }
            }

            // Section 4
            item {
                HelpSection(title = "+ (PLUS)") {
                    PngIcon(com.ica.lb_dice.R.drawable.calc_add, "PLUS", modifier = Modifier.size(40.dp))
                    BulletPoint(text = "Add the current value to the accumulated result.")
                    Spacer(Modifier.height(16.dp))
                }
            }

            // Section 5
            item {
                HelpSection(title = "ATT, DEF") {
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        PngIcon(com.ica.lb_dice.R.drawable.calc_att, "ATT", modifier = Modifier.size(40.dp))
                        PngIcon(com.ica.lb_dice.R.drawable.calc_def, "DEF", modifier = Modifier.size(40.dp))
                    }
                    BulletPoint(text = "Apply the accumulated result to the Attack or Defend value on the main screen.")
                    BulletPoint(text = "ATT: Apply to Attacker.")
                    BulletPoint(text = "DEF: Apply to Defender.")
                    Spacer(Modifier.height(16.dp))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCalculatorHelpContent() {
    CalculatorHelpContent(
    )
}
