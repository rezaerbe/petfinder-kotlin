package com.erbeandroid.petfinder.feature.login.profile

import android.content.Context
import androidx.fragment.app.viewModels
import com.erbeandroid.petfinder.core.common.util.BaseFragment
import com.erbeandroid.petfinder.core.common.util.click
import com.erbeandroid.petfinder.core.common.util.launchAndCollectIn
import com.erbeandroid.petfinder.feature.login.databinding.FragmentProfileBinding
import com.erbeandroid.petfinder.feature.login.util.LoginListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment :
    BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate) {

    private val profileViewModel: ProfileViewModel by viewModels()
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
        profileViewModel.state.launchAndCollectIn(viewLifecycleOwner) { state ->
            if (state == "Success") {
                profileViewModel.addUser()
                listener?.onLoginSuccess()
            }
        }
    }

    override fun initInteraction() {
        binding.submit.setOnClickListener(click {
            val name = binding.fieldName.text.toString()
            if (name.isNotEmpty()) {
                profileViewModel.updateUser(name)
            }
        })
    }
}