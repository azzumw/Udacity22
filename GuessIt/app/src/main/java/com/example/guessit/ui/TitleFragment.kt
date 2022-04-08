package com.example.guessit.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.guessit.R
import com.example.guessit.databinding.FragmentTitleBinding


class TitleFragment : Fragment() {

    private var _binding : FragmentTitleBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = DataBindingUtil.inflate(inflater,R.layout.fragment_title,container,false)
//        _binding = FragmentTitleBinding.inflate(inflater,container,false)

        binding.titleFrag = this

        return binding.root
    }


    fun playButton(view: View){
        val action = TitleFragmentDirections.actionTitleFragmentToGameFragment()
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}