package com.ica.lb_dice.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.ica.lb_dice.navigation.NavigationDestinations

import com.ica.lb_dice.viewmodels.DiceRollViewModel
import com.ica.lb_dice.viewmodels.FireCombatViewModel

@Composable
fun FireCombatScreen(navController: NavController, diceRollViewModel: DiceRollViewModel) {
    val scope = rememberCoroutineScope()
    val fireCombatViewModel: FireCombatViewModel = viewModel()
    LaunchedEffect(key1 = Unit) {
        diceRollViewModel.fabEvent.collect {
            // Perform FireCombatScreen-specific logic here
            println("FAB tapped in FireCombatScreen!")
            fireCombatViewModel.onFabClicked()
        }
    }
    Column {
        Text("Fire Combat Screen")
        Button(onClick = { navController.navigate(NavigationDestinations.MeleeCombat.route) }) {
            Text("Go to Melee Combat")
        }
    }
}