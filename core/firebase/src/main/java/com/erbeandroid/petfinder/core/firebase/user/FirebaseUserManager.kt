package com.erbeandroid.petfinder.core.firebase.user

import kotlinx.coroutines.flow.MutableStateFlow

interface FirebaseUserManager {

    val state: MutableStateFlow<String?>

    fun currentUser(): String?
    fun updateUser(name: String)
    fun signOut()
}