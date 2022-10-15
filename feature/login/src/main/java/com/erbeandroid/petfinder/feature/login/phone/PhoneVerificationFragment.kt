package com.erbeandroid.petfinder.feature.login.phone

import android.content.Context
import android.util.Log
import androidx.fragment.app.activityViewModels
import com.erbeandroid.petfinder.core.common.base.BaseFragment
import com.erbeandroid.petfinder.core.common.base.click
import com.erbeandroid.petfinder.core.common.extension.launchAndCollectIn
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

    override fun initInteraction() {
        binding.verify.setOnClickListener(click {
            val code = binding.fieldCode.text.toString()
            if (code.isNotEmpty()) {
                phoneLoginViewModel.verify(code)
            }
        })
    }

    override fun initObservation() {
        phoneLoginViewModel.state.launchAndCollectIn(viewLifecycleOwner) { state ->
            Log.d("TAG", state.toString())
            if (state == "Update") {
                verificationToProfile(this@PhoneVerificationFragment)
            }
            if (state == "Success") {
                listener?.onLoginSuccess()
            }
        }
    }
}