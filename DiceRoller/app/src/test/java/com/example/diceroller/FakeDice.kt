package com.example.diceroller

class FakeDice(private val num:Int):Dice {

    override fun roll(): Int {
        return num
    }
}