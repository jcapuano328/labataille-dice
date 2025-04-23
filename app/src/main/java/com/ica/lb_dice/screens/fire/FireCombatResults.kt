package com.ica.lb_dice.screens.fire

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ica.lb_dice.screens.results.CombatResults
import com.ica.lb_dice.screens.results.LeaderCasualtyResults
import com.ica.lb_dice.screens.results.MoraleResults
import com.ica.lb_dice.viewmodels.FireCombatResultsSet

@Composable
fun FireCombatResults(resultsSet: FireCombatResultsSet) {
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
                .weight(8/10f)
            ,horizontalArrangement = Arrangement.spacedBy(4.dp)
            ,verticalAlignment = Alignment.Top
        ) {
            CombatResults(Modifier.weight(1f), resultsSet.fireResults)
            LeaderCasualtyResults(Modifier.weight(1f), resultsSet.leaderCasualtyResults)
            MoraleResults(modifier = Modifier.weight(1f), results = resultsSet.moraleResults)
        }
    }
}