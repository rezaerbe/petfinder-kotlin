package com.erbeandroid.petfinder.core.firebase.user

import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.MutableStateFlow

interface FirebaseUserManager {

    val state: MutableStateFlow<String?>

    fun currentUser(): FirebaseUser?
    fun updateUser(name: String)
    fun signOut()
}