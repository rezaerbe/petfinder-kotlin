package com.erbeandroid.petfinder.core.firebase.authentication

import android.util.Log
import androidx.fragment.app.FragmentActivity
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import kotlinx.coroutines.flow.MutableStateFlow
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class PhoneLoginManagerImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val activity: FragmentActivity
) : PhoneLoginManager {

    override val state = MutableStateFlow<String?>(null)
    override val code = MutableStateFlow<String?>(null)

    private val verificationIdState = MutableStateFlow<String?>(null)
    private val tokenState = MutableStateFlow<PhoneAuthProvider.ForceResendingToken?>(null)

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
            .setForceResendingToken(tokenState.value!!)
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    override fun verify(code: String) {
        val credential = PhoneAuthProvider.getCredential(verificationIdState.value!!, code)
        signInWithPhoneAuthCredential(credential)
    }

    private val callback = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            Log.d("TAG", "onVerificationCompleted: ")
            state.value = "onVerificationCompleted"
            code.value = credential.smsCode

            signInWithPhoneAuthCredential(credential)
        }

        override fun onVerificationFailed(exception: FirebaseException) {
            Log.d("TAG", "onVerificationFailed: ")
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
            Log.d("TAG", "onCodeSent: ")
            state.value = "onCodeSent"

            verificationIdState.value = verificationId
            tokenState.value = token
        }
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("TAG", "signInWithPhoneAuthCredential: Success")
                    state.value = "Success"
                } else {
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        Log.d("TAG", "The verification code entered was invalid")
                    }
                    Log.d("TAG", "signInWithPhoneAuthCredential: Failed")
                    state.value = "Failed"
                }
            }
    }
}