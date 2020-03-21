package com.java90.pruebamultimedialab.usecases

import com.java90.pruebamultimedialab.domain.RegisterRepository
import com.java90.pruebamultimedialab.vo.Resource

class RegisterUseCase (private val registerRepository: RegisterRepository) {

    suspend fun signUp(email: String, password: String) : Resource<String> {
        return registerRepository.signUpFirebase(email, password)
    }
}