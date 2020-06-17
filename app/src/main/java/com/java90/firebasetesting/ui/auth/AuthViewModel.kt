package com.java90.firebasetesting.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.java90.firebasetesting.domain.usecases.AuthUseCases
import com.java90.firebasetesting.utils.Resource
import kotlinx.coroutines.Dispatchers

class AuthViewModel(private val authUseCases: AuthUseCases) : ViewModel() {

    fun loginUser(email: String, password: String) = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            val response = authUseCases.loginUser(email, password)
            emit(response)
        }catch (e: Exception) {
            emit(Resource.Failure(e.message.toString()))
        }
    }

    fun registerUser(email: String, password: String) = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            val response = authUseCases.registerUser(email, password)
            emit(response)
        }catch (e:Exception) {
            emit(Resource.Failure(e.message.toString(), e))
        }
    }

}