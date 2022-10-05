package com.erbeandroid.petfinder.core.firebase.user

import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class FirebaseUserManagerImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : FirebaseUserManager {

    override fun currentUser(): String? {
        return firebaseAuth.currentUser?.phoneNumber
    }

    override fun signOut() {
        firebaseAuth.signOut()
    }
}