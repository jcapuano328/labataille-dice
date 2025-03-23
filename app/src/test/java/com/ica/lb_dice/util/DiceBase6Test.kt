package com.ica.lb_dice.util

import org.junit.Test
import org.junit.Assert.*

class DiceBase6Test {

    @Test
    fun `construct with die values`() {
        val db6a = DiceBase6.fromDice(1, 1)
        assertEquals(7, db6a.toDecimal())
        assertEquals(11, db6a.toDiceBase6())

        val db6b = DiceBase6.fromDice(5, 6)
        assertEquals(36, db6b.toDecimal())
        assertEquals(56, db6b.toDiceBase6())

        val db6c = DiceBase6.fromDice(4, 3)
        assertEquals(27, db6c.toDecimal())
        assertEquals(43, db6c.toDiceBase6())
    }

    @Test
    fun `construct with decimal values`() {
        val db6a = DiceBase6.fromDecimal(7)
        assertEquals(7, db6a.toDecimal())
        assertEquals(11, db6a.toDiceBase6())

        val db6b = DiceBase6.fromDecimal(36)
        assertEquals(36, db6b.toDecimal())
        assertEquals(56, db6b.toDiceBase6())

        val db6c = DiceBase6.fromDecimal(27)
        assertEquals(27, db6c.toDecimal())
        assertEquals(43, db6c.toDiceBase6())
    }

    @Test
    fun `toDecimal valid range`() {
        // Verify that `toDecimal()` returns a value within the valid range [1, 36] 
        // for all valid DiceBase6 instances.
        // TODO implement test
    }

    @Test
    fun `toDecimal boundary values`() {
        // Test `toDecimal()` for boundary values: (1, 1) and (6, 6) to 
        // ensure they map to 1 and 36 respectively.
        // TODO implement test
    }

    @Test
    fun `toDecimal intermediate values`() {
        // Test `toDecimal()` for some intermediate values of tens and ones, 
        // to ensure correct mapping to the decimal representation.
        // TODO implement test
    }

    @Test
    fun `decompose basic functionality`() {
        // Check if `decompose()` correctly returns the tens and ones 
        // values as a Pair.
        // TODO implement test
    }

    @Test
    fun `decompose value preservation`() {
        // Verify that the values returned by `decompose()` are the 
        // same as the original tens and ones used to create the DiceBase6 instance.
        // TODO implement test
    }

    @Test
    fun `plus with zero`() {
        val db6 = DiceBase6.fromDice(5, 6)
        assertEquals(56, db6.toDiceBase6())

        val d = db6.plus(0)
        assertEquals(56, d.toDiceBase6())
    }

    @Test
    fun `plus with positive value`() {
        val db6 = DiceBase6.fromDice(5, 6)
        assertEquals(56, db6.toDiceBase6())

        val d = db6.plus(3)
        assertEquals(63, d.toDiceBase6())
    }

    @Test
    fun `plus decimal over 36`() {
        val db6 = DiceBase6.fromDice(5, 6)
        assertEquals(56, db6.toDiceBase6())

        val d = db6.plus(9)
        assertEquals(66, d.toDiceBase6())

    }

    @Test
    fun `plus boundary values`() {
        // Check `plus()` with boundary values such that sum of 
        // decimal values equal to 1 and 36.
        // TODO implement test
    }

    @Test
    fun `toString basic functionality`() {
        // Check if `toString()` returns a two-digit string with the tens 
        // digit followed by the ones digit.
        // TODO implement test
    }

    @Test
    fun `toString single digit number`() {
        // Check `toString()` for values where the tens and ones are both single-digit numbers.
        // TODO implement test
    }

    @Test
    fun `toString boundary values`() {
        // Test `toString()` for boundary values (1, 1) and (6, 6) to 
        // verify that the output is "11" and "66" respectively.
        // TODO implement test
    }

}