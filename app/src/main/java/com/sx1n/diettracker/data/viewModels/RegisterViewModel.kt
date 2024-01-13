package com.sx1n.diettracker.data.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.Locale

class RegisterViewModel: ViewModel() {
    val firstName = MutableLiveData<String>()
    val lastName = MutableLiveData<String>()
    val sex = MutableLiveData<Char>()
    val height = MutableLiveData<Int>()
    val weight = MutableLiveData<Double>()

    fun registerName(newFirstName: String, newLastName: String) {
        firstName.value = newFirstName
        lastName.value = newLastName
    }

    fun registerSex(newSex: Char) {
        sex.value = newSex
    }

    fun registerHeight(newHeight: Int) {
        height.value = newHeight
    }
    fun registerWeight(newWeight: Double) {
        weight.value = newWeight
    }

}