package com.example.androidtrivia

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.androidtrivia.databinding.FragmentAboutBinding

class AboutFragment : Fragment() {

    private var _binding:FragmentAboutBinding?=null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAboutBinding.inflate(inflater,container,false)
        return binding.root
    }



    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}