package com.java90.pruebamultimedialab.ui.fragments.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.java90.pruebamultimedialab.domain.usecases.LoginUseCase
import com.java90.pruebamultimedialab.utils.Resource
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class LoginViewModel(private val loginUseCase: LoginUseCase) : ViewModel() {

    fun loginUser(email: String, password: String) = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            val response = loginUseCase.loginUser(email, password)
            emit(response)
        }catch (e: Exception) {
            emit(Resource.Failure(e.message.toString()))
        }
    }
}