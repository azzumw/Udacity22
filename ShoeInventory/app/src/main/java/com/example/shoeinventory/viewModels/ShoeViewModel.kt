package com.example.shoeinventory.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoeinventory.models.Shoe

class ShoeViewModel : ViewModel() {

    private val _shoesList = MutableLiveData<MutableList<Shoe>>(mutableListOf())
    val shoesList: LiveData<MutableList<Shoe>>
        get() = _shoesList

    val  shoeName = MutableLiveData("")
    val shoeBrand = MutableLiveData("")
    val shoeSize = MutableLiveData("")
    val shoeDescription = MutableLiveData("")

    private fun insertShoeInstance(shoe: Shoe) {
        _shoesList.value?.add(shoe)
    }

    fun addShoe(){
        val shoename = shoeName.value.toString()
        val shoebrand = shoeBrand.value.toString()
        val shoesize = shoeSize.value?.toDouble()!!
        val shoedesc = shoeDescription.value.toString()

        val shoeBinding = Shoe(shoename,shoesize,shoebrand,shoedesc)

        insertShoeInstance(shoeBinding)
    }

    fun isValidEntry(): Boolean {

        if(shoeName.value.isNullOrBlank() ||
            shoeBrand.value.isNullOrBlank() ||
            shoeSize.value.isNullOrBlank() ){

            return false
        }
        return true
    }

    fun clearData(){
        shoeName.value = ""
        shoeBrand.value = ""
        shoeSize.value = ""
        shoeDescription.value = ""
    }
}