package com.example.guessit.ui

import android.os.Bundle
import android.text.format.DateUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.guessit.R
import com.example.guessit.databinding.FragmentGameBinding
import com.example.guessit.viewmodels.GameViewModel

class GameFragment : Fragment() {

    private var _binding : FragmentGameBinding? = null
    private val binding get() = _binding!!

    private lateinit var gameViewModel: GameViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = DataBindingUtil.inflate(inflater,R.layout.fragment_game,container,false)

        Log.e(this.javaClass.canonicalName,"GameFragment onCreateView")
        gameViewModel = ViewModelProvider(this).get(GameViewModel::class.java)

        binding.gameFrag = this
        binding.gameViewM = gameViewModel
        binding.lifecycleOwner = viewLifecycleOwner

       gameViewModel.apply {
//           word.observe(viewLifecycleOwner){
//               binding.wordText.text = it
//           }
//           score.observe(viewLifecycleOwner){
//               binding.scoreText.text = it.toString()
//           }

//           currentTime.observe(viewLifecycleOwner){
//               newTime ->
//               val t = DateUtils.formatElapsedTime(newTime)
//               binding.timerText.text = t
//           }

           eventGameFinished.observe(viewLifecycleOwner){
               if(it){
                   gameFinished()
                   gameViewModel.onGameFinishComplete()
               }
           }
       }

        return binding.root
    }

    /**
     * Called when the game is finished
     */
    private fun gameFinished() {
        val action = GameFragmentDirections.actionGameFragmentToScoreFragment(score = gameViewModel.score.value!!.toInt())
        findNavController().navigate(action)
    }

    /** Methods for updating the UI **/



    override fun onDestroyView() {
        super.onDestroyView()
        Log.e(this.javaClass.canonicalName,"OnDestroyView")
        _binding = null
    }
}