package com.example.diceroller

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class MainPresenterWithFakesTests {
    private lateinit var mainPresenter: MainPresenter

    @Before
    fun setUp() {
        mainPresenter = MainPresenter(FakeDice(4))
    }

    @Test
    fun correct_image_id() {
        assertEquals(R.drawable.dice_4,mainPresenter.getDrawableForDice())
    }
}