package com.erbeandroid.petfinder.feature.component.list.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.erbeandroid.petfinder.core.common.base.click
import com.erbeandroid.petfinder.feature.component.databinding.ComponentBottomSheetBinding
import com.erbeandroid.petfinder.feature.component.list.util.ModalBottomSheet
import com.google.android.material.bottomsheet.BottomSheetBehavior

class BottomSheetComponent @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding =
        ComponentBottomSheetBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        val standardBottomSheetBehavior = BottomSheetBehavior.from(binding.standardBottomSheet)

        standardBottomSheetBehavior.saveFlags = BottomSheetBehavior.SAVE_ALL

        binding.buttonStandardBottomSheet.setOnClickListener(click {
            if (standardBottomSheetBehavior.state == BottomSheetBehavior.STATE_COLLAPSED) {
                standardBottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            } else {
                standardBottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
            }
        })

        binding.buttonModalBottomSheet.setOnClickListener(click {
            val activity = context as AppCompatActivity
            val modalBottomSheet = ModalBottomSheet()
            modalBottomSheet.show(activity.supportFragmentManager, ModalBottomSheet.TAG)
        })

/*        val bottomSheetCallback = object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                // Do something for new state.
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                // Do something for slide offset.
            }
        }

        // To add the callback:
        standardBottomSheetBehavior.addBottomSheetCallback(bottomSheetCallback)

        // To remove the callback:
        standardBottomSheetBehavior.removeBottomSheetCallback(bottomSheetCallback)*/
    }
}