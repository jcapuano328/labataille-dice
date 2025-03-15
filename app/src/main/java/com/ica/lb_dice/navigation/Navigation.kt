package com.ica.lb_dice.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ica.lb_dice.ui.FontAwesomeIcon
import com.ica.lb_dice.screens.FireCombatScreen
import com.ica.lb_dice.screens.MeleeCombatScreen
import com.ica.lb_dice.screens.MoraleCheckScreen
import com.ica.lb_dice.screens.GeneralScreen

// In Navigation.kt

@Composable
fun MainNavigation() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { MainBottomNavigationBar(navController = navController) } // Pass navController here
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = NavigationDestinations.FireCombat.route,
            modifier = Modifier.padding(innerPadding) // Apply padding here
        ) {
            composable(NavigationDestinations.FireCombat.route) { FireCombatScreen(navController) }
            composable(NavigationDestinations.MeleeCombat.route) { MeleeCombatScreen(navController) }
            composable(NavigationDestinations.MoraleCheck.route) { MoraleCheckScreen(navController) }
            composable(NavigationDestinations.General.route) { GeneralScreen(navController) }
        }
    }
}

@Composable
fun MainBottomNavigationBar(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar { // or BottomNavigation
        listOf(
            NavigationDestinations.FireCombat,
            NavigationDestinations.MeleeCombat,
            NavigationDestinations.MoraleCheck,
            NavigationDestinations.General
        ).forEach { destination ->
            NavigationBarItem( // or BottomNavigationItem
                icon = { FontAwesomeIcon(unicode = destination.icon.toString(), description = destination.route, modifier = Modifier.padding(20.dp)) }, // Replace with appropriate icons
                label = { Text(text = destination.route) },
                selected = currentDestination?.hierarchy?.any { it.route == destination.route } == true,
                onClick = {
                    navController.navigate(destination.route) {
                        // Avoid building up a large stack of destinations
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        // Avoid multiple copies of the same destination when reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                }
            )
        }
    }
}