package com.example.diceroller

class RealDice(val sides:Int=6): Dice {
    override fun roll():Int {
        return(1..sides).random()
    }
}