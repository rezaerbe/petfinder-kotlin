package com.erbeandroid.petfinder.core.database.datasource

import com.erbeandroid.petfinder.core.database.model.PostEntity
import com.erbeandroid.petfinder.core.database.model.UserEntity
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {

    suspend fun insertUser(userEntity: UserEntity)
    suspend fun insertPost(postEntity: PostEntity)

    fun loadPost(): Flow<List<PostEntity>>
    fun loadPostDetail(id: Int): Flow<PostEntity>
}