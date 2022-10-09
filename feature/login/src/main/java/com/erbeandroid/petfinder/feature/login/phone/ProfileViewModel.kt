package com.erbeandroid.petfinder.feature.login.phone

import androidx.lifecycle.ViewModel
import com.erbeandroid.petfinder.core.firebase.database.manager.FirebaseDatabaseManager
import com.erbeandroid.petfinder.core.firebase.user.FirebaseUserManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val firebaseUserManager: FirebaseUserManager,
    private val firebaseDatabaseManager: FirebaseDatabaseManager
) : ViewModel() {

    fun updateUser(name: String) {
        firebaseUserManager.updateUser(name)
    }

    fun postUser() {
        firebaseDatabaseManager.postUser()
    }

    val state: StateFlow<String?> = firebaseUserManager.state
}