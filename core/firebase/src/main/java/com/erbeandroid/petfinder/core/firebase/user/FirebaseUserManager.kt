package com.erbeandroid.petfinder.core.firebase.user

import com.erbeandroid.petfinder.core.firebase.model.User
import kotlinx.coroutines.flow.MutableStateFlow

interface FirebaseUserManager {

    val state: MutableStateFlow<String?>

    fun currentUser(): User?
    fun updateUser(name: String)
    fun signOut()
}