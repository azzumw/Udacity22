package com.example.diceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.diceroller.databinding.ActivityMainBinding

/**
 * This activity allows the user to roll a dice and view the result
 * on the screen.
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private var currentNumber = 0
    lateinit var mainPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainPresenter = MainPresenter(RealDice())

        rollDice()

        binding.apply {
            rollBtn.setOnClickListener{
                rollDice()
            }
        }
    }


    /**
     * Roll the dice and update the screen with the result.
     */
    private fun rollDice(){
        currentNumber = mainPresenter.getDrawableForDice()
        setImage(currentNumber)
    }

    private fun setImage(imageId : Int){
        binding.imageview.setImageResource(imageId)
    }
}