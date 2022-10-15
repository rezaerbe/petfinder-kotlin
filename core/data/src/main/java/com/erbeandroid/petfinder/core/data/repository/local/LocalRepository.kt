package com.erbeandroid.petfinder.core.data.repository.local

import com.erbeandroid.petfinder.core.data.model.local.Post
import com.erbeandroid.petfinder.core.data.model.local.User
import kotlinx.coroutines.flow.Flow

interface LocalRepository {

    suspend fun insertUser(user: User)
    suspend fun insertPost(post: Post)

    fun loadPost(): Flow<List<Post>>
    fun loadPostDetail(id: Int): Flow<Post>
}