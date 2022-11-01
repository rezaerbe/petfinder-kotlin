package com.erbeandroid.petfinder.feature.component.list.component

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.erbeandroid.petfinder.core.common.base.click
import com.erbeandroid.petfinder.feature.component.databinding.ComponentSnackbarBinding
import com.google.android.material.snackbar.Snackbar

class SnackbarComponent @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding =
        ComponentSnackbarBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        binding.buttonSnackbar.setOnClickListener(click {
            Snackbar.make(binding.container, "Text label", Snackbar.LENGTH_SHORT)
                .setAction("Action") {
                    // Responds to click on the action
                    Log.d("TAG", "onClick: Snackbar")
                }
                .show()
        })
    }
}