package com.ica.lb_dice.features.melee

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.ica.lb_dice.features.calculator.CalculatorDialog
import com.ica.lb_dice.features.common.DiceRollViewModel

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

    var selectedTabIndex by remember { mutableStateOf(0) }

    data class TabItem(val title: String, val icon: Int)
    val tabs = listOf(
        TabItem("Pre-Melee", com.ica.lb_dice.R.drawable.melee_premelee),
        TabItem("Combat", com.ica.lb_dice.R.drawable.melee_combat)
    )

    //val tabTitles = listOf("Pre-Melee", "Melee")

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

        TabRow(selectedTabIndex = selectedTabIndex) {
            //tabTitles.forEachIndexed { index, title ->
            tabs.forEachIndexed { index, tab ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index },
                    text = { Text(text = tab.title, fontSize = 12.sp) },
                    icon = { com.ica.lb_dice.ui.PngIcon(tab.icon, tab.title, Modifier.height(48.dp)) }
                )
            }
        }

        when (selectedTabIndex) {
            0 -> PreMeleeMoraleCheckSection(
                modifier = Modifier
                    .fillMaxWidth()
                    //.height(250.dp)
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
            1 -> MeleeCombatSection(
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
    }
    if (isCalculatorDialogOpen) {
        CalculatorDialog(
            Modifier.fillMaxSize(),
            onSetAttack = { value ->
                meleeCombatViewModel.setAttackerMeleeStrength(value.toString())
            },
            onSetDefend = { value ->
                meleeCombatViewModel.setDefenderMeleeStrength(value.toString())
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

