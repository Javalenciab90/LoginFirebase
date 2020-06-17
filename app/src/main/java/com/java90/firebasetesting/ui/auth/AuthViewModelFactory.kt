package com.java90.firebasetesting.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.java90.firebasetesting.domain.usecases.AuthUseCases

class AuthViewModelFactory(private val authUseCases: AuthUseCases) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AuthViewModel(authUseCases) as T
    }
}