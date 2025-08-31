package com.ica.lb_dice.features.melee

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ica.lb_dice.features.common.MoraleResults
import com.ica.lb_dice.features.morale.MoraleService
import com.ica.lb_dice.ui.DiceSet
import com.ica.lb_dice.ui.ModifierButtonsRow
import com.ica.lb_dice.util.DieConfig
import com.ica.lb_dice.features.fire.DiceSet
import com.ica.lb_dice.features.common.MoraleResult
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun PreMeleeMoraleCheckSection(modifier: Modifier = Modifier,
                               diceSetAttackerPreMeleeMorale: DiceSet, onPreMeleeMoraleAttackerDieIncrement: (die: Int) -> Unit, onPreMeleeMoraleAttackerDiceModify: (value: Int) -> Unit,
                               diceSetDefenderPreMeleeMorale: DiceSet, onPreMeleeMoraleDefenderDieIncrement: (die: Int) -> Unit, onPreMeleeMoraleDefenderDiceModify: (value: Int) -> Unit,
                               attackerPreMeleeMoraleResults: List<MoraleResult>, defenderPreMeleeMoraleResults: List<MoraleResult>
) {
    Column(
        modifier = modifier
            //.clip(RoundedCornerShape(16.dp)) // Rounded corners for the whole table
            //.border(2.dp, Color.Black, RoundedCornerShape(16.dp)) // Black border around the entire table
            .fillMaxWidth()
            //.fillMaxHeight()
            .background(Color(0xFFFFFAE5)) // Light Yellow
        ,
        verticalArrangement = Arrangement.Top
    ) {
        /*
        // Header Row (Gray)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(Color.Gray)
                .padding(4.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Pre-Melee Morale Check",
                style = TextStyle(color = Color.White, fontWeight = FontWeight.Bold, fontSize = 12.sp, textAlign = TextAlign.Center),
                //modifier = Modifier.weight(2f)
            )
        }
        */
        PreMeleeMoraleCheckDice(Modifier.fillMaxWidth(),
            diceSetAttackerPreMeleeMorale, onPreMeleeMoraleAttackerDieIncrement, onPreMeleeMoraleAttackerDiceModify,
            diceSetDefenderPreMeleeMorale, onPreMeleeMoraleDefenderDieIncrement, onPreMeleeMoraleDefenderDiceModify
        )
        Spacer(modifier = Modifier.height(4.dp))
        PreMeleeMoraleCheckResults(Modifier.fillMaxWidth(),
            attackerPreMeleeMoraleResults,
            defenderPreMeleeMoraleResults
        )
    }
}
@Composable
fun PreMeleeMoraleCheckDice(modifier: Modifier = Modifier,
                            diceSetAttackerPreMeleeMorale: DiceSet, onPreMeleeMoraleAttackerDieIncrement: (die: Int) -> Unit, onPreMeleeMoraleAttackerDiceModify: (value: Int) -> Unit,
                            diceSetDefenderPreMeleeMorale: DiceSet, onPreMeleeMoraleDefenderDieIncrement: (die: Int) -> Unit, onPreMeleeMoraleDefenderDiceModify: (value: Int) -> Unit) {

    Column(modifier = modifier
        .fillMaxWidth()
        .wrapContentHeight()
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
            ,horizontalArrangement = Arrangement.spacedBy(8.dp)
            ,verticalAlignment = Alignment.Top
        ) {
            DiceSet(
                dieConfigs = diceSetAttackerPreMeleeMorale.dieConfigs,
                dieValues = diceSetAttackerPreMeleeMorale.dieValues.collectAsState().value,
                modifier = modifier.weight(1f),
                onDieClicked = { dieNumber ->
                    println("Attacker Pre-Melee Morale Die $dieNumber clicked")
                    onPreMeleeMoraleAttackerDieIncrement(dieNumber)
                }
            )
            Spacer(modifier = modifier.weight(1f))
            DiceSet(
                dieConfigs = diceSetDefenderPreMeleeMorale.dieConfigs,
                dieValues = diceSetDefenderPreMeleeMorale.dieValues.collectAsState().value,
                modifier = modifier.weight(1f),
                onDieClicked = { dieNumber ->
                    println("Defender Pre-Melee Morale Die $dieNumber clicked")
                    onPreMeleeMoraleDefenderDieIncrement(dieNumber)
                }
            )
        }

        ModifierButtonsRow(
            label = "Attacker",
            foregroundColor = Color.White,
            backgroundColor = Color.Blue,
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight()
            ,
            onModifierButtonClicked = { value ->
                println("Fire Modifier clicked: $value")
                onPreMeleeMoraleAttackerDiceModify(value)
            }
        )
        Spacer(modifier = modifier.height(4.dp))
        ModifierButtonsRow(
            label = "Morale",
            foregroundColor = Color.White,
            backgroundColor = Color(0xFFB200FF), // purple
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight()
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
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
        ,horizontalAlignment = Alignment.CenterHorizontally
        ,verticalArrangement = Arrangement.Top
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
            ,horizontalArrangement = Arrangement.spacedBy(4.dp)
            ,verticalAlignment = Alignment.Top
        ) {
            MoraleResults(modifier = modifier.weight(3f), title = "If Attacker Morale is...", results = attackerPreMeleeMoraleResults)
            Spacer(modifier = modifier.weight(1f))
            MoraleResults(modifier = modifier.weight(3f), title = "If Defender Morale is...", results = defenderPreMeleeMoraleResults)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewPreMeleeMoraleCheckDice() {
    val dieValuesMoraleAttacker = MutableStateFlow(List(2) { 6 })
    val dieConfigsMoraleAttacker = listOf(
        DieConfig(backgroundColor = Color.Black, dotColor = Color.Red),
        DieConfig(backgroundColor = Color.Black, dotColor = Color.White)
    )
    val diceSetMoraleAttacker = DiceSet(dieConfigsMoraleAttacker, dieValuesMoraleAttacker)

    val dieValuesMoraleDefender = MutableStateFlow(List(2) { 6 })
    val dieConfigsMoraleDefender = listOf(
        DieConfig(backgroundColor = Color.Black, dotColor = Color.Red),
        DieConfig(backgroundColor = Color.Black, dotColor = Color.White)
    )
    val diceSetMoraleDefender = DiceSet(dieConfigsMoraleDefender, dieValuesMoraleDefender)

    PreMeleeMoraleCheckDice(
        modifier = Modifier
            .fillMaxWidth()
        ,
        diceSetAttackerPreMeleeMorale = diceSetMoraleAttacker,
        onPreMeleeMoraleAttackerDieIncrement = {},
        onPreMeleeMoraleAttackerDiceModify = {},
        diceSetDefenderPreMeleeMorale = diceSetMoraleDefender,
        onPreMeleeMoraleDefenderDieIncrement = {},
        onPreMeleeMoraleDefenderDiceModify = {}
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewPreMeleeMoraleCheckResults() {
    val moraleService = MoraleService()
    val attackerPreMeleeMoraleResults = moraleService.check(33)
    val defenderPreMeleeMoraleResults = moraleService.check(21)

    PreMeleeMoraleCheckResults(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
        ,
        attackerPreMeleeMoraleResults = attackerPreMeleeMoraleResults.map { MoraleResult(it.result, it.modifier, it.icon) },
        defenderPreMeleeMoraleResults = defenderPreMeleeMoraleResults.map { MoraleResult(it.result, it.modifier, it.icon) }
    )
}


@Preview(showBackground = true)
@Composable
fun PreviewPreMeleeMoraleCheckSection() {
    val dieValuesMoraleAttacker = MutableStateFlow(List(2) { 6 })
    val dieConfigsMoraleAttacker = listOf(
        DieConfig(backgroundColor = Color.Black, dotColor = Color.Red),
        DieConfig(backgroundColor = Color.Black, dotColor = Color.White)
    )
    val diceSetMoraleAttacker = DiceSet(dieConfigsMoraleAttacker, dieValuesMoraleAttacker)

    val dieValuesMoraleDefender = MutableStateFlow(List(2) { 6 })
    val dieConfigsMoraleDefender = listOf(
        DieConfig(backgroundColor = Color.Black, dotColor = Color.Red),
        DieConfig(backgroundColor = Color.Black, dotColor = Color.White)
    )
    val diceSetMoraleDefender = DiceSet(dieConfigsMoraleDefender, dieValuesMoraleDefender)

    val moraleService = MoraleService()
    val attackerPreMeleeMoraleResults = moraleService.check(33)
    val defenderPreMeleeMoraleResults = moraleService.check(21)

    PreMeleeMoraleCheckSection(
        modifier = Modifier.fillMaxWidth().height(400.dp),
        diceSetAttackerPreMeleeMorale = diceSetMoraleAttacker,
        onPreMeleeMoraleAttackerDieIncrement = {},
        onPreMeleeMoraleAttackerDiceModify = {},
        diceSetDefenderPreMeleeMorale = diceSetMoraleDefender,
        onPreMeleeMoraleDefenderDieIncrement = {},
        onPreMeleeMoraleDefenderDiceModify = {},
        attackerPreMeleeMoraleResults = attackerPreMeleeMoraleResults.map { MoraleResult(it.result, it.modifier, it.icon) },
        defenderPreMeleeMoraleResults = defenderPreMeleeMoraleResults.map { MoraleResult(it.result, it.modifier, it.icon) }
    )
}