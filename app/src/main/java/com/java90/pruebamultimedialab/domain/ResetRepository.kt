package com.java90.pruebamultimedialab.domain

import com.java90.pruebamultimedialab.vo.Resource

interface ResetRepository {

    suspend fun resetPasswordFirebase(email: String) : Resource<String>
}