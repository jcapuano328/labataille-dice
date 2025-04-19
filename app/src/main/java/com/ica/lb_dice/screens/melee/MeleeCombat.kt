package com.ica.lb_dice.screens.melee

import androidx.compose.foundation.background
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
import com.ica.lb_dice.viewmodels.MeleeCombatResultsSet
import com.ica.lb_dice.screens.results.CombatResults
import com.ica.lb_dice.screens.results.LeaderCasualtyResults
import com.ica.lb_dice.screens.results.MoraleResults


@Composable
fun MeleeCombatSection(modifier: Modifier = Modifier,
                       meleeDiceSet: DiceSet, onMeleeDieIncrement: (die: Int) -> Unit, onMeleeDiceModify: (value: Int) -> Unit,
                       diceSetLeader: DiceSet, onLeaderDieIncrement: (die: Int) -> Unit,
                       diceSetMorale: DiceSet, onMoraleDieIncrement: (die: Int) -> Unit,
                       meleeCombatResults: MeleeCombatResultsSet
) {
    Column(modifier = modifier) {
        MeleeCombatDice(
            modifier = modifier
                .weight(0.25f)
            ,
            diceSetMelee = meleeDiceSet,
            onMeleeDieIncrement = onMeleeDieIncrement,
            onMeleeDiceModify = onMeleeDiceModify,
            diceSetLeader = diceSetLeader,
            onLeaderDieIncrement = onLeaderDieIncrement,
            diceSetMorale = diceSetMorale,
            onMoraleDieIncrement = onMoraleDieIncrement
        )
        MeleeCombatResultsSection(
            modifier
                .weight(0.75f)
            ,
            meleeCombatResults
        )
    }
}
@Composable
fun MeleeCombatDice(modifier: Modifier = Modifier,
                    diceSetMelee: DiceSet, onMeleeDieIncrement: (die: Int) -> Unit, onMeleeDiceModify: (value: Int) -> Unit,
                    diceSetLeader: DiceSet, onLeaderDieIncrement: (die: Int) -> Unit,
                    diceSetMorale: DiceSet, onMoraleDieIncrement: (die: Int) -> Unit
) {
    Column(
        modifier = modifier
            .wrapContentHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
            //.height(200.dp)
            //.padding(8.dp)
            ,horizontalArrangement = Arrangement.spacedBy(8.dp)
            ,verticalAlignment = Alignment.Top
        ) {
            DiceSet(
                dieConfigs = diceSetMelee.dieConfigs,
                dieValues = diceSetMelee.dieValues.collectAsState().value,
                modifier = Modifier.weight(2/7f),
                //modifier = Modifier.fillMaxWidth(),
                onDieClicked = { dieNumber ->
                    println("Fire Die $dieNumber clicked")
                    onMeleeDieIncrement(dieNumber)
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
        ModifierButtonsRow(
            label = "Melee",
            foregroundColor = Color.White,
            backgroundColor = Color.Blue,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
            //.weight(1 / 10f)
            ,
            onModifierButtonClicked = { value ->
                println("Melee Modifier clicked: $value")
                onMeleeDiceModify(value)
            }
        )
    }
}
@Composable
fun MeleeCombatResultsSection(modifier: Modifier = Modifier, resultsSet: MeleeCombatResultsSet) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            //.fillMaxHeight()
            //.weight(8/10f)
        ,horizontalArrangement = Arrangement.spacedBy(4.dp)
        ,verticalAlignment = Alignment.Top
    ) {
        CombatResults(Modifier.weight(1f), resultsSet.meleeResults)
        LeaderCasualtyResults(Modifier.weight(1f), resultsSet.leaderCasualtyResults)
        MoraleResults(modifier = Modifier.weight(1f), results = resultsSet.moraleResults)
    }
}
