package com.erbeandroid.petfinder

import androidx.lifecycle.ViewModel
import com.erbeandroid.petfinder.core.common.connection.ConnectionManager
import com.erbeandroid.petfinder.core.firebase.model.User
import com.erbeandroid.petfinder.core.firebase.user.FirebaseUserManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val firebaseUserManager: FirebaseUserManager,
    connectionManager: ConnectionManager
) : ViewModel() {

    val connectionStatus: Flow<Boolean> = connectionManager.connectionStatus()

    fun currentUser(): User? {
        return firebaseUserManager.currentUser()
    }
}