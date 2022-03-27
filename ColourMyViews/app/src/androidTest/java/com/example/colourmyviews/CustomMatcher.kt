package com.example.colourmyviews

import android.graphics.drawable.ColorDrawable
import android.view.View
import android.widget.TextView
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher

object CustomMatcher {

    fun hasColor(expectedColorId:Int): Matcher<View> {
        return object :BoundedMatcher<View,TextView>(TextView::class.java){
            override fun describeTo(description: Description?) {
                description?.appendText("Color does not match. Expected: $expectedColorId")
            }

            override fun matchesSafely(item: TextView?): Boolean {
                val c = item?.background  as ColorDrawable
                return  c.color == expectedColorId
            }
        }
    }
}