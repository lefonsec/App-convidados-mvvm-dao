package com.example.convidados.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.convidados.model.GuestModel
import com.example.convidados.repository.GuestRepository

class GuestFormViewModel(application: Application) : AndroidViewModel(application ) {

    private  val repository = GuestRepository(application)
    private val guestModel = MutableLiveData<GuestModel>()
    val guest: LiveData<GuestModel> = guestModel

    private val saveGuest = MutableLiveData<String>()
    val guestSave: LiveData<String> = saveGuest

    fun get(id:Int){
       guestModel.value = repository.get(id)
    }

    fun save(model: GuestModel) {
        if (model.id == 0){
            if (repository.insert(model)){
                saveGuest.value = "Inserçõ com sucesso"
            }else{
                saveGuest.value =""
            }
        }else{
          if( repository.update(model)){
              saveGuest.value ="Atualização com sucesso"
          }
            else{
              saveGuest.value =""
          }
        }
    }
}