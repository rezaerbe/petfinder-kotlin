package com.erbeandroid.petfinder.core.firebase.database

import com.erbeandroid.petfinder.core.firebase.model.Post
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

interface FirebaseDatabaseManager {

    val state: MutableStateFlow<String?>
    val postDetail: MutableStateFlow<Post?>

    fun addUser()
    fun addPost(title: String, description: String)
    fun listPost(): Flow<List<Post>>
    fun detailPost(key: String)
}