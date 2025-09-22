package com.ica.lb_dice.features.fire

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ica.lb_dice.ui.DiceSet
import com.ica.lb_dice.ui.ModifierButtonsRow
import com.ica.lb_dice.ui.DieConfig
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun FireCombatDice(
    modifier: Modifier = Modifier,
    diceSetFire: DiceSet, onFireDieIncrement: (die: Int) -> Unit, onFireDiceModify: (value: Int) -> Unit,
    diceSetLeader: DiceSet, onLeaderDieIncrement: (die: Int) -> Unit,
    diceSetMorale: DiceSet, onMoraleDieIncrement: (die: Int) -> Unit, onMoraleDiceModify: (value: Int) -> Unit
)
{
    Column(modifier = modifier
        .fillMaxWidth()
        .wrapContentHeight()
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
            ,horizontalArrangement = Arrangement.spacedBy(8.dp)
            ,verticalAlignment = Alignment.Top
        ) {
            DiceSet(
                dieConfigs = diceSetFire.dieConfigs,
                dieValues = diceSetFire.dieValues.collectAsState().value,
                modifier = Modifier.weight(2/7f),
                onDieClicked = { dieNumber ->                    
                    onFireDieIncrement(dieNumber)
                }
            )
            DiceSet(
                dieConfigs = diceSetLeader.dieConfigs,
                dieValues = diceSetLeader.dieValues.collectAsState().value,
                modifier = Modifier.weight(3/7f),
                onDieClicked = { dieNumber ->
                    onLeaderDieIncrement(dieNumber)
                }
            )
            DiceSet(
                dieConfigs = diceSetMorale.dieConfigs,
                dieValues = diceSetMorale.dieValues.collectAsState().value,
                modifier = Modifier.weight(2/7f),
                onDieClicked = { dieNumber ->
                    onMoraleDieIncrement(dieNumber)
                }
            )
        }
        ModifierButtonsRow(
            label = "Fire",
            foregroundColor = Color.White,
            backgroundColor = Color.Blue,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
            ,
            onModifierButtonClicked = { value ->
                onFireDiceModify(value)
            }
        )
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

@Preview(showBackground = true)
@Composable
fun PreviewFireCombatDice() {
    val dieValuesCombat = MutableStateFlow(List(2) { 6 })
    val dieConfigsCombat = listOf(
        DieConfig(dieColor = Color.Red, dotColor = Color.White),
        DieConfig(dieColor = Color.White, dotColor = Color.Black)
    )
    val diceSetCombat = DiceSet(dieConfigsCombat, dieValuesCombat)

    val dieValuesLeaderCasualty = MutableStateFlow(List(3) { 6 })
    val dieConfigsLeaderCasualty = listOf(
        DieConfig(dieColor = Color.Blue, dotColor = Color.White),
        DieConfig(dieColor = Color.Yellow, dotColor = Color.Black),
        DieConfig(dieColor = Color.Green, dotColor = Color.Black),
    )
    val diceSetLeaderCasualty = DiceSet(dieConfigsLeaderCasualty, dieValuesLeaderCasualty)

    val dieValuesMorale = MutableStateFlow(List(2) { 6 })
    val dieConfigsMorale = listOf(
        DieConfig(dieColor = Color.Black, dotColor = Color.Red),
        DieConfig(dieColor = Color.Black, dotColor = Color.White)
    )
    val diceSetMorale = DiceSet(dieConfigsMorale, dieValuesMorale)

    FireCombatDice(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
        ,
        diceSetFire = diceSetCombat,
        onFireDieIncrement = { die ->
            println("Fire Dice Changed")
        },
        onFireDiceModify = { value ->
            println("Fire Dice Modified")
        },
        diceSetLeader = diceSetLeaderCasualty,
        onLeaderDieIncrement = { die ->
            println("Leader Dice Changed")
        },
        diceSetMorale = diceSetMorale,
        onMoraleDieIncrement = { die ->
            println("Morale Dice Changed")
        },
        onMoraleDiceModify = { value ->
            println("Morale Dice Modified")
        }
    )
}