package com.ica.lb_dice.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ica.lb_dice.util.DieConfig

@Composable
fun DiceSet(
    dieConfigs: List<DieConfig>,
    dieValues: List<Int>,
    modifierButtonsVisible: Boolean,
    onDieClicked: (Int) -> Unit,
    onModifierButtonClicked: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier
        .fillMaxWidth()
        .wrapContentHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DiceRow(dieConfigs = dieConfigs, dieValues = dieValues, onDieClicked = onDieClicked, modifier = modifier)
        if (modifierButtonsVisible) {
            ModifierButtonsRow(onModifierButtonClicked = onModifierButtonClicked)
        }
    }
}

@Composable
fun DiceRow(
    dieConfigs: List<DieConfig>,
    dieValues: List<Int>,
    onDieClicked: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            //.background(Color.Gray)
            ,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        for (i in dieConfigs.indices) {
            //println("Die $i has value ${dieValues[i]}")
            Die(dieNumber = i,
                modifier = //modifier.size(40.dp),
                Modifier
                    .weight(1f)
                    .aspectRatio(1f),
                onDieClicked = onDieClicked,
                backgroundColor = dieConfigs[i].backgroundColor,
                dotColor = dieConfigs[i].dotColor,
                dieValue = dieValues[i]
            )
        }
    }
}

@Composable
fun ModifierButtonsRow(
    onModifierButtonClicked: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        for (i in 1..6) {
            ModifierButton(value = i, onModifierButtonClicked = { onModifierButtonClicked(i) })
        }
    }
}

@Composable
fun ModifierButton(
    value: Int,
    onModifierButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(onClick = onModifierButtonClicked, modifier = modifier) {
        Text("+$value")
    }
}