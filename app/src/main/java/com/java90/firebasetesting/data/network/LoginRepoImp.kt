package com.java90.pruebamultimedialab.data.network

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.java90.pruebamultimedialab.domain.repositories.LoginRepository
import com.java90.pruebamultimedialab.utils.Resource

class LoginRepoImp : LoginRepository {

    override suspend fun loginUserFirebase(email: String, password: String) : Resource<String> {
        val auth = FirebaseAuth.getInstance()
        auth.signInWithEmailAndPassword(email, password)
        return Resource.Success("Acceso exitoso.")
    }
}