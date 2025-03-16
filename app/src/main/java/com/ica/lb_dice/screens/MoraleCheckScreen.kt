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
import com.ica.lb_dice.viewmodels.MoraleCheckViewModel

@Composable
fun MoraleCheckScreen(navController: NavController, diceRollViewModel: DiceRollViewModel) {
    val scope = rememberCoroutineScope()
    val moraleCheckViewModel: MoraleCheckViewModel = viewModel()
    LaunchedEffect(key1 = Unit) {
        diceRollViewModel.fabEvent.collect {
            // Perform MoralCheckScreen-specific logic here
            println("FAB tapped in MoraleCheckScreen!")
            moraleCheckViewModel.onFabClicked()
        }
    }
    Column {
        Text("Morale Check Screen")
        Button(onClick = { navController.popBackStack() }) { // navigate back
            Text("Go Back")
        }
    }
}
