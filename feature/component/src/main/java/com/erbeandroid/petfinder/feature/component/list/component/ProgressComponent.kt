package com.erbeandroid.petfinder.feature.component.list.component

import android.content.Context
import android.os.CountDownTimer
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.erbeandroid.petfinder.feature.component.databinding.ComponentProgressBinding

class ProgressComponent @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding =
        ComponentProgressBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        val timer = object : CountDownTimer(5000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                // binding.linearProgress.progress = 0
                // binding.circularProgress.progress = 0
            }

            override fun onFinish() {
                // binding.linearProgress.hide()
                // binding.circularProgress.hide()
            }
        }

        timer.start()
    }
}