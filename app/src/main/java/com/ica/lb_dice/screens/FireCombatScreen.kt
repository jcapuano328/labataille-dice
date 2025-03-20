package com.ica.lb_dice.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

import com.ica.lb_dice.viewmodels.DiceRollViewModel
import com.ica.lb_dice.viewmodels.FireCombatViewModel
import com.ica.lb_dice.ui.DiceSet

@Composable
fun FireCombatScreen(navController: NavController, diceRollViewModel: DiceRollViewModel) {
    val scope = rememberCoroutineScope()
    // 1. Get the FireCombatViewModel instance
    val fireCombatViewModel: FireCombatViewModel = viewModel()
    // 2. Get the state from the viewmodel.
    val diceSetConfig by fireCombatViewModel.diceSetConfig.collectAsState()
    val dieValues by fireCombatViewModel.dieValues.collectAsState()

    LaunchedEffect(key1 = Unit) {
        diceRollViewModel.fabEvent.collect {
            // Perform FireCombatScreen-specific logic here
            println("FAB tapped in FireCombatScreen!")
            fireCombatViewModel.onFabClicked()
        }
    }
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize()) {
        // Display Fire Combat Screen Text
        Text("Fire Combat Screen")
        Spacer(modifier = Modifier.height(16.dp))
        // 3. Display the DiceSet, using the dieConfigs from ScreenViewModel.
        if (diceSetConfig != null && dieValues.isNotEmpty()) {
            DiceSet(
                dieConfigs = diceSetConfig!!.dieConfigs,
                dieValues = dieValues,
                modifierButtonsVisible = false,
                modifier = Modifier.fillMaxWidth(),
                onDieClicked = { dieNumber ->
                    println("Die $dieNumber clicked")
                },
                onModifierButtonClicked = { buttonNumber ->
                    println("Modifier Button $buttonNumber clicked")
                }
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        // Display the navigation button
        Button(onClick = { navController.popBackStack() }) {
            Text("Go Back")
        }
    }
}