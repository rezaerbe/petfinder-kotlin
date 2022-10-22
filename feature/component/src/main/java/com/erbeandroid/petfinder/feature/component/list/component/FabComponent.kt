package com.erbeandroid.petfinder.feature.component.list.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.erbeandroid.petfinder.feature.component.databinding.ComponentFabBinding

class FabComponent @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding =
        ComponentFabBinding.inflate(LayoutInflater.from(context), this, true)

/*    init {
        // fab.show()
        // fab.hide()
        binding.fab.setOnClickListener(click {
            // Respond to FAB click
        })

        // miniFab.show()
        // miniFab.hide()
        binding.miniFab.setOnClickListener(click {
            // Respond to Mini FAB click
        })

        // extendedFab.extend()
        // extendedFab.shrink()
        binding.extendedFab.setOnClickListener(click {
            // Respond to Extended FAB click
        })
    }*/
}