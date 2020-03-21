package com.java90.pruebamultimedialab.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.google.firebase.FirebaseException
import com.java90.pruebamultimedialab.usecases.LoginUseCase
import com.java90.pruebamultimedialab.vo.Resource
import kotlinx.coroutines.Dispatchers

class LoginViewModel (private val loginUseCase: LoginUseCase): ViewModel(){

    fun signIn(email: String, password: String) = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(loginUseCase.signIn(email, password))
        }catch (e: FirebaseException) {
            emit(Resource.Failure(e))
        }
    }
}