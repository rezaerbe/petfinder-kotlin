package com.erbeandroid.petfinder.feature.discussion.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.erbeandroid.petfinder.feature.discussion.common.launchAndCollectIn
import com.erbeandroid.petfinder.feature.discussion.databinding.FragmentPhoneLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhoneLoginFragment : Fragment() {

    private var _binding: FragmentPhoneLoginBinding? = null
    private val binding get() = _binding!!

    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPhoneLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.send.setOnClickListener {
            loginViewModel.send(binding.fieldPhone.text.toString())
        }

        binding.verify.setOnClickListener {
            loginViewModel.verify(binding.fieldCode.text.toString())
        }

        observeData()
    }

    private fun observeData() {
        loginViewModel.state.launchAndCollectIn(viewLifecycleOwner) { state ->
            if (state == "onCodeSent") {
                binding.fieldPhone.visibility = View.GONE
                binding.send.visibility = View.GONE
                binding.fieldCode.visibility = View.VISIBLE
                binding.verify.visibility = View.VISIBLE
            }
            if (state == "Success") {
                findNavController().navigate(
                    PhoneLoginFragmentDirections.actionPhoneLoginFragmentToHomeFragment()
                )
            }
        }

        loginViewModel.code.launchAndCollectIn(viewLifecycleOwner) { code ->
            code?.let {
                binding.fieldCode.setText(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}