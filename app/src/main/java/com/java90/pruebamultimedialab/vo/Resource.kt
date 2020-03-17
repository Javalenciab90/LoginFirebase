package com.java90.pruebamultimedialab.vo

sealed class Resource<out T> {
    class Loading<out T> : Resource<T>()
    data class Success<out T>(val data: T) : Resource<T>()
    data class Failure<out T>(val error: String) : Resource<T>()
}