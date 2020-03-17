package com.java90.pruebamultimedialab.domain

import androidx.lifecycle.LiveData
import com.java90.pruebamultimedialab.vo.Resource

interface ResetRepository {

    fun resetPasswordFirebase(email: String) : LiveData<Resource<String>>
}