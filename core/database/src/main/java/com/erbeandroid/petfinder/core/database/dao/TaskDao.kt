package com.erbeandroid.petfinder.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.erbeandroid.petfinder.core.database.model.PostEntity
import com.erbeandroid.petfinder.core.database.model.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(userEntity: UserEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPost(postEntity: PostEntity)

    @Query("SELECT * FROM post")
    fun loadPost(): Flow<List<PostEntity>>

    @Query("SELECT * FROM post WHERE id = :id")
    fun loadPostDetail(id: Int): Flow<PostEntity>
}