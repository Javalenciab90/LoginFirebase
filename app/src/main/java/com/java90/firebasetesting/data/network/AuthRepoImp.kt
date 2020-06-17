package com.java90.firebasetesting.data.network

import com.google.firebase.auth.FirebaseAuth
import com.java90.firebasetesting.domain.repositories.AuthRepository
import com.java90.firebasetesting.utils.Resource
import kotlinx.coroutines.tasks.await

class AuthRepoImp : AuthRepository {

    val auth = FirebaseAuth.getInstance()

    override suspend fun loginUserFirebase(email: String, password: String) : Resource<String> {
        auth.signInWithEmailAndPassword(email, password).await()
        return Resource.Success("Acceso exitoso.")
    }

    override suspend fun registerUserFirebase(email: String, password: String): Resource<String> {
        auth.createUserWithEmailAndPassword(email, password).await()
        return Resource.Success("Registro exitoso")
    }

}