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

    private val shoeViewModel: ShoeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_detail, container, false)
        binding.shoeDetailFrag = this
        binding.lifecycleOwner = this
        binding.shoeViewModel = shoeViewModel

        shoeViewModel.clearData()

        return binding.root
    }

    fun saveShoeDetails(){
        setErrorText(false)
        if(shoeViewModel.isValidEntry()){
            //add the shoe, and navigate to the shoeList
            shoeViewModel.addShoe()
            navigateToShoeList()
        }else{
            //show the Error
            setErrorText(true)
        }
    }

    fun cancel() {
        navigateToShoeList()
    }

    private fun navigateToShoeList() {
        findNavController().navigate(R.id.action_shoeDetailFragment_to_shoeListFragment)
    }

    private fun setErrorText(error: Boolean) {
        if (error) {
            if (shoeViewModel.shoeName.value.isNullOrBlank()) {

                binding.shoeNameLabel.isErrorEnabled = true
                binding.shoeNameLabel.error = getString(R.string.shoe_name_missing_error)
            }
            if (shoeViewModel.shoeBrand.value.isNullOrBlank()) {
                binding.brandNameLabel.isErrorEnabled = true
                binding.brandNameLabel.error = getString(R.string.shoe_brand_missing_error)
            }
            if (shoeViewModel.shoeSize.value.isNullOrBlank()) {
                binding.sizeNameLabel.isErrorEnabled = true
                binding.sizeNameLabel.error = getString(R.string.size_missing_error)
            }

        } else {
            binding.shoeNameLabel.isErrorEnabled = false
            binding.brandNameLabel.isErrorEnabled = false
            binding.sizeNameLabel.isErrorEnabled = false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}