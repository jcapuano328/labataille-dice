package com.ica.lb_dice.features.general

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.ica.lb_dice.ui.DiceSet
import com.ica.lb_dice.ui.ModifierButtonsRow

import com.ica.lb_dice.features.common.DiceRollViewModel
import com.ica.lb_dice.features.fire.DiceSet

@Composable
fun GeneralScreen(navController: NavController, diceRollViewModel: DiceRollViewModel) {
    val generalViewModel: GeneralViewModel = viewModel()
    val diceSet1 by generalViewModel.diceSet1.collectAsState()
    val diceSet2 by generalViewModel.diceSet2.collectAsState()
    val diceSet3 by generalViewModel.diceSet3.collectAsState()
    val diceSet4 by generalViewModel.diceSet4.collectAsState()
    val diceSet5 by generalViewModel.diceSet5.collectAsState()

    LaunchedEffect(key1 = Unit) {
        diceRollViewModel.fabEvent.collect {
            // Perform GeneralScreen-specific logic here
            println("FAB tapped in GeneralScreen!")
            generalViewModel.onFabClicked()
        }
    }
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize().padding(2.dp)) {
        /*
        GeneralDice(
            modifier = Modifier
                .fillMaxWidth()
            ,
            showModifierButtons = false,
            diceSet = diceSet5,
            onDieIncrement = { die ->
                generalViewModel.incrementDieSet5(die)
            },
            onDiceModify = { }
        )
        */
        Initiative(
            modifier = Modifier.fillMaxWidth(),
            dieImperial = diceSet5.dieValues.collectAsState().value[0],
            onDieImperialClicked = {
                generalViewModel.incrementDieSet5(0)
            },
            dieAllies = diceSet5.dieValues.collectAsState().value[1],
            onDieAlliesClicked = {
                generalViewModel.incrementDieSet5(1)
            }
        )


        //Spacer(modifier = Modifier.height(16.dp))
        HorizontalDivider(modifier = Modifier.height(16.dp).padding(vertical = 8.dp, horizontal = 16.dp), thickness = 1.dp, color = Color.DarkGray)

        GeneralDice(
            modifier = Modifier
                .fillMaxWidth()
            ,
            buttonForegroundColor = Color.White,
            buttonBackgroundColor = Color.Blue,
            diceSet = diceSet1,
            onDieIncrement = { die ->
                generalViewModel.incrementDieSet1(die)
            },
            onDiceModify = { value ->
                generalViewModel.modifyDiceSet1(value)
            }
        )
        Spacer(modifier = Modifier.height(4.dp))
        GeneralDice(
            modifier = Modifier
                .fillMaxWidth()
            ,
            buttonForegroundColor = Color.White,
            buttonBackgroundColor = Color(0xFFB200FF), // purple
            diceSet = diceSet2,
            onDieIncrement = { die ->
                generalViewModel.incrementDieSet2(die)
            },
            onDiceModify = { value ->
                generalViewModel.modifyDiceSet2(value)
            }
        )
        Spacer(modifier = Modifier.height(4.dp))
        GeneralDice(
            modifier = Modifier
                .fillMaxWidth()
            ,
            buttonForegroundColor = Color.White,
            buttonBackgroundColor = Color(0xFF007F0E), // dark green
            fullRow = false,
            diceSet = diceSet3,
            onDieIncrement = { die ->
                generalViewModel.incrementDieSet3(die)
            },
            onDiceModify = { value ->
                generalViewModel.modifyDiceSet3(value)
            }
        )
        //Spacer(modifier = Modifier.height(12.dp))
        HorizontalDivider(modifier = Modifier.height(16.dp).padding(vertical = 8.dp, horizontal = 16.dp), thickness = 1.dp, color = Color.DarkGray)
        GeneralDice(
            modifier = Modifier
                .fillMaxWidth()
            ,
            showModifierButtons = false,
            diceSet = diceSet4,
            onDieIncrement = { die ->
                generalViewModel.incrementDieSet4(die)
            },
            onDiceModify = { }
        )
    }
}

@Composable
fun GeneralDice(modifier: Modifier = Modifier,
                showModifierButtons: Boolean = true,
                buttonForegroundColor: Color = Color.Transparent,
                buttonBackgroundColor: Color = Color.Transparent,
                fullRow: Boolean = true,
                diceSet: DiceSet,
                onDieIncrement: (die: Int) -> Unit,
                onDiceModify: (value: Int) -> Unit
) {

    Column(modifier = modifier
        .fillMaxWidth()
        .wrapContentHeight()
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
            ,horizontalArrangement = Arrangement.Center
            ,verticalAlignment = Alignment.Top
        ) {
            DiceSet(
                dieConfigs = diceSet.dieConfigs,
                dieValues = diceSet.dieValues.collectAsState().value,
                onDieClicked = { dieNumber ->
                    onDieIncrement(dieNumber)
                }
            )
        }
        if (showModifierButtons) {
            Spacer(modifier = modifier.height(4.dp))
            ModifierButtonsRow(
                label = "General",
                foregroundColor = buttonForegroundColor,
                backgroundColor = buttonBackgroundColor,
                fullRow = fullRow,
                modifier = modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                onModifierButtonClicked = { value ->
                    onDiceModify(value)
                }
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewGeneralScreen() {
    GeneralScreen(
        navController = NavController(LocalContext.current),
        diceRollViewModel = DiceRollViewModel()
    )
}

