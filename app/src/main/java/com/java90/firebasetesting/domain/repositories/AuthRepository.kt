package com.java90.firebasetesting.domain.repositories

import com.java90.firebasetesting.utils.Resource

interface AuthRepository {
    suspend fun loginUserFirebase(email: String, password: String) : Resource<String>
    suspend fun registerUserFirebase(email: String, password: String) : Resource<String>
    //suspend fun resetPasswordUser(email: String) : Resource<String>
}