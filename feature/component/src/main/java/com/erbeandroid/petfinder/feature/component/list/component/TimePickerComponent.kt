package com.erbeandroid.petfinder.feature.component.list.component

import android.content.Context
import android.text.format.DateFormat.is24HourFormat
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.erbeandroid.petfinder.core.common.base.click
import com.erbeandroid.petfinder.feature.component.databinding.ComponentTimePickerBinding
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat

class TimePickerComponent @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding =
        ComponentTimePickerBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        binding.timePicker.setOnClickListener(click {
            showTimePicker()
        })
    }

    private fun showTimePicker() {
        val fragmentManager = (context as AppCompatActivity).supportFragmentManager

        val isSystem24Hour = is24HourFormat(context)
        val clockFormat = if (isSystem24Hour) TimeFormat.CLOCK_24H else TimeFormat.CLOCK_12H

        /*
        * .setInputMode(INPUT_MODE_KEYBOARD)
        */
        val timePicker =
            MaterialTimePicker.Builder()
                .setTimeFormat(clockFormat)
                .setHour(12)
                .setMinute(10)
                .setTitleText("Select Appointment time")
                .build()

        timePicker.show(fragmentManager, "timePicker")

        timePicker.addOnPositiveButtonClickListener {
            // Respond to positive button click.
            val time = "${timePicker.hour}:${timePicker.minute}"
            Log.d("TAG", "showTimePicker: $time")
        }
        timePicker.addOnNegativeButtonClickListener {
            // Respond to negative button click.
            Log.d("TAG", "showTimePicker: Negative")
        }
        timePicker.addOnCancelListener {
            // Respond to cancel button click.
            Log.d("TAG", "showTimePicker: Cancel")
        }
        timePicker.addOnDismissListener {
            // Respond to dismiss events.
            Log.d("TAG", "showTimePicker: Dismiss")
        }
    }
}