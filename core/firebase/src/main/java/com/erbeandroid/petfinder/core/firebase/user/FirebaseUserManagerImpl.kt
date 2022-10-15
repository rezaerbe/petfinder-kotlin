package com.erbeandroid.petfinder.core.firebase.user

import com.erbeandroid.petfinder.core.firebase.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class FirebaseUserManagerImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : FirebaseUserManager {

    private val user = firebaseAuth.currentUser

    override val state = MutableStateFlow<String?>(null)

    override fun currentUser(): User? {
        return user?.let {
            User(user.uid, user.displayName, user.phoneNumber)
        }
    }

    override fun updateUser(name: String) {
        val profileUpdate = userProfileChangeRequest {
            displayName = name
        }
        if (user != null) {
            user.updateProfile(profileUpdate)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        state.value = "Success"
                    } else {
                        state.value = "Failed"
                    }
                }
        } else {
            state.value = "User null"
        }
    }

    override fun signOut() {
        firebaseAuth.signOut()
    }
}