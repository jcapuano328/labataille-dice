package com.ica.lb_dice.features.help.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ica.lb_dice.ui.Die
import com.ica.lb_dice.ui.ModifierButtonsRow
import com.ica.lb_dice.ui.PngIcon
import com.ica.lb_dice.ui.ResultImage

@Composable
fun RollDiceHelpSection(modifier: Modifier = Modifier) {
    Column(modifier.padding(16.dp)) {
        Card(
            modifier = Modifier
                .padding(1.dp)
                .size(60.dp),
            shape = androidx.compose.foundation.shape.CircleShape
        ) {
            PngIcon(com.ica.lb_dice.R.drawable.dice_round, "Dice", modifier = Modifier.size(60.dp))
        }
        BulletPoint(text = "Tap the button to roll the dice.")
        BulletPoint(text = "Dice rolls are unique for each screen.")
        Spacer(Modifier.height(16.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewRollDiceHelpSection() {
    RollDiceHelpSection(
    )
}
