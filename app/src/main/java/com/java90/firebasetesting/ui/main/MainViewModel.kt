package com.java90.firebasetesting.ui.main

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.java90.firebasetesting.domain.usecases.MainUseCases
import com.java90.firebasetesting.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val mainUseCases: MainUseCases) : ViewModel() {

    fun signOut() = viewModelScope.launch {
        mainUseCases.signOut()
    }

    fun updateProfile(imageUri: Uri, userName: String) = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            val response = mainUseCases.updateProfile(imageUri, userName)
            emit(response)
        }catch (e: Exception) {
            emit(Resource.Failure(e.message.toString()))
        }
    }

    fun initProfileUser() = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            val response = mainUseCases.initProfile()
            emit(response)
        }catch (e: Exception) {
            emit(Resource.Failure(e.message.toString()))
        }
    }
}