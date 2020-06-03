package com.java90.pruebamultimedialab.domain.repositories

import com.java90.pruebamultimedialab.utils.Resource

interface RegisterRepository {

    suspend fun registerUserFirebase(email: String, password: String) : Resource<String>
}