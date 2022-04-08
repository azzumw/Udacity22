package com.example.guessit.viewmodels

import android.os.CountDownTimer
import android.text.format.DateUtils
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class GameViewModel:ViewModel() {
    companion object {
        // These represent different important times
        // This is when the game is over
        const val DONE = 0L
        // This is the number of milliseconds in a second
        const val ONE_SECOND = 1000L
        // This is the total time of the game
        const val COUNTDOWN_TIME = 10000L
    }

    private val _currentTime = MutableLiveData<Long>(0L)
    val currentTime : LiveData<Long>
    get() = _currentTime

    val currentTimeString = Transformations.map(currentTime) {
        DateUtils.formatElapsedTime(it)
    }
    private val timer:CountDownTimer
    // The current word
    private var _word = MutableLiveData("")
    val word : LiveData<String>
    get() = _word

    // The current score
    private var _score = MutableLiveData(0)
    val score:LiveData<Int>
    get() = _score

    private val _eventGameFinished = MutableLiveData<Boolean>(false)
    val eventGameFinished : LiveData<Boolean>
    get() = _eventGameFinished

    // The list of words - the front of the list is the next word to guess
    private lateinit var wordList: MutableList<String>


    init {
        Log.e("GameViewmodel","ViewModel created!")
        resetList()
        nextWord()

        timer = object :CountDownTimer(COUNTDOWN_TIME, ONE_SECOND){
            override fun onTick(miliSecondsUntilFinished: Long) {
                _currentTime.value = (miliSecondsUntilFinished / ONE_SECOND)
            }

            override fun onFinish() {
                _currentTime.value = DONE
                _eventGameFinished.value = true
            }

        }.start()
    }

    /**
     * Resets the list of words and randomizes the order
     */
    private fun resetList() {
        wordList = mutableListOf(
            "queen",
            "hospital",
            "basketball",
            "cat",
            "change",
            "snail",
            "soup",
            "calendar",
            "sad",
            "desk",
            "guitar",
            "home",
            "railway",
            "zebra",
            "jelly",
            "car",
            "crow",
            "trade",
            "bag",
            "roll",
            "bubble"
        )
        wordList.shuffle()
    }

    /**
     * Moves to the next word in the list
     */
     private fun nextWord(){
        //Select and remove a word from the list
        if (wordList.isEmpty()){
            resetList()
        }
            _word.value = wordList.removeAt(0)

    }

    /** Methods for buttons presses **/

    fun onSkip() {
        _score.value = _score.value?.dec()
        nextWord()
    }

    fun onCorrect() {
        _score.value = (_score.value)?.inc()
        nextWord()
    }

    fun onGameFinishComplete()
    {
        _eventGameFinished.value = false
    }
    private fun reinitialise(){
        _score.value = 0
        resetList()
        nextWord()
    }


    override fun onCleared() {
        super.onCleared()
        Log.e("GameViewModel","GameViewModel destroyed!")
        timer.cancel()
    }
}