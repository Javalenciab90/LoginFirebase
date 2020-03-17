package com.java90.pruebamultimedialab.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.java90.pruebamultimedialab.usecases.RegisterUseCase
import com.java90.pruebamultimedialab.vo.Resource

class RegisterViewModel(private val registerUseCase: RegisterUseCase): ViewModel() {

    fun signUp(email: String, password: String) : LiveData<Resource<String>> {
        return registerUseCase.signUp(email, password)
    }
}