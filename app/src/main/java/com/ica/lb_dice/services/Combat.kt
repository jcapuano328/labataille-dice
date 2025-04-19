package com.ica.lb_dice.services

data class CombatResult(val odds: String, val result: String)

data class CombatOdds(val odds: String, val results: List<CombatCasualty>)
data class CombatCasualty(val range: DiceRange, val result: String)
data class DiceRange(val min: Int, val max: Int)
