package com.java90.pruebamultimedialab.data.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.java90.pruebamultimedialab.domain.RegisterRepository
import com.java90.pruebamultimedialab.vo.Resource
import kotlinx.coroutines.tasks.await

class RegisterRepoImpl : RegisterRepository{

    override suspend fun signUpFirebase(email: String, password: String) : Resource<String> {

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).await()
        return Resource.Success("Registro Exitoso")
    }
}
