package com.java90.pruebamultimedialab.domain

import androidx.lifecycle.LiveData
import com.java90.pruebamultimedialab.vo.Resource

interface RegisterRepository {
    suspend fun signUpFirebase(email: String, password: String) : Resource<String>
}