package com.java90.pruebamultimedialab.ui.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.java90.pruebamultimedialab.usecases.RegisterUseCase

class RegisterViewModelFactory(private val registerUseCase: RegisterUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(RegisterUseCase::class.java).newInstance(registerUseCase)
    }
}