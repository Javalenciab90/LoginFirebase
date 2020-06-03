package com.java90.pruebamultimedialab.domain.usecases

import com.java90.pruebamultimedialab.domain.repositories.LoginRepository
import com.java90.pruebamultimedialab.utils.Resource

class LoginUseCase(private val loginRepository: LoginRepository) {
    suspend fun loginUser(email: String, password: String) : Resource<String> {
        return loginRepository.loginUserFirebase(email, password)
    }

}