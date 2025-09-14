package com.ica.lb_dice.features.general

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
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
import androidx.navigation.NavController
import com.ica.lb_dice.R
import com.ica.lb_dice.features.common.DiceRollViewModel
import com.ica.lb_dice.ui.Die
import com.ica.lb_dice.ui.PngIcon

@Composable
fun Initiative(modifier: Modifier = Modifier,
               dieImperial: Int = 1,
               onDieImperialClicked: () -> Unit = {},
               dieAllies: Int = 1,
               onDieAlliesClicked: () -> Unit = {}
    ) {
    Row(
        modifier = modifier
            .fillMaxWidth()
        ,verticalAlignment = Alignment.CenterVertically
        ,horizontalArrangement = Arrangement.Center
    ) {
        Die(dieNumber = 1,
            modifier = Modifier
                .padding(1.dp)
                .size(40.dp)
            ,
            onDieClicked = { _ -> onDieImperialClicked() },
            sides = 6,
            dieColor = Color.Blue,
            dotColor = Color.White,
            dieValue = dieImperial
        )
        Spacer(modifier = Modifier.width(8.dp))
        PngIcon(initiativeIcon(dieImperial, dieAllies), "Initiative", modifier = Modifier.size(40.dp))
        Spacer(modifier = Modifier.width(8.dp))
        Die(dieNumber = 2,
            modifier = Modifier
                .padding(1.dp)
                .size(40.dp)
            ,
            onDieClicked = { _ -> onDieAlliesClicked() },
            sides = 6,
            dieColor = Color.Yellow,
            dotColor = Color.Black,
            dieValue = dieAllies
        )
    }
}

private fun initiativeIcon(dieImperial: Int, dieAllies: Int): Int {
    return when {
        dieImperial > dieAllies -> R.drawable.initiative_imperial
        dieImperial < dieAllies -> R.drawable.initiative_allies
        else -> R.drawable.dice_reroll
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewInitiative() {
    var dieImperial by remember { mutableStateOf((1..6).random()) }
    var dieAllies by remember { mutableStateOf((1..6).random()) }

    Initiative(
        modifier = Modifier,
        dieImperial = dieImperial,
        onDieImperialClicked = {
            dieImperial += 1
            if (dieImperial > 6) {
                dieImperial = 1
            }
        },
        dieAllies = dieAllies,
        onDieAlliesClicked = {
            dieAllies += 1
            if (dieAllies > 6) {
                dieAllies = 1
            }
        }
    )
}


