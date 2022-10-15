package com.erbeandroid.petfinder.core.firebase.model

import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Post(
    var id: String? = "",
    var uid: String? = "",
    var name: String? = "",
    var title: String? = "",
    var description: String? = ""
) {

    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "id" to id,
            "uid" to uid,
            "name" to name,
            "title" to title,
            "description" to description
        )
    }
}