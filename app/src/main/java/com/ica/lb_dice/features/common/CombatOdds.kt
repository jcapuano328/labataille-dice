package com.ica.lb_dice.features.common

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
fun CombatOddsSection(modifier: Modifier = Modifier,
                      attackerLabel: String = "Attacker",
                      attackerStrength: String = "1", onAttackerStrengthChange: (value: String) -> Unit,
                      defenderLabel: String = "Defender",
                      defenderStrength: String = "1", onDefenderStrengthChange: (value: String) -> Unit,
                      combatOdds: String = "1:1",
                      showCalculator: Boolean = true,
                      onShowCalculatorClicked : () -> Unit = {}) {
    //Text(text = "Combat Odds", modifier = modifier)
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
                text = attackerLabel,
                style = TextStyle(color = Color.Red, fontWeight = FontWeight.Bold, fontSize = 12.sp, textAlign = TextAlign.Center),
                modifier = Modifier
                    .weight(1f)
            )
            //*
            Box(modifier = Modifier.weight(0.5f)) {
                if (showCalculator) {
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
            }
            //*/
            //Spacer(modifier = Modifier.weight(0.5f))
            Text(
                text = defenderLabel,
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
                value = attackerStrength,
                onValueChange = onAttackerStrengthChange,
                label = "Attacker",
                modifier = Modifier.weight(1f)
            )
            Text(text = combatOdds, modifier = Modifier.weight(0.5f), fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
            InputNumeric(
                value = defenderStrength,
                onValueChange = onDefenderStrengthChange,
                label = "Defender",
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCombatOdds() {
    CombatOddsSection(
        modifier = Modifier.fillMaxWidth().wrapContentHeight(),
        attackerLabel = "Fubar",
        attackerStrength = "1",
        onAttackerStrengthChange = {},
        defenderLabel = "Foobar",
        defenderStrength = "1",
        onDefenderStrengthChange = {},
        combatOdds = "1:1",
        showCalculator = false,
        onShowCalculatorClicked = {}
    )
}