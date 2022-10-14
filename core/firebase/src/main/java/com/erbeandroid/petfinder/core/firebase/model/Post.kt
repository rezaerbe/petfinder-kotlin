package com.erbeandroid.petfinder.core.firebase.model

import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Post(
    var key: String? = "",
    var uid: String? = "",
    var name: String? = "",
    var title: String? = "",
    var description: String? = ""
) {

    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "key" to key,
            "uid" to uid,
            "name" to name,
            "title" to title,
            "description" to description
        )
    }
}