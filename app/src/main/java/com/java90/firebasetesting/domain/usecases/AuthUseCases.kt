package com.java90.firebasetesting.domain.usecases

import com.java90.firebasetesting.domain.repositories.AuthRepository
import com.java90.firebasetesting.utils.Resource

class AuthUseCases(private val authRepository: AuthRepository) {
    suspend fun loginUser(email: String, password: String) : Resource<String> {
        return authRepository.loginUserFirebase(email, password)
    }

    suspend fun registerUser(email: String, password: String) : Resource<String> {
        return authRepository.registerUserFirebase(email, password)
    }
}