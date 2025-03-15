package com.ica.lb_dice.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavController
import com.ica.lb_dice.viewmodels.DiceRollViewModel

@Composable
fun MoraleCheckScreen(navController: NavController, viewModel: DiceRollViewModel) {
    val scope = rememberCoroutineScope()
    LaunchedEffect(key1 = Unit) {
        viewModel.fabEvent.collect {
            // Perform MoralCheckScreen-specific logic here
            println("FAB tapped in MoraleCheckScreen!")
        }
    }
    Column {
        Text("Morale Check Screen")
        Button(onClick = { navController.popBackStack() }) { // navigate back
            Text("Go Back")
        }
    }
}
