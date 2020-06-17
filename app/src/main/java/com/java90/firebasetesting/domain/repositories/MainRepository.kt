package com.java90.firebasetesting.domain.repositories

import android.net.Uri
import com.java90.firebasetesting.utils.Resource

interface MainRepository {

    suspend fun signOutFromFirebase()

    suspend fun updateProfileInFirebase(imageUriPicked: Uri, userName: String) : Resource<String>

    suspend fun initProfile() : Resource<Uri>

}