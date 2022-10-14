package com.erbeandroid.petfinder.core.database.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.erbeandroid.petfinder.core.database.dao.TaskDao
import com.erbeandroid.petfinder.core.database.model.PostEntity
import com.erbeandroid.petfinder.core.database.model.UserEntity

@Database(
    entities = [UserEntity::class, PostEntity::class],
    version = 1,
    exportSchema = false
)
abstract class TaskDatabase : RoomDatabase() {

    abstract fun taskDao(): TaskDao
}