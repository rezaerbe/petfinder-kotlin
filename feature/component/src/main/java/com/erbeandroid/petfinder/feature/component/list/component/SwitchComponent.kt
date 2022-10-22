package com.erbeandroid.petfinder.feature.component.list.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.erbeandroid.petfinder.feature.component.databinding.ComponentSwitchBinding

class SwitchComponent @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding =
        ComponentSwitchBinding.inflate(LayoutInflater.from(context), this, true)

/*    init {
        binding.switch1.isChecked = true

        binding.switch1.setOnCheckedChangeListener(check {
            // Responds to switch1 being checked/unchecked
        })

        binding.switch2.setOnCheckedChangeListener(check {
            // Responds to switch2 being checked/unchecked
        })
    }*/
}