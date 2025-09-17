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
fun CombatCalculatorHelpContent() {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
        .fillMaxSize()
        .padding(2.dp)) {
        Card(modifier = Modifier.padding(2.dp)) {
            Text(
                "This is a simple calculator to help determine strengths and modifiers.",
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
                    BulletPoint(text = "Use the number pad to calculate values.")
                    BulletPoint(text = "Add final result to the Attack or Defend value by choosing the appropriate button.")
                    Spacer(Modifier.height(16.dp))
                }
            }

            // Section 2
            item {
                HelpSection(title = "ATT, DEF") {
                    BulletPoint(text = "Apply the current value to the Attack or Defend value on the main screen.")
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
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCombatCalculatorHelpContent() {
    CombatCalculatorHelpContent(
    )
}
