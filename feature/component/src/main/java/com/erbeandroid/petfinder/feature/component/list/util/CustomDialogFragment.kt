package com.erbeandroid.petfinder.feature.component.list.util

import com.erbeandroid.petfinder.core.common.base.BaseDialogFragment
import com.erbeandroid.petfinder.core.common.base.click
import com.erbeandroid.petfinder.feature.component.databinding.FragmentCustomDialogBinding

class CustomDialogFragment :
    BaseDialogFragment<FragmentCustomDialogBinding>(FragmentCustomDialogBinding::inflate) {

    override fun initInteraction() {
        binding.dialogButton.setOnClickListener(click {
            dismiss()
        })
    }
}