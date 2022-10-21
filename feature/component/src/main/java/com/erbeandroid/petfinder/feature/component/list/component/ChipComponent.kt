package com.erbeandroid.petfinder.feature.component.list.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.erbeandroid.petfinder.core.common.base.checkState
import com.erbeandroid.petfinder.core.common.base.click
import com.erbeandroid.petfinder.feature.component.databinding.ComponentChipBinding

class ChipComponent @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding =
        ComponentChipBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        // val checkedChipId = binding.chipGroup.checkedChipId
        // val checkedChipIds = binding.chipGroup.checkedChipIds

        binding.chipGroup.setOnCheckedStateChangeListener(checkState {
            // Responds to child chip checked/unchecked
        })

        binding.actionChip.setOnClickListener(click {
            // Responds to child chip clicked
        })
    }
}