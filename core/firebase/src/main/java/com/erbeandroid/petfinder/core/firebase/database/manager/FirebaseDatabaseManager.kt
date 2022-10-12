package com.erbeandroid.petfinder.core.firebase.database.manager

import com.erbeandroid.petfinder.core.firebase.database.model.Post
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

interface FirebaseDatabaseManager {

    val state: MutableStateFlow<String?>
    val listPost: Flow<List<Post>>
    val postDetail: MutableStateFlow<Post?>

    fun postUser()
    fun addPost(title: String, description: String)
    fun detailPost(key: String)
}