package com.erbeandroid.petfinder.core.firebase.user

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class FirebaseUserManagerImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : FirebaseUserManager {

    override val state = MutableStateFlow<String?>(null)

    override fun currentUser(): String? {
        return firebaseAuth.currentUser?.displayName
    }

    override fun updateUser(name: String) {
        val profileUpdate = userProfileChangeRequest {
            displayName = name
        }
        firebaseAuth.currentUser?.updateProfile(profileUpdate)
            ?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("TAG", "Update success")
                    state.value = "Success"
                } else {
                    Log.d("TAG", "Update failed")
                    state.value = "Failed"
                }
            }
    }

    override fun signOut() {
        firebaseAuth.signOut()
    }
}