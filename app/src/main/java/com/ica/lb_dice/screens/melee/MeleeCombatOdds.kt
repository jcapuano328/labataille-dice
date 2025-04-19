package com.ica.lb_dice.screens.melee

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ica.lb_dice.ui.InputFloatWithIncrementDecrement

@Composable
fun MeleeCombatOddsSection(modifier: Modifier = Modifier,
                           attackerMeleeStrength: Float = 1f, onAttackerMeleeStrengthChange: (value: Float) -> Unit,
                           defenderMeleeStrength: Float = 1f, onDefenderMeleeStrengthChange: (value: Float) -> Unit,
                           meleeOdds: String = "1:1") {
    //Text(text = "Melee Combat Odds", modifier = modifier)
    Column(
        modifier = modifier
            //.clip(RoundedCornerShape(16.dp)) // Rounded corners for the whole table
            //.border(2.dp, Color.Black, RoundedCornerShape(16.dp)) // Black border around the entire table
            .fillMaxWidth()
            //.fillMaxHeight()
            //.background(Color.Blue)
            //.background(Color(0xFFFFFAE5)) // Light Yellow
    ) {
        // Header Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                //.background(Color.Gray)
                .background(Color.Transparent)
                .padding(8.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Attacker",
                style = TextStyle(color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 12.sp, textAlign = TextAlign.Center),
                modifier = Modifier
                    .weight(1f)
            )
            Spacer(modifier = Modifier.weight(0.5f))
            Text(
                text = "Defender",
                style = TextStyle(color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 12.sp, textAlign = TextAlign.Center),
                modifier = Modifier
                    .weight(1f)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                //.background(Color.Gray)
                .padding(8.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            InputFloatWithIncrementDecrement(
                value = attackerMeleeStrength,
                onValueChange = onAttackerMeleeStrengthChange,
                label = "Attacker",
                modifier = Modifier.weight(1f)
            )
            Text(text = meleeOdds, modifier = Modifier.weight(0.5f), fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
            InputFloatWithIncrementDecrement(
                value = defenderMeleeStrength,
                onValueChange = onDefenderMeleeStrengthChange,
                label = "Defender",
                modifier = Modifier.weight(1f)
            )
        }
    }
}
