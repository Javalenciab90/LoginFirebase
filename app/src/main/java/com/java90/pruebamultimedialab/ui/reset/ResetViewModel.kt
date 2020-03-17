package com.java90.pruebamultimedialab.ui.reset

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.java90.pruebamultimedialab.usecases.ResetUseCase
import com.java90.pruebamultimedialab.vo.Resource

class ResetViewModel (private val resetUseCase: ResetUseCase) : ViewModel(){
    fun resetPassWord(email: String) : LiveData<Resource<String>> {
        return resetUseCase.resetPassWord(email)
    }

}