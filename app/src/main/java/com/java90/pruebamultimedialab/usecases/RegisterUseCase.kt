package com.java90.pruebamultimedialab.usecases

import androidx.lifecycle.LiveData
import com.java90.pruebamultimedialab.domain.RegisterRepository
import com.java90.pruebamultimedialab.vo.Resource

class RegisterUseCase (private val registerRepository: RegisterRepository) {

    fun signUp(email: String, password: String) : LiveData<Resource<String>> {
        return registerRepository.signUpFirebase(email, password)
    }
}