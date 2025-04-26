package com.ica.lb_dice.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
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
import com.ica.lb_dice.screens.melee.MeleeCombatOddsSection
import com.ica.lb_dice.screens.melee.MeleeCombatSection
import com.ica.lb_dice.screens.melee.PreMeleeMoraleCheckSection
import com.ica.lb_dice.ui.CalculatorDialog
import com.ica.lb_dice.viewmodels.DiceRollViewModel
import com.ica.lb_dice.viewmodels.MeleeCombatViewModel

@Composable
fun MeleeCombatScreen(navController: NavController, diceRollViewModel: DiceRollViewModel) {
    val meleeCombatViewModel: MeleeCombatViewModel = viewModel()

    val diceSetAttackerPreMeleeMorale by meleeCombatViewModel.diceSetAttackerPreMeleeMorale.collectAsState()
    val diceSetDefenderPreMeleeMorale by meleeCombatViewModel.diceSetDefenderPreMeleeMorale.collectAsState()

    val diceSetMelee by meleeCombatViewModel.diceSetMelee.collectAsState()
    val diceSetLeader by meleeCombatViewModel.diceSetLeader.collectAsState()
    val diceSetMorale by meleeCombatViewModel.diceSetMorale.collectAsState()
    val meleeResults = meleeCombatViewModel.meleeResultsSet.collectAsState()

    var isCalculatorDialogOpen by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = Unit) {
        diceRollViewModel.fabEvent.collect {
            // Perform MeleeCombatScreen-specific logic here
            println("FAB tapped in MeleeCombatScreen!")
            meleeCombatViewModel.onFabClicked()
        }
    }
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize().padding(2.dp)) {
        MeleeCombatOddsSection(
            modifier = Modifier
                .fillMaxWidth()
            ,
            meleeResults.value.attackerMeleeStrength,
            onAttackerMeleeStrengthChange = { value ->
                meleeCombatViewModel.setAttackerMeleeStrength(value)
            },
            meleeResults.value.defenderMeleeStrength,
            onDefenderMeleeStrengthChange = { value ->
                meleeCombatViewModel.setDefenderMeleeStrength(value)
            },
            meleeOdds = meleeResults.value.meleeOdds,
            onShowCalculatorClicked = {
                isCalculatorDialogOpen = true
            }
        )
        PreMeleeMoraleCheckSection(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
            ,
            diceSetAttackerPreMeleeMorale = diceSetAttackerPreMeleeMorale,
            onPreMeleeMoraleAttackerDieIncrement = { die ->
                meleeCombatViewModel.incrementAttackerPreMeleeMoraleDie(die)
            },
            onPreMeleeMoraleAttackerDiceModify = { value ->
                meleeCombatViewModel.modifyAttackerPreMeleeMoraleDice(value)
            },
            diceSetDefenderPreMeleeMorale = diceSetDefenderPreMeleeMorale,
            onPreMeleeMoraleDefenderDieIncrement = { die ->
                meleeCombatViewModel.incrementDefenderPreMeleeMoraleDie(die)
            },
            onPreMeleeMoraleDefenderDiceModify = { value ->
                meleeCombatViewModel.modifyDefenderPreMeleeMoraleDice(value)
            },
            attackerPreMeleeMoraleResults = meleeResults.value.attackerPreMeleeMoraleResults,
            defenderPreMeleeMoraleResults = meleeResults.value.defenderPreMeleeMoraleResults
        )
        MeleeCombatSection(
            modifier = Modifier
                .fillMaxWidth()
            ,
            meleeDiceSet = diceSetMelee,
            onMeleeDieIncrement = { die ->
                meleeCombatViewModel.incrementMeleeDie(die)
            },
            onMeleeDiceModify = { value ->
                meleeCombatViewModel.modifyMeleeDice(value)
            },
            diceSetLeader = diceSetLeader,
            onLeaderDieIncrement = { die ->
                meleeCombatViewModel.incrementLeaderDie(die)
            },
            diceSetMorale = diceSetMorale,
            onMoraleDieIncrement = { die ->
                meleeCombatViewModel.incrementMoraleDie(die)
            },
            meleeCombatResults = meleeResults.value
        )
    }
    if (isCalculatorDialogOpen) {
        CalculatorDialog(
            Modifier.fillMaxSize(),
            onSetAttack = { value ->
                meleeCombatViewModel.setAttackerMeleeStrength(value)
            },
            onSetDefend = { value ->
                meleeCombatViewModel.setDefenderMeleeStrength(value)
            },
            onDismissRequest = {
                isCalculatorDialogOpen = false
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMeleeCombatScreen() {
    MeleeCombatScreen(
        navController = NavController(LocalContext.current),
        diceRollViewModel = DiceRollViewModel()
    )
}

