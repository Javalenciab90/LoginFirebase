package com.java90.pruebamultimedialab.ui.main.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.java90.pruebamultimedialab.domain.usecases.ProfileUseCase

class ProfileViewModelFactory(private val profileUseCase: ProfileUseCase) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProfileViewModel(profileUseCase) as T
    }
}