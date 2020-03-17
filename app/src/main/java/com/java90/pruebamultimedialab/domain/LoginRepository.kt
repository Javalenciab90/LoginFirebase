package com.java90.pruebamultimedialab.domain

import androidx.lifecycle.LiveData
import com.java90.pruebamultimedialab.vo.Resource

interface LoginRepository {

    fun signInFirebase(email: String, password: String) : LiveData<Resource<String>>
}