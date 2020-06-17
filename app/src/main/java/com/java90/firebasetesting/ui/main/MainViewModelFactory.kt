package com.java90.firebasetesting.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.java90.firebasetesting.domain.usecases.ProfileUseCase

class ProfileViewModelFactory(private val profileUseCase: ProfileUseCase) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProfileViewModel(profileUseCase) as T
    }
}