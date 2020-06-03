package com.java90.pruebamultimedialab.domain.repositories

import android.net.Uri
import com.java90.pruebamultimedialab.utils.Resource

interface ProfileUserRepository {

    suspend fun signOutFromFirebase()

    suspend fun updateProfileInFirebase(imageUriPicked: Uri, userName: String) : Resource<String>

    suspend fun initProfile() : Resource<Uri>

}