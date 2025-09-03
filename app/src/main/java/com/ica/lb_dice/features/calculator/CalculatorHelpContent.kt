package com.ica.lb_dice.features.calculator

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
import com.ica.lb_dice.R
import com.ica.lb_dice.features.help.BulletPoint
import com.ica.lb_dice.features.help.HelpSection
import com.ica.lb_dice.ui.PngIcon

@Composable
fun CalculatorHelpContent() {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
        .fillMaxSize()
        .padding(2.dp)) {
        Card(modifier = Modifier.padding(2.dp)) {
            Text(
                "This is a specialized calculator to determine the proportional strength of a unit. The idea is to open the calculator, determine attack and defense strengths and then return to the resolution screen.",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(8.dp)
            )
            Text(
                "Each time the calculator is opened a new \"calculation session\" is begun.",
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
                    BulletPoint(text = "Enter Size, Loss, and Strength values using the number pad: the proportional strength is automatically calculated.")
                    BulletPoint(text = "Apply the optional strength modifier to the value by tapping the appropriate button.")
                    BulletPoint(text = "Add whole number values to the total using the number pad and PLUS button.")
                    BulletPoint(text = "Add final result to the Attack or Defend value by choosing the appropriate button.")
                    Spacer(Modifier.height(16.dp))
                }
            }

            // Section 2
            item {
                HelpSection(title = "SIZE, LOSS, STR") {
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        PngIcon(R.drawable.calc_size, "SIZE", modifier = Modifier.size(40.dp))
                        PngIcon(R.drawable.calc_loss, "LOSS", modifier = Modifier.size(40.dp))
                        PngIcon(R.drawable.calc_strength, "STR", modifier = Modifier.size(40.dp))
                    }
                    BulletPoint(text = "SIZE: Total increments of the unit.")
                    BulletPoint(text = "LOSS: Total increments lost from the unit.")
                    BulletPoint(text = "STR:  Full melee strength of the unit.")
                    Spacer(Modifier.height(16.dp))
                }
            }

            // Section 3
            item {
                HelpSection(title = "1/3, 1/2, 3/2, 2") {
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        PngIcon(R.drawable.calc_third, "1/3", modifier = Modifier.size(40.dp))
                        PngIcon(R.drawable.calc_half, "1/2", modifier = Modifier.size(40.dp))
                        PngIcon(R.drawable.calc_three_halves, "3/2", modifier = Modifier.size(40.dp))
                        PngIcon(R.drawable.calc_twice, "2", modifier = Modifier.size(40.dp))
                    }
                    BulletPoint(text = "Quick action multipliers applied to the result.")
                    BulletPoint(text = "Multipliers correspond to standard adjustments to strength.")
                    Spacer(Modifier.height(16.dp))
                }
            }

            // Section 4
            item {
                HelpSection(title = "+ (PLUS)") {
                    PngIcon(R.drawable.calc_add, "PLUS", modifier = Modifier.size(40.dp))
                    BulletPoint(text = "This is a quick way to include a full strength unit (no losses) to the accumulated result.")
                    BulletPoint(text = "Enter a value with the number pad and tap the PLUS button.")
                    Spacer(Modifier.height(16.dp))
                }
            }

            // Section 5
            item {
                HelpSection(title = "ATT, DEF") {
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        PngIcon(R.drawable.calc_att, "ATT", modifier = Modifier.size(40.dp))
                        PngIcon(R.drawable.calc_def, "DEF", modifier = Modifier.size(40.dp))
                    }
                    BulletPoint(text = "Apply the accumulated result to the Attack or Defend value on the main screen.")
                    Column(Modifier.padding(start = 16.dp)) {
                        BulletPoint(text = "ATT: Apply to Attacker.")
                        BulletPoint(text = "DEF: Apply to Defender.")
                    }
                    BulletPoint(text = "The value is added to the existing value on the main screen.")
                    BulletPoint(text = "EXCEPTION: Upon entry into the calculator, the first tap of the button replaces the value on the main screen; subsequent taps add to the existing value.")
                    Spacer(Modifier.height(16.dp))
                }
            }

            item {
                HelpSection(title = "Example") {
                    Text(
                        "Calculate the total melee strength of 3 units.",
                        style = MaterialTheme.typography.labelMedium,
                        modifier = Modifier.padding(8.dp)
                    )
                    BulletPoint(text = "Enter the Size, Loss, and Strength values for the first unit.")
                    BulletPoint(text = "Tap the ATT or DEF button to apply the result to the Attack or Defend value, as appropriate.")
                    BulletPoint(text = "Enter the full strength of the second unit using the number pad (it has no losses), tap the PLUS button and finally tap the ATT or DEF button.")
                    BulletPoint(text = "Enter the Size, Loss, and Strength values for the third unit.")
                    BulletPoint(text = "Tap the ATT or DEF button to apply the result to the Attack or Defend value, as appropriate.")
                    BulletPoint(text = "Result: the accumulated strength of all units is presented on the main screen.")
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
