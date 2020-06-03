package com.java90.pruebamultimedialab.ui.fragments.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.java90.pruebamultimedialab.domain.usecases.RegisterUseCase
import com.java90.pruebamultimedialab.utils.Resource
import kotlinx.coroutines.Dispatchers

class RegisterViewModel(private val registerUseCase: RegisterUseCase) : ViewModel() {

    fun registerUser(email: String, password: String) = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            val response = registerUseCase.registerUser(email, password)
            emit(response)
        }catch (e:Exception) {
            emit(Resource.Failure(e.message.toString(), e))
        }
    }
}