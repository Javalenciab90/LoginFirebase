package com.java90.firebasetesting.ui.main

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.java90.firebasetesting.domain.usecases.ProfileUseCase
import com.java90.firebasetesting.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileViewModel(private val profileUseCase: ProfileUseCase) : ViewModel() {

    fun signOut() = viewModelScope.launch {
        profileUseCase.signOut()
    }

    fun updateProfile(imageUri: Uri, userName: String) = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            val response = profileUseCase.updateProfile(imageUri, userName)
            emit(response)
        }catch (e: Exception) {
            emit(Resource.Failure(e.message.toString()))
        }
    }

    fun initProfileUser() = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            val response = profileUseCase.initProfile()
            emit(response)
        }catch (e: Exception) {
            emit(Resource.Failure(e.message.toString()))
        }
    }
}