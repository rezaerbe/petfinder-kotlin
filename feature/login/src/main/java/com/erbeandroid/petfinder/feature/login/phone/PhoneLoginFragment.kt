package com.erbeandroid.petfinder.feature.login.phone

import androidx.fragment.app.activityViewModels
import com.erbeandroid.petfinder.core.common.util.BaseFragment
import com.erbeandroid.petfinder.core.common.util.click
import com.erbeandroid.petfinder.core.common.util.launchAndCollectIn
import com.erbeandroid.petfinder.feature.login.databinding.FragmentPhoneLoginBinding
import com.erbeandroid.petfinder.feature.login.util.loginToVerification
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhoneLoginFragment :
    BaseFragment<FragmentPhoneLoginBinding>(FragmentPhoneLoginBinding::inflate) {

    private val phoneLoginViewModel: PhoneLoginViewModel by activityViewModels()

    override fun initObserver() {
        phoneLoginViewModel.state.launchAndCollectIn(viewLifecycleOwner) { state ->
            if (state == "onCodeSent") {
                loginToVerification(this@PhoneLoginFragment)
            }
        }
    }

    override fun initInteraction() {
        binding.send.setOnClickListener(click {
            val phone = binding.fieldPhone.text.toString()
            if (phone.isNotEmpty()) {
                phoneLoginViewModel.send(phone)
            }
        })
    }
}