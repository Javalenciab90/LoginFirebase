package com.java90.pruebamultimedialab.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.java90.pruebamultimedialab.usecases.LoginUseCase
import com.java90.pruebamultimedialab.vo.Resource

class LoginViewModel (private val loginUseCase: LoginUseCase): ViewModel(){

    fun signIn(email: String, password: String) : LiveData<Resource<String>> {
        return loginUseCase.signIn(email, password)
    }
}