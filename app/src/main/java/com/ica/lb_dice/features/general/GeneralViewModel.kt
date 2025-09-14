package com.ica.lb_dice.features.general

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ica.lb_dice.R
import com.ica.lb_dice.features.fire.DiceSet
import com.ica.lb_dice.util.DiceBase6
import com.ica.lb_dice.ui.DieConfig
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

    private val _diceSet4 = MutableStateFlow(DiceSet(emptyList(), MutableStateFlow(emptyList())))
    val diceSet4 = _diceSet4.asStateFlow()

    private val _diceSet5 = MutableStateFlow(DiceSet(emptyList(), MutableStateFlow(emptyList())))
    val diceSet5 = _diceSet5.asStateFlow()

    init {
        createDiceSets()
        setInitialDieValues()
    }

    private fun createDiceSets() {
        _diceSet1.value = DiceSet(listOf(DieConfig(dieColor = Color.Red, dotColor = Color.White), DieConfig(dieColor = Color.White, dotColor = Color.Black)), MutableStateFlow(emptyList()))
        _diceSet2.value = DiceSet(listOf(DieConfig(dieColor = Color.Black, dotColor = Color.White), DieConfig(dieColor = Color.Black, dotColor = Color.Red)), MutableStateFlow(emptyList()))
        _diceSet3.value = DiceSet(listOf(DieConfig(dieColor = Color.Blue, dotColor = Color.White)), MutableStateFlow(emptyList()))
        _diceSet4.value = DiceSet(listOf(
            DieConfig(dieColor = Color.Yellow, dotColor = Color.Black),
            DieConfig(dieColor = Color.Green, dotColor = Color.Black),
            DieConfig(dieColor = Color(0xFF800080), dotColor = Color.White), // purple
            DieConfig(dieColor = Color(0xFF808000), dotColor = Color.White), // olive
            DieConfig(dieColor = Color(0xFFA0522D), dotColor = Color.White), // sienna
            DieConfig(dieColor = Color.Gray, dotColor = Color.White),
            DieConfig(dieColor = Color.Blue, dotColor = Color.White),
            DieConfig(dieColor = Color.Magenta, dotColor = Color.White),
        ), MutableStateFlow(emptyList()))

        _diceSet5.value = DiceSet(listOf(
            //DieConfig(dieColor = Color.White, sides = 0)
            DieConfig(dieColor = Color.Blue, dotColor = Color.White),
            DieConfig(dieColor = Color.Yellow, dotColor = Color.Black)
        ), MutableStateFlow(emptyList()))
    }
    private fun setInitialDieValues() {
        viewModelScope.launch {
            diceSet1.value.dieValues.value = List(diceSet1.value.dieConfigs.size) { 1 }
            diceSet2.value.dieValues.value = List(diceSet2.value.dieConfigs.size) { 1 }
            diceSet3.value.dieValues.value = List(diceSet3.value.dieConfigs.size) { 1 }
            diceSet4.value.dieValues.value = List(diceSet4.value.dieConfigs.size) { 1 }
            diceSet5.value.dieValues.value = List(diceSet5.value.dieConfigs.size) { 1 }
            //diceSet5.value.dieValues.value = List(diceSet5.value.dieConfigs.size) { firstInitiativeFlag() }
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

    fun incrementDieSet4(die: Int) {
        viewModelScope.launch {
            var newValue = _diceSet4.value.dieValues.value[die] + 1
            if (newValue > 6) newValue = 1
            _diceSet4.value.dieValues.value = _diceSet4.value.dieValues.value.toMutableList().also { it[die] = newValue }
        }
    }

    fun incrementDieSet5(die: Int) {
        viewModelScope.launch {
            //var newValue = nextInitiativeFlag(_diceSet5.value.dieValues.value[die])
            var newValue = _diceSet5.value.dieValues.value[die] + 1
            if (newValue > 6) newValue = 1
            _diceSet5.value.dieValues.value = _diceSet5.value.dieValues.value.toMutableList().also { it[die] = newValue }
        }
    }

    suspend fun onFabClicked() {
        viewModelScope.launch {
            rollDice()
        }
    }

    private fun rollDice() {
        val random = MathUtils()

        // Generate random numbers from 1 to 6
        _diceSet1.value.dieValues.value = List(_diceSet1.value.dieConfigs.size) { random.randomDie6() }
        _diceSet2.value.dieValues.value = List(_diceSet2.value.dieConfigs.size) { random.randomDie6() }
        _diceSet3.value.dieValues.value = List(_diceSet3.value.dieConfigs.size) { random.randomDie6() }
        _diceSet4.value.dieValues.value = List(_diceSet4.value.dieConfigs.size) { random.randomDie6() }
        _diceSet5.value.dieValues.value = List(_diceSet5.value.dieConfigs.size) { random.randomDie6() }
        //_diceSet5.value.dieValues.value = List(_diceSet5.value.dieConfigs.size) { randomInitiativeFlag(random) }
    }

    /*
    private fun firstFlag() : Int {
        return R.drawable.flag_austrian
    }

    private fun nextFlag(value: Int) : Int {
        return when (value) {
            R.drawable.flag_austrian -> R.drawable.flag_british
            R.drawable.flag_british -> R.drawable.flag_french
            R.drawable.flag_french -> R.drawable.flag_prussian
            R.drawable.flag_prussian -> R.drawable.flag_russian
            R.drawable.flag_russian -> R.drawable.flag_spanish
            else -> R.drawable.flag_austrian
        }
    }

    private fun randomFlag(random: MathUtils = MathUtils()) : Int {
        val v = random.randomDie6()
        return when (v) {
            1 -> R.drawable.flag_austrian
            2 -> R.drawable.flag_british
            3 -> R.drawable.flag_french
            4 -> R.drawable.flag_prussian
            5 -> R.drawable.flag_russian
            else -> R.drawable.flag_spanish
        }
    }

    private fun firstInitiativeFlag() : Int {
        return R.drawable.flag_imperial
    }

    private fun nextInitiativeFlag(value: Int) : Int {
        return when (value) {
            R.drawable.flag_imperial -> R.drawable.flag_allies
            else -> R.drawable.flag_imperial
        }
    }

    private fun randomInitiativeFlag(random: MathUtils = MathUtils()) : Int {
        val v = random.randomDie6()
        return when (v) {
            1 -> R.drawable.flag_imperial
            2 -> R.drawable.flag_allies
            3 -> R.drawable.flag_imperial
            4 -> R.drawable.flag_allies
            5 -> R.drawable.flag_imperial
            else -> R.drawable.flag_allies
        }
    }
    */
}