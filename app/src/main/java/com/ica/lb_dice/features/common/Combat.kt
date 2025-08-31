package com.ica.lb_dice.features.common

data class CombatResult(val odds: String, val result: String)
data class CombatResultAlt(val odds: String, val result1: String, val result2: String, val result3: String, val result4: String, val result5: String)

data class CombatOdds(val odds: String, val results: List<CombatCasualty>)
data class CombatCasualty(val range: DiceRange, val result: String)
data class DiceRange(val min: Int, val max: Int)
