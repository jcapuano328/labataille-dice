package com.ica.lb_dice.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import com.ica.lb_dice.util.DieConfig

@Composable
fun DiceSet(
    dieConfigs: List<DieConfig>,
    dieValues: List<Int>,
    onDieClicked: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier
        .fillMaxWidth()
        .wrapContentHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DiceRow(dieConfigs = dieConfigs, dieValues = dieValues, onDieClicked = onDieClicked, modifier = modifier)
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