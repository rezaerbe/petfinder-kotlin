package com.erbeandroid.petfinder.feature.component.list.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.erbeandroid.petfinder.feature.component.databinding.ComponentCardBinding

class CardComponent @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding =
        ComponentCardBinding.inflate(LayoutInflater.from(context), this, true)

/*    init {
        binding.card.setOnLongClickListener(longClick {
            binding.card.isChecked = !binding.card.isChecked
        })
    }*/
}