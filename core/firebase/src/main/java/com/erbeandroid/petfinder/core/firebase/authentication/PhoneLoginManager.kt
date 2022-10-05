package com.erbeandroid.petfinder.core.firebase.authentication

import kotlinx.coroutines.flow.MutableStateFlow

interface PhoneLoginManager {

    val state: MutableStateFlow<String?>
    val code: MutableStateFlow<String?>

    fun send(phoneNumber: String)
    fun resend(phoneNumber: String)
    fun verify(code: String)
}