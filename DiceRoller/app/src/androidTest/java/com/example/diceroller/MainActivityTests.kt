package com.example.diceroller

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTests:BaseTest() {

    @Rule @JvmField
    var rule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun preconditions() {
        //check image view is present
        onView(withId(R.id.imageview)).check(matches(isDisplayed()))
        //check roll button is present
        onView(withId(R.id.roll_btn)).check(matches(isDisplayed()))
    }
}