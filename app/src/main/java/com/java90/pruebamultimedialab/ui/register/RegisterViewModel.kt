package com.java90.pruebamultimedialab.ui.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.google.firebase.FirebaseException
import com.java90.pruebamultimedialab.usecases.RegisterUseCase
import com.java90.pruebamultimedialab.vo.Resource
import kotlinx.coroutines.Dispatchers

class RegisterViewModel(private val registerUseCase: RegisterUseCase): ViewModel() {

    fun signUp(email: String, password: String) = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(registerUseCase.signUp(email, password))
        }catch (e: FirebaseException) {
            emit(Resource.Failure(e))
        }
    }
}