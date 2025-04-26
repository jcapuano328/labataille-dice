package com.ica.lb_dice.screens.melee

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ica.lb_dice.ui.DiceSet
import com.ica.lb_dice.ui.ModifierButtonsRow
import com.ica.lb_dice.viewmodels.DiceSet
import com.ica.lb_dice.viewmodels.MeleeCombatResultsSet
import com.ica.lb_dice.screens.results.CombatResults
import com.ica.lb_dice.screens.results.LeaderCasualtyResults
import com.ica.lb_dice.screens.results.MoraleResults
import com.ica.lb_dice.services.LeaderCasualtyService
import com.ica.lb_dice.services.MeleeCombatService
import com.ica.lb_dice.services.MoraleService
import com.ica.lb_dice.util.DieConfig
import com.ica.lb_dice.viewmodels.CombatResult
import com.ica.lb_dice.viewmodels.LeaderCasualtyResult
import com.ica.lb_dice.viewmodels.MoraleResult
import kotlinx.coroutines.flow.MutableStateFlow


@Composable
fun MeleeCombatSection(modifier: Modifier = Modifier,
                       meleeDiceSet: DiceSet, onMeleeDieIncrement: (die: Int) -> Unit, onMeleeDiceModify: (value: Int) -> Unit,
                       diceSetLeader: DiceSet, onLeaderDieIncrement: (die: Int) -> Unit,
                       diceSetMorale: DiceSet, onMoraleDieIncrement: (die: Int) -> Unit,
                       meleeCombatResults: MeleeCombatResultsSet
) {
    Column(modifier = modifier) {
        /*
        // Header Row (Gray)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(Color.Gray)
                .padding(4.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Melee",
                style = TextStyle(color = Color.White, fontWeight = FontWeight.Bold, fontSize = 12.sp, textAlign = TextAlign.Center),
                //modifier = Modifier.weight(2f)
            )
        }
        */
        MeleeCombatDice(
            modifier = modifier,
            diceSetMelee = meleeDiceSet,
            onMeleeDieIncrement = onMeleeDieIncrement,
            onMeleeDiceModify = onMeleeDiceModify,
            diceSetLeader = diceSetLeader,
            onLeaderDieIncrement = onLeaderDieIncrement,
            diceSetMorale = diceSetMorale,
            onMoraleDieIncrement = onMoraleDieIncrement
        )
        Spacer(modifier = Modifier.height(4.dp))
        MeleeCombatResults(
            modifier.weight(1f),
            meleeCombatResults
        )
    }
}
@Composable
fun MeleeCombatDice(modifier: Modifier = Modifier,
                    diceSetMelee: DiceSet, onMeleeDieIncrement: (die: Int) -> Unit, onMeleeDiceModify: (value: Int) -> Unit,
                    diceSetLeader: DiceSet, onLeaderDieIncrement: (die: Int) -> Unit,
                    diceSetMorale: DiceSet, onMoraleDieIncrement: (die: Int) -> Unit
) {
    Column(
        modifier = modifier
            .wrapContentHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
            //.height(200.dp)
            //.padding(8.dp)
            ,horizontalArrangement = Arrangement.spacedBy(8.dp)
            ,verticalAlignment = Alignment.Top
        ) {
            DiceSet(
                dieConfigs = diceSetMelee.dieConfigs,
                dieValues = diceSetMelee.dieValues.collectAsState().value,
                modifier = Modifier.weight(2/7f),
                //modifier = Modifier.fillMaxWidth(),
                onDieClicked = { dieNumber ->
                    println("Fire Die $dieNumber clicked")
                    onMeleeDieIncrement(dieNumber)
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
        ModifierButtonsRow(
            label = "Melee",
            foregroundColor = Color.White,
            backgroundColor = Color.Blue,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
            //.weight(1 / 10f)
            ,
            onModifierButtonClicked = { value ->
                println("Melee Modifier clicked: $value")
                onMeleeDiceModify(value)
            }
        )
    }
}
@Composable
fun MeleeCombatResults(modifier: Modifier = Modifier, resultsSet: MeleeCombatResultsSet) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            //.fillMaxHeight()
            //.weight(8/10f)
        ,horizontalArrangement = Arrangement.spacedBy(4.dp)
        ,verticalAlignment = Alignment.Top
    ) {
        CombatResults(Modifier.weight(1f), resultsSet.meleeResults)
        LeaderCasualtyResults(Modifier.weight(1f), resultsSet.leaderCasualtyResults)
        MoraleResults(modifier = Modifier.weight(1f), results = resultsSet.moraleResults)
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewMeleeCombat() {
    val dieValuesCombat = MutableStateFlow(List(2) { 6 })
    val dieConfigsCombat = listOf(
        DieConfig(backgroundColor = Color.Red, dotColor = Color.White),
        DieConfig(backgroundColor = Color.White, dotColor = Color.Black)
    )
    val diceSetCombat = DiceSet(dieConfigsCombat, dieValuesCombat)

    val dieValuesLeaderCasualty = MutableStateFlow(List(3) { 6 })
    val dieConfigsLeaderCasualty = listOf(
        DieConfig(backgroundColor = Color.Blue, dotColor = Color.White),
        DieConfig(backgroundColor = Color.Yellow, dotColor = Color.Black),
        DieConfig(backgroundColor = Color.Green, dotColor = Color.Black),
    )
    val diceSetLeaderCasualty = DiceSet(dieConfigsLeaderCasualty, dieValuesLeaderCasualty)

    val dieValuesMorale = MutableStateFlow(List(2) { 6 })
    val dieConfigsMorale = listOf(
        DieConfig(backgroundColor = Color.Black, dotColor = Color.Red),
        DieConfig(backgroundColor = Color.Black, dotColor = Color.White)
    )
    val diceSetMorale = DiceSet(dieConfigsMorale, dieValuesMorale)

    val moraleService = MoraleService()
    val meleeCombatService = MeleeCombatService()
    val leaderCasualtyService = LeaderCasualtyService()
    val leaderCasualtyResult = leaderCasualtyService.resolveMeleeCombat(52, 3, 1, 4)

    val resultsSet = MeleeCombatResultsSet(
        attackerPreMeleeMoraleDice = 33,
        attackerPreMeleeMoraleResults = moraleService.check(33).map { MoraleResult(it.result, it.modifier, it.icon) },
        defenderPreMeleeMoraleDice = 44,
        defenderPreMeleeMoraleResults = moraleService.check(44).map { MoraleResult(it.result, it.modifier, it.icon) },
        attackerMeleeStrength = 2f,
        defenderMeleeStrength = 1f,
        meleeOdds = "2.0/1",
        meleeDice = 52,
        meleeResults = meleeCombatService.resolve(52).map { CombatResult(it.odds, it.result) },
        leaderCasualtyDice = 3,
        leaderCasualtyDurationDice = 4,
        leaderCasualtyResults = LeaderCasualtyResult(leaderCasualtyResult.result, leaderCasualtyResult.icon),
        moraleDice = 44,
        moraleResults = moraleService.check(44).map { MoraleResult(it.result, it.modifier, it.icon) }
    )

    val dieValuesMoraleAttacker = MutableStateFlow(List(2) { 6 })
    val dieConfigsMoraleAttacker = listOf(
        DieConfig(backgroundColor = Color.Black, dotColor = Color.Red),
        DieConfig(backgroundColor = Color.Black, dotColor = Color.White)
    )
    val diceSetMoraleAttacker = DiceSet(dieConfigsMoraleAttacker, dieValuesMoraleAttacker)

    val dieValuesMoraleDefender = MutableStateFlow(List(2) { 6 })
    val dieConfigsMoraleDefender = listOf(
        DieConfig(backgroundColor = Color.Black, dotColor = Color.Red),
        DieConfig(backgroundColor = Color.Black, dotColor = Color.White)
    )
    val diceSetMoraleDefender = DiceSet(dieConfigsMoraleDefender, dieValuesMoraleDefender)

    //val moraleService = MoraleService()
    val attackerPreMeleeMoraleResults = moraleService.check(33)
    val defenderPreMeleeMoraleResults = moraleService.check(21)

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize().padding(2.dp)) {
        MeleeCombatOddsSection(
            modifier = Modifier
                .fillMaxWidth()
            ,
            attackerMeleeStrength = 1f,
            onAttackerMeleeStrengthChange = {},
            defenderMeleeStrength = 1f,
            onDefenderMeleeStrengthChange = {},
            meleeOdds = "1:1"
        )
        PreMeleeMoraleCheckSection(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
            ,
            diceSetAttackerPreMeleeMorale = diceSetMoraleAttacker,
            onPreMeleeMoraleAttackerDieIncrement = {},
            onPreMeleeMoraleAttackerDiceModify = {},
            diceSetDefenderPreMeleeMorale = diceSetMoraleDefender,
            onPreMeleeMoraleDefenderDieIncrement = {},
            onPreMeleeMoraleDefenderDiceModify = {},
            attackerPreMeleeMoraleResults = attackerPreMeleeMoraleResults.map { MoraleResult(it.result, it.modifier, it.icon) },
            defenderPreMeleeMoraleResults = defenderPreMeleeMoraleResults.map { MoraleResult(it.result, it.modifier, it.icon) }
        )
        MeleeCombatSection(
            modifier = Modifier
                .fillMaxWidth()
            ,
            meleeDiceSet = diceSetCombat,
            onMeleeDieIncrement = { die ->
                println("Melee Dice Changed")
            },
            onMeleeDiceModify = { value ->
                println("Melee Dice Modified")
            },
            diceSetLeader = diceSetLeaderCasualty,
            onLeaderDieIncrement = { die ->
                println("Leader Dice Changed")
            },
            diceSetMorale = diceSetMorale,
            onMoraleDieIncrement = { die ->
                println("Morale Dice Changed")
            },
            meleeCombatResults = resultsSet
        )

    }
}
