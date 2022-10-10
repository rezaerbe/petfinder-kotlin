package com.erbeandroid.petfinder.feature.login.phone

import android.content.Context
import androidx.fragment.app.activityViewModels
import com.erbeandroid.petfinder.core.common.util.BaseFragment
import com.erbeandroid.petfinder.core.common.util.click
import com.erbeandroid.petfinder.core.common.util.launchAndCollectIn
import com.erbeandroid.petfinder.feature.login.databinding.FragmentPhoneVerificationBinding
import com.erbeandroid.petfinder.feature.login.util.LoginListener
import com.erbeandroid.petfinder.feature.login.util.verificationToProfile

class PhoneVerificationFragment :
    BaseFragment<FragmentPhoneVerificationBinding>(FragmentPhoneVerificationBinding::inflate) {

    private val phoneLoginViewModel: PhoneLoginViewModel by activityViewModels()
    private var listener: LoginListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is LoginListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString())
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun initObserver() {
        phoneLoginViewModel.state.launchAndCollectIn(viewLifecycleOwner) { state ->
            if (state == "Update") {
                verificationToProfile(this@PhoneVerificationFragment)
            }
            if (state == "Success") {
                listener?.onLoginSuccess()
            }
        }
    }

    override fun initInteraction() {
        binding.verify.setOnClickListener(click {
            val code = binding.fieldCode.text.toString()
            if (code.isNotEmpty()) {
                phoneLoginViewModel.verify(code)
            }
        })
    }
}