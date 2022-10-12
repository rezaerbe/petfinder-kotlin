package com.erbeandroid.petfinder.feature.discussion.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.erbeandroid.petfinder.core.firebase.database.manager.FirebaseDatabaseManager
import com.erbeandroid.petfinder.core.firebase.database.model.Post
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class DetailPostViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val firebaseDatabaseManager: FirebaseDatabaseManager
) : ViewModel() {

    val post: StateFlow<Post?> = firebaseDatabaseManager.postDetail

    init {
        detailPost()
    }

    private fun detailPost() {
        val key = savedStateHandle.get<String>("key") ?: ""
        firebaseDatabaseManager.detailPost(key)
    }
}