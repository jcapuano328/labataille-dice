package com.ica.lb_dice.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.navigation.NavDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

import com.ica.lb_dice.R
import com.ica.lb_dice.features.calculator.CalculatorDialog
import com.ica.lb_dice.features.calculator.CalculatorDialogRequest
import com.ica.lb_dice.features.fire.FireCombatHelpContent
import com.ica.lb_dice.features.general.GeneralHelpContent
import com.ica.lb_dice.features.help.HelpDialog
import com.ica.lb_dice.features.melee.MeleeCombatHelpContent
import com.ica.lb_dice.features.morale.MoraleHelpContent
import com.ica.lb_dice.features.common.DiceRollViewModel
import com.ica.lb_dice.features.fire.FireCombatScreen
import com.ica.lb_dice.features.melee.MeleeCombatScreen
import com.ica.lb_dice.features.morale.MoraleCheckScreen
import com.ica.lb_dice.features.general.GeneralScreen
import com.ica.lb_dice.ui.PngIcon

// In Navigation.kt
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainNavigation() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val diceRollViewModel: DiceRollViewModel = viewModel()
    var showHelp by remember { mutableStateOf(false) }
    var dialogRequest: CalculatorDialogRequest? by remember { mutableStateOf(null) }
    val openDialog: (Float, (Float) -> Unit, (Float) -> Unit) -> Unit =
        { initial, onSetAttack, onSetDefend ->
            dialogRequest = CalculatorDialogRequest(initial, onSetAttack, onSetDefend)
        }

    Scaffold(
        topBar = {
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
                title = { Text("La Bataille Dice") },
                actions = {
                    IconButton(onClick = { showHelp = true }) {
                        Icon(Icons.Default.HelpOutline, contentDescription = "Help")
                    }
                }
            )
        },
        bottomBar = { MainBottomNavigationBar(navController = navController) },
        floatingActionButton = {
            if (dialogRequest == null) {
                MainFloatingActionButton(navController, diceRollViewModel)
            }
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = NavigationDestinations.FireCombat.route,
            modifier = Modifier.padding(innerPadding) // Apply padding here
        ) {
            composable(NavigationDestinations.FireCombat.route) {
                FireCombatScreen(
                    navController,
                    diceRollViewModel
                )
            }
            composable(NavigationDestinations.MeleeCombat.route) {
                MeleeCombatScreen(
                    navController,
                    diceRollViewModel,
                    openDialog = openDialog
                )
            }
            composable(NavigationDestinations.MoraleCheck.route) {
                MoraleCheckScreen(
                    navController,
                    diceRollViewModel
                )
            }
            composable(NavigationDestinations.General.route) {
                GeneralScreen(
                    navController,
                    diceRollViewModel
                )
            }
        }
    }

    dialogRequest?.let { req ->
        CalculatorDialog(
            Modifier.fillMaxSize(),
            onSetAttack = { value ->
                req.onSetAttack(value)
            },
            onSetDefend = { value ->
                req.onSetDefend(value)
            },
            onDismissRequest = {
                dialogRequest = null
            }
        )
    }

    if (showHelp) {
        currentRoute?.let {
            HelpDialog(
                onDismiss = {showHelp = false},
                currentTopic = it
            ) {
                when (currentRoute) {
                    NavigationDestinations.FireCombat.route -> FireCombatHelpContent()
                    NavigationDestinations.MeleeCombat.route -> MeleeCombatHelpContent()
                    NavigationDestinations.MoraleCheck.route -> MoraleHelpContent()
                    NavigationDestinations.General.route -> GeneralHelpContent()
                    else -> Text("No help available for this screen")
                }
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
        NavigationDestinations.FireCombat,
        NavigationDestinations.MeleeCombat
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
        NavigationDestinations.MoraleCheck,
        NavigationDestinations.General
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