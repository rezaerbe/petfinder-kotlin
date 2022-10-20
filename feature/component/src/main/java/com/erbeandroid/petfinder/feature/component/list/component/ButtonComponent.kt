package com.erbeandroid.petfinder.feature.component.list.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.erbeandroid.petfinder.core.common.base.click
import com.erbeandroid.petfinder.core.common.base.toggle
import com.erbeandroid.petfinder.feature.component.databinding.ComponentButtonBinding

class ButtonComponent @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding =
        ComponentButtonBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        binding.textButton.setOnClickListener(click {
            // Respond to text button press
        })

        binding.outlinedButton.setOnClickListener(click {
            // Respond to outlined button press
        })

        binding.containedButton.setOnClickListener(click {
            // Respond to contained button press
        })

        binding.toggleButton.addOnButtonCheckedListener(toggle {
            // Respond to button selection
        })

        binding.toggleIcon.addOnButtonCheckedListener(toggle {
            // Respond to icon selection
        })
    }
}