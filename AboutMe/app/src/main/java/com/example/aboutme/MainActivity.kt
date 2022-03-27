package com.example.aboutme

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import com.example.aboutme.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val myname = Name("Azzum Waqar")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        binding.dataname = myname
        binding.activity = this

//        binding.doneButton.setOnClickListener {
//           addNickName(it)
//        }
    }

    fun addNickName(view:View){
        val name = binding.nicknameEdit.text.toString()

        binding.apply {
            dataname?.nickName = name
            //in order to refresh the ui wiht new data
            // we need to invalidate all binding expressions
            //so that they get recreated with correct data
            invalidateAll()
            nickname.isVisible = true
            nicknameEdit.isVisible = false
            doneButton.isVisible = false
        }

        val imm: InputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}