package com.ica.lb_dice.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.compose.material3.FabPosition
import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

import com.ica.lb_dice.R
import com.ica.lb_dice.ui.FontAwesomeIcon
import com.ica.lb_dice.viewmodels.DiceRollViewModel
import com.ica.lb_dice.screens.FireCombatScreen
import com.ica.lb_dice.screens.MeleeCombatScreen
import com.ica.lb_dice.screens.MoraleCheckScreen
import com.ica.lb_dice.screens.GeneralScreen

// In Navigation.kt

@Composable
fun MainNavigation() {
    val navController = rememberNavController()
    val diceRollViewModel: DiceRollViewModel = viewModel()
    Scaffold(
        bottomBar = { MainBottomNavigationBar(navController = navController) },
        floatingActionButton = { MainFloatingActionButton(navController, diceRollViewModel) },
        floatingActionButtonPosition = FabPosition.Center
    ) { innerPadding ->
        MainNavigationContent(navController, innerPadding, diceRollViewModel)
    }
}

@Composable
fun MainNavigationContent(navController: NavHostController, innerPadding: PaddingValues, viewModel: DiceRollViewModel) {
    NavHost(
        navController = navController,
        startDestination = NavigationDestinations.FireCombat.route,
        modifier = Modifier.padding(innerPadding) // Apply padding here
    ) {
        composable(NavigationDestinations.FireCombat.route) { FireCombatScreen(navController, viewModel) }
        composable(NavigationDestinations.MeleeCombat.route) { MeleeCombatScreen(navController, viewModel) }
        composable(NavigationDestinations.MoraleCheck.route) { MoraleCheckScreen(navController, viewModel) }
        composable(NavigationDestinations.General.route) { GeneralScreen(navController, viewModel) }
    }
}

@Composable
fun MainBottomNavigationBar(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomAppBar(
        actions = {
            MyNavigationItems(navController, currentDestination)
        },
        modifier = Modifier,
        tonalElevation = 10.dp,
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
    )
}

@Composable
fun RowScope.MyNavigationItems(navController: NavHostController, currentDestination: NavDestination?) {
    listOf(
        NavigationDestinations.FireCombat,
        NavigationDestinations.MeleeCombat,
        NavigationDestinations.MoraleCheck,
        NavigationDestinations.General
    ).forEach { destination ->
        NavigationBarItem(
            icon = { FontAwesomeIcon(unicode = destination.icon, description = destination.route) },
            label = { Text(text = destination.route) },
            selected = currentDestination?.hierarchy?.any { it.route == destination.route } == true,
            onClick = {
                navController.navigate(destination.route) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            },
            colors = NavigationBarItemDefaults.colors(
                indicatorColor = Color.Transparent,
                selectedIconColor = Color.White,
                selectedTextColor = Color.Red,
                unselectedIconColor = Color.Gray,
                unselectedTextColor = Color.Gray
            )
        )
    }
}


@Composable
fun MainFloatingActionButton(navController: NavHostController, viewModel: DiceRollViewModel) {
    val scope  = rememberCoroutineScope()
    FloatingActionButton(onClick = {
        scope.launch {
            viewModel.onFabClicked()
        }
    }) {
        Image(
            painter = painterResource(id = R.drawable.dice),
            contentDescription = "Roll"
        )
    }
}