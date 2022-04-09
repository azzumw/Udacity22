package com.example.shoeinventory.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber

class UserViewModel: ViewModel() {
    private val _isLoggedIn = MutableLiveData(false)
    val isLoggedIn get() = _isLoggedIn

    init {
        Timber.e("User status: $isLoggedIn")
    }
    fun authenticate(){
        _isLoggedIn.value = true
    }

    fun isValidEntry(username:String,password:String):Boolean{
        return !(username.isNullOrBlank() || password.isNullOrBlank())
    }

    fun logout(){
        _isLoggedIn.value = false
        Timber.e("User status: $isLoggedIn")
    }

    override fun onCleared() {
        super.onCleared()
        logout()
        Timber.e("User status: $isLoggedIn")
    }
}