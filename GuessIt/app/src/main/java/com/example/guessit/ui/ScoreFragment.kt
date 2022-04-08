package com.example.guessit.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.guessit.R
import com.example.guessit.databinding.FragmentScoreBinding
import com.example.guessit.viewmodels.ScoreViewModel
import com.example.guessit.viewmodels.ScoreViewModelFactory

class ScoreFragment : Fragment() {

    private lateinit var scoreViewModel:ScoreViewModel
    private lateinit var scoreViewModelFactory: ScoreViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate view and obtain an instance of the binding class.
        val binding: FragmentScoreBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_score,
            container,
            false
        )
        val scoreFragmentArgs by navArgs<ScoreFragmentArgs>()

        scoreViewModelFactory = ScoreViewModelFactory(scoreFragmentArgs.score)
        scoreViewModel= ViewModelProvider(this, scoreViewModelFactory).get(ScoreViewModel::class.java)
        // Get args using by navArgs property delegate
        binding.scoreFrag = this
        binding.scoreViewModel = scoreViewModel
//        binding.lifecycleOwner = viewLifecycleOwner
//        binding.scoreText.text = scoreFragmentArgs.score.toString()
//        binding.playAgainButton.setOnClickListener { onPlayAgain() }

        return binding.root

    }

     fun onPlayAgain() {
        findNavController().navigate(ScoreFragmentDirections.actionRestart())
    }
}