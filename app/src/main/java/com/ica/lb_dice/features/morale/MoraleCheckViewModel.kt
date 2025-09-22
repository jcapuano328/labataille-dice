package com.ica.lb_dice.features.morale

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ica.lb_dice.features.common.LeaderCasualtyResult
import com.ica.lb_dice.features.fire.DiceSet
import com.ica.lb_dice.features.fire.FireCombatResultsSet
import com.ica.lb_dice.util.DiceBase6
import com.ica.lb_dice.ui.DieConfig
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

import com.ica.lb_dice.util.MathUtils
import com.ica.lb_dice.features.common.MoraleResult

class MoraleCheckViewModel : ViewModel() {
    private val _diceSetMorale = MutableStateFlow(DiceSet(emptyList(), MutableStateFlow(emptyList())))
    val diceSetMorale = _diceSetMorale.asStateFlow()

    private val _resultsSet = MutableStateFlow(FireCombatResultsSet("", "", "", 0, emptyList(), 0, 0, LeaderCasualtyResult("", "", ""), 0, emptyList()))
    val resultsSet = _resultsSet.asStateFlow()

    init {
        createDiceSets()
        setInitialDieValues()
        updateResults()
    }

    private fun createDiceSets() {
        _diceSetMorale.value = DiceSet(listOf(DieConfig(dieColor = Color.Black, dotColor = Color.White), DieConfig(dieColor = Color.Black, dotColor = Color.Red)), MutableStateFlow(emptyList()))
    }
    private fun setInitialDieValues() {
        viewModelScope.launch {
            _diceSetMorale.value.dieValues.value = List(_diceSetMorale.value.dieConfigs.size) { 1 }
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
        val random = MathUtils()

        // Generate random numbers from 1 to 6
        _diceSetMorale.value.dieValues.value = List(_diceSetMorale.value.dieConfigs.size) { random.randomDie6() }
    }

    private fun updateResults() {
        updateMoraleResults()
    }

    private fun updateMoraleResults() {
        val moraleDice = DiceBase6.fromDice(_diceSetMorale.value.dieValues.value[0], _diceSetMorale.value.dieValues.value[1]).toDiceBase6()
        val moraleResults = MoraleService().check(moraleDice)
        _resultsSet.value = _resultsSet.value.copy(moraleResults = moraleResults.map { MoraleResult(it.result, it.modifier, it.icon) })
    }
}