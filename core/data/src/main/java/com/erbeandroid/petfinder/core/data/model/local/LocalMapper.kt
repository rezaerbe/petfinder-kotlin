package com.erbeandroid.petfinder.core.data.model.local

import com.erbeandroid.petfinder.core.database.model.PostEntity
import com.erbeandroid.petfinder.core.database.model.UserEntity

fun UserEntity.asDomain() = User(
    uid = uid,
    name = name,
    phone = phone
)

fun User.asEntity() = UserEntity(
    uid = uid,
    name = name,
    phone = phone
)

fun PostEntity.asDomain() = Post(
    id = id,
    uid = uid,
    name = name,
    title = title,
    description = description
)

fun Post.asEntity() = PostEntity(
    uid = uid,
    name = name,
    title = title,
    description = description
)