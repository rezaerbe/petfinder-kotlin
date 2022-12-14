package com.erbeandroid.petfinder.feature.component.list.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.erbeandroid.petfinder.core.common.base.checkedStateChange
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

        binding.chipGroup.setOnCheckedStateChangeListener(checkedStateChange { _, _ ->
            // Responds to child chip checked/unchecked
        })

        binding.actionChip.setOnClickListener(click {
            // Responds to action chip click
        })

        binding.inputChip.setOnCloseIconClickListener(click {
            // Responds to input chip's close icon click if one is present
        })

/*        binding.choiceChip.setOnCheckedChangeListener(buttonCheckedChange { _, _ ->
            // Responds to choice chip checked/unchecked
        })

        binding.filterChip.setOnCheckedChangeListener(buttonCheckedChange { _, _ ->
            // Responds to filter chip checked/unchecked
        })*/
    }
}