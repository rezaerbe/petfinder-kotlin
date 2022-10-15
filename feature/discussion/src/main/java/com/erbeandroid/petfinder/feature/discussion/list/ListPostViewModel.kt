package com.erbeandroid.petfinder.feature.discussion.list

import androidx.lifecycle.ViewModel
import com.erbeandroid.petfinder.core.common.state.StateData
import com.erbeandroid.petfinder.core.firebase.database.FirebaseDatabaseManager
import com.erbeandroid.petfinder.core.firebase.firestore.FirebaseFirestoreManager
import com.erbeandroid.petfinder.core.firebase.model.Post
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class ListPostViewModel @Inject constructor(
    firebaseDatabaseManager: FirebaseDatabaseManager,
    firebaseFirestoreManager: FirebaseFirestoreManager
) : ViewModel() {

    val listPost: Flow<StateData<List<Post>>> = firebaseDatabaseManager.listPost()
    val listPostNew: Flow<StateData<List<Post>>> = firebaseFirestoreManager.listPost()
}