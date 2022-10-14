package com.erbeandroid.petfinder.core.data.repository

import com.erbeandroid.petfinder.core.data.model.Post
import com.erbeandroid.petfinder.core.data.model.User
import kotlinx.coroutines.flow.Flow

interface LocalRepository {

    suspend fun insertUser(user: User)
    suspend fun insertPost(post: Post)

    fun loadPost(): Flow<List<Post>>
    fun loadPostDetail(id: Int): Flow<Post>
}