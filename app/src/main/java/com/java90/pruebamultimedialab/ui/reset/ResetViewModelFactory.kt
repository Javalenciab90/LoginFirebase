package com.java90.pruebamultimedialab.ui.reset

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.java90.pruebamultimedialab.usecases.ResetUseCase

class ResetViewModelFactory(private val resetUseCase: ResetUseCase): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(ResetUseCase::class.java).newInstance(resetUseCase)
    }
}