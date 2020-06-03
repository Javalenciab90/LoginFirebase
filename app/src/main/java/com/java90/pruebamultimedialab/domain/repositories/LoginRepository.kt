package com.java90.pruebamultimedialab.domain.repositories

import com.java90.pruebamultimedialab.utils.Resource

interface LoginRepository {
    suspend fun loginUserFirebase(email: String, password: String) : Resource<String>
}