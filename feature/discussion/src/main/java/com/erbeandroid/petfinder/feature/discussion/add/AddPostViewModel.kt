package com.erbeandroid.petfinder.feature.discussion.add

import androidx.lifecycle.ViewModel
import com.erbeandroid.petfinder.core.firebase.database.manager.FirebaseDatabaseManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class AddPostViewModel @Inject constructor(
    private val firebaseDatabaseManager: FirebaseDatabaseManager
) : ViewModel() {

    val state: StateFlow<String?> = firebaseDatabaseManager.state

    fun addPost(title: String, description: String) {
        firebaseDatabaseManager.addPost(title, description)
    }
}