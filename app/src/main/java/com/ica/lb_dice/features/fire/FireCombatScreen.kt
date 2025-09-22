package com.ica.lb_dice.features.fire

import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.ica.lb_dice.features.calculator.CalculatorDialogType
import com.ica.lb_dice.features.common.CombatOddsSection

import com.ica.lb_dice.features.common.DiceRollViewModel
import com.ica.lb_dice.ui.FlashingBackground
import kotlinx.coroutines.launch


@Composable
fun FireCombatScreen(navController: NavController, diceRollViewModel: DiceRollViewModel,
                     openDialog: (initial: Float, onSetAttack: (Float) -> Unit, onSetDefend: (Float) -> Unit, type: CalculatorDialogType) -> Unit) {
    val scope = rememberCoroutineScope()
    var rollTrigger by remember { mutableStateOf(0) }
    // 1. Get the FireCombatViewModel instance
    val fireCombatViewModel: FireCombatViewModel = viewModel()
    // 2. Get the state from the viewmodel.
    val diceSetFire by fireCombatViewModel.diceSetFire.collectAsState()
    val diceSetLeader by fireCombatViewModel.diceSetLeader.collectAsState()
    val diceSetMorale by fireCombatViewModel.diceSetMorale.collectAsState()
    val resultsSet by fireCombatViewModel.resultsSet.collectAsState()

    LaunchedEffect(key1 = Unit) {
        diceRollViewModel.fabEvent.collect {
            // Perform FireCombatScreen-specific logic here
            rollTrigger++
            //fireCombatViewModel.onFabClicked()
        }
    }

    FlashingBackground(
        trigger = rollTrigger,
        flashColor = Color(0xffffff9f),
        baseColor = MaterialTheme.colorScheme.surfaceVariant,
        animationSpecIn = tween(durationMillis = 50),  //300
        holdDurationMillis = 50, //200
        animationSpecOut = tween(durationMillis = 50), //300
        onFlashFull = {
            // Optional: Do something when the flash cycle is at full brightness
            scope.launch {
                fireCombatViewModel.onFabClicked()
            }
        },
        onFlashComplete = {
            // Optional: Do something when the flash cycle is done
            //fireCombatViewModel.onFabClicked()
        }
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize().padding(2.dp)
        ) {
            CombatOddsSection(
                modifier = Modifier
                    .fillMaxWidth(),
                attackerLabel = "Fire Value",
                attackerStrength = resultsSet.attackerStrength,
                onAttackerStrengthChange = { value ->
                    fireCombatViewModel.setAttackerStrength(value)
                },
                defenderLabel = "Defense Value",
                defenderStrength = resultsSet.defenderStrength,
                onDefenderStrengthChange = { value ->
                    fireCombatViewModel.setDefenderStrength(value)
                },
                combatOdds = resultsSet.fireOdds,
                showCalculator = true,
                onShowCalculatorClicked = {
                    openDialog(/*uiState.attack*/0f,
                        { value ->
                            fireCombatViewModel.setAttackerStrength(value.toString())
                        },
                        { value ->
                            fireCombatViewModel.setDefenderStrength(value.toString())
                        },
                        CalculatorDialogType.Combat
                    )
                }

            )

            FireCombatDice(
                diceSetFire = diceSetFire,
                onFireDieIncrement = { die ->
                    fireCombatViewModel.incrementFireDie(die)
                },
                onFireDiceModify = { value ->
                    fireCombatViewModel.modifyFireDice(value)
                },
                diceSetLeader = diceSetLeader,
                onLeaderDieIncrement = { die ->
                    fireCombatViewModel.incrementLeaderDie(die)
                },
                diceSetMorale = diceSetMorale,
                onMoraleDieIncrement = { die ->
                    fireCombatViewModel.incrementMoraleDie(die)
                },
                onMoraleDiceModify = { value ->
                    fireCombatViewModel.modifyMoraleDice(value)
                }
            )
            Spacer(modifier = Modifier.height(4.dp))
            // Display the results
            FireCombatResults(resultsSet)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewFireCombatScreen() {
    FireCombatScreen(
        navController = NavController(LocalContext.current),
        diceRollViewModel = DiceRollViewModel(),
        openDialog = { _, _, _, _ -> }
    )
}


