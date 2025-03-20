package com.ica.lb_dice.viewmodels

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

import com.ica.lb_dice.util.DieConfig
import com.ica.lb_dice.util.DiceSetConfig
import com.ica.lb_dice.util.Random
import kotlinx.coroutines.flow.update

class FireCombatViewModel : ViewModel() {
    private val _diceSetConfig = MutableStateFlow<DiceSetConfig?>(null)
    val diceSetConfig = _diceSetConfig.asStateFlow()
    private val _dieValues = MutableStateFlow<List<Int>>(emptyList())
    val dieValues = _dieValues.asStateFlow()

    init {
        createDiceSetConfig()
        setInitialDieValues()
    }
    private fun createDiceSetConfig() {
        val dieConfigs = listOf(
            DieConfig(backgroundColor = Color.Red, dotColor = Color.White),
            DieConfig(backgroundColor = Color.White, dotColor = Color.Black),

            DieConfig(backgroundColor = Color.Blue, dotColor = Color.White),
            DieConfig(backgroundColor = Color.Yellow, dotColor = Color.Black),
            DieConfig(backgroundColor = Color.Green, dotColor = Color.Black),

            DieConfig(backgroundColor = Color.Black, dotColor = Color.White),
            DieConfig(backgroundColor = Color.Black, dotColor = Color.Red)
        )
        _diceSetConfig.update { DiceSetConfig(dieConfigs = dieConfigs) }
    }
    private fun setInitialDieValues() {
        viewModelScope.launch {
            val newDieValues = List(7 ){1}
            _dieValues.value = newDieValues
        }
    }

    fun updateDiceConfig(index: Int, newDieConfig: DieConfig) {
        _diceSetConfig.update {
            if (it != null) {
                val newDieConfigs = it.dieConfigs.toMutableList().apply {
                    this[index] = newDieConfig
                }
                it.copy(dieConfigs = newDieConfigs)
            } else {
                it
            }
        }
    }

    private fun generateRandomNumbers() {
        viewModelScope.launch {
            println("FireCombatViewModel: generate random numbers")
            val random = Random()

            // Generate 7 random numbers from 1 to 6
            val newNumbers = List(7) { random.RandomDie6() }

            // print the numbers
            println("Random numbers: $newNumbers")

            _dieValues.value = newNumbers
        }
    }

    suspend fun onFabClicked() {
        generateRandomNumbers()
    }
}