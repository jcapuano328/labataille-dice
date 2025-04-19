package com.ica.lb_dice.screens.fire

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ica.lb_dice.ui.DiceSet
import com.ica.lb_dice.ui.ModifierButtonsRow
import com.ica.lb_dice.viewmodels.DiceSet

@Composable
fun FireCombatDice(
    diceSetFire: DiceSet, onFireDieIncrement: (die: Int) -> Unit, onFireDiceModify: (value: Int) -> Unit,
    diceSetLeader: DiceSet, onLeaderDieIncrement: (die: Int) -> Unit,
    diceSetMorale: DiceSet, onMoraleDieIncrement: (die: Int) -> Unit, onMoraleDiceModify: (value: Int) -> Unit
)
{
    Row(
        modifier = Modifier
            .fillMaxWidth()
        //.height(200.dp)
        //.padding(8.dp)
        ,horizontalArrangement = Arrangement.spacedBy(8.dp)
        ,verticalAlignment = Alignment.Top
    ) {
        DiceSet(
            dieConfigs = diceSetFire.dieConfigs,
            dieValues = diceSetFire.dieValues.collectAsState().value,
            modifier = Modifier.weight(2/7f),
            //modifier = Modifier.fillMaxWidth(),
            onDieClicked = { dieNumber ->
                println("Fire Die $dieNumber clicked")
                onFireDieIncrement(dieNumber)
            }
        )
        DiceSet(
            dieConfigs = diceSetLeader.dieConfigs,
            dieValues = diceSetLeader.dieValues.collectAsState().value,
            modifier = Modifier.weight(3/7f),
            //modifier = Modifier.fillMaxWidth(),
            onDieClicked = { dieNumber ->
                println("Leader Die $dieNumber clicked")
                onLeaderDieIncrement(dieNumber)
            }
        )
        DiceSet(
            dieConfigs = diceSetMorale.dieConfigs,
            dieValues = diceSetMorale.dieValues.collectAsState().value,
            modifier = Modifier.weight(2/7f),
            //modifier = Modifier.fillMaxWidth(),
            onDieClicked = { dieNumber ->
                println("Morale Die $dieNumber clicked")
                onMoraleDieIncrement(dieNumber)
            }
        )
    }
    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
    ) {
        ModifierButtonsRow(
            label = "Fire",
            foregroundColor = Color.White,
            backgroundColor = Color.Blue,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
            //.weight(1 / 10f)
            ,
            onModifierButtonClicked = { value ->
                println("Fire Modifier clicked: $value")
                onFireDiceModify(value)
            }
        )
    }

    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
    ) {
        ModifierButtonsRow(
            label = "Morale",
            foregroundColor = Color.White,
            backgroundColor = Color(0xFFB200FF), // purple
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
            //.weight(1 / 10f)
            ,
            onModifierButtonClicked = { value ->
                println("Morale Modifier clicked: $value")
                onMoraleDiceModify(value)
            }
        )
    }
}