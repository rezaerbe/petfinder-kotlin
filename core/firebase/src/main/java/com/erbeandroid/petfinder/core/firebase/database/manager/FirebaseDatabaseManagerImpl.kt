package com.erbeandroid.petfinder.core.firebase.database.manager

import android.util.Log
import com.erbeandroid.petfinder.core.firebase.database.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import javax.inject.Inject

class FirebaseDatabaseManagerImpl @Inject constructor(
    private val reference: DatabaseReference,
    private val firebaseAuth: FirebaseAuth
) : FirebaseDatabaseManager {

    override fun postUser() {
        val user = firebaseAuth.currentUser
        user?.let {
            val postUser = User(it.displayName, it.phoneNumber)
            reference
                .child("users")
                .child(it.uid)
                .setValue(postUser)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d("TAG", "postUser: Success")
                    } else {
                        Log.d("TAG", "postUser: Failed")
                    }
                }
        }
    }
}