package com.traductor.popoloca.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.traductor.popoloca.model.Image
import com.traductor.popoloca.network.Callback
import com.traductor.popoloca.network.FirestoreService
import java.lang.Exception

class ImageViewModel :ViewModel() {
    val firestoreService = FirestoreService()
    var listImage: MutableLiveData<List<Image>> = MutableLiveData()
    var isLoading = MutableLiveData<Boolean>()

    //OBTENER LOS DATOS DE FIREBASE
    fun refresh (categoria : String){
        if (categoria.equals("default")){
            getImageFromFirebase()
        }else{
            getImageFromFirebase(categoria)
        }
    }

    fun getImageFromFirebase(){
        firestoreService.getImages(object: Callback<List<Image>>{
            override fun onSuccess(result: List<Image>?) {
                listImage.postValue(result)
                processFinished()
            }
            override fun onFailed(exception: Exception) {
                processFinished()
            }
        })
    }

    fun getImageFromFirebase(categoria : String){
        firestoreService.getImages(object: Callback<List<Image>>{
            override fun onSuccess(result: List<Image>?) {
                listImage.postValue(result)
                processFinished()
            }
            override fun onFailed(exception: Exception) {
                processFinished()
            }
        }, categoria)
    }

    fun processFinished(){
        isLoading.value = true
    }
}