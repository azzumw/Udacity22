package com.example.colourmyviews

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.example.colourmyviews.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        binding.apply {
            activity = this@MainActivity
        }
    }


    fun changeBackground(view: View){
        when(view.id){
            R.id.box_one_text -> view.setBackgroundColor(Color.DKGRAY)
            R.id.box_two_text -> view.setBackgroundColor(Color.GRAY)

            //boxes using android color resources
            R.id.box_three_text -> view.setBackgroundResource(android.R.color.holo_green_light)
            R.id.box_four_text -> view.setBackgroundResource(android.R.color.holo_green_dark)
            R.id.box_five_text -> view.setBackgroundResource(android.R.color.holo_green_light)

            R.id.button_red -> binding.boxThreeText.setBackgroundResource(R.color.my_red)
            R.id.button_green -> binding.boxFourText.setBackgroundResource(R.color.my_green)
            R.id.button_yellow -> binding.boxFiveText.setBackgroundResource(R.color.my_yellow)
        }

        if(view is TextView){
            view.setTextColor(Color.WHITE)
        }
    }
}