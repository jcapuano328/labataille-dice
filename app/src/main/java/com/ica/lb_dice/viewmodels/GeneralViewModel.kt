package com.ica.lb_dice.viewmodels

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ica.lb_dice.util.DiceBase6
import com.ica.lb_dice.util.DieConfig
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

import com.ica.lb_dice.util.MathUtils

class GeneralViewModel : ViewModel() {
    private val _diceSet1 = MutableStateFlow(DiceSet(emptyList(), MutableStateFlow(emptyList())))
    val diceSet1 = _diceSet1.asStateFlow()

    private val _diceSet2 = MutableStateFlow(DiceSet(emptyList(), MutableStateFlow(emptyList())))
    val diceSet2 = _diceSet2.asStateFlow()

    private val _diceSet3 = MutableStateFlow(DiceSet(emptyList(), MutableStateFlow(emptyList())))
    val diceSet3 = _diceSet3.asStateFlow()

    init {
        createDiceSets()
        setInitialDieValues()
    }

    private fun createDiceSets() {
        _diceSet1.value = DiceSet(listOf(DieConfig(backgroundColor = Color.Red, dotColor = Color.White), DieConfig(backgroundColor = Color.White, dotColor = Color.Black)), MutableStateFlow(emptyList()))
        _diceSet2.value = DiceSet(listOf(DieConfig(backgroundColor = Color.Black, dotColor = Color.White), DieConfig(backgroundColor = Color.Black, dotColor = Color.Red)), MutableStateFlow(emptyList()))
        _diceSet3.value = DiceSet(listOf(DieConfig(backgroundColor = Color.Blue, dotColor = Color.White)), MutableStateFlow(emptyList()))
    }
    private fun setInitialDieValues() {
        viewModelScope.launch {
            diceSet1.value.dieValues.value = List(diceSet1.value.dieConfigs.size) { 1 }
            diceSet2.value.dieValues.value = List(diceSet1.value.dieConfigs.size) { 1 }
            diceSet3.value.dieValues.value = List(diceSet1.value.dieConfigs.size) { 1 }
        }
    }

    fun incrementDieSet1(die: Int) {
        viewModelScope.launch {
            var newValue = _diceSet1.value.dieValues.value[die] + 1
            if (newValue > 6) newValue = 1
            _diceSet1.value.dieValues.value = _diceSet1.value.dieValues.value.toMutableList().also { it[die] = newValue }
        }
    }
    fun modifyDiceSet1(value: Int) {
        viewModelScope.launch {
            val newValue = DiceBase6.fromDice(
                _diceSet1.value.dieValues.value[0],
                _diceSet1.value.dieValues.value[1]
            ).plus(value)
            _diceSet1.value.dieValues.value = newValue.decompose().toList()
        }
    }

    fun incrementDieSet2(die: Int) {
        viewModelScope.launch {
            var newValue = _diceSet2.value.dieValues.value[die] + 1
            if (newValue > 6) newValue = 1
            _diceSet2.value.dieValues.value = _diceSet2.value.dieValues.value.toMutableList().also { it[die] = newValue }
        }
    }
    fun modifyDiceSet2(value: Int) {
        viewModelScope.launch {
            val newValue = DiceBase6.fromDice(
                _diceSet2.value.dieValues.value[0],
                _diceSet2.value.dieValues.value[1]
            ).plus(value)
            _diceSet2.value.dieValues.value = newValue.decompose().toList()
        }
    }

    fun incrementDieSet3(die: Int) {
        viewModelScope.launch {
            var newValue = _diceSet3.value.dieValues.value[die] + 1
            if (newValue > 6) newValue = 1
            _diceSet3.value.dieValues.value = _diceSet3.value.dieValues.value.toMutableList().also { it[die] = newValue }
        }
    }
    fun modifyDiceSet3(value: Int) {
        viewModelScope.launch {
            var newValue = _diceSet3.value.dieValues.value[0] + value
            if (newValue > 6) newValue = 1
            if (newValue < 1) newValue = 6
            _diceSet3.value.dieValues.value = _diceSet3.value.dieValues.value.toMutableList().also { it[0] = newValue }
        }
    }


    suspend fun onFabClicked() {
        viewModelScope.launch {
            rollDice()
        }
    }

    private fun rollDice() {
        println("GeneralViewModel: roll dice:")
        val random = MathUtils()

        // Generate random numbers from 1 to 6
        _diceSet1.value.dieValues.value = List(_diceSet1.value.dieConfigs.size) { random.randomDie6() }
        _diceSet2.value.dieValues.value = List(_diceSet2.value.dieConfigs.size) { random.randomDie6() }
        _diceSet3.value.dieValues.value = List(_diceSet3.value.dieConfigs.size) { random.randomDie6() }

        println("Set 1: ${_diceSet1.value.dieValues.value}")
        println("Set 2: ${_diceSet2.value.dieValues.value}")
        println("Set 3: ${_diceSet3.value.dieValues.value}")
    }
    
}