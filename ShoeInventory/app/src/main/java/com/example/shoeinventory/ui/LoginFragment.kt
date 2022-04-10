package com.example.shoeinventory.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.shoeinventory.R
import com.example.shoeinventory.databinding.FragmentLoginBinding
import com.example.shoeinventory.viewModels.ShoeViewModel
import com.example.shoeinventory.viewModels.UserViewModel

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val userViewModel: UserViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)

        findNavController().popBackStack(R.id.shoeListFragment,true)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.loginFrag = this
    }


    private fun authenticateUser() {
        userViewModel.authenticate()
        Log.e("LOGIN", "${userViewModel.isLoggedIn.value}")
//        Toast.makeText(context, "Welcome", Toast.LENGTH_SHORT).show()
    }

    fun loginAndGotoWelcomeScreen(view: View) {
        setErrorTextField(false)
        if (isValidEntry()) {
            authenticateUser()
            val name = binding.emailEditText.text.toString().capitalize()
            val action = LoginFragmentDirections.actionLoginFragmentToWelcomeFragment(name)
            findNavController().navigate(action)

        } else {
            setErrorTextField(true)
            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
        }
    }

    private fun isValidEntry(): Boolean {
        val username = binding.emailEditText.text.toString()
        val password = binding.passEditText.text.toString()

        return userViewModel.isValidEntry(username, password)
    }

    private fun setErrorTextField(error: Boolean) {
        if (error) {
            if (binding.emailEditText.text.isNullOrBlank()) {

                binding.emailTextField.isErrorEnabled = true
                binding.emailTextField.error = getString(R.string.email_error)
            }
            if (binding.passEditText.text.isNullOrBlank()) {
                binding.passwordLabelText.isErrorEnabled = true
                binding.passwordLabelText.error = getString(R.string.password_error)
            }

        } else {
            binding.emailTextField.isErrorEnabled = false
            binding.passwordLabelText.isErrorEnabled = false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}