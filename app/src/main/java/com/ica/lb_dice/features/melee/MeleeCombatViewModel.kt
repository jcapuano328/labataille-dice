package com.ica.lb_dice.features.melee

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

import com.ica.lb_dice.util.DiceBase6
import com.ica.lb_dice.ui.DieConfig
import com.ica.lb_dice.util.MathUtils
import com.ica.lb_dice.features.common.LeaderCasualtyService
import com.ica.lb_dice.features.fire.DiceSet
import com.ica.lb_dice.features.morale.MoraleService
import com.ica.lb_dice.features.common.CombatResult
import com.ica.lb_dice.features.common.LeaderCasualtyResult
import com.ica.lb_dice.features.common.MoraleResult

data class MeleeCombatResultsSet(
    val attackerPreMeleeMoraleDice: Int,
    val attackerPreMeleeMoraleResults: List<MoraleResult>,
    val defenderPreMeleeMoraleDice: Int,
    val defenderPreMeleeMoraleResults: List<MoraleResult>,

    val attackerMeleeStrength: String,
    val defenderMeleeStrength: String,
    val meleeOdds: String,

    val meleeDice: Int,
    val meleeResults: List<CombatResult>,
    val leaderCasualtyDice: Int,
    val leaderCasualtyDurationDice: Int,
    val leaderCasualtyResults: LeaderCasualtyResult,
    val moraleDice: Int,
    val moraleResults: List<MoraleResult>
)

class MeleeCombatViewModel : ViewModel() {
    private val _diceSetAttackerPreMeleeMorale = MutableStateFlow(DiceSet(emptyList(), MutableStateFlow(emptyList())))
    val diceSetAttackerPreMeleeMorale = _diceSetAttackerPreMeleeMorale.asStateFlow()

    private val _diceSetDefenderPreMeleeMorale = MutableStateFlow(DiceSet(emptyList(), MutableStateFlow(emptyList())))
    val diceSetDefenderPreMeleeMorale = _diceSetDefenderPreMeleeMorale.asStateFlow()
    
    private val _diceSetMelee = MutableStateFlow(DiceSet(emptyList(), MutableStateFlow(emptyList())))
    val diceSetMelee = _diceSetMelee.asStateFlow()

    private val _diceSetLeader = MutableStateFlow(DiceSet(emptyList(), MutableStateFlow(emptyList())))
    val diceSetLeader = _diceSetLeader.asStateFlow()

    private val _diceSetMorale = MutableStateFlow(DiceSet(emptyList(), MutableStateFlow(emptyList())))
    val diceSetMorale = _diceSetMorale.asStateFlow()

    private val _meleeResultsSet = MutableStateFlow(MeleeCombatResultsSet(0, emptyList(), 0, emptyList(), "1", "1", "", 0, emptyList(), 0, 0, LeaderCasualtyResult("", "", ""), 0, emptyList()))
    val meleeResultsSet = _meleeResultsSet.asStateFlow()

    init {
        createDiceSets()
        setInitialDieValues()
        setInitialState()
        updateResults()
    }

    private fun createDiceSets() {
        _diceSetAttackerPreMeleeMorale.value = DiceSet(listOf(DieConfig(dieColor = Color.Black, dotColor = Color.White), DieConfig(dieColor = Color.Black, dotColor = Color.Red)), MutableStateFlow(emptyList()))
        _diceSetDefenderPreMeleeMorale.value = DiceSet(listOf(DieConfig(dieColor = Color.Black, dotColor = Color.White), DieConfig(dieColor = Color.Black, dotColor = Color.Red)), MutableStateFlow(emptyList()))
        _diceSetMelee.value = DiceSet(listOf(DieConfig(dieColor = Color.Red, dotColor = Color.White), DieConfig(dieColor = Color.White, dotColor = Color.Black)), MutableStateFlow(emptyList()))
        _diceSetLeader.value = DiceSet(listOf(DieConfig(dieColor = Color.Blue, dotColor = Color.White), DieConfig(dieColor = Color.Yellow, dotColor = Color.Black), DieConfig(dieColor = Color.Green, dotColor = Color.Black)), MutableStateFlow(emptyList()))
        _diceSetMorale.value = DiceSet(listOf(DieConfig(dieColor = Color.Black, dotColor = Color.White), DieConfig(dieColor = Color.Black, dotColor = Color.Red)), MutableStateFlow(emptyList()))
    }
    private fun setInitialDieValues() {
        viewModelScope.launch {
            _diceSetAttackerPreMeleeMorale.value.dieValues.value = List(_diceSetMorale.value.dieConfigs.size) { 1 }
            _diceSetDefenderPreMeleeMorale.value.dieValues.value = List(_diceSetMorale.value.dieConfigs.size) { 1 }
            _diceSetMelee.value.dieValues.value = List(_diceSetMelee.value.dieConfigs.size) { 1 }
            _diceSetLeader.value.dieValues.value = List(_diceSetLeader.value.dieConfigs.size) { 1 }
            _diceSetMorale.value.dieValues.value = List(_diceSetMorale.value.dieConfigs.size) { 1 }
        }
    }
    private fun setInitialState() {
        _meleeResultsSet.value = MeleeCombatResultsSet(0, emptyList(), 0, emptyList(), "1", "1", "", 0, emptyList(), 0, 0, LeaderCasualtyResult("", "", ""), 0, emptyList())
    }

    fun incrementAttackerPreMeleeMoraleDie(die: Int) {
        viewModelScope.launch {
            var newValue = _diceSetAttackerPreMeleeMorale.value.dieValues.value[die] + 1
            if (newValue > 6) newValue = 1
            _diceSetAttackerPreMeleeMorale.value.dieValues.value = _diceSetAttackerPreMeleeMorale.value.dieValues.value.toMutableList().also { it[die] = newValue }
            updateResults()
        }
    }
    fun modifyAttackerPreMeleeMoraleDice(value: Int) {
        viewModelScope.launch {
            val newValue = DiceBase6.fromDice(
                _diceSetAttackerPreMeleeMorale.value.dieValues.value[0],
                _diceSetAttackerPreMeleeMorale.value.dieValues.value[1]
            ).plus(value)
            println("MeleeCombatViewModel.modifyAttackerPreMeleeMoraleDice() New Value: ${newValue.toDiceBase6()}")
            _diceSetAttackerPreMeleeMorale.value.dieValues.value = newValue.decompose().toList()
            updateResults()
        }
    }

    fun incrementDefenderPreMeleeMoraleDie(die: Int) {
        viewModelScope.launch {
            var newValue = _diceSetDefenderPreMeleeMorale.value.dieValues.value[die] + 1
            if (newValue > 6) newValue = 1
            _diceSetDefenderPreMeleeMorale.value.dieValues.value = _diceSetDefenderPreMeleeMorale.value.dieValues.value.toMutableList().also { it[die] = newValue }
            updateResults()
        }
    }
    fun modifyDefenderPreMeleeMoraleDice(value: Int) {
        viewModelScope.launch {
            val newValue = DiceBase6.fromDice(
                _diceSetDefenderPreMeleeMorale.value.dieValues.value[0],
                _diceSetDefenderPreMeleeMorale.value.dieValues.value[1]
            ).plus(value)
            println("MeleeCombatViewModel.modifyDefenderPreMeleeMoraleDice() New Value: ${newValue.toDiceBase6()}")
            _diceSetDefenderPreMeleeMorale.value.dieValues.value = newValue.decompose().toList()
            updateResults()
        }
    }

    fun incrementAttackerMeleeStrength() {
        val newValue = (_meleeResultsSet.value.attackerMeleeStrength.toFloatOrNull() ?: 1f) + 1
        _meleeResultsSet.value = _meleeResultsSet.value.copy(attackerMeleeStrength = newValue.toString())
        updateResults()
    }
    fun decrementAttackerMeleeStrength() {
        val newValue = (_meleeResultsSet.value.attackerMeleeStrength.toFloatOrNull() ?: 1f) - 1
        _meleeResultsSet.value = _meleeResultsSet.value.copy(attackerMeleeStrength = newValue.toString())
        updateResults()
    }
    fun setAttackerMeleeStrength(value: String) {
        _meleeResultsSet.value = _meleeResultsSet.value.copy(attackerMeleeStrength = value)
        updateResults()
    }

    fun incrementDefenderMeleeStrength() {
        val newValue = (_meleeResultsSet.value.defenderMeleeStrength.toFloatOrNull() ?: 1f) + 1
        _meleeResultsSet.value = _meleeResultsSet.value.copy(defenderMeleeStrength = newValue.toString())
        updateResults()
    }
    fun decrementDefenderMeleeStrength() {
        val newValue = (_meleeResultsSet.value.defenderMeleeStrength.toFloatOrNull() ?: 1f) - 1
        _meleeResultsSet.value = _meleeResultsSet.value.copy(defenderMeleeStrength = newValue.toString())
        updateResults()
    }
    fun setDefenderMeleeStrength(value: String) {
        _meleeResultsSet.value = _meleeResultsSet.value.copy(defenderMeleeStrength = value)
        updateResults()
    }

    fun incrementMeleeDie(die: Int) {
        viewModelScope.launch {
            var newValue = _diceSetMelee.value.dieValues.value[die] + 1
            if (newValue > 6) newValue = 1
            _diceSetMelee.value.dieValues.value = _diceSetMelee.value.dieValues.value.toMutableList().also { it[die] = newValue }
            updateResults()
        }
    }
    fun modifyMeleeDice(value: Int) {
        viewModelScope.launch {
            val newValue = DiceBase6.fromDice(
                _diceSetMelee.value.dieValues.value[0],
                _diceSetMelee.value.dieValues.value[1]
            ).plus(value)
            println("MeleeCombatViewModel.modifyFireDice() New Value: ${newValue.toDiceBase6()}")
            _diceSetMelee.value.dieValues.value = newValue.decompose().toList()
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
            println("MeleeCombatViewModel.modifyMoraleDice() New Value: ${newValue.toDiceBase6()}")
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
        println("MeleeCombatViewModel: roll dice:")
        val random = MathUtils()

        // Generate random numbers from 1 to 6
        _diceSetAttackerPreMeleeMorale.value.dieValues.value = List(_diceSetAttackerPreMeleeMorale.value.dieConfigs.size) { random.randomDie6() }
        _diceSetDefenderPreMeleeMorale.value.dieValues.value = List(_diceSetDefenderPreMeleeMorale.value.dieConfigs.size) { random.randomDie6() }
        _diceSetMelee.value.dieValues.value = List(_diceSetMelee.value.dieConfigs.size) { random.randomDie6() }
        _diceSetLeader.value.dieValues.value = List(_diceSetLeader.value.dieConfigs.size) { random.randomDie6() }
        _diceSetMorale.value.dieValues.value = List(_diceSetMorale.value.dieConfigs.size) { random.randomDie6() }
        println("Set Attacker Pre-Melee: ${_diceSetAttackerPreMeleeMorale.value.dieValues.value}")
        println("Set Defender Pre-Melee: ${_diceSetDefenderPreMeleeMorale.value.dieValues.value}")
        println("Set Melee: ${_diceSetMelee.value.dieValues.value}")
        println("Set Leader: ${_diceSetLeader.value.dieValues.value}")
        println("Set Morale: ${_diceSetMorale.value.dieValues.value}")
    }

    private fun updateResults() {
        updateAttackerPreMeleeMoraleResults()
        updateDefenderPreMeleeMoraleResults()
        updateMeleeOdds()
        updateMeleeCombatResults()
        updateLeaderCasualtyResults()
        updateMoraleResults()
    }

    private fun updateAttackerPreMeleeMoraleResults() {
        println("MeleeCombatViewModel: updateAttackerPreMeleeMoraleResults:")
        val moraleDice = DiceBase6.fromDice(_diceSetAttackerPreMeleeMorale.value.dieValues.value[0], _diceSetAttackerPreMeleeMorale.value.dieValues.value[1]).toDiceBase6()
        val moraleResults = MoraleService().check(moraleDice)
        _meleeResultsSet.value = _meleeResultsSet.value.copy(attackerPreMeleeMoraleResults = moraleResults.map { MoraleResult(it.result, it.modifier, it.icon) })
    }

    private fun updateDefenderPreMeleeMoraleResults() {
        println("MeleeCombatViewModel: updateDefenderPreMeleeMoraleResults:")
        val moraleDice = DiceBase6.fromDice(_diceSetDefenderPreMeleeMorale.value.dieValues.value[0], _diceSetDefenderPreMeleeMorale.value.dieValues.value[1]).toDiceBase6()
        val moraleResults = MoraleService().check(moraleDice)
        _meleeResultsSet.value = _meleeResultsSet.value.copy(defenderPreMeleeMoraleResults = moraleResults.map { MoraleResult(it.result, it.modifier, it.icon) })
    }

    private fun updateMeleeOdds() {
        var odds = 1f
        val attackerStr = _meleeResultsSet.value.attackerMeleeStrength.toFloatOrNull() ?: 1f
        val defenderStr = _meleeResultsSet.value.defenderMeleeStrength.toFloatOrNull() ?: 1f
        val attackerAdvantage = attackerStr >= defenderStr

        if (attackerAdvantage) {
            odds = attackerStr / defenderStr
        } else {
            odds = defenderStr / attackerStr
        }
        println("MeleeCombatViewModel: updateMeleeOdds: raw: $odds ($attackerAdvantage)")

        // Round the odds to the nearest 0.5, rounding up
        val roundedOdds = MathUtils().roundFloatToNearestHalf(odds, !attackerAdvantage)
        println("MeleeCombatViewModel: updateMeleeOdds: rounded: $roundedOdds ($attackerAdvantage)")
        var oddsString = ""
        if (attackerAdvantage) {
            oddsString = "$roundedOdds:1"
        } else {
            oddsString = "1:$roundedOdds"
        }
        println("MeleeCombatViewModel: updateMeleeOdds: odds: $oddsString")
        _meleeResultsSet.value = _meleeResultsSet.value.copy(meleeOdds = oddsString)
    }

    private fun updateMeleeCombatResults() {
        println("MeleeCombatViewModel: updateFireCombatResults:")
        val meleeDice = DiceBase6.fromDice(_diceSetMelee.value.dieValues.value[0], _diceSetMelee.value.dieValues.value[1]).toDiceBase6()
        val meleeResults = MeleeCombatService().resolve(meleeDice)
        _meleeResultsSet.value = _meleeResultsSet.value.copy(meleeResults = meleeResults.map { CombatResult(it.odds, it.result) })
    }

    private fun updateLeaderCasualtyResults() {
        println("MeleeCombatViewModel: updateLeaderCasualtyResults:")
        val meleeDice = DiceBase6.fromDice(_diceSetMelee.value.dieValues.value[0], _diceSetMelee.value.dieValues.value[1]).toDiceBase6()
        val leaderCasualtyDie = _diceSetLeader.value.dieValues.value[0]
        val leaderCasualtyDurationDie1 = _diceSetLeader.value.dieValues.value[1]
        val leaderCasualtyDurationDie2 = _diceSetLeader.value.dieValues.value[2]
        val leaderCasualtyResults = LeaderCasualtyService().resolveMeleeCombat(meleeDice, leaderCasualtyDie, leaderCasualtyDurationDie1, leaderCasualtyDurationDie2)
        _meleeResultsSet.value = _meleeResultsSet.value.copy(leaderCasualtyResults = LeaderCasualtyResult(leaderCasualtyResults.side, leaderCasualtyResults.result, leaderCasualtyResults.icon))
    }

    private fun updateMoraleResults() {
        println("MeleeCombatViewModel: updateMoraleResults:")
        val moraleDice = DiceBase6.fromDice(_diceSetMorale.value.dieValues.value[0], _diceSetMorale.value.dieValues.value[1]).toDiceBase6()
        val moraleResults = MoraleService().check(moraleDice)
        _meleeResultsSet.value = _meleeResultsSet.value.copy(moraleResults = moraleResults.map { MoraleResult(it.result, it.modifier, it.icon) })
    }
}