package com.ica.lb_dice.services

class LeaderCasualtyService {
    fun resolveFireCombat(combatDice: Int, casualtyDie: Int, durationDie1: Int, durationDie2: Int): LeaderCasualtyResult {
        val result = resolve(combatDice, casualtyDie, durationDie1, durationDie2, 65, 66)
        return result.copy(side = "")
    }
    fun resolveMeleeCombat(combatDice: Int, casualtyDie: Int, durationDie1: Int, durationDie2: Int): LeaderCasualtyResult {
        var result = resolve(combatDice, casualtyDie, durationDie1, durationDie2, 11, 12)
        if (result.result == "") {
            result = resolve(combatDice, casualtyDie, durationDie1, durationDie2, 64, 66)
        }
        return result
    }

    private fun resolve(combatDice: Int, casualtyDie: Int, durationDie1: Int, durationDie2: Int, rangeLow: Int, rangeHigh: Int): LeaderCasualtyResult {
        if (combatDice < rangeLow || combatDice > rangeHigh) {
            return LeaderCasualtyResult("","", "")
        }
        var side = ""
        if (combatDice <= 12) {
            side = "A"
        } else {
            side = "D"
        }

        when (casualtyDie) {
            1 -> {
                return LeaderCasualtyResult(side, "Head", "Mortal")
            }
            2 -> {
                return LeaderCasualtyResult(side, "Chest", "Mortal")
            }
            3 -> {
                val duration = durationDie1 + durationDie2
                return LeaderCasualtyResult(side, "$duration hours", "Leg")
            }
            4 -> {
                return LeaderCasualtyResult(side, "$durationDie1 hours", "Arm")
            }
            5 -> {
                return LeaderCasualtyResult(side,"$durationDie1 turns", "Stun")
            }
            else -> {
                return LeaderCasualtyResult(side,"No time", "Flesh")
            }
        }
    }
}

data class LeaderCasualtyResult(val side: String, val result: String, val icon: String)