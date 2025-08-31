package com.ica.lb_dice.features.melee

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ica.lb_dice.ui.InputNumeric
import com.ica.lb_dice.ui.PngIcon

@Composable
fun MeleeCombatOddsSection(modifier: Modifier = Modifier,
                           attackerMeleeStrength: String = "1", onAttackerMeleeStrengthChange: (value: String) -> Unit,
                           defenderMeleeStrength: String = "1", onDefenderMeleeStrengthChange: (value: String) -> Unit,
                           meleeOdds: String = "1:1",
                           onShowCalculatorClicked : () -> Unit = {}) {
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
                //.padding(4.dp)
            ,
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                text = "Attacker",
                style = TextStyle(color = Color.Red, fontWeight = FontWeight.Bold, fontSize = 12.sp, textAlign = TextAlign.Center),
                modifier = Modifier
                    .weight(1f)
            )
            //*
            Box(modifier = Modifier.weight(0.5f)) {
                IconButton(
                    onClick = onShowCalculatorClicked,
                    modifier = Modifier
                        .align(Alignment.Center)
                ) {
                    PngIcon(
                        resId = com.ica.lb_dice.R.drawable.calc,
                        desc = "Calculator",
                        modifier = Modifier
                            .size(32.dp)
                            .align(Alignment.Center)
                    )
                }

            }
            //*/
            //Spacer(modifier = Modifier.weight(0.5f))
            Text(
                text = "Defender",
                style = TextStyle(color = Color.Blue, fontWeight = FontWeight.Bold, fontSize = 12.sp, textAlign = TextAlign.Center),
                modifier = Modifier
                    .weight(1f)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                //.background(Color.Gray)
                .padding(4.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            InputNumeric(
                value = attackerMeleeStrength,
                onValueChange = onAttackerMeleeStrengthChange,
                label = "Attacker",
                modifier = Modifier.weight(1f)
            )
            Text(text = meleeOdds, modifier = Modifier.weight(0.5f), fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
            InputNumeric(
                value = defenderMeleeStrength,
                onValueChange = onDefenderMeleeStrengthChange,
                label = "Defender",
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMeleeCombatOdds() {
    MeleeCombatOddsSection(
        modifier = Modifier.fillMaxWidth().wrapContentHeight(),
        attackerMeleeStrength = "1",
        onAttackerMeleeStrengthChange = {},
        defenderMeleeStrength = "1",
        onDefenderMeleeStrengthChange = {},
        meleeOdds = "1:1"
    )
}