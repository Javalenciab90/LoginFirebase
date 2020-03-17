package com.java90.pruebamultimedialab.data.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.java90.pruebamultimedialab.domain.RegisterRepository
import com.java90.pruebamultimedialab.vo.Resource

class RegisterRepoImpl : RegisterRepository{

    override fun signUpFirebase(email: String, password: String): LiveData<Resource<String>> {
        val result = MutableLiveData<Resource<String>>()
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener{
                if(it.isSuccessful) {
                    result.value =  Resource.Success("Registro exitoso")
                }
            }
            .addOnFailureListener {
                result.value = Resource.Failure("Error: ${it.message}")
            }

        return result
    }
}
