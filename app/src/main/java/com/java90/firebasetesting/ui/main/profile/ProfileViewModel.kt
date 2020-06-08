package com.java90.pruebamultimedialab.ui.main.profile

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.java90.pruebamultimedialab.domain.usecases.ProfileUseCase
import com.java90.pruebamultimedialab.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

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