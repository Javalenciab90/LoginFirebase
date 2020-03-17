package com.java90.pruebamultimedialab.data.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.java90.pruebamultimedialab.domain.LoginRepository
import com.java90.pruebamultimedialab.vo.Resource

class LoginRepoImpl : LoginRepository {

    override fun signInFirebase(email: String, password: String): LiveData<Resource<String>> {
            val result = MutableLiveData<Resource<String>>()
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener{
                    if(it.isSuccessful) {
                        result.value =  Resource.Success("Inicio de sesión exitoso")
                    }
                }
                .addOnFailureListener {
                    result.value = Resource.Failure("Error: ${it.message}")
                }

            return result
        }
    }
