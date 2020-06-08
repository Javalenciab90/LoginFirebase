package com.java90.pruebamultimedialab.domain.usecases

import com.java90.pruebamultimedialab.domain.repositories.RegisterRepository
import com.java90.pruebamultimedialab.utils.Resource

class RegisterUseCase(private val registerRepository: RegisterRepository)  {

    suspend fun registerUser(email: String, password: String) : Resource<String> {
        return registerRepository.registerUserFirebase(email, password)
    }
}
