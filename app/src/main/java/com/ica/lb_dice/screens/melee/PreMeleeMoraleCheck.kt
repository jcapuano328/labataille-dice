package com.ica.lb_dice.screens.melee

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ica.lb_dice.screens.results.MoraleResults
import com.ica.lb_dice.ui.DiceSet
import com.ica.lb_dice.ui.ModifierButtonsRow
import com.ica.lb_dice.viewmodels.DiceSet
import com.ica.lb_dice.viewmodels.MoraleResult

@Composable
fun PreMeleeMoraleCheckSection(modifier: Modifier = Modifier,
                               diceSetAttackerPreMeleeMorale: DiceSet, onPreMeleeMoraleAttackerDieIncrement: (die: Int) -> Unit, onPreMeleeMoraleAttackerDiceModify: (value: Int) -> Unit,
                               diceSetDefenderPreMeleeMorale: DiceSet, onPreMeleeMoraleDefenderDieIncrement: (die: Int) -> Unit, onPreMeleeMoraleDefenderDiceModify: (value: Int) -> Unit,
                               attackerPreMeleeMoraleResults: List<MoraleResult>, defenderPreMeleeMoraleResults: List<MoraleResult>
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp)) // Rounded corners for the whole table
            .border(2.dp, Color.Black, RoundedCornerShape(16.dp)) // Black border around the entire table
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color(0xFFFFFAE5)) // Light Yellow
        ,
        verticalArrangement = Arrangement.Top
    ) {
        // Header Row (Gray)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1 / 10f)
                .background(Color.Gray)
                .padding(2.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Pre-Melee Morale Check",
                style = TextStyle(color = Color.White, fontWeight = FontWeight.Bold, fontSize = 12.sp, textAlign = TextAlign.Center),
            )
        }
        Column(modifier = Modifier
            .fillMaxWidth()
            .weight(9/10f)
            .background(color = Color.Magenta)
            //.border(2.dp, Color.Black, shape = RoundedCornerShape(16.dp))
        ) {
            PreMeleeMoraleCheckDice(modifier.weight(1/5f),
                diceSetAttackerPreMeleeMorale, onPreMeleeMoraleAttackerDieIncrement, onPreMeleeMoraleAttackerDiceModify,
                diceSetDefenderPreMeleeMorale, onPreMeleeMoraleDefenderDieIncrement, onPreMeleeMoraleDefenderDiceModify
            )
            PreMeleeMoraleCheckResults(modifier.weight(4/5f), attackerPreMeleeMoraleResults, defenderPreMeleeMoraleResults)
        }
    }
}
@Composable
fun PreMeleeMoraleCheckDice(modifier: Modifier = Modifier,
                            diceSetAttackerPreMeleeMorale: DiceSet, onPreMeleeMoraleAttackerDieIncrement: (die: Int) -> Unit, onPreMeleeMoraleAttackerDiceModify: (value: Int) -> Unit,
                            diceSetDefenderPreMeleeMorale: DiceSet, onPreMeleeMoraleDefenderDieIncrement: (die: Int) -> Unit, onPreMeleeMoraleDefenderDiceModify: (value: Int) -> Unit) {

    Column(modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .weight(1f)
                //.wrapContentHeight()
                //.height(200.dp)
                //.padding(8.dp)
            ,horizontalArrangement = Arrangement.spacedBy(8.dp)
            ,verticalAlignment = Alignment.Top
        ) {
            DiceSet(
                dieConfigs = diceSetAttackerPreMeleeMorale.dieConfigs,
                dieValues = diceSetAttackerPreMeleeMorale.dieValues.collectAsState().value,
                modifier = modifier.weight(.6f),
                //modifier = Modifier.fillMaxWidth(),
                onDieClicked = { dieNumber ->
                    println("Attacker Pre-Melee Morale Die $dieNumber clicked")
                    onPreMeleeMoraleAttackerDieIncrement(dieNumber)
                }
            )
            Spacer(modifier = modifier.weight(1f))
            DiceSet(
                dieConfigs = diceSetDefenderPreMeleeMorale.dieConfigs,
                dieValues = diceSetDefenderPreMeleeMorale.dieValues.collectAsState().value,
                modifier = modifier.weight(.6f),
                //modifier = Modifier.fillMaxWidth(),
                onDieClicked = { dieNumber ->
                    println("Defender Pre-Melee Morale Die $dieNumber clicked")
                    onPreMeleeMoraleDefenderDiceModify(dieNumber)
                }
            )
        }

        ModifierButtonsRow(
            label = "Attacker",
            foregroundColor = Color.White,
            backgroundColor = Color.Blue,
            modifier = modifier
                .fillMaxWidth()
                //.wrapContentHeight()
                .weight(1f)
            //.weight(1 / 10f)
            ,
            onModifierButtonClicked = { value ->
                println("Fire Modifier clicked: $value")
                onPreMeleeMoraleAttackerDiceModify(value)
            }
        )
        ModifierButtonsRow(
            label = "Morale",
            foregroundColor = Color.White,
            backgroundColor = Color(0xFFB200FF), // purple
            modifier = modifier
                .fillMaxWidth()
                //.wrapContentHeight()
                .weight(1f)
            //.weight(1 / 10f)
            ,
            onModifierButtonClicked = { value ->
                println("Morale Modifier clicked: $value")
                onPreMeleeMoraleDefenderDiceModify(value)
            }
        )
    }
}
@Composable
fun PreMeleeMoraleCheckResults(modifier: Modifier = Modifier, attackerPreMeleeMoraleResults: List<MoraleResult>, defenderPreMeleeMoraleResults: List<MoraleResult>) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            //.background(Color.Gray)
            .padding(8.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        //MoraleResults(modifier = modifier.weight(1f), title = "Attacker", results = attackerPreMeleeMoraleResults)
        //MoraleResults(modifier = modifier.weight(1f), title = "Defender", results = defenderPreMeleeMoraleResults)
    }
}