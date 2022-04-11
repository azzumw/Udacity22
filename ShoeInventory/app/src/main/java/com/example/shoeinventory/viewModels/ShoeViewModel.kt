package com.example.shoeinventory.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoeinventory.models.Shoe

class ShoeViewModel : ViewModel() {

    private val _shoesList = MutableLiveData<MutableList<Shoe>>(mutableListOf())
    val shoesList: LiveData<MutableList<Shoe>>
        get() = _shoesList


    private fun insertShoeInstance(shoe: Shoe) {
        _shoesList.value?.add(shoe)
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
}