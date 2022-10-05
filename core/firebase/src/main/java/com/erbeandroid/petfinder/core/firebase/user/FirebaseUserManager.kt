package com.erbeandroid.petfinder.core.firebase.user

interface FirebaseUserManager {

    fun currentUser(): String?
    fun signOut()
}