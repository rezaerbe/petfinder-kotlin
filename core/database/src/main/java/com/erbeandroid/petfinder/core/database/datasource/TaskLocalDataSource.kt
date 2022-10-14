package com.erbeandroid.petfinder.core.database.datasource

import com.erbeandroid.petfinder.core.common.dispatcher.Dispatcher
import com.erbeandroid.petfinder.core.common.dispatcher.PetFinderDispatcher.*
import com.erbeandroid.petfinder.core.database.dao.TaskDao
import com.erbeandroid.petfinder.core.database.model.PostEntity
import com.erbeandroid.petfinder.core.database.model.UserEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class TaskLocalDataSource @Inject constructor(
    private val taskDao: TaskDao,
    @Dispatcher(IO) private val ioDispatcher: CoroutineDispatcher
) : LocalDataSource {

    override suspend fun insertUser(userEntity: UserEntity) {
        taskDao.insertUser(userEntity)
    }

    override suspend fun insertPost(postEntity: PostEntity) {
        taskDao.insertPost(postEntity)
    }

    override fun loadPost(): Flow<List<PostEntity>> =
        taskDao.loadPost().flowOn(ioDispatcher)

    override fun loadPostDetail(id: Int): Flow<PostEntity> =
        taskDao.loadPostDetail(id).flowOn(ioDispatcher)
}