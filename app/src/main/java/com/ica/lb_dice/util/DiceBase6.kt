package com.ica.lb_dice.util

class DiceBase6 private constructor(private val tens: Int, private val ones: Int) {

    companion object {
        fun fromDice(die1: Int, die2: Int): DiceBase6 {
            require(die1 in 1..6 && die2 in 1..6) { "Dice values must be between 1 and 6." }
            return DiceBase6(die1, die2)
        }

        fun fromDiceBase6(diceBase6: Int): DiceBase6 {
            val tens = diceBase6 / 10
            val ones = diceBase6 % 10
            return fromDice(tens, ones)
        }

        fun fromDecimal(decimal: Int): DiceBase6 {
            require(decimal in 7..42) { "Decimal value must be between 7 and 42." }
            val adjusted = decimal - 7
            val tens = (adjusted / 6) + 1
            val ones = (adjusted % 6) + 1
            return DiceBase6(tens, ones)
        }
    }

    fun toDecimal(): Int {
        return ((tens - 1) * 6) + (ones - 1) + 7
    }

    fun toDiceBase6(): Int {
        return (tens * 10) + ones
    }

    fun decompose(): Pair<Int, Int> {
        return Pair(tens, ones)
    }

    operator fun plus(decimal: Int): DiceBase6 {
        val newDecimal = (toDecimal() + decimal).coerceIn(7, 42)
        return fromDecimal(newDecimal)
    }

    operator fun minus(decimal: Int) : DiceBase6 {
        val newDecimal = (toDecimal() - decimal).coerceIn(7, 42)
        return fromDecimal(newDecimal)
    }

    override fun toString(): String {
        return "$tens$ones"
    }
}
