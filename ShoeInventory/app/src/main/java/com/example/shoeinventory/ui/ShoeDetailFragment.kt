package com.example.shoeinventory.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.shoeinventory.R
import com.example.shoeinventory.databinding.FragmentShoeDetailBinding
import com.example.shoeinventory.viewModels.ShoeViewModel

class ShoeDetailFragment : Fragment() {

    private var _binding: FragmentShoeDetailBinding? = null
    private val binding get() = _binding!!

    private val sharedViewModel: ShoeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_detail, container, false)
        binding.shoeDetailFrag = this
        binding.lifecycleOwner = this

        return binding.root
    }

    fun saveShoe() {

        if (isValidEntry()) {
            sharedViewModel.addShoeDetails(
                binding.shoeNameInput.text.toString(),
                binding.sizeNameInput.text.toString().toDouble(),
                binding.brandNameInput.text.toString(),
                binding.descriptionNameInput.text.toString()
            )

            navigateToShoeList()
        } else {
            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
        }

    }

    fun cancel() {
        navigateToShoeList()
    }

    private fun isValidEntry(): Boolean {

        return sharedViewModel.isValidEntry(
            binding.shoeNameInput.text.toString(),
            binding.sizeNameInput.text.toString(),
            binding.brandNameInput.toString(),
        )
    }

    private fun navigateToShoeList() {
        findNavController().navigate(R.id.action_shoeDetailFragment_to_shoeListFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}