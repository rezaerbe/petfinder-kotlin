package com.erbeandroid.petfinder.feature.login.phone

import androidx.lifecycle.ViewModel
import com.erbeandroid.petfinder.core.firebase.user.FirebaseUserManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val firebaseUserManager: FirebaseUserManager
) : ViewModel() {

    fun updateUser(name: String) {
        firebaseUserManager.updateUser(name)
    }

    val state: StateFlow<String?> = firebaseUserManager.state
}