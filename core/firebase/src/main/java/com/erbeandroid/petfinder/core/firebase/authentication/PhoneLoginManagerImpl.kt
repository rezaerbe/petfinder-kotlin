package com.erbeandroid.petfinder.core.firebase.authentication

import android.util.Log
import androidx.fragment.app.FragmentActivity
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.coroutines.flow.MutableStateFlow
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class PhoneLoginManagerImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val activity: FragmentActivity
) : PhoneLoginManager {

    override val state = MutableStateFlow<String?>(null)
    override val code = MutableStateFlow<String?>(null)

    private lateinit var verificationIdState: String
    private lateinit var tokenState: PhoneAuthProvider.ForceResendingToken

    override fun send(phoneNumber: String) {
        val options = PhoneAuthOptions.newBuilder(firebaseAuth)
            .setPhoneNumber(phoneNumber)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(activity)
            .setCallbacks(callback)
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    override fun resend(phoneNumber: String) {
        val options = PhoneAuthOptions.newBuilder(firebaseAuth)
            .setPhoneNumber(phoneNumber)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(activity)
            .setCallbacks(callback)
            .setForceResendingToken(tokenState)
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    override fun verify(code: String) {
        val credential = PhoneAuthProvider.getCredential(verificationIdState, code)
        signInWithPhoneAuthCredential(credential)
    }

    private val callback = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            state.value = "onVerificationCompleted"
            code.value = credential.smsCode

            signInWithPhoneAuthCredential(credential)
        }

        override fun onVerificationFailed(exception: FirebaseException) {
            state.value = "onVerificationFailed"

            if (exception is FirebaseAuthInvalidCredentialsException) {
                Log.d("TAG", "Invalid request")
            } else if (exception is FirebaseTooManyRequestsException) {
                Log.d("TAG", "The SMS quota for the project has been exceeded")
            }
        }

        override fun onCodeSent(
            verificationId: String,
            token: PhoneAuthProvider.ForceResendingToken
        ) {
            state.value = "onCodeSent"

            verificationIdState = verificationId
            tokenState = token
        }
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val name = firebaseAuth.currentUser?.displayName
                    if (name != null) {
                        state.value = "Success"
                    } else {
                        state.value = "Update"
                    }
                } else {
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        Log.d("TAG", "The verification code entered was invalid")
                    }
                    state.value = "Failed"
                }
            }
    }
}