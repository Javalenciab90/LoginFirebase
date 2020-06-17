package com.java90.firebasetesting.domain.usecases


import android.net.Uri
import com.java90.firebasetesting.domain.repositories.MainRepository
import com.java90.firebasetesting.utils.Resource

class MainUseCases(private val mainRepository: MainRepository) {

    suspend fun signOut() {
        mainRepository.signOutFromFirebase()
    }

    suspend fun updateProfile(imageUri: Uri, userName: String) : Resource<String> {
        return mainRepository.updateProfileInFirebase(imageUri, userName)
    }

    suspend fun initProfile() = mainRepository.initProfile()
}