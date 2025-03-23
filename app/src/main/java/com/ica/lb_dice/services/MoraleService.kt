package com.ica.lb_dice.services

import com.ica.lb_dice.util.DiceBase6

class MoraleService {
    fun check(dice: Int): List<MoraleResult> {
        if (dice <= 11)
            return listOf(MoraleResult("Auto", "", "Fail"))
        if (dice >= 66)
            return listOf(MoraleResult("Auto", "", "Pass"))

        var d = DiceBase6.fromDiceBase6(dice).plus(-1).toDiceBase6()
        return listOf(
            MoraleResult("<= $d", "", "Pass"),
            result(dice, ">=", 0, "Fail"),
            result(dice, ">=", -3, "Fail"),
            result(dice, ">=", -6, "Fail"),
            result(dice, ">=", -9, "Fail"),
            result(dice, ">=", -12, "Fail"),
            result(dice, ">=", -18, "Fail")
        )
    }

    fun range(dice: Int): List<MoraleResult> {
        return listOf(
            result(dice, ">", -12, "Pass"),
            result(dice, ">",-9, "Pass"),
            result(dice, ">",-6, "Pass"),
            result(dice, ">",-3, "Pass"),
            result(dice, ">",0, "Pass"),
            result(dice, ">",3, "Pass"),
            result(dice, ">",6, "Pass"),
            result(dice, ">",9, "Pass"),
            result(dice, ">",12, "Pass")
        )
    }

    private fun result(dice: Int, prepend: String, modifier: Int, icon: String): MoraleResult {
        val d = DiceBase6.fromDiceBase6(dice).plus(modifier).toDiceBase6()
        return MoraleResult("$prepend $d", modifier.toString(), icon)
    }
}

data class MoraleResult(val result: String, val modifier: String, val icon: String)
