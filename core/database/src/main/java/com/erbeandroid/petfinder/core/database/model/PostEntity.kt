package com.erbeandroid.petfinder.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "post")
data class PostEntity(
    @PrimaryKey(autoGenerate = true) val id: Int? = 0,
    val uid: String?,
    val name: String?,
    val title: String?,
    val description: String?
)