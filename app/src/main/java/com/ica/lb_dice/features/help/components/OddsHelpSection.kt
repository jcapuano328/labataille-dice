package com.ica.lb_dice.features.help.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ica.lb_dice.R
import com.ica.lb_dice.features.help.BulletPoint
import com.ica.lb_dice.features.help.HelpSection
import com.ica.lb_dice.ui.PngIcon

@Composable
fun OddsHelpSection() {
    HelpSection(title = "Odds Determination") {
        Column(Modifier.padding(16.dp)) {
            BulletPoint(text = "Odds determination is a convenience feature only.")
            BulletPoint(text = "Enter the attacker and defender strength values using the number pad.")
            BulletPoint(text = "The odds are calculated and displayed.")
            PngIcon(R.drawable.calc, "Calculator", modifier = Modifier.size(30.dp))
            BulletPoint(text = "Tap the calculator button to open the calculator.")
            Spacer(Modifier.height(12.dp))
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewOddsHelpSection() {
    OddsHelpSection(
    )
}
