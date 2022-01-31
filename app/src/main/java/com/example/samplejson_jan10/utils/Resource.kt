package com.example.samplejson_jan10.utils

sealed interface Resource<out T> {
    object Loading: Resource<Nothing>
    data class Success<T>(val data: T): Resource<T>
    data class Error(val message: String): Resource<Nothing>
}