package com.erbeandroid.petfinder.feature.login.util

import androidx.navigation.fragment.findNavController
import com.erbeandroid.petfinder.feature.login.phone.PhoneLoginFragment
import com.erbeandroid.petfinder.feature.login.phone.PhoneLoginFragmentDirections
import com.erbeandroid.petfinder.feature.login.phone.PhoneVerificationFragment
import com.erbeandroid.petfinder.feature.login.phone.PhoneVerificationFragmentDirections

fun loginToVerification(fragment: PhoneLoginFragment) {
    fragment.findNavController().navigate(
        PhoneLoginFragmentDirections.actionPhoneLoginFragmentToPhoneVerificationFragment()
    )
}

fun verificationToProfile(fragment: PhoneVerificationFragment) {
    fragment.findNavController().navigate(
        PhoneVerificationFragmentDirections.actionPhoneVerificationFragmentToProfileFragment()
    )
}