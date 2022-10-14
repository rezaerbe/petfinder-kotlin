package com.erbeandroid.petfinder.core.firebase.model

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class User(
    var name: String? = "",
    var phone: String? = ""
)