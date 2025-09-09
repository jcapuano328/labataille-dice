package com.ica.lb_dice.features.help.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LabelImportant
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ica.lb_dice.features.help.BulletPoint
import com.ica.lb_dice.ui.Die
import com.ica.lb_dice.ui.PngIcon
import com.ica.lb_dice.ui.ResultImage

@Composable
fun LeaderCasualtyResultsHelpSection(modifier: Modifier = Modifier, melee: Boolean = false) {
    Column(modifier.padding(16.dp)) {
        // leader casualty dice
        Row() {
            Die(dieNumber = 1,
                modifier = Modifier
                    .padding(1.dp)
                    .size(40.dp)
                ,
                onDieClicked = { },
                backgroundColor = Color.Blue,
                dotColor = Color.White,
                dieValue = 1
            )
            Spacer(modifier = Modifier.width(2.dp))
            Die(dieNumber = 2,
                modifier = Modifier
                    .padding(1.dp)
                    .size(40.dp)
                ,
                onDieClicked = { },
                backgroundColor = Color.Yellow,
                dotColor = Color.Black,
                dieValue = 1
            )
            Spacer(modifier = Modifier.width(2.dp))
            Die(dieNumber = 3,
                modifier = Modifier
                    .padding(1.dp)
                    .size(40.dp)
                ,
                onDieClicked = { },
                backgroundColor = Color.Green,
                dotColor = Color.Black,
                dieValue = 1
            )
        }
        BulletPoint(text = "The leader dice are used to determine the leader casualty results, in the event the combat causes a leader casualty.")
        BulletPoint(text = "Tap a die to increase its value by 1.")
        BulletPoint(text = "The first die is the casualty die.")
        BulletPoint(text = "The remaining two dice are the duration dice.")
        Spacer(Modifier.height(12.dp))

        BulletPoint(text = "1: Head. Mortal.")
        PngIcon(ResultImage.iconForResult("Head"), "Head", modifier = Modifier.size(40.dp))
        Spacer(Modifier.height(4.dp))
        BulletPoint(text = "2: Chest. Mortal.")
        PngIcon(ResultImage.iconForResult("Chest"), "Chest", modifier = Modifier.size(40.dp))
        Spacer(Modifier.height(4.dp))
        BulletPoint(text = "3: Leg. 2 dice hours out of battle.")
        PngIcon(ResultImage.iconForResult("Leg"), "Leg", modifier = Modifier.size(40.dp))
        Spacer(Modifier.height(4.dp))
        BulletPoint(text = "4: Arm. 1 die hours out of battle.")
        PngIcon(ResultImage.iconForResult("Arm"), "Arm", modifier = Modifier.size(40.dp))
        Spacer(Modifier.height(4.dp))
        BulletPoint(text = "5: Stun. 1 die turns out of battle.")
        PngIcon(ResultImage.iconForResult("Stun"), "Stun", modifier = Modifier.size(40.dp))
        Spacer(Modifier.height(4.dp))
        BulletPoint(text = "6: Flesh. No effect.")
        PngIcon(ResultImage.iconForResult("Flesh"), "Flesh", modifier = Modifier.size(40.dp))
        Spacer(Modifier.height(4.dp))

        if (melee) {
            BulletPoint(text = "The victim of the casualty is indicated by a leading icon.")
            Column(modifier.padding(16.dp)) {
                BulletPoint(text = "Attacker.")
                Icon(
                    Icons.Default.LabelImportant,
                    contentDescription = "Attacker",
                    tint = Color.Red
                )
                Spacer(Modifier.height(4.dp))
                BulletPoint(text = "Defender.")
                Icon(Icons.Default.Shield, contentDescription = "Defender", tint = Color.Blue)
            }
            Spacer(Modifier.height(4.dp))
        }
        Spacer(Modifier.height(12.dp))

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLeaderCasualtyResultsHelpSection() {
    LeaderCasualtyResultsHelpSection(
        melee = true
    )
}
