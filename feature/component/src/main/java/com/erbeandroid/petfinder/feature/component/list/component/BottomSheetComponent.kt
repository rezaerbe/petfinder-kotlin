package com.erbeandroid.petfinder.feature.component.list.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.erbeandroid.petfinder.core.common.base.click
import com.erbeandroid.petfinder.feature.component.databinding.ComponentBottomSheetBinding

class BottomSheetComponent @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding =
        ComponentBottomSheetBinding.inflate(LayoutInflater.from(context), this, true)

    fun buttonBottomSheet(action: () -> Unit) {
        binding.buttonBottomSheet.setOnClickListener(click {
            action()
        })
    }
}