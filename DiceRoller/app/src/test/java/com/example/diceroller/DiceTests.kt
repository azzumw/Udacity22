package com.example.diceroller

import androidx.test.filters.SmallTest
import org.junit.Test

import org.junit.Assert.*
import com.google.common.truth.Truth.assertThat
import org.junit.Before

/**
 * Example local unit test, which will execute on the development machine (host).
 */
@SmallTest
class DiceTests {
    private lateinit var dice:RealDice

    @Before
    fun setUp() {
        dice = RealDice()
    }

    @Test
    fun check_number_of_sides() {
        assertEquals("Sides Not Equal",6,dice.sides)
    }

    @Test
    fun generates_number(){

        val rollResult = dice.roll()

        assertTrue("The value of rollResult was not between 1 and 6",rollResult in 1..6)
    }

    @Test
    fun number_in_range() {

        val rollResult = dice.roll()

        assertThat(rollResult).isIn(1..6)
    }
}