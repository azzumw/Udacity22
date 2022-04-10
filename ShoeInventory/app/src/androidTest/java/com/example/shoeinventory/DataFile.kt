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
val nextBtn = onView(withId(R.id.nextBtn))


//Shoe List elements
val shoeImage = onView(withId(R.id.shoe_image))
val fab = onView(withId(R.id.fab))


//Shoe Detail Frag elements
val shoeNameEditText = onView(withId(R.id.shoe_name_input))
val shoeBrandEditText = onView(withId(R.id.brand_name_input))
val shoeSizeEditText = onView(withId(R.id.size_name_input))
val shoeDesciptionEditText = onView(withId(R.id.description_name_input))
val cancelBtn = onView(withId(R.id.cancel_btn))
val saveBtn = onView(withId(R.id.save_btn))
