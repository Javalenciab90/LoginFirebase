package com.java90.firebasetesting.domain.usecases


import android.net.Uri
import com.java90.firebasetesting.domain.repositories.ProfileUserRepository
import com.java90.firebasetesting.utils.Resource

class ProfileUseCase(private val profileUserRepository: ProfileUserRepository) {

    suspend fun signOut() {
        profileUserRepository.signOutFromFirebase()
    }

    suspend fun updateProfile(imageUri: Uri, userName: String) : Resource<String> {
        return profileUserRepository.updateProfileInFirebase(imageUri, userName)
    }

    suspend fun initProfile() = profileUserRepository.initProfile()
}