package com.ica.lb_dice.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

import com.ica.lb_dice.viewmodels.DiceRollViewModel
import com.ica.lb_dice.viewmodels.FireCombatViewModel
import com.ica.lb_dice.viewmodels.FireCombatResultsSet
import com.ica.lb_dice.screens.fire.FireCombatDice
import com.ica.lb_dice.screens.fire.FireCombatResults


@Composable
fun FireCombatScreen(navController: NavController, diceRollViewModel: DiceRollViewModel) {
    val scope = rememberCoroutineScope()
    // 1. Get the FireCombatViewModel instance
    val fireCombatViewModel: FireCombatViewModel = viewModel()
    // 2. Get the state from the viewmodel.
    val diceSetFire by fireCombatViewModel.diceSetFire.collectAsState()
    val diceSetLeader by fireCombatViewModel.diceSetLeader.collectAsState()
    val diceSetMorale by fireCombatViewModel.diceSetMorale.collectAsState()
    val resultsSet by fireCombatViewModel.resultsSet.collectAsState()

    LaunchedEffect(key1 = Unit) {
        diceRollViewModel.fabEvent.collect {
            // Perform FireCombatScreen-specific logic here
            println("FAB tapped in FireCombatScreen!")
            fireCombatViewModel.onFabClicked()
        }
    }
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize().padding(8.dp)) {
        // Display Fire Combat.kt Screen Text
        //Text("Fire Combat.kt Screen")
        //Spacer(modifier = Modifier.height(16.dp))
        FireCombatDice(
            diceSetFire = diceSetFire,
            onFireDieIncrement = { die ->
                println("Fire Dice Changed")
                fireCombatViewModel.incrementFireDie(die)
            },
            onFireDiceModify = { value ->
                fireCombatViewModel.modifyFireDice(value)
            },
            diceSetLeader = diceSetLeader,
            onLeaderDieIncrement = { die ->
                println("Leader Dice Changed")
                fireCombatViewModel.incrementLeaderDie(die)
            },
            diceSetMorale = diceSetMorale,
            onMoraleDieIncrement = { die ->
                println("Morale Dice Changed")
                fireCombatViewModel.incrementMoraleDie(die)
            },
            onMoraleDiceModify = { value ->
                fireCombatViewModel.modifyMoraleDice(value)
            }
        )
        Spacer(modifier = Modifier.height(4.dp))
        // Display the results
        FireCombatResults(resultsSet)
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewFireCombatScreen() {
    FireCombatScreen(
        navController = NavController(LocalContext.current),
        diceRollViewModel = DiceRollViewModel()
    )
}


