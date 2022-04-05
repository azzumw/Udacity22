package com.example.shoeinventory.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoeinventory.models.Shoe

class ShoeViewModel:ViewModel() {
    private var _isLoggedIn : Boolean = false
    val isLoggedIn get() = _isLoggedIn

    private val _shoesList = MutableLiveData<List<Shoe>>()
    val shoesList get() = _shoesList


    override fun onCleared() {
        super.onCleared()
        _isLoggedIn = false
    }

}