package com.java90.firebasetesting.data.network

import android.net.Uri
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.storage.FirebaseStorage
import com.java90.firebasetesting.domain.repositories.ProfileUserRepository
import com.java90.firebasetesting.utils.Resource
import kotlinx.coroutines.tasks.await

class ProfileUserRepoImp : ProfileUserRepository {

    private val auth = FirebaseAuth.getInstance()
    private val storeRef = FirebaseStorage.getInstance()

    override suspend fun signOutFromFirebase() {
        auth.signOut()
    }

    override suspend fun updateProfileInFirebase(
        imageUriPicked: Uri,
        userName: String) : Resource<String> {

        storeRef.reference.child("/images/${auth.currentUser?.uid}")
            .putFile(imageUriPicked).await()

        val updates = UserProfileChangeRequest.Builder()
            .setDisplayName(userName)
            .setPhotoUri(imageUriPicked)
            .build()

        auth.currentUser?.updateProfile(updates)?.await()

        return Resource.Success("Updated Data Profile successfully")
    }

    override suspend fun initProfile() : Resource<Uri> {

        val imageUriUrl = storeRef.getReferenceFromUrl("gs://pruebamultimedialab.appspot.com/")
            .child("images/${auth.currentUser?.uid}")
            .downloadUrl.await()

        return Resource.Success(imageUriUrl)
    }
}