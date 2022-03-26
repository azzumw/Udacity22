package com.example.aboutme

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {

    private lateinit var nicknameEditTextView: EditText
    private lateinit var done:Button
    private lateinit var nicknameDisplayTextView:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nicknameEditTextView = findViewById(R.id.nickname_edit)
        done = findViewById(R.id.done_button)
        nicknameDisplayTextView = findViewById(R.id.nickname)

        done.setOnClickListener {
           addNickName(it)
        }
    }

    private fun addNickName(view:View){
        val name = nicknameEditTextView.text.toString()
        nicknameDisplayTextView.text = name
        nicknameDisplayTextView.isVisible = true
        nicknameEditTextView.isVisible = false
        done.isVisible = false

        val imm: InputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}