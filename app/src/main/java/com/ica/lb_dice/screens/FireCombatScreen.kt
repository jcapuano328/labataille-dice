package com.ica.lb_dice.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.ica.lb_dice.navigation.NavigationDestinations

@Composable
fun FireCombatScreen(navController: NavController) {
    Column {
        Text("Fire Combat Screen")
        Button(onClick = { navController.navigate(NavigationDestinations.MeleeCombat.route) }) {
            Text("Go to Melee Combat")
        }
    }
}