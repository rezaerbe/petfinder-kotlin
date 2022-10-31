package com.erbeandroid.petfinder.feature.component.list

import androidx.navigation.fragment.findNavController
import com.erbeandroid.petfinder.core.common.base.BaseFragment
import com.erbeandroid.petfinder.feature.component.databinding.FragmentListBinding

class ListFragment :
    BaseFragment<FragmentListBinding>(FragmentListBinding::inflate) {

    override fun initInteraction() {
        binding.bottomSheet.buttonBottomSheet {
            findNavController().navigate(
                ListFragmentDirections.actionListFragmentToDetailFragment()
            )
        }
    }

    override fun initObservation() = Unit
}