package com.ica.lb_dice.features.melee

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.ica.lb_dice.features.common.DiceRollViewModel
import com.ica.lb_dice.features.common.CombatOddsSection

@Composable
fun MeleeCombatScreen(navController: NavController, diceRollViewModel: DiceRollViewModel,
                      openDialog: (initial: Float, onSetAttack: (Float) -> Unit, onSetDefend: (Float) -> Unit) -> Unit) {
    val meleeCombatViewModel: MeleeCombatViewModel = viewModel()

    val diceSetAttackerPreMeleeMorale by meleeCombatViewModel.diceSetAttackerPreMeleeMorale.collectAsState()
    val diceSetDefenderPreMeleeMorale by meleeCombatViewModel.diceSetDefenderPreMeleeMorale.collectAsState()

    val diceSetMelee by meleeCombatViewModel.diceSetMelee.collectAsState()
    val diceSetLeader by meleeCombatViewModel.diceSetLeader.collectAsState()
    val diceSetMorale by meleeCombatViewModel.diceSetMorale.collectAsState()
    val meleeResults = meleeCombatViewModel.meleeResultsSet.collectAsState()

    var selectedTabIndex by remember { mutableStateOf(0) }

    val tabTitles = listOf("Assault", "Melee")

    LaunchedEffect(key1 = Unit) {
        diceRollViewModel.fabEvent.collect {
            // Perform MeleeCombatScreen-specific logic here
            println("FAB tapped in MeleeCombatScreen!")
            meleeCombatViewModel.onFabClicked()
        }
    }
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize().padding(2.dp)) {
        CombatOddsSection(
            modifier = Modifier
                .fillMaxWidth()
            ,
            meleeResults.value.attackerMeleeStrength,
            onAttackerStrengthChange = { value ->
                meleeCombatViewModel.setAttackerMeleeStrength(value)
            },
            meleeResults.value.defenderMeleeStrength,
            onDefenderStrengthChange = { value ->
                meleeCombatViewModel.setDefenderMeleeStrength(value)
            },
            combatOdds = meleeResults.value.meleeOdds,
            onShowCalculatorClicked = {
                openDialog(/*uiState.attack*/0f,
                    { value ->
                        meleeCombatViewModel.setAttackerMeleeStrength(value.toString())
                    },
                    { value ->
                        meleeCombatViewModel.setDefenderMeleeStrength(value.toString())
                    })
            }
        )

        TabRow(selectedTabIndex = selectedTabIndex) {
            tabTitles.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index },
                    text = { Text(text = title, fontSize = 12.sp) }
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
}

@Preview(showBackground = true)
@Composable
fun PreviewMeleeCombatScreen() {
    MeleeCombatScreen(
        navController = NavController(LocalContext.current),
        diceRollViewModel = DiceRollViewModel(),
        openDialog = { _, _, _ -> }
    )
}

