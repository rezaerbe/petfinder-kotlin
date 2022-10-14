package com.erbeandroid.petfinder.feature.discussion.add

import androidx.lifecycle.ViewModel
import com.erbeandroid.petfinder.core.firebase.database.FirebaseDatabaseManager
import com.erbeandroid.petfinder.core.firebase.firestore.FirebaseFirestoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class AddPostViewModel @Inject constructor(
    private val firebaseDatabaseManager: FirebaseDatabaseManager,
    private val firebaseFirestoreManager: FirebaseFirestoreManager
) : ViewModel() {

    val state: StateFlow<String?> = firebaseDatabaseManager.state
    val stateNew: StateFlow<String?> = firebaseFirestoreManager.state

    fun addPost(title: String, description: String) {
        firebaseDatabaseManager.addPost(title, description)
    }

    fun addPostNew(title: String, description: String) {
        firebaseFirestoreManager.addPost(title, description)
    }
}