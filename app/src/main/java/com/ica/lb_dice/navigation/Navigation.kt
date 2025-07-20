package com.ica.lb_dice.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
import com.ica.lb_dice.ui.PngIcon

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainNavigationContent(navController: NavHostController, innerPadding: PaddingValues, viewModel: DiceRollViewModel) {
    Column() {
        TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = MaterialTheme.colorScheme.onPrimary,
                navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                actionIconContentColor = MaterialTheme.colorScheme.onSecondary
            ),
            navigationIcon = {
                // perhaps make this a button and display the "about" popup?
                PngIcon(
                    resId = com.ica.lb_dice.R.drawable.logo,
                    desc = "Logo",
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .size(40.dp)
                    //.align(Alignment.Center)
                )
            },
            title = { Text("La Bataille Dice") }
        )

        NavHost(
            navController = navController,
            startDestination = NavigationDestinationsAlt.FireCombat.route,
            modifier = Modifier.padding(innerPadding) // Apply padding here
        ) {
            composable(NavigationDestinationsAlt.FireCombat.route) {
                FireCombatScreen(
                    navController,
                    viewModel
                )
            }
            composable(NavigationDestinationsAlt.MeleeCombat.route) {
                MeleeCombatScreen(
                    navController,
                    viewModel
                )
            }
            composable(NavigationDestinationsAlt.MoraleCheck.route) {
                MoraleCheckScreen(
                    navController,
                    viewModel
                )
            }
            composable(NavigationDestinationsAlt.General.route) {
                GeneralScreen(
                    navController,
                    viewModel
                )
            }
        }
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
        NavigationDestinationsAlt.FireCombat,
        NavigationDestinationsAlt.MeleeCombat
    ).forEach { destination ->
        NavigationBarItem(
            //icon = { FontAwesomeIcon(unicode = destination.icon, description = destination.route) },
            icon = { PngIcon(resId = destination.icon, desc = destination.route) },
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
    Spacer(Modifier.weight(1f)) // Space for FAB
    listOf(
        NavigationDestinationsAlt.MoraleCheck,
        NavigationDestinationsAlt.General
    ).forEach { destination ->
        NavigationBarItem(
            //icon = { FontAwesomeIcon(unicode = destination.icon, description = destination.route) },
            icon = { PngIcon(resId = destination.icon, desc = destination.route) },
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
    SmallFloatingActionButton(
        modifier = Modifier.offset(y = 80.dp), // Moves FAB downward into the BottomAppBar
        shape = CircleShape,
        onClick = {
        scope.launch {
            viewModel.onFabClicked()
        }
    }) {
        Image(
            painter = painterResource(id = R.drawable.dice_round),
            contentDescription = "Roll"
        )
    }
}