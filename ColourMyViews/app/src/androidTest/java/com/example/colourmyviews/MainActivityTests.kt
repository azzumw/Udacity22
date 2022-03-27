package com.example.colourmyviews

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.SystemClock
import androidx.core.graphics.drawable.toDrawable
import androidx.core.graphics.toColor
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.hasBackground
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class MainActivityTests {

    val appContext: Context = InstrumentationRegistry.getInstrumentation().targetContext

    @Before
    fun setUp() {
        launch(MainActivity::class.java)
    }

    @Test
    fun check_backgroundColor_box_one_text() {

        onView(withId(R.id.box_one_text))
            .perform(click())
            .check(matches(CustomMatcher.hasColor(Color.DKGRAY)))
    }

    @Test
    fun check_backgroundColor_box_three_text() {
        val c = appContext.applicationContext.resources.getColor(android.R.color.holo_green_light)

        onView(withId(R.id.box_three_text))
            .perform(click())
            .check(matches(CustomMatcher.hasColor(c)))
    }

    @Test
    fun check_backgroundColor_box_three_changes_to_red() {
        val c = appContext.applicationContext.resources.getColor(R.color.my_red)

        onView(withId(R.id.box_three_text)).perform(click())

        onView(withId(R.id.button_red)).perform(click())

        onView(withId(R.id.box_three_text)).check(matches(CustomMatcher.hasColor(c)))
    }
}