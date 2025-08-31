package com.ica.lb_dice.features.fire

import com.ica.lb_dice.features.common.CombatCasualty
import com.ica.lb_dice.features.common.CombatOdds
import com.ica.lb_dice.features.common.CombatResult
import com.ica.lb_dice.features.common.CombatResultAlt
import com.ica.lb_dice.features.common.DiceRange

class FireCombatService {

    fun resolve(dice: Int) : List<CombatResult> {
        return FireCombatTable.getResults(dice)
    }

    fun resolveAlt(dice: Int) : List<CombatResultAlt> {
        return FireCombatTable.getResultsAlt(dice)
    }
}

private class FireCombatTable {

    companion object {
        private val fireCombatTable = listOf(
            CombatOdds("1:3", listOf(
                CombatCasualty(DiceRange(65, 66), "1")
            )),
            CombatOdds("1:2.5", listOf(
                CombatCasualty(DiceRange(64, 66), "1")
            )),
            CombatOdds("1:2", listOf(
                CombatCasualty(DiceRange(62, 66), "1")
            )),
            CombatOdds("1:1.5", listOf(
                CombatCasualty(DiceRange(55, 66), "1")
            )),
            CombatOdds("1:1", listOf(
                CombatCasualty(DiceRange(52, 66), "1")
            )),
            CombatOdds("1.5:1", listOf(
                CombatCasualty(DiceRange(42, 66), "1")
            )),
            CombatOdds("2:1", listOf(
                CombatCasualty(DiceRange(33, 66), "1")
            )),
            CombatOdds("2.5:1", listOf(
                CombatCasualty(DiceRange(26, 66), "1")
            )),
            CombatOdds("3:1", listOf(
                CombatCasualty(DiceRange(22, 66), "1")
            )),
            CombatOdds("4:1", listOf(
                CombatCasualty(DiceRange(13, 53), "1"),
                CombatCasualty(DiceRange(54, 66), "2")
            )),
            CombatOdds("5:1", listOf(
                CombatCasualty(DiceRange(11, 44), "1"),
                CombatCasualty(DiceRange(45, 66), "2")
            )),
            CombatOdds("6:1", listOf(
                CombatCasualty(DiceRange(11, 32), "1"),
                CombatCasualty(DiceRange(33, 61), "2"),
                CombatCasualty(DiceRange(33, 66), "3")

            )),
            CombatOdds("7:1", listOf(
                CombatCasualty(DiceRange(11, 22), "1"),
                CombatCasualty(DiceRange(23, 51), "2"),
                CombatCasualty(DiceRange(52, 66), "3")
            )),
            CombatOdds("8:1", listOf(
                CombatCasualty(DiceRange(11, 14), "1"),
                CombatCasualty(DiceRange(15, 44), "2"),
                CombatCasualty(DiceRange(45, 65), "3"),
                CombatCasualty(DiceRange(66, 66), "4")
            )),
            CombatOdds("9:1", listOf(
                CombatCasualty(DiceRange(11, 41), "2"),
                CombatCasualty(DiceRange(42, 62), "3"),
                CombatCasualty(DiceRange(63, 66), "4")
            )),
            CombatOdds("10:1", listOf(
                CombatCasualty(DiceRange(11, 25), "2"),
                CombatCasualty(DiceRange(26, 54), "3"),
                CombatCasualty(DiceRange(55, 64), "4"),
                CombatCasualty(DiceRange(65, 66), "5")
            ))
        )

        /**
         * Get results from the fire combat table.
         *
         * @param dice The dice roll value.
         * @return A list of FireCombatResults that match the specified conditions.
         */
        fun getResults(dice: Int): List<CombatResult> {
            val results = mutableListOf<CombatResult>()
            for (odds in fireCombatTable) {
                for (casualty in odds.results) {
                    if (dice >= casualty.range.min && dice <= casualty.range.max) {
                        results.add(CombatResult(odds.odds, casualty.result))
                        break
                    }
                }
                if(results.any{ it.odds == odds.odds}){
                    continue
                }
            }
            return results
        }

        fun getResultsAlt(dice: Int): List<CombatResultAlt> {
            val results = mutableListOf<CombatResultAlt>()
            for (odds in fireCombatTable) {
                val casualties = odds.results.filter { dice >= it.range.min && dice <= it.range.max }
                if (casualties.isNotEmpty()) {
                    var result = CombatResultAlt(odds.odds, "", "", "", "","")
                    for (casualty in casualties) {
                        when (casualty.result) {
                            "1" -> result = result.copy(result1 = "${casualty.range.min}-${casualty.range.max}")
                            "2" -> result = result.copy(result2 = "${casualty.range.min}-${casualty.range.max}")
                            "3" -> result = result.copy(result3 = "${casualty.range.min}-${casualty.range.max}")
                            "4" -> result = result.copy(result4 = "${casualty.range.min}-${casualty.range.max}")
                            "5" -> result = result.copy(result5 = "${casualty.range.min}-${casualty.range.max}")
                        }
                    }
                    results.add(result)
                }
            }

            return results
        }
    }
}