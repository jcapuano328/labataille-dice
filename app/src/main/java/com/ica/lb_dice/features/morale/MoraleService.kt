package com.ica.lb_dice.features.morale

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

    fun range(dice: Int): List<MoraleResultAlt> {
        return listOf(
            resultAlt(dice, 12),
            resultAlt(dice, 9),
            resultAlt(dice, 6),
            resultAlt(dice, 3),
            resultAlt(dice, 0),
            resultAlt(dice, -3),
            resultAlt(dice, -6),
            resultAlt(dice, -9),
            resultAlt(dice, -12)
        )
    }

    private fun result(dice: Int, prepend: String, modifier: Int, icon: String): MoraleResult {
        val d = DiceBase6.fromDiceBase6(dice).plus(modifier).toDiceBase6()
        return MoraleResult("$prepend $d", modifier.toString(), icon)
    }

    private fun resultAlt(dice: Int, modifier: Int): MoraleResultAlt {
        val pass = DiceBase6.fromDiceBase6(dice).plus(modifier-1).toDiceBase6()
        val fail = DiceBase6.fromDiceBase6(dice).plus(modifier).toDiceBase6()

        var passResult = "<= $pass"
        if (pass >= 66 || pass <= 11) { passResult = "Auto" }
        var failResult = ">= $fail"
        if (fail >= 66 || fail <= 11) { failResult = "Auto" }


        return MoraleResultAlt(
            passResult,
            failResult,
            modifier.toString()
        )
    }
}

data class MoraleResult(val result: String, val modifier: String, val icon: String)
data class MoraleResultAlt(val pass: String, val fail: String, val modifier: String)
