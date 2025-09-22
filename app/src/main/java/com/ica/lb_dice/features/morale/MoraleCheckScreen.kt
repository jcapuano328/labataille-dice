package com.ica.lb_dice.features.morale

import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.ica.lb_dice.features.common.MoraleResults
import com.ica.lb_dice.ui.DiceSet
import com.ica.lb_dice.ui.ModifierButtonsRow
import com.ica.lb_dice.features.common.DiceRollViewModel
import com.ica.lb_dice.features.fire.DiceSet
import com.ica.lb_dice.features.common.MoraleResult
import com.ica.lb_dice.ui.FlashingBackground
import kotlinx.coroutines.launch

@Composable
fun MoraleCheckScreen(navController: NavController, diceRollViewModel: DiceRollViewModel) {
    val scope = rememberCoroutineScope()
    var rollTrigger by remember { mutableStateOf(0) }

    val moraleCheckViewModel: MoraleCheckViewModel = viewModel()
    // 2. Get the state from the viewmodel.
    val diceSetMorale by moraleCheckViewModel.diceSetMorale.collectAsState()
    val resultsSet by moraleCheckViewModel.resultsSet.collectAsState()

    LaunchedEffect(key1 = Unit) {
        diceRollViewModel.fabEvent.collect {
            // Perform MoralCheckScreen-specific logic here
            rollTrigger++
            //moraleCheckViewModel.onFabClicked()
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
                moraleCheckViewModel.onFabClicked()
            }
        },
        onFlashComplete = {
            // Optional: Do something when the flash cycle is done
            //moraleCheckViewModel.onFabClicked()
        }
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize().padding(2.dp)
        ) {
            MoraleCheckDice(
                Modifier.fillMaxWidth(),
                diceSetMorale,
                onMoraleDieIncrement = { die ->
                    moraleCheckViewModel.incrementMoraleDie(die)
                },
                onMoraleDiceModify = { value ->
                    moraleCheckViewModel.modifyMoraleDice(value)
                }
            )
            Spacer(modifier = Modifier.height(4.dp))
            MoraleCheckResults(
                Modifier
                    .fillMaxWidth()
                    .height(400.dp),
                resultsSet.moraleResults
            )
        }
    }
}

@Composable
fun MoraleCheckDice(modifier: Modifier = Modifier,
                    diceSetMorale: DiceSet,
                    onMoraleDieIncrement: (die: Int) -> Unit,
                    onMoraleDiceModify: (value: Int) -> Unit,
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
                dieConfigs = diceSetMorale.dieConfigs,
                dieValues = diceSetMorale.dieValues.collectAsState().value,
                onDieClicked = { dieNumber ->
                    onMoraleDieIncrement(dieNumber)
                }
            )
        }
        Spacer(modifier = modifier.height(4.dp))
        ModifierButtonsRow(
            label = "Morale",
            foregroundColor = Color.White,
            backgroundColor = Color(0xFFB200FF), // purple
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight()
            ,
            onModifierButtonClicked = { value ->
                onMoraleDiceModify(value)
            }
        )
    }
}

@Composable
fun MoraleCheckResults(modifier: Modifier = Modifier, moraleResults: List<MoraleResult>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
        ,horizontalAlignment = Alignment.CenterHorizontally
        ,verticalArrangement = Arrangement.Top
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
            ,horizontalArrangement = Arrangement.spacedBy(4.dp)
            ,verticalAlignment = Alignment.Top
        ) {
            Spacer(modifier = modifier.weight(1f))
            MoraleResults(modifier = modifier.weight(3f), title = "If Morale is...", results = moraleResults)
            Spacer(modifier = modifier.weight(1f))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMoraleCheckScreen() {
    MoraleCheckScreen(
        navController = NavController(LocalContext.current),
        diceRollViewModel = DiceRollViewModel()
    )
}

