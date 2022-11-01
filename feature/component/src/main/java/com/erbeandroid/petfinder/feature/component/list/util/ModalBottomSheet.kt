package com.erbeandroid.petfinder.feature.component.list.util

import androidx.core.view.updateLayoutParams
import com.erbeandroid.petfinder.core.common.base.BaseBottomSheetDialogFragment
import com.erbeandroid.petfinder.feature.component.databinding.ModalBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog

class ModalBottomSheet :
    BaseBottomSheetDialogFragment<ModalBottomSheetBinding>(ModalBottomSheetBinding::inflate) {

    override fun initInteraction() {
        val modalBottomSheetBehavior = (dialog as BottomSheetDialog).behavior

        modalBottomSheetBehavior.saveFlags = BottomSheetBehavior.SAVE_ALL

        binding.bottomSheet.post {
            modalBottomSheetBehavior.peekHeight = binding.bottomSheet.measuredHeight
            binding.modalBottomSheet.updateLayoutParams {
                height = 3 * binding.bottomSheet.measuredHeight
            }
        }

/*        val bottomSheetCallback = object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                // Do something for new state.
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                // Do something for slide offset.
            }
        }

        // To add the callback:
        modalBottomSheetBehavior.addBottomSheetCallback(bottomSheetCallback)

        // To remove the callback:
        modalBottomSheetBehavior.removeBottomSheetCallback(bottomSheetCallback)*/
    }

    companion object {
        const val TAG = "ModalBottomSheet"
    }
}