package com.ica.lb_dice.features.fire

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

import com.ica.lb_dice.util.DieConfig
import com.ica.lb_dice.util.MathUtils

import com.ica.lb_dice.features.common.LeaderCasualtyService

import com.ica.lb_dice.features.morale.MoraleService
import com.ica.lb_dice.util.DiceBase6
import com.ica.lb_dice.features.common.CombatResult
import com.ica.lb_dice.features.common.LeaderCasualtyResult
import com.ica.lb_dice.features.common.MoraleResult


data class DiceSet(
    val dieConfigs: List<DieConfig>,
    val dieValues: MutableStateFlow<List<Int>>
)

data class FireCombatResultsSet(
    val fireDice: Int,
    val fireResults: List<CombatResult>,
    val leaderCasualtyDice: Int,
    val leaderCasualtyDurationDice: Int,
    val leaderCasualtyResults: LeaderCasualtyResult,
    val moraleDice: Int,
    val moraleResults: List<MoraleResult>
)

class FireCombatViewModel : ViewModel() {
    private val _diceSetFire = MutableStateFlow(DiceSet(emptyList(), MutableStateFlow(emptyList())))
    val diceSetFire = _diceSetFire.asStateFlow()

    private val _diceSetLeader = MutableStateFlow(DiceSet(emptyList(), MutableStateFlow(emptyList())))
    val diceSetLeader = _diceSetLeader.asStateFlow()

    private val _diceSetMorale = MutableStateFlow(DiceSet(emptyList(), MutableStateFlow(emptyList())))
    val diceSetMorale = _diceSetMorale.asStateFlow()

    private val _resultsSet = MutableStateFlow(FireCombatResultsSet(0, emptyList(), 0, 0, LeaderCasualtyResult("", "", ""), 0, emptyList()))
    val resultsSet = _resultsSet.asStateFlow()

    init {
        createDiceSets()
        setInitialDieValues()
        updateResults()
    }

    private fun createDiceSets() {
        _diceSetFire.value = DiceSet(listOf(DieConfig(backgroundColor = Color.Red, dotColor = Color.White), DieConfig(backgroundColor = Color.White, dotColor = Color.Black)), MutableStateFlow(emptyList()))
        _diceSetLeader.value = DiceSet(listOf(DieConfig(backgroundColor = Color.Blue, dotColor = Color.White), DieConfig(backgroundColor = Color.Yellow, dotColor = Color.Black), DieConfig(backgroundColor = Color.Green, dotColor = Color.Black)), MutableStateFlow(emptyList()))
        _diceSetMorale.value = DiceSet(listOf(DieConfig(backgroundColor = Color.Black, dotColor = Color.White), DieConfig(backgroundColor = Color.Black, dotColor = Color.Red)), MutableStateFlow(emptyList()))
    }
    private fun setInitialDieValues() {
        viewModelScope.launch {
            _diceSetFire.value.dieValues.value = List(_diceSetFire.value.dieConfigs.size) { 1 }
            _diceSetLeader.value.dieValues.value = List(_diceSetLeader.value.dieConfigs.size) { 1 }
            _diceSetMorale.value.dieValues.value = List(_diceSetMorale.value.dieConfigs.size) { 1 }
        }
    }

    fun incrementFireDie(die: Int) {
        viewModelScope.launch {
            var newValue = _diceSetFire.value.dieValues.value[die] + 1
            if (newValue > 6) newValue = 1
            _diceSetFire.value.dieValues.value = _diceSetFire.value.dieValues.value.toMutableList().also { it[die] = newValue }
            updateResults()
        }
    }
    fun modifyFireDice(value: Int) {
        viewModelScope.launch {
            val newValue = DiceBase6.fromDice(
                _diceSetFire.value.dieValues.value[0],
                _diceSetFire.value.dieValues.value[1]
            ).plus(value)
            println("FireCombatViewModel.modifyFireDice() New Value: ${newValue.toDiceBase6()}")
            _diceSetFire.value.dieValues.value = newValue.decompose().toList()
            updateResults()
        }
    }

    fun incrementLeaderDie(die: Int) {
        viewModelScope.launch {
            var newValue = _diceSetLeader.value.dieValues.value[die] + 1
            if (newValue > 6) newValue = 1
            _diceSetLeader.value.dieValues.value = _diceSetLeader.value.dieValues.value.toMutableList().also { it[die] = newValue }
            updateResults()
        }
    }

    fun incrementMoraleDie(die: Int) {
        viewModelScope.launch {
            var newValue = _diceSetMorale.value.dieValues.value[die] + 1
            if (newValue > 6) newValue = 1
            _diceSetMorale.value.dieValues.value = _diceSetMorale.value.dieValues.value.toMutableList().also { it[die] = newValue }
            updateResults()
        }
    }

    fun modifyMoraleDice(value: Int) {
        viewModelScope.launch {
            val newValue = DiceBase6.fromDice(
                _diceSetMorale.value.dieValues.value[0],
                _diceSetMorale.value.dieValues.value[1]
            ).plus(value)
            println("FireCombatViewModel.modifyMoraleDice() New Value: ${newValue.toDiceBase6()}")
            _diceSetMorale.value.dieValues.value = newValue.decompose().toList()
            updateResults()
        }
    }

    suspend fun onFabClicked() {
        viewModelScope.launch {
            rollDice()
            updateResults()
        }
    }

    private fun rollDice() {
        println("FireCombatViewModel: roll dice:")
        val random = MathUtils()

        // Generate random numbers from 1 to 6
        _diceSetFire.value.dieValues.value = List(_diceSetFire.value.dieConfigs.size) { random.randomDie6() }
        _diceSetLeader.value.dieValues.value = List(_diceSetLeader.value.dieConfigs.size) { random.randomDie6() }
        _diceSetMorale.value.dieValues.value = List(_diceSetMorale.value.dieConfigs.size) { random.randomDie6() }
        println("Set Fire: ${_diceSetFire.value.dieValues.value}")
        println("Set Leader: ${_diceSetLeader.value.dieValues.value}")
        println("Set Morale: ${_diceSetMorale.value.dieValues.value}")
    }

    private fun updateResults() {
        updateFireCombatResults()
        updateLeaderCasualtyResults()
        updateMoraleResults()
    }

    private fun updateFireCombatResults() {
        println("FireCombatViewModel: updateFireCombatResults:")
        val fireDice = DiceBase6.fromDice(_diceSetFire.value.dieValues.value[0], _diceSetFire.value.dieValues.value[1]).toDiceBase6()
        val fireResults = FireCombatService().resolve(fireDice)
        _resultsSet.value = _resultsSet.value.copy(fireResults = fireResults.map { CombatResult(it.odds, it.result) })
    }

    private fun updateLeaderCasualtyResults() {
        println("FireCombatViewModel: updateLeaderCasualtyResults:")
        val fireDice = DiceBase6.fromDice(_diceSetFire.value.dieValues.value[0], _diceSetFire.value.dieValues.value[1]).toDiceBase6()
        val leaderCasualtyDie = _diceSetLeader.value.dieValues.value[0]
        val leaderCasualtyDurationDie1 = _diceSetLeader.value.dieValues.value[1]
        val leaderCasualtyDurationDie2 = _diceSetLeader.value.dieValues.value[2]
        val leaderCasualtyResults = LeaderCasualtyService().resolveFireCombat(fireDice, leaderCasualtyDie, leaderCasualtyDurationDie1, leaderCasualtyDurationDie2)
        _resultsSet.value = _resultsSet.value.copy(leaderCasualtyResults = LeaderCasualtyResult(leaderCasualtyResults.side, leaderCasualtyResults.result, leaderCasualtyResults.icon))
    }

    private fun updateMoraleResults() {
        println("FireCombatViewModel: updateMoraleResults:")
        val moraleDice = DiceBase6.fromDice(_diceSetMorale.value.dieValues.value[0], _diceSetMorale.value.dieValues.value[1]).toDiceBase6()
        val moraleResults = MoraleService().check(moraleDice)
        _resultsSet.value = _resultsSet.value.copy(moraleResults = moraleResults.map { MoraleResult(it.result, it.modifier, it.icon) })

    }
}