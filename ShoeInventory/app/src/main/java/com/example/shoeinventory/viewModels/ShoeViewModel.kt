package com.example.shoeinventory.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoeinventory.models.Shoe
import timber.log.Timber

class ShoeViewModel : ViewModel() {

    private val _shoesList = MutableLiveData<MutableList<Shoe>>(mutableListOf())
    val shoesList: LiveData<MutableList<Shoe>>
        get() = _shoesList

    init {

        Timber.i("Viewmodel created!")
    }

    private fun insertShoeInstance(shoe: Shoe) {
        _shoesList.value?.add(shoe)
        Log.e("VIEWMODEL", shoesList?.value?.size.toString())
//        Timber.i("Shoe added: ${shoesList.value?.size}" )
    }

    fun addShoeDetails(
        name: String,
        size: Double,
        company: String,
        description: String
    ) {
        val shoe = Shoe(name, size, company, description)

        insertShoeInstance(shoe)
    }

    //isValidEntry
    fun isValidEntry(
        name: String,
        size: String,
        company: String
    ): Boolean {

        return name.isNotBlank() && size.isNotBlank() && company.isNotBlank()
    }

    override fun onCleared() {
        super.onCleared()
        Timber.i("ViewModel cleared!")
    }
}