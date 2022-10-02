package com.erbeandroid.petfinder.feature.animal.common

sealed interface StateData<out T> {
    data class Success<T>(val data: T) : StateData<T>
    data class Error(val exception: Exception) : StateData<Nothing>
    object Loading : StateData<Nothing>
}