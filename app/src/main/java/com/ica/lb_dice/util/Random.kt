package com.ica.lb_dice.util

import kotlin.random.Random

class Random {
    fun RandomDie6(): Int {
        return generateRandomNumber(1, 6)
    }

    private fun generateRandomNumber(min: Int, max: Int): Int {
        require(min <= max) { "Min value must be less than or equal to max value." }

        if (min == max) {
            return min
        }
        val range = max - min + 1
        return Random.nextInt(range) + min
    }

}