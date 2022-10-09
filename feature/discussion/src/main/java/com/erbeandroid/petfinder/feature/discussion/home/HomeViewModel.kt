package com.erbeandroid.petfinder.feature.discussion.home

import androidx.lifecycle.ViewModel
import com.erbeandroid.petfinder.core.firebase.user.FirebaseUserManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val firebaseUserManager: FirebaseUserManager
) : ViewModel() {

/*    fun currentUser(): String? {
        return firebaseUserManager.currentUser()
    }*/
}