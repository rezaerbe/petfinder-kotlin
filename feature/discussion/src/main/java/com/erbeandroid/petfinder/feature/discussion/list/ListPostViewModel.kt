package com.erbeandroid.petfinder.feature.discussion.list

import androidx.lifecycle.ViewModel
import com.erbeandroid.petfinder.core.firebase.database.manager.FirebaseDatabaseManager
import com.erbeandroid.petfinder.core.firebase.database.model.Post
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class ListPostViewModel @Inject constructor(
    firebaseDatabaseManager: FirebaseDatabaseManager
) : ViewModel() {

    val listPost: Flow<List<Post>> = firebaseDatabaseManager.listPost()
}