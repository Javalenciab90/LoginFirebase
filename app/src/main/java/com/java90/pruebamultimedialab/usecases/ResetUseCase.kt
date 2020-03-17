package com.java90.pruebamultimedialab.usecases

import androidx.lifecycle.LiveData
import com.java90.pruebamultimedialab.domain.ResetRepository
import com.java90.pruebamultimedialab.vo.Resource

class ResetUseCase(private val resetRepository: ResetRepository){

    fun resetPassWord(email: String): LiveData<Resource<String>> {
        return resetRepository.resetPasswordFirebase(email)
    }
}