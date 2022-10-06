package com.erbeandroid.petfinder.feature.login.phone

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.erbeandroid.petfinder.core.common.util.launchAndCollectIn
import com.erbeandroid.petfinder.feature.login.databinding.FragmentPhoneLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhoneLoginFragment : Fragment() {

    private var _binding: FragmentPhoneLoginBinding? = null
    private val binding get() = _binding!!

    private val phoneLoginViewModel: PhoneLoginViewModel by activityViewModels()

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
            phoneLoginViewModel.send(binding.fieldPhone.text.toString())
        }

        binding.verify.setOnClickListener {
            phoneLoginViewModel.verify(binding.fieldCode.text.toString())
        }

        observeData()
    }

    private fun observeData() {
        phoneLoginViewModel.state.launchAndCollectIn(viewLifecycleOwner) { state ->
            if (state == "onCodeSent") {
                binding.fieldPhone.visibility = View.GONE
                binding.send.visibility = View.GONE
                binding.fieldCode.visibility = View.VISIBLE
                binding.verify.visibility = View.VISIBLE
            }
        }

        phoneLoginViewModel.code.launchAndCollectIn(viewLifecycleOwner) { code ->
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