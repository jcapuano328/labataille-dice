package com.ica.lb_dice.services

import com.ica.lb_dice.util.DiceBase6
import org.junit.Test

class FireCombatServiceTest {

    @Test
    fun `Dice roll of 11`() {
        // Test with a valid dice roll (e.g., 1 to 6 if standard dice).
        // Verify that the function returns a non-empty list of FireCombatResult.
        val fireDice = DiceBase6.fromDice(1, 1).toDiceBase6()

        val service = FireCombatService()
        val results = service.resolve(fireDice)
        assert(results.isNotEmpty())
        assert(results.size == 6)

        assert(results[0].odds == "5:1")
        assert(results[0].result == "1")

        assert(results[1].odds == "6:1")
        assert(results[1].result == "1")

        assert(results[2].odds == "7:1")
        assert(results[2].result == "1")

        assert(results[3].odds == "8:1")
        assert(results[3].result == "1")

        assert(results[4].odds == "9:1")
        assert(results[4].result == "2")

        assert(results[5].odds == "10:1")
        assert(results[5].result == "2")

    }


    @Test
    fun `Valid dice roll`() {
        // Test with a valid dice roll (e.g., 1 to 6 if standard dice). 
        // Verify that the function returns a non-empty list of FireCombatResult.
        // TODO implement test
    }

    @Test
    fun `Dice roll of 1`() {
        // Test with the minimum possible dice roll (1). 
        // Verify that the function returns the expected list of FireCombatResult.
        // TODO implement test
    }

    @Test
    fun `Dice roll at maximum value`() {
        // Test with the maximum possible dice roll. 
        // Verify that the function returns the expected list of FireCombatResult.
        // TODO implement test
    }

    @Test
    fun `Dice roll outside valid range  below min `() {
        // Test with a dice roll value below the minimum valid range (e.g., 0, -1). 
        // Verify that it might throw an exception, 
        // return an empty list, or handle it gracefully according to the specification.
        // TODO implement test
    }

    @Test
    fun `Dice roll outside valid range  above max `() {
        // Test with a dice roll value above the maximum valid range (e.g., 7, 100). 
        // Verify that it might throw an exception, 
        // return an empty list, or handle it gracefully according to the specification.
        // TODO implement test
    }

    @Test
    fun `Dice roll for all possible valid values`() {
        // Iterate through all the valid dice values and check if the return is correct. 
        // Will require knowledge of possible return values for each dice value.
        // TODO implement test
    }

    @Test
    fun `Large number handling  int max `() {
        // Test with a very large integer, approaching Int.MAX_VALUE to check for overflow. 
        // Determine expected outcome or error handling.
        // TODO implement test
    }

    @Test
    fun `Small number handling  int min `() {
        // Test with a very small integer, approaching Int.MIN_VALUE. 
        // Determine expected outcome or error handling.
        // TODO implement test
    }

    @Test
    fun `Null dice input`() {
        // Check what happens when null is passed in. 
        // Will require code change as current dice input is of type int. 
        // Consider changing input to Int?
        // TODO implement test
    }

    @Test
    fun `Edge case values near valid boundaries`() {
        // Test dice values immediately adjacent to the boundaries of valid input ranges. 
        // (e.g., min-1, min, min+1, max-1, max, max+1)
        // TODO implement test
    }

    @Test
    fun `Verify FireCombatResult data integrity`() {
        // Ensure all returned FireCombatResult objects 
        // contain valid data and values are correct according to table values
        // TODO implement test
    }

}