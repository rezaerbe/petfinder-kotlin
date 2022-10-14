package com.erbeandroid.petfinder.core.database.di

import android.content.Context
import androidx.room.Room
import com.erbeandroid.petfinder.core.database.dao.TaskDao
import com.erbeandroid.petfinder.core.database.datasource.LocalDataSource
import com.erbeandroid.petfinder.core.database.datasource.TaskLocalDataSource
import com.erbeandroid.petfinder.core.database.room.TaskDatabase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DatabaseModule {

    @Binds
    fun bindLocalDataSource(
        taskLocalDataSource: TaskLocalDataSource
    ): LocalDataSource

    companion object {
        @Provides
        @Singleton
        fun provideTaskDatabase(
            @ApplicationContext context: Context
        ): TaskDatabase {
            return Room
                .databaseBuilder(
                    context,
                    TaskDatabase::class.java,
                    "Task.db"
                )
                .fallbackToDestructiveMigration()
                .build()
        }

        @Provides
        @Singleton
        fun provideTaskDao(taskDatabase: TaskDatabase): TaskDao {
            return taskDatabase.taskDao()
        }
    }
}