package com.erbeandroid.petfinder.core.firebase.firestore

import com.erbeandroid.petfinder.core.common.state.StateData
import com.erbeandroid.petfinder.core.firebase.model.Post
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

interface FirebaseFirestoreManager {

    val state: MutableStateFlow<String?>
    val postDetail: MutableStateFlow<StateData<Post?>>

    fun addUser()
    fun addPost(title: String, description: String)
    fun listPost(): Flow<StateData<List<Post>>>
    fun detailPost(key: String)
}