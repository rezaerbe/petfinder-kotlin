package com.erbeandroid.petfinder.feature.discussion.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.erbeandroid.petfinder.core.firebase.database.FirebaseDatabaseManager
import com.erbeandroid.petfinder.core.firebase.firestore.FirebaseFirestoreManager
import com.erbeandroid.petfinder.core.firebase.model.Post
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class DetailPostViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val firebaseDatabaseManager: FirebaseDatabaseManager,
    private val firebaseFirestoreManager: FirebaseFirestoreManager
) : ViewModel() {

    val post: StateFlow<Post?> = firebaseDatabaseManager.postDetail
    val postNew: StateFlow<Post?> = firebaseFirestoreManager.postDetail

    init {
        detailPost()
        detailPostNew()
    }

    private fun detailPost() {
        val key = savedStateHandle.get<String>("key") ?: ""
        firebaseDatabaseManager.detailPost(key)
    }

    private fun detailPostNew() {
        val key = savedStateHandle.get<String>("key") ?: ""
        firebaseFirestoreManager.detailPost(key)
    }
}