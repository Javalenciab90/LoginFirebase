package com.java90.pruebamultimedialab.data.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.java90.pruebamultimedialab.domain.ResetRepository
import com.java90.pruebamultimedialab.vo.Resource
import kotlinx.coroutines.tasks.await

class ResetRepoImpl : ResetRepository {

    override suspend fun resetPasswordFirebase(email: String): Resource<String> {
        FirebaseAuth.getInstance().sendPasswordResetEmail(email).await()
            return Resource.Success("Verifique su correo. Siga las indicaciones.")
    }

}