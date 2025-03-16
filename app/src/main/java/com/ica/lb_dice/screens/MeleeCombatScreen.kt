package com.ica.lb_dice.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.ica.lb_dice.viewmodels.DiceRollViewModel
import com.ica.lb_dice.viewmodels.MeleeCombatViewModel

@Composable
fun MeleeCombatScreen(navController: NavController, diceRollViewModel: DiceRollViewModel) {
    val scope = rememberCoroutineScope()
    val meleeCombatViewModel: MeleeCombatViewModel = viewModel()
    LaunchedEffect(key1 = Unit) {
        diceRollViewModel.fabEvent.collect {
            // Perform MeleeombatScreen-specific logic here
            println("FAB tapped in MeleeCombatScreen!")
            meleeCombatViewModel.onFabClicked()
        }
    }
    Column {
        Text("Melee Combat Screen")
        Button(onClick = { navController.popBackStack() }) { // navigate back
            Text("Go Back")
        }
    }
}