package com.example.shoeinventory

import android.content.Context
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.uiautomator.*
import junit.runner.Version.id
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class MainActivityTests {

    @get:Rule
    val activity = ActivityScenarioRule(MainActivity::class.java)

    companion object{
        private val context = InstrumentationRegistry.getInstrumentation().targetContext
        private val instrumentation = InstrumentationRegistry.getInstrumentation()
        private val uiDevice: UiDevice = UiDevice.getInstance(instrumentation)
        private const val password = "password"

        fun getShoeName():String{
            return "Beetle"
        }

        fun getBrandName():String{
            return "Camper"
        }

        fun getShoeSize():Double = 8.0

        fun getDescription():String = "${System.currentTimeMillis()} description"

        private val googlePasswordPopUpWatcher = UiWatcher{
            val notNowButton = uiDevice.findObject(UiSelector().text("Not now"))
            if(null != notNowButton){
                notNowButton.click()
                return@UiWatcher true
            }
            false
        }

        private fun  registerGooglePasswordPopUp(){
            uiDevice.registerWatcher("googlePasswordPopUpWatcher",googlePasswordPopUpWatcher)

            uiDevice.runWatchers()
        }

        private fun removeWatchers(){
            uiDevice.removeWatcher("googlePasswordPopUpWatcher")
        }

    }

    @Before
    fun setUp() {
        registerGooglePasswordPopUp()
    }

    @After
    fun tearDown() {
        removeWatchers()
    }

    @Test
    fun happy_flow() {
        onView(withId(R.id.emailEditText)).perform(typeText("Azzum"), closeSoftKeyboard())
        onView(withId(R.id.passEditText)).perform(typeText("password"), closeSoftKeyboard())
        onView(withId(R.id.loginBtn)).perform(click())

        onView(withText("Welcome Azzum")).check(matches(isDisplayed()))

        onView(withId(R.id.nextBtn)).perform(click())

        onView(withContentDescription(R.string.cd_login)).perform(click())

        onView(withId(R.id.shoe_image)).check(matches(isDisplayed()))

        onView(withId(R.id.fab)).perform(click())

        onView(withId(R.id.shoe_name_input)).perform(typeText(getShoeName()), closeSoftKeyboard())
        onView(withId(R.id.brand_name_input)).perform(typeText(getBrandName()), closeSoftKeyboard())
        onView(withId(R.id.size_name_input)).perform(typeText(getShoeSize().toString()),
            closeSoftKeyboard())
        onView(withId(R.id.description_name_input)).perform(typeText(getDescription()),
            closeSoftKeyboard())
        onView(withId(R.id.save_btn)).perform(click())

        onView(withText(getShoeName())).check(matches(isDisplayed()))
    }

    @Test
    fun happy_flow_log_out(){

        onView(withId(R.id.emailEditText)).perform(typeText("Azzum"), closeSoftKeyboard())
        onView(withId(R.id.passEditText)).perform(typeText("password"), closeSoftKeyboard())
        onView(withId(R.id.loginBtn)).perform(click())

        onView(withId(R.id.nextBtn)).perform(click())

        onView(withContentDescription(R.string.cd_login)).perform(click())

        onView(withId(R.id.logout)).perform(click())

       onView(withId(R.id.loginBtn)).check(matches(isDisplayed()))

    }
}