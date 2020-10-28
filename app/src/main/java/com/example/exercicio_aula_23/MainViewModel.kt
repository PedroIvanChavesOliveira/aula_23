package com.example.exercicio_aula_23

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    val updatedPartner: MutableLiveData<Partner> = MutableLiveData()

    fun setNewPartner(partner: Partner) {
        updatedPartner.postValue(partner)
    }
}