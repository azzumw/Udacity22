package com.example.shoeinventory

import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.test.espresso.matcher.BoundedMatcher
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import org.hamcrest.Description
import org.hamcrest.Matcher

object CustomMatchers {

    fun hasError(expectedErrorText: String): Matcher<View> {
        return object : BoundedMatcher<View, TextInputLayout>(TextInputLayout::class.java) {
            override fun describeTo(description: Description?) {
                description?.appendText("Could not find: $expectedErrorText")
            }

            override fun matchesSafely(item: TextInputLayout?): Boolean {
                return expectedErrorText == item?.error.toString()
            }

        }
    }
}