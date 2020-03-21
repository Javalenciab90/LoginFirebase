package com.java90.pruebamultimedialab.usecases

import androidx.lifecycle.LiveData
import com.java90.pruebamultimedialab.domain.LoginRepository
import com.java90.pruebamultimedialab.vo.Resource

class LoginUseCase(private val loginRepository: LoginRepository){

    suspend fun signIn(email: String, password: String): Resource<String> {
        return loginRepository.signInFirebase(email, password)
    }
}