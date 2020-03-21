package com.java90.pruebamultimedialab.data.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.java90.pruebamultimedialab.domain.LoginRepository
import com.java90.pruebamultimedialab.vo.Resource
import kotlinx.coroutines.tasks.await

class LoginRepoImpl : LoginRepository {

    override suspend fun signInFirebase(email: String, password: String): Resource<String> {

            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).await()
                return Resource.Success("Inicio de sesion Exitoso.")
        }
    }
