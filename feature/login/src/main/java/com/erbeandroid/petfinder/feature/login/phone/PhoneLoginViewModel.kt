package com.erbeandroid.petfinder.feature.login.phone

import androidx.lifecycle.ViewModel
import com.erbeandroid.petfinder.core.firebase.authentication.PhoneLoginManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class PhoneLoginViewModel @Inject constructor(
    private val phoneLoginManager: PhoneLoginManager
) : ViewModel() {

    fun send(phoneNumber: String) {
        phoneLoginManager.send(phoneNumber)
    }

    fun verify(code: String) {
        phoneLoginManager.verify(code)
    }

    val state: StateFlow<String?> = phoneLoginManager.state
    val code: StateFlow<String?> = phoneLoginManager.code
}