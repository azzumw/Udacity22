package com.example.diceroller

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class MainPresenterWithMock {

    private lateinit var dice:Dice
    private lateinit var mainPresenter: MainPresenter

    @Before
    fun setUp() {
        dice = mock(Dice::class.java)
        mainPresenter = MainPresenter(dice)
    }

    @Test
    fun check_drawable_for_dice() {
        //given
        `when`(dice.roll()).thenReturn(5)

        //when
        val drawableId = mainPresenter.getDrawableForDice()

        assertEquals(R.drawable.dice_5,drawableId)
    }

    @Test
    fun check_roll_is_called() {

        mainPresenter.getDrawableForDice()

        verify(dice).roll()
    }
}