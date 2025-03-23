package com.ica.lb_dice.services

class FireCombatService {

    fun resolve(dice: Int) : List<FireCombatResult> {
        return FireCombatTable.getResults(dice)
    }
}
data class FireCombatResult(val odds: String, val result: String)

private data class FireCombatOdds(val odds: String, val results: List<FireCombatCasualty>)
private data class FireCombatCasualty(val range: DiceRange, val result: String)
private data class DiceRange(val min: Int, val max: Int)

private class FireCombatTable {

    companion object {
        private val fireCombatTable = listOf(
            FireCombatOdds("1:3", listOf(
                FireCombatCasualty(DiceRange(65, 66), "1")
            )),
            FireCombatOdds("1:2.5", listOf(
                FireCombatCasualty(DiceRange(64, 66), "1")
            )),
            FireCombatOdds("1:2", listOf(
                FireCombatCasualty(DiceRange(62, 66), "1")
            )),
            FireCombatOdds("1:1.5", listOf(
                FireCombatCasualty(DiceRange(55, 66), "1")
            )),
            FireCombatOdds("1:1", listOf(
                FireCombatCasualty(DiceRange(52, 66), "1")
            )),
            FireCombatOdds("1.5:1", listOf(
                FireCombatCasualty(DiceRange(42, 66), "1")
            )),
            FireCombatOdds("2:1", listOf(
                FireCombatCasualty(DiceRange(33, 66), "1")
            )),
            FireCombatOdds("2.5:1", listOf(
                FireCombatCasualty(DiceRange(26, 66), "1")
            )),
            FireCombatOdds("3:1", listOf(
                FireCombatCasualty(DiceRange(22, 66), "1")
            )),
            FireCombatOdds("4:1", listOf(
                FireCombatCasualty(DiceRange(13, 53), "1"),
                FireCombatCasualty(DiceRange(54, 66), "2")
            )),
            FireCombatOdds("5:1", listOf(
                FireCombatCasualty(DiceRange(11, 44), "1"),
                FireCombatCasualty(DiceRange(45, 66), "2")
            )),
            FireCombatOdds("6:1", listOf(
                FireCombatCasualty(DiceRange(11, 32), "1"),
                FireCombatCasualty(DiceRange(33, 61), "2"),
                FireCombatCasualty(DiceRange(33, 66), "3")

            )),
            FireCombatOdds("7:1", listOf(
                FireCombatCasualty(DiceRange(11, 22), "1"),
                FireCombatCasualty(DiceRange(23, 51), "2"),
                FireCombatCasualty(DiceRange(52, 66), "3")
            )),
            FireCombatOdds("8:1", listOf(
                FireCombatCasualty(DiceRange(11, 14), "1"),
                FireCombatCasualty(DiceRange(15, 44), "2"),
                FireCombatCasualty(DiceRange(45, 65), "3"),
                FireCombatCasualty(DiceRange(66, 66), "4")
            )),
            FireCombatOdds("9:1", listOf(
                FireCombatCasualty(DiceRange(11, 41), "2"),
                FireCombatCasualty(DiceRange(42, 62), "3"),
                FireCombatCasualty(DiceRange(63, 66), "4")
            )),
            FireCombatOdds("10:1", listOf(
                FireCombatCasualty(DiceRange(11, 25), "2"),
                FireCombatCasualty(DiceRange(26, 54), "3"),
                FireCombatCasualty(DiceRange(55, 64), "4"),
                FireCombatCasualty(DiceRange(65, 66), "5")
            ))
        )

        /**
         * Get results from the fire combat table.
         *
         * @param dice The dice roll value.
         * @return A list of FireCombatResults that match the specified conditions.
         */
        fun getResults(dice: Int): List<FireCombatResult> {
            val results = mutableListOf<FireCombatResult>()
            for (odds in fireCombatTable) {
                for (casualty in odds.results) {
                    if (dice >= casualty.range.min && dice <= casualty.range.max) {
                        results.add(FireCombatResult(odds.odds, casualty.result))
                        break
                    }
                }
                if(results.any{ it.odds == odds.odds}){
                    continue
                }
            }
            return results
        }
    }
}