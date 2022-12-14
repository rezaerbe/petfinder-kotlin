package com.erbeandroid.petfinder.feature.login.profile

import androidx.lifecycle.ViewModel
import com.erbeandroid.petfinder.core.firebase.database.FirebaseDatabaseManager
import com.erbeandroid.petfinder.core.firebase.firestore.FirebaseFirestoreManager
import com.erbeandroid.petfinder.core.firebase.user.FirebaseUserManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val firebaseUserManager: FirebaseUserManager,
    private val firebaseDatabaseManager: FirebaseDatabaseManager,
    private val firebaseFirestoreManager: FirebaseFirestoreManager
) : ViewModel() {

    val state: StateFlow<String?> = firebaseUserManager.state

    fun updateUser(name: String) {
        firebaseUserManager.updateUser(name)
    }

    fun addUser() {
        firebaseDatabaseManager.addUser()
        firebaseFirestoreManager.addUser()
    }
}