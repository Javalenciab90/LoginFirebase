package com.java90.pruebamultimedialab.data.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.java90.pruebamultimedialab.domain.ResetRepository
import com.java90.pruebamultimedialab.vo.Resource

class ResetRepoImpl : ResetRepository {

    override fun resetPasswordFirebase(email: String): LiveData<Resource<String>> {
        val result = MutableLiveData<Resource<String>>()
        FirebaseAuth.getInstance().sendPasswordResetEmail(email)
            .addOnCompleteListener{
                if(it.isSuccessful) {
                    result.value =  Resource.Success("Cambio exitoso")
                }
            }
            .addOnFailureListener {
                result.value = Resource.Failure("Error: ${it.message}")
            }

        return result
    }

}