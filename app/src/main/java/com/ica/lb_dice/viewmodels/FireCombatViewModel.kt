package com.ica.lb_dice.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

import com.ica.lb_dice.util.Random

class FireCombatViewModel : ViewModel() {
    private val _randomNumbers = MutableStateFlow<List<Int>>(emptyList())
    val randomNumbers = _randomNumbers.asStateFlow()

    private fun generateRandomNumbers() {
        viewModelScope.launch {
            val random = Random()

            // Generate 7 random numbers from 1 to 6
            val newNumbers = List(7) { random.RandomDie6() }
            _randomNumbers.value = newNumbers
        }
    }

    suspend fun onFabClicked() {
        generateRandomNumbers()
    }
}