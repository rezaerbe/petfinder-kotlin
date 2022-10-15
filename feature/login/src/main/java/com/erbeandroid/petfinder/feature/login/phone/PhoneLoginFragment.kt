package com.erbeandroid.petfinder.feature.login.phone

import android.util.Log
import androidx.fragment.app.activityViewModels
import com.erbeandroid.petfinder.core.common.base.BaseFragment
import com.erbeandroid.petfinder.core.common.base.click
import com.erbeandroid.petfinder.core.common.extension.launchAndCollectIn
import com.erbeandroid.petfinder.feature.login.databinding.FragmentPhoneLoginBinding
import com.erbeandroid.petfinder.feature.login.util.loginToVerification
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhoneLoginFragment :
    BaseFragment<FragmentPhoneLoginBinding>(FragmentPhoneLoginBinding::inflate) {

    private val phoneLoginViewModel: PhoneLoginViewModel by activityViewModels()

    override fun initInteraction() {
        binding.send.setOnClickListener(click {
            val phone = binding.fieldPhone.text.toString()
            if (phone.isNotEmpty()) {
                phoneLoginViewModel.send(phone)
            }
        })
    }

    override fun initObservation() {
        phoneLoginViewModel.state.launchAndCollectIn(viewLifecycleOwner) { state ->
            Log.d("TAG", state.toString())
            if (state == "onCodeSent") {
                loginToVerification(this@PhoneLoginFragment)
            }
        }
    }
}