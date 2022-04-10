package com.example.shoeinventory

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withId

const val password = "password"
const val email = "azzum"

val loginBtn = onView(withId(R.id.loginBtn))
val passwordEditText = onView(withId(R.id.passEditText))
val emailEditText = onView(withId(R.id.emailEditText))
val emailTextView = onView(withId(R.id.emailTextField))
val passwordTextView = onView(withId(R.id.passwordLabelText))
