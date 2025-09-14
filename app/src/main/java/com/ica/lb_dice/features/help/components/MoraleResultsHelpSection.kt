package com.ica.lb_dice.features.help.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ica.lb_dice.features.help.BulletPoint
import com.ica.lb_dice.ui.Die
import com.ica.lb_dice.ui.ModifierButtonsRow

@Composable
fun MoraleResultsHelpSection(modifier: Modifier = Modifier, details: String = "") {
    var text = "The morale dice are used to determine the morale results"
    if (details.isNotEmpty()) {
        text += "$details"
    } else {
        text += "."
    }

    Column(modifier.padding(16.dp)) {
        // morale dice
        Row() {
            Die(dieNumber = 1,
                modifier = Modifier
                    .padding(1.dp)
                    .size(40.dp)
                ,
                onDieClicked = { },
                sides = 6,
                dieColor = Color.Black,
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
                sides = 6,
                dieColor = Color.Black,
                dotColor = Color.Red,
                dieValue = 1
            )
        }
        BulletPoint(text = text)
        BulletPoint(text = "Tap a die to increase its value by 1.")
        Spacer(Modifier.height(12.dp))

        // modifier buttons
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
        BulletPoint(text = "The modifier buttons are used to adjust the dice.")
        BulletPoint(text = "Tap a button to adjust the dice by the value of the button.")
        Spacer(Modifier.height(12.dp))

        // results explanation
        BulletPoint(text = "The morale results are a list of the possible outcomes based on the dice.")
        BulletPoint(text = "Each result includes a morale value and a pass or fail indicator.")
        BulletPoint(text = "For quick reference, the list includes estimates of outcomes for common morale modifiers.")
        Spacer(Modifier.height(16.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMoraleResultsHelpSection() {
    MoraleResultsHelpSection(
    )
}
