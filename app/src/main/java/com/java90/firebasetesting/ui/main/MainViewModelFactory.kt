package com.java90.firebasetesting.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.java90.firebasetesting.domain.usecases.MainUseCases

class MainViewModelFactory(private val mainUseCases: MainUseCases) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(mainUseCases) as T
    }
}