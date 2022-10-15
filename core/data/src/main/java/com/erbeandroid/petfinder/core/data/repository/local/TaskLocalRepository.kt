package com.erbeandroid.petfinder.core.data.repository.local

import com.erbeandroid.petfinder.core.common.dispatcher.Dispatcher
import com.erbeandroid.petfinder.core.common.dispatcher.PetFinderDispatcher.*
import com.erbeandroid.petfinder.core.data.model.local.Post
import com.erbeandroid.petfinder.core.data.model.local.User
import com.erbeandroid.petfinder.core.data.model.local.asDomain
import com.erbeandroid.petfinder.core.data.model.local.asEntity
import com.erbeandroid.petfinder.core.database.datasource.LocalDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TaskLocalRepository @Inject constructor(
    private val localDataSource: LocalDataSource,
    @Dispatcher(Default) private val defaultDispatcher: CoroutineDispatcher
) : LocalRepository {

    override suspend fun insertUser(user: User) {
        localDataSource.insertUser(user.asEntity())
    }

    override suspend fun insertPost(post: Post) {
        localDataSource.insertPost(post.asEntity())
    }

    override fun loadPost(): Flow<List<Post>> =
        localDataSource.loadPost().map { posts ->
            posts.map { post ->
                post.asDomain()
            }
        }.flowOn(defaultDispatcher)


    override fun loadPostDetail(id: Int): Flow<Post> =
        localDataSource.loadPostDetail(id).map { post ->
            post.asDomain()
        }.flowOn(defaultDispatcher)
}