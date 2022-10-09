package com.erbeandroid.petfinder

import androidx.lifecycle.ViewModel
import com.erbeandroid.petfinder.core.firebase.user.FirebaseUserManager
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val firebaseUserManager: FirebaseUserManager
) : ViewModel() {

    fun currentUser(): FirebaseUser? {
        return firebaseUserManager.currentUser()
    }
}