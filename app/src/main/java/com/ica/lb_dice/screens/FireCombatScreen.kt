package com.ica.lb_dice.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

import com.ica.lb_dice.viewmodels.DiceRollViewModel
import com.ica.lb_dice.viewmodels.FireCombatViewModel
import com.ica.lb_dice.ui.DiceSet
import com.ica.lb_dice.ui.ModifierButtonsRow
import com.ica.lb_dice.ui.PngIcon
import com.ica.lb_dice.viewmodels.DiceSet
import com.ica.lb_dice.viewmodels.FireCombatResultsSet
import com.ica.lb_dice.viewmodels.FireResult
import com.ica.lb_dice.viewmodels.LeaderCasualtyResult
import com.ica.lb_dice.viewmodels.MoraleResult

@Composable
fun FireCombatScreen(navController: NavController, diceRollViewModel: DiceRollViewModel) {
    val scope = rememberCoroutineScope()
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
            println("FAB tapped in FireCombatScreen!")
            fireCombatViewModel.onFabClicked()
        }
    }
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize().padding(16.dp)) {
        // Display Fire Combat Screen Text
        //Text("Fire Combat Screen")
        //Spacer(modifier = Modifier.height(16.dp))
        FireCombatDice(
            diceSetFire = diceSetFire,
            onFireDieIncrement = { die ->
                println("Fire Dice Changed")
                fireCombatViewModel.incrementFireDie(die)
            },
            diceSetLeader = diceSetLeader,
            onLeaderDieIncrement = { die ->
                println("Leader Dice Changed")
                fireCombatViewModel.incrementLeaderDie(die)
            },
            diceSetMorale = diceSetMorale,
            onMoraleDieIncrement = { die ->
                println("Morale Dice Changed")
                fireCombatViewModel.incrementMoraleDie(die)
            }
        )
        Spacer(modifier = Modifier.height(4.dp))
        // Display the results
        FireCombatResults(resultsSet,
            onFireDiceModify = { value ->
                fireCombatViewModel.modifyFireDice(value)
            },
            onMoraleDiceModify = { value ->
                fireCombatViewModel.modifyMoraleDice(value)
            }
        )
    }
}

@Composable
fun FireCombatDice(
    diceSetFire: DiceSet, onFireDieIncrement: (die: Int) -> Unit,
    diceSetLeader: DiceSet, onLeaderDieIncrement: (die: Int) -> Unit,
    diceSetMorale: DiceSet, onMoraleDieIncrement: (die: Int) -> Unit
)
{
    Row(
        modifier = Modifier
            .fillMaxWidth()
        //.height(200.dp)
        //.padding(8.dp)
        //.background(Color.LightGray)
        ,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.Top
    ) {
        DiceSet(
            dieConfigs = diceSetFire.dieConfigs,
            dieValues = diceSetFire.dieValues.collectAsState().value,
            modifier = Modifier.weight(2/7f),
            //modifier = Modifier.fillMaxWidth(),
            onDieClicked = { dieNumber ->
                println("Fire Die $dieNumber clicked")
                onFireDieIncrement(dieNumber)
            }
        )
        DiceSet(
            dieConfigs = diceSetLeader.dieConfigs,
            dieValues = diceSetLeader.dieValues.collectAsState().value,
            modifier = Modifier.weight(3/7f),
            //modifier = Modifier.fillMaxWidth(),
            onDieClicked = { dieNumber ->
                println("Leader Die $dieNumber clicked")
                onLeaderDieIncrement(dieNumber)
            }
        )
        DiceSet(
            dieConfigs = diceSetMorale.dieConfigs,
            dieValues = diceSetMorale.dieValues.collectAsState().value,
            modifier = Modifier.weight(2/7f),
            //modifier = Modifier.fillMaxWidth(),
            onDieClicked = { dieNumber ->
                println("Morale Die $dieNumber clicked")
                onMoraleDieIncrement(dieNumber)
            }
        )
    }
}

@Composable
fun FireCombatResults(resultsSet: FireCombatResultsSet, onFireDiceModify: (value: Int) -> Unit, onMoraleDiceModify: (value: Int) -> Unit) {
    //Text("Results")
    Column(
        modifier = Modifier
            .fillMaxWidth()
            //.fillMaxHeight()
            .wrapContentHeight()
            //.padding(8.dp)
            //.background(Color.LightGray)
    ) {
        ModifierButtonsRow(
            label = "Fire",
            foregroundColor = Color.White,
            backgroundColor = Color.Blue,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .weight(1/10f)
            ,
            onModifierButtonClicked = { value ->
                println("Fire Modifier clicked: $value")
                onFireDiceModify(value)
            }
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                //.fillMaxHeight()
                .weight(3/8f)
                .wrapContentHeight()
            //.padding(8.dp)
            //.background(Color.Red)
            ,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            CombatResults(Modifier.weight(1f), resultsSet.fireResults)
            LeaderCasualtyResults(Modifier.weight(1f), resultsSet.leaderCasualtyResults)
        }
        ModifierButtonsRow(
            label = "Morale",
            foregroundColor = Color.White,
            backgroundColor = Color(0xFFB200FF), // purple
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .weight(1/10f)
            ,
            onModifierButtonClicked = { value ->
                println("Morale Modifier clicked: $value")
                onMoraleDiceModify(value)
            }
        )
        MoraleResults(Modifier.weight(3/8f), resultsSet.moraleResults)
    }
}

@Composable
fun CombatResults(modifier: Modifier = Modifier, results: List<FireResult>) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp)) // Rounded corners for the whole table
            .border(2.dp, Color.Black, RoundedCornerShape(16.dp)) // Black border around the entire table
            .fillMaxWidth()
            .background(Color(0xFFFFFAE5)) // Light Yellow
    ) {
        val data = results.map { Pair(it.odds, it.result) }
        // Header Row (Gray)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Gray)
                .padding(8.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Combat",
                style = TextStyle(color = Color.White, fontWeight = FontWeight.Bold, fontSize = 12.sp, textAlign = TextAlign.Center),
                modifier = Modifier
                    .weight(2f)
            )
        }

        // Body (Light Yellow)
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFFFFAE5)) // Light Yellow
        ) {
            items(data) { item ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = item.first, modifier = Modifier.weight(2/3f), textAlign = TextAlign.Center)
                    Text(text = item.second, modifier = Modifier.weight(1/3f), textAlign = TextAlign.Center)
                }
            }
        }
    }
}

@Composable
fun LeaderCasualtyResults(modifier: Modifier = Modifier, data: LeaderCasualtyResult) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp)) // Rounded corners for the whole table
            .border(2.dp, Color.Black, RoundedCornerShape(16.dp)) // Black border around the entire table
            .fillMaxWidth()
            //.background(Color.Blue)
    ) {
        //Text("Leader Casualty Results")
        // Header Row (Gray)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Gray)
                .padding(8.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Leader Casualty",
                style = TextStyle(color = Color.White, fontWeight = FontWeight.Bold, fontSize = 12.sp, textAlign = TextAlign.Center),
                modifier = Modifier
                    .weight(2f)
            )
        }

        // Body (Light Yellow)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFFFFAE5)) // Light Yellow
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (data.icon != "")
                    PngIcon(iconForResult(data.icon), data.icon, modifier = Modifier.weight(1/3f))
                Text(text = data.result, modifier = Modifier.weight(2/3f), textAlign = TextAlign.Center)
            }
        }
    }
}

@Composable
fun MoraleResults(modifier: Modifier = Modifier, results: List<MoraleResult>) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp)) // Rounded corners for the whole table
            .border(2.dp, Color.Black, RoundedCornerShape(16.dp)) // Black border around the entire table
            .fillMaxWidth()
            //.background(Color.Green)
    ) {
        // Header Row (Gray)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Gray)
                .padding(8.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Morale",
                style = TextStyle(color = Color.White, fontWeight = FontWeight.Bold, fontSize = 12.sp, textAlign = TextAlign.Center),
                modifier = Modifier
                    .weight(2f)
            )
        }

        val data = results.map { Triple(it.result, it.modifier, iconForResult(it.icon)) }
        // Body (Light Yellow)
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(Color(0xFFFFFAE5)) // Light Yellow
        ) {
            items(data) { item ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = item.first, modifier = Modifier.weight(3/7f), fontSize = 12.sp, textAlign = TextAlign.Center)
                    Text(text = item.second, modifier = Modifier.weight(2/7f), fontSize = 12.sp, textAlign = TextAlign.Center)
//                    Text(text = item.third, modifier = Modifier.weight(1/7f), fontSize = 8.sp)
                    PngIcon(item.third, "", modifier = Modifier.weight(2/7f).fillParentMaxHeight(1/8f))
                }
            }
        }
    }
}

private fun iconForResult(result: String) : Int {
    if (result == "Pass") return com.ica.lb_dice.R.drawable.pass
    if (result == "Fail") return com.ica.lb_dice.R.drawable.fail
    if (result == "Arm") return com.ica.lb_dice.R.drawable.arm_2
    if (result == "Leg") return com.ica.lb_dice.R.drawable.leg_2
    if (result == "Stun") return com.ica.lb_dice.R.drawable.stun
    if (result == "Mortal") return com.ica.lb_dice.R.drawable.tombstone_2
    return 0
}