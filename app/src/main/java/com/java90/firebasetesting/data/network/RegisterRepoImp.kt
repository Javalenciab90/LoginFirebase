package com.java90.pruebamultimedialab.data.network

import com.google.firebase.auth.FirebaseAuth
import com.java90.pruebamultimedialab.domain.repositories.RegisterRepository
import com.java90.pruebamultimedialab.utils.Resource
import kotlinx.coroutines.tasks.await

class RegisterRepoImp : RegisterRepository{

    override suspend fun registerUserFirebase(email: String, password: String) : Resource<String> {

        val auth = FirebaseAuth.getInstance()
        auth.createUserWithEmailAndPassword(email, password).await()
        return Resource.Success("Registro exitoso")
    }
}