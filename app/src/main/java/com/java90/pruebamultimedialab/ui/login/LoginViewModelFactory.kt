package com.java90.pruebamultimedialab.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.java90.pruebamultimedialab.usecases.LoginUseCase

class LoginViewModelFactory(private val loginUseCase: LoginUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(LoginUseCase::class.java).newInstance(loginUseCase)
    }

}