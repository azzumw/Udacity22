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
import org.hamcrest.Matchers
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
class MainActivityTests : BaseTest(){

    @get:Rule
    val activity = ActivityScenarioRule(MainActivity::class.java)

    companion object{

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
    override fun setUp() {
        registerGooglePasswordPopUp()
    }

    @After
    fun tearDown() {
        removeWatchers()
    }

    @Test
    fun happy_flow() {

        emailEditText.perform(typeText(email), closeSoftKeyboard())
        passwordEditText.perform(typeText(password), closeSoftKeyboard())
        loginBtn.perform(click())

        onView(withText(Matchers.equalToIgnoringCase("Welcome $email"))).check(matches(isDisplayed()))

        nextBtn.perform(click())

        onView(withContentDescription(R.string.cd_login)).perform(click())

        shoeImage.check(matches(isDisplayed()))

        fab.perform(click())

        shoeNameEditText.perform(typeText(getShoeName()), closeSoftKeyboard())
        shoeBrandEditText.perform(typeText(getBrandName()), closeSoftKeyboard())
        shoeSizeEditText.perform(typeText(getShoeSize().toString()),
            closeSoftKeyboard())
        shoeDesciptionEditText.perform(typeText(getDescription()),
            closeSoftKeyboard())
        saveBtn.perform(click())

        onView(withText(getShoeName())).check(matches(isDisplayed()))
    }

    @Test
    fun happy_flow_log_out(){

        onView(withId(R.id.emailEditText)).perform(typeText(email), closeSoftKeyboard())
        onView(withId(R.id.passEditText)).perform(typeText(password), closeSoftKeyboard())
        onView(withId(R.id.loginBtn)).perform(click())

        onView(withId(R.id.nextBtn)).perform(click())

        onView(withContentDescription(R.string.cd_login)).perform(click())

        onView(withId(R.id.logout)).perform(click())

       onView(withId(R.id.loginBtn)).check(matches(isDisplayed()))

    }
}