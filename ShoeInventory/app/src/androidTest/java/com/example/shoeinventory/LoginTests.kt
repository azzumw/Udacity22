package com.example.shoeinventory

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import org.hamcrest.Matchers.equalToIgnoringCase
import org.hamcrest.Matchers.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginTests :BaseTest(){

    @get:Rule
    val activity = ActivityScenarioRule(MainActivity::class.java)


    @Test
    fun failed_login_with_no_email_and_password() {

        val emailError = context.resources.getString(R.string.email_error)
        val passError = context.resources.getString(R.string.password_error)

        onView(withId(R.id.loginBtn))
            .perform(click())

        //create EditText error customer matcher
        onView(withId(R.id.emailTextField)).check(matches(CustomMatchers.hasError(emailError)))
        onView(withId(R.id.passwordLabelText)).check(matches(CustomMatchers.hasError(passError)))
    }

    @Test
    fun failed_login_with_no_email() {
        val emailError = context.resources.getString(R.string.email_error)
        val passError = context.resources.getString(R.string.password_error)

        passwordEditText.perform(typeText(password))

        loginBtn
            .perform(click())

        emailTextView.check(matches(CustomMatchers.hasError(emailError)))
        passwordTextView.check(matches(not(CustomMatchers.hasError(passError))))
    }

    @Test
    fun failed_login_no_password() {
        val passError = context.resources.getString(R.string.password_error)
        val emailError = context.resources.getString(R.string.email_error)

        emailEditText.perform(typeText(email))

        loginBtn.perform(click())

        passwordTextView.check(matches(CustomMatchers.hasError(passError)))
        emailTextView.check(matches(not(CustomMatchers.hasError(emailError))))
    }

    @Test
    fun successLogin(){
        emailEditText.perform(typeText(email))
        passwordEditText.perform(typeText(password))

        loginBtn.perform(click())

        onView(withText(equalToIgnoringCase("Welcome $email"))).check(matches(isDisplayed()))
    }
}