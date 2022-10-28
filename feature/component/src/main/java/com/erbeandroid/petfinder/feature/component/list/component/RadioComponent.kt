package com.erbeandroid.petfinder.feature.component.list.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.erbeandroid.petfinder.core.common.base.buttonCheckedChange
import com.erbeandroid.petfinder.core.common.base.radioCheckedChange
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

        binding.radioGroup.setOnCheckedChangeListener(radioCheckedChange { _, _ ->
            // Responds to child RadioButton checked/unchecked
        })

        binding.radio1.isChecked = true

        binding.radio1.setOnCheckedChangeListener(buttonCheckedChange { _, _ ->
            // Responds to radio1 being checked/unchecked
        })

        binding.radio2.setOnCheckedChangeListener(buttonCheckedChange { _, _ ->
            // Responds to radio2 being checked/unchecked
        })
    }
}