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
fun ProportionalStrengthCalculatorHelpContent() {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
        .fillMaxSize()
        .padding(2.dp)) {
        Card(modifier = Modifier.padding(2.dp)) {
            Text(
                "This is a specialized calculator to determine the proportional strength of a unit. The idea is to open the calculator, determine attack and defense strengths and then return to the resolution screen.",
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
                    BulletPoint(text = "Enter a value using the number pad and assign it to the Size, Loss, or Strength using appropriate button: the proportional strength is automatically calculated.")
                    BulletPoint(text = "Apply the optional strength modifier to the value by tapping the appropriate button.")
                    BulletPoint(text = "Add the result to the total value by tapping the <-/+ (SET/PLUS) button.")
                    BulletPoint(text = "Add whole number values to the total using the number pad and the <-/+ (SET/PLUS) button.")
                    BulletPoint(text = "Add final result to the Attack or Defend value by choosing the appropriate button.")
                    Spacer(Modifier.height(16.dp))
                }
            }

            // Section 2
            item {
                HelpSection(title = "Total, Calculated and Current Value") {
                    BulletPoint(text = "The Green area shows the Total value.")
                    BulletPoint(text = "The Red area shows the Calculated value.")
                    BulletPoint(text = "The Clear area shows the Current value entered via the number pad.")
                    Column(Modifier.padding(start = 16.dp)) {
                        BulletPoint(text = "Use the number pad to enter a value in the Current area.")
                    }
                    Spacer(Modifier.height(16.dp))
                }
            }

            // Section 3
            item {
                HelpSection(title = "SIZE, LOSS, STR") {
                    BulletPoint(text = "Enter the unit information to calculate the proportional strength.")
                    Spacer(Modifier.height(4.dp))

                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
                        PngIcon(R.drawable.calc_size, "SIZE", modifier = Modifier.size(40.dp))
                        Text("Total increments of the unit.", style = MaterialTheme.typography.labelMedium)
                    }
                    Column(Modifier.padding(start = 16.dp)) {
                        BulletPoint(text = "Use the number pad to enter the total number of increments for the unit.")
                        BulletPoint(text = "Use the SIZE button to set the value.")
                    }
                    Spacer(Modifier.height(4.dp))

                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
                        PngIcon(R.drawable.calc_loss, "LOSS", modifier = Modifier.size(40.dp))
                        Text("Total increments lost from the unit.", style = MaterialTheme.typography.labelMedium)
                    }
                    Column(Modifier.padding(start = 16.dp)) {
                        BulletPoint(text = "Use the number pad to enter the total number of increments lost from the unit.")
                        BulletPoint(text = "Use the LOSS button to set the value.")
                    }
                    Spacer(Modifier.height(4.dp))

                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
                        PngIcon(R.drawable.calc_strength, "STR", modifier = Modifier.size(40.dp))
                        Text("Full melee strength of the unit.", style = MaterialTheme.typography.labelMedium)
                    }
                    Column(Modifier.padding(start = 16.dp)) {
                        BulletPoint(text = "Use the number pad to enter the full melee strength of the unit.")
                        BulletPoint(text = "Use the STR button to set the value.")
                    }
                    Spacer(Modifier.height(16.dp))
                }
            }

            // Section 4
            item {
                HelpSection(title = "1/3, 1/2, 3/2, 2") {
                    BulletPoint(text = "Quick action multipliers applied to the Calculated value.")
                    BulletPoint(text = "Multipliers correspond to standard adjustments to strength.")
                    Spacer(Modifier.height(4.dp))

                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
                        PngIcon(R.drawable.calc_third, "1/3", modifier = Modifier.size(40.dp))
                        Text("Reduce value by one third.", style = MaterialTheme.typography.labelMedium)
                    }
                    Spacer(Modifier.height(4.dp))

                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
                        PngIcon(R.drawable.calc_half, "1/2", modifier = Modifier.size(40.dp))
                        Text("Reduce value by one half.", style = MaterialTheme.typography.labelMedium)
                    }
                    Spacer(Modifier.height(4.dp))

                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
                        PngIcon(R.drawable.calc_three_halves, "3/2", modifier = Modifier.size(40.dp))
                        Text("Increase value by one half.", style = MaterialTheme.typography.labelMedium)
                    }
                    Spacer(Modifier.height(4.dp))

                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
                        PngIcon(R.drawable.calc_twice, "2", modifier = Modifier.size(40.dp))
                        Text("Double value.", style = MaterialTheme.typography.labelMedium)
                    }

                    Spacer(Modifier.height(16.dp))
                }
            }

            // Section 5
            item {
                HelpSection(title = "the <-/+ (SET/PLUS)") {
                    BulletPoint(text = "Add the Calculated OR the Current value to the Total.")
                    Spacer(Modifier.height(4.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
                        PngIcon(R.drawable.calc_set, "SET", modifier = Modifier.size(40.dp))
                        Text("Set the total to whole value.", style = MaterialTheme.typography.labelMedium)
                    }
                    Spacer(Modifier.height(4.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
                        PngIcon(R.drawable.calc_add, "PLUS", modifier = Modifier.size(40.dp))
                        Text("Add whole value to the total.", style = MaterialTheme.typography.labelMedium)
                    }
                    Spacer(Modifier.height(16.dp))
                }
            }

            // Section 6
            item {
                HelpSection(title = "ATT, DEF") {
                    BulletPoint(text = "Apply the accumulated result to the Attack or Defend value on the main screen.")
                    Spacer(Modifier.height(4.dp))

                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
                        PngIcon(R.drawable.calc_att, "ATT", modifier = Modifier.size(40.dp))
                        Text("Apply to Attacker.", style = MaterialTheme.typography.labelMedium)
                    }
                    Spacer(Modifier.height(4.dp))

                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
                        PngIcon(R.drawable.calc_def, "DEF", modifier = Modifier.size(40.dp))
                        Text("Apply to Defender.", style = MaterialTheme.typography.labelMedium)
                    }

                    Spacer(Modifier.height(4.dp))
                    BulletPoint(text = "The value is added to the existing value on the main screen.")
                    BulletPoint(text = "EXCEPTION: Upon entry into the calculator, the first tap of the button replaces the value on the main screen; subsequent taps add to the existing value.")

                    Spacer(Modifier.height(16.dp))
                }
            }

            // Section 7
            item {
                HelpSection(title = "Example: Calculate the total melee strength of 3 units") {
                    Text(
                        "Step 1. Determine the strength of the first unit:",
                        style = MaterialTheme.typography.labelLarge,
                        modifier = Modifier.padding(2.dp)
                    )
                    Column(Modifier.padding(start = 16.dp)) {
                        BulletPoint(text = "Enter the value 12 using the number pad")
                        BulletPoint(text = "Tap the SIZE button")
                        BulletPoint(text = "Enter the value 2 using the number pad")
                        BulletPoint(text = "Tap the LOSS button")
                        BulletPoint(text = "Enter the value 15 using the number pad")
                        BulletPoint(text = "Tap the STR button")
                        BulletPoint(text = "The Red Calculated area shows the value 12.5")
                        BulletPoint(text = "Tap the <- (SET) button")
                        Column(Modifier.padding(start = 16.dp)) {
                            BulletPoint(text = "The Green Total area shows the value 12.5")
                            BulletPoint(text = "The Red Calculated and Clear Current areas are cleared")
                            BulletPoint(text = "The <- (SET) button becomes the + (PLUS) button")
                        }
                    }

                    Text(
                        "Step 2. Add the strength of the second unit:",
                        style = MaterialTheme.typography.labelLarge,
                        modifier = Modifier.padding(2.dp)
                    )
                    Column(Modifier.padding(start = 16.dp)) {
                        BulletPoint(text = "Enter the value 11 using the number pad")
                        BulletPoint(text = "Tap the + (PLUS) button")
                        Column(Modifier.padding(start = 16.dp)) {
                            BulletPoint(text = "The Green Total area shows the value 23.5")
                            BulletPoint(text = "The Clear Current area is cleared")
                        }
                    }

                    Text(
                        "Step 3. Determine the strength of the third unit:",
                        style = MaterialTheme.typography.labelLarge,
                        modifier = Modifier.padding(2.dp)
                    )
                    Column(Modifier.padding(start = 16.dp)) {
                        BulletPoint(text = "Enter the value 6 using the number pad")
                        BulletPoint(text = "Tap the SIZE button")
                        BulletPoint(text = "Enter the value 1 using the number pad")
                        BulletPoint(text = "Tap the LOSS button")
                        BulletPoint(text = "Enter the value 9 using the number pad")
                        BulletPoint(text = "Tap the STR button")
                        BulletPoint(text = "The Red Calculated area shows the value 7.5")
                        BulletPoint(text = "Tap the + (PLUS) button")
                        Column(Modifier.padding(start = 16.dp)) {
                            BulletPoint(text = "The Green Total area shows the value 31.0")
                            BulletPoint(text = "The Red Calculated and Clear Current areas are cleared")
                        }
                    }

                    Text(
                        "Step 4. Set the Attack value:",
                        style = MaterialTheme.typography.labelLarge,
                        modifier = Modifier.padding(2.dp)
                    )
                    Column(Modifier.padding(start = 16.dp)) {
                        BulletPoint(text = "Tap the ATT button")
                        Column(Modifier.padding(start = 16.dp)) {
                            BulletPoint(text = "The Attack value on the main screen is set to 31.0")
                            BulletPoint(text = "The Green Total area is cleared")
                            BulletPoint(text = "The + (PLUS) button becomes the <- (SET) button")
                        }
                    }
                    Spacer(Modifier.height(16.dp))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCalculatorHelpContent() {
    ProportionalStrengthCalculatorHelpContent(
    )
}
