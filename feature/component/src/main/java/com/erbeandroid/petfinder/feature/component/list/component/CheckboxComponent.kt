package com.erbeandroid.petfinder.feature.component.list.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.erbeandroid.petfinder.core.common.base.check
import com.erbeandroid.petfinder.feature.component.databinding.ComponentCheckboxBinding

class CheckboxComponent @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding =
        ComponentCheckboxBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        binding.checkbox1.isChecked = true

        binding.checkbox1.setOnCheckedChangeListener(check {
            // Responds to checkbox1 being checked/unchecked
        })

        binding.checkbox2.setOnCheckedChangeListener(check {
            // Responds to checkbox2 being checked/unchecked
        })
    }
}