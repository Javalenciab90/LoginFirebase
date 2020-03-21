package com.java90.pruebamultimedialab.ui.reset

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.google.firebase.FirebaseException
import com.java90.pruebamultimedialab.usecases.ResetUseCase
import com.java90.pruebamultimedialab.vo.Resource
import kotlinx.coroutines.Dispatchers

class ResetViewModel (private val resetUseCase: ResetUseCase) : ViewModel(){

    fun resetPassWord(email: String) = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(resetUseCase.resetPassWord(email))
        }catch (e: FirebaseException){
            emit(Resource.Failure(e))
        }
    }
}