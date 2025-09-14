package com.ica.lb_dice.features.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ica.lb_dice.R
import com.ica.lb_dice.features.help.BulletPoint
import com.ica.lb_dice.features.help.HelpSection
import com.ica.lb_dice.ui.PngIcon

@Composable
fun LbDiceAboutContent() {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
        .fillMaxSize()
        .padding(2.dp)) {
        LazyColumn(
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth()
        ) {
            // Section 1
            item {
                LbDiceIntroductionSection()
            }
            // Section 2
            item {
                LbDiceFireResolutionSection()
            }
            // Section 3
            item {
                LbDiceMeleeResolutionSection()
            }
            // Section 4
            item {
                LbDiceMoraleResolutionSection()
            }
            // Section 5
            item {
                LbDiceGeneralSection()
            }
        }
    }
}

@Composable
fun LbDiceIntroductionSection() {
    HelpSection(title = "Introduction") {
        Column(Modifier.padding(16.dp)) {

            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape) // Clips the Box to a circle
                    .background(MaterialTheme.colorScheme.primary), // Sets the background color
                contentAlignment = Alignment.Center // Centers the icon within the Box
            ) {
                PngIcon(R.drawable.logo, "LOGO", modifier = Modifier.size(40.dp))
            }

            Card(modifier = Modifier.padding(2.dp)) {
                Text(
                    "This is a dice roller assistant for the La Bataille series of tabletop games. The simple functions are intended to keep player focus on the game table, not the screen.",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(8.dp)
                )
                Text(
                    "The typical mass of charts and tables for La Bataille are not represented in this app. Only the standard Fire and Melee combat results tables are directly supported.",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(8.dp)
                )
                Text(
                    "The player is expected to use the assistant to roll dice and then consult the appropriate charts in the object world: quick reference and back to the table.",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(8.dp)
                )

                Text(
                    "There is no concept of the Rules version in use: play whichever you prefer. However, terminology had to be selected so there is a necessary nod to rules in the melee section. Sorry.",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(8.dp)
                )

            }
            Spacer(Modifier.height(16.dp))
        }
    }
}

@Composable
fun LbDiceFireResolutionSection() {
    HelpSection(title = "Fire Resolution") {
        Column(Modifier.padding(16.dp)) {

            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape) // Clips the Box to a circle
                    .background(MaterialTheme.colorScheme.secondary), // Sets the background color
                contentAlignment = Alignment.Center // Centers the icon within the Box
            ) {
                PngIcon(R.drawable.fire, "Fire", modifier = Modifier.size(40.dp))
            }
            BulletPoint(text = "Roll the dice and consult the quick reference fire combat results.")
            BulletPoint(text = "Adjust the dice for applicable modifiers.")
            BulletPoint(text = "Estimate the odds or calculate the precise odds if the dice call for it. (Many shots are not effective!)")
            BulletPoint(text = "Consult the morale and leader casualty results when appropriate.")

            Spacer(Modifier.height(16.dp))
        }
    }
}

@Composable
fun LbDiceMeleeResolutionSection() {
    HelpSection(title = "Melee Resolution") {
        Column(Modifier.padding(16.dp)) {

            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape) // Clips the Box to a circle
                    .background(MaterialTheme.colorScheme.secondary), // Sets the background color
                contentAlignment = Alignment.Center // Centers the icon within the Box
            ) {
                PngIcon(R.drawable.melee, "Melee", modifier = Modifier.size(40.dp))
            }
            BulletPoint(text = "Roll the dice and consult the quick reference morale results. (Pre-Melee? Roll to Close? Whatever.)")
            BulletPoint(text = "Consult the quick reference melee combat results if the assault goes in.")
            BulletPoint(text = "Adjust the morale and combat dice for applicable modifiers.")
            BulletPoint(text = "Estimate the odds or calculate the precise odds if the dice call for it.")
            BulletPoint(text = "Consult the morale and leader casualty results when appropriate.")
            Spacer(Modifier.height(16.dp))
        }
    }
}

@Composable
fun LbDiceMoraleResolutionSection() {
    HelpSection(title = "Morale Resolution") {
        Column(Modifier.padding(16.dp)) {

            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape) // Clips the Box to a circle
                    .background(MaterialTheme.colorScheme.secondary), // Sets the background color
                contentAlignment = Alignment.Center // Centers the icon within the Box
            ) {
                PngIcon(R.drawable.morale, "Morale", modifier = Modifier.size(40.dp))
            }
            BulletPoint(text = "Roll the dice and consult the quick reference morale results.")
            BulletPoint(text = "Adjust the dice for applicable modifiers.")
            Spacer(Modifier.height(16.dp))
        }
    }
}

@Composable
fun LbDiceGeneralSection() {
    HelpSection(title = "General Tasks") {
        Column(Modifier.padding(16.dp)) {

            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape) // Clips the Box to a circle
                    .background(MaterialTheme.colorScheme.secondary), // Sets the background color
                contentAlignment = Alignment.Center // Centers the icon within the Box
            ) {
                PngIcon(R.drawable.dice, "Dice", modifier = Modifier.size(40.dp))
            }
            BulletPoint(text = "Sets of dice are provided for miscellaneous tasks:")
            Column(Modifier.padding(start = 16.dp)) {
                BulletPoint(text = "Square formation")
                BulletPoint(text = "Artillery limber")
                BulletPoint(text = "Stand before Charge")
                BulletPoint(text = "Etc.")
            }
            BulletPoint(text = "Roll the dice, consult the appropriate object world charts and apply the results.")
            BulletPoint(text = "Adjust the dice for applicable modifiers.")
            Spacer(Modifier.height(16.dp))
        }
    }
}



@Preview(showBackground = true)
@Composable
fun PreviewLbDiceAboutContent() {
    LbDiceAboutContent(
    )
}
