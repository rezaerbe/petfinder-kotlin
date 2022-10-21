package com.erbeandroid.petfinder.feature.component.list.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.erbeandroid.petfinder.core.common.base.checkRadio
import com.erbeandroid.petfinder.feature.component.databinding.ComponentRadioBinding

class RadioComponent @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding =
        ComponentRadioBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        // val checkedRadioButtonId = binding.radioGroup.checkedRadioButtonId

        binding.radioGroup.setOnCheckedChangeListener(checkRadio {
            // Responds to child RadioButton checked/unchecked
        })
    }
}