package com.example.shoeinventory.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoeinventory.models.Shoe

class ShoeViewModel:ViewModel() {

    //move this to new User View model
    private var _isLoggedIn = MutableLiveData(false)
    val isLoggedIn :LiveData<Boolean>
    get() = _isLoggedIn

    private val _shoesList = MutableLiveData<List<Shoe>>()
    val shoesList get() = _shoesList

    private val _shoe = MutableLiveData<Shoe>()
    val shoe:LiveData<Shoe>
    get() = _shoe

    override fun onCleared() {
        super.onCleared()
        //careful
        _isLoggedIn.value = false
    }

}