package com.java90.pruebamultimedialab.domain.repositories

interface ResetPasswordRepository {

suspend fun resetPasswordUser(email: String)

}