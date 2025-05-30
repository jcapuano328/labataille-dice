package com.ica.lb_dice.screens.fire

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ica.lb_dice.screens.results.CombatResults
import com.ica.lb_dice.screens.results.CombatResultsAlt
import com.ica.lb_dice.screens.results.LeaderCasualtyResults
import com.ica.lb_dice.screens.results.MoraleResults
import com.ica.lb_dice.screens.results.MoraleResultsAlt
import com.ica.lb_dice.services.FireCombatService
import com.ica.lb_dice.services.LeaderCasualtyService
import com.ica.lb_dice.services.MoraleService
import com.ica.lb_dice.viewmodels.FireCombatResultsSet
import com.ica.lb_dice.viewmodels.results.CombatResult
import com.ica.lb_dice.viewmodels.results.LeaderCasualtyResult
import com.ica.lb_dice.viewmodels.results.MoraleResult

@Composable
fun FireCombatResults(resultsSet: FireCombatResultsSet) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
        ,horizontalArrangement = Arrangement.spacedBy(4.dp)
        ,verticalAlignment = Alignment.Top
    ) {
        CombatResults(Modifier.weight(1f), "If Odds are...", resultsSet.fireResults)

        Column(
            modifier = Modifier
                .weight(1f)
                .wrapContentHeight()
            ,horizontalAlignment = Alignment.CenterHorizontally
            ,verticalArrangement = Arrangement.Top
        ) {
            MoraleResults(modifier = Modifier.weight(1f), "If Morale is...", results = resultsSet.moraleResults)
            LeaderCasualtyResults(Modifier.height(100.dp).padding(top = 4.dp), resultsSet.leaderCasualtyResults)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFireCombatResults() {
    val fireCombatService = FireCombatService()
    val leaderCasualtyService = LeaderCasualtyService()
    val moraleService = MoraleService()

    val lcr = leaderCasualtyService.resolveFireCombat(53, 1, 2, 4)
    val leaderCasualtyResult = LeaderCasualtyResult(lcr.side, lcr.result, lcr.icon)

    val results = FireCombatResultsSet(
        fireDice = 52,
        fireResults = fireCombatService.resolve(53).map { CombatResult(it.odds, it.result) },
        leaderCasualtyDice = 3,
        leaderCasualtyDurationDice = 8,
        leaderCasualtyResults = leaderCasualtyResult,
        moraleDice = 44,
        moraleResults = moraleService.check(44).map { MoraleResult(it.result, it.modifier, it.icon) }
    )

    FireCombatResults(
        resultsSet = results
    )
}