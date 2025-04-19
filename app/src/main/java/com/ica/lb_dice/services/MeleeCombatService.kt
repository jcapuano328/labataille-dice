package com.ica.lb_dice.services

class MeleeCombatService {

    fun resolve(dice: Int) : List<CombatResult> {
        return MeleeCombatTable.getResults(dice)
    }
}
private class MeleeCombatTable {

    companion object {
        private val meleeCombatTable = listOf(
            CombatOdds("1:2", listOf(
                CombatCasualty(DiceRange(11, 14), "AR"),
                CombatCasualty(DiceRange(15, 34), "AD"),
                CombatCasualty(DiceRange(35, 51), "NE"),
                CombatCasualty(DiceRange(52, 52), "*0/0"),
                CombatCasualty(DiceRange(53, 53), "1/1"),
                CombatCasualty(DiceRange(54, 54), "1/2*"),
                CombatCasualty(DiceRange(55, 55), "0/1"),
                CombatCasualty(DiceRange(56, 56), "1/0*"),
                CombatCasualty(DiceRange(61, 61), "0/2"),
                CombatCasualty(DiceRange(62, 62), "2/1"),
                CombatCasualty(DiceRange(63, 63), "0/0"),
                CombatCasualty(DiceRange(64, 64), "2/2"),
                CombatCasualty(DiceRange(65, 66), "DD")
            )),
            CombatOdds("1:1", listOf(
                CombatCasualty(DiceRange(11, 15), "AD"),
                CombatCasualty(DiceRange(16, 41), "NE"),
                CombatCasualty(DiceRange(42, 42), "*2/0"),
                CombatCasualty(DiceRange(43, 43), "2/1"),
                CombatCasualty(DiceRange(44, 44), "2/1"),
                CombatCasualty(DiceRange(45, 45), "1*/1"),
                CombatCasualty(DiceRange(46, 46), "1/2"),
                CombatCasualty(DiceRange(51, 51), "1/1"),
                CombatCasualty(DiceRange(52, 52), "0/0*"),
                CombatCasualty(DiceRange(53, 53), "2/1"),
                CombatCasualty(DiceRange(54, 54), "1*/2"),
                CombatCasualty(DiceRange(55, 55), "2/2"),
                CombatCasualty(DiceRange(56, 56), "0/0"),
                CombatCasualty(DiceRange(61, 61), "2/0"),
                CombatCasualty(DiceRange(62, 66), "DD")
            )),

            CombatOdds("1.5:1", listOf(
                CombatCasualty(DiceRange(11, 12), "AD"),
                CombatCasualty(DiceRange(13, 32), "NE"),
                CombatCasualty(DiceRange(33, 33), "1/2"),
                CombatCasualty(DiceRange(34, 34), "0/0"),
                CombatCasualty(DiceRange(35, 35), "1/1"),
                CombatCasualty(DiceRange(36, 36), "*2/0"),
                CombatCasualty(DiceRange(41, 41), "0/1*"),
                CombatCasualty(DiceRange(42, 42), "1/1"),
                CombatCasualty(DiceRange(43, 43), "2/2*"),
                CombatCasualty(DiceRange(44, 44), "3/1"),
                CombatCasualty(DiceRange(45, 45), "0/2"),
                CombatCasualty(DiceRange(46, 46), "2/1"),
                CombatCasualty(DiceRange(51, 51), "1/1*"),
                CombatCasualty(DiceRange(52, 52), "2*/1"),
                CombatCasualty(DiceRange(53, 66), "DD")
            )),
            CombatOdds("2:1", listOf(
                CombatCasualty(DiceRange(11, 11), "AD"),
                CombatCasualty(DiceRange(12, 24), "NE"),
                CombatCasualty(DiceRange(25, 25), "0/3"),
                CombatCasualty(DiceRange(26, 26), "1/2"),
                CombatCasualty(DiceRange(31, 31), "*2/1"),
                CombatCasualty(DiceRange(32, 32), "0/0"),
                CombatCasualty(DiceRange(33, 33), "0/1*"),
                CombatCasualty(DiceRange(34, 34), "1/0"),
                CombatCasualty(DiceRange(35, 35), "3/2"),
                CombatCasualty(DiceRange(36, 36), "1/1"),
                CombatCasualty(DiceRange(41, 41), "2/2*"),
                CombatCasualty(DiceRange(42, 42), "*1/2"),
                CombatCasualty(DiceRange(43, 43), "*1/1"),
                CombatCasualty(DiceRange(44, 44), "0/2*"),
                CombatCasualty(DiceRange(45, 66), "DD")
            )),
            CombatOdds("2.5:1", listOf(
                CombatCasualty(DiceRange(11, 22), "NE"),
                CombatCasualty(DiceRange(23, 23), "1/4"),
                CombatCasualty(DiceRange(24, 24), "2/3"),
                CombatCasualty(DiceRange(25, 25), "*0/0"),
                CombatCasualty(DiceRange(26, 26), "1/1*"),
                CombatCasualty(DiceRange(31, 31), "2/3*"),
                CombatCasualty(DiceRange(32, 32), "3/3"),
                CombatCasualty(DiceRange(33, 33), "0/1"),
                CombatCasualty(DiceRange(34, 34), "1/0"),
                CombatCasualty(DiceRange(35, 35), "2/2*"),
                CombatCasualty(DiceRange(36, 66), "DD")
            )),
            CombatOdds("3:1", listOf(
                CombatCasualty(DiceRange(11, 15), "NE"),
                CombatCasualty(DiceRange(16, 16), "0/0*"),
                CombatCasualty(DiceRange(21, 21), "2/3"),
                CombatCasualty(DiceRange(22, 22), "0/2"),
                CombatCasualty(DiceRange(23, 23), "*2/0"),
                CombatCasualty(DiceRange(24, 24), "1/2"),
                CombatCasualty(DiceRange(25, 25), "0/1"),
                CombatCasualty(DiceRange(26, 26), "*2/3"),
                CombatCasualty(DiceRange(32, 64), "DD"),
                CombatCasualty(DiceRange(65, 66), "DR")
            )),
            CombatOdds("3.5:1", listOf(
                CombatCasualty(DiceRange(11, 11), "NE"),
                CombatCasualty(DiceRange(12, 12), "0/0"),
                CombatCasualty(DiceRange(13, 13), "*2/2"),
                CombatCasualty(DiceRange(14, 14), "3/3"),
                CombatCasualty(DiceRange(15, 15), "2/4"),
                CombatCasualty(DiceRange(16, 16), "3/1"),
                CombatCasualty(DiceRange(21, 21), "0/1*"),
                CombatCasualty(DiceRange(22, 61), "DD"),
                CombatCasualty(DiceRange(62, 66), "DR")
            )),

            CombatOdds("4:1", listOf(
                CombatCasualty(DiceRange(11, 11), "*2/1"),
                CombatCasualty(DiceRange(12, 12), "1/2"),
                CombatCasualty(DiceRange(13, 13), "0/2"),
                CombatCasualty(DiceRange(14, 14), "0/1*"),
                CombatCasualty(DiceRange(15, 15), "1/1*"),
                CombatCasualty(DiceRange(16, 55), "DD"),
                CombatCasualty(DiceRange(56, 66), "DR")
            )),

            CombatOdds("4.5:1", listOf(
                CombatCasualty(DiceRange(11, 11), "3/2"),
                CombatCasualty(DiceRange(12, 41), "DD"),
                CombatCasualty(DiceRange(42, 65), "DR"),
                CombatCasualty(DiceRange(66, 66), "DS")
            )),

            CombatOdds("5:1", listOf(
                CombatCasualty(DiceRange(11, 32), "DD"),
                CombatCasualty(DiceRange(33, 61), "DR"),
                CombatCasualty(DiceRange(62, 66), "DR")
            ))

        )

        /**
         * Get results from the melee combat table.
         *
         * @param dice The dice roll value.
         * @return A list of CombatResults that match the specified conditions.
         */
        fun getResults(dice: Int): List<CombatResult> {
            val results = mutableListOf<CombatResult>()
            for (odds in meleeCombatTable) {
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
    }
}