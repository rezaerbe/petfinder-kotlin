package com.erbeandroid.petfinder.feature.component.list.component

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.util.Pair
import com.erbeandroid.petfinder.core.common.base.click
import com.erbeandroid.petfinder.feature.component.databinding.ComponentDatePickerBinding
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointForward
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone

class DatePickerComponent @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding =
        ComponentDatePickerBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        binding.datePicker.setOnClickListener(click {
            showDatePicker()
        })

        binding.dateRangePicker.setOnClickListener(click {
            showDateRangePicker()
        })
    }

    private fun showDatePicker() {
        val fragmentManager = (context as AppCompatActivity).supportFragmentManager

        val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

        /*
        * .setInputMode(MaterialDatePicker.INPUT_MODE_TEXT)
        */
        val datePicker =
            MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select date")
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .setCalendarConstraints(calendarConstraint().build())
                .build()

        datePicker.show(fragmentManager, "datePicker")

        datePicker.addOnPositiveButtonClickListener {
            // Respond to positive button click.
            val date = format.format(Date(it))
            Log.d("TAG", "showDatePicker: $date")
        }
        datePicker.addOnNegativeButtonClickListener {
            // Respond to negative button click.
            Log.d("TAG", "showDatePicker: Negative")
        }
        datePicker.addOnCancelListener {
            // Respond to cancel button click.
            Log.d("TAG", "showDatePicker: Cancel")
        }
        datePicker.addOnDismissListener {
            // Respond to dismiss events.
            Log.d("TAG", "showDatePicker: Dismiss")
        }
    }

    private fun showDateRangePicker() {
        val fragmentManager = (context as AppCompatActivity).supportFragmentManager

        val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

        /*
        * .setInputMode(MaterialDatePicker.INPUT_MODE_TEXT)
        */
        val dateRangePicker =
            MaterialDatePicker.Builder.dateRangePicker()
                .setTitleText("Select dates")
                .setSelection(
                    Pair(
                        MaterialDatePicker.thisMonthInUtcMilliseconds(),
                        MaterialDatePicker.todayInUtcMilliseconds()
                    )
                )
                .setCalendarConstraints(calendarConstraint().build())
                .build()

        dateRangePicker.show(fragmentManager, "dateRangePicker")

        dateRangePicker.addOnPositiveButtonClickListener {
            // Respond to positive button click.
            val start = format.format(Date(it.first))
            val end = format.format(Date(it.second))
            Log.d("TAG", "showDatePicker: $start - $end")
        }
        dateRangePicker.addOnNegativeButtonClickListener {
            // Respond to negative button click.
            Log.d("TAG", "showDatePicker: Negative")
        }
        dateRangePicker.addOnCancelListener {
            // Respond to cancel button click.
            Log.d("TAG", "showDatePicker: Cancel")
        }
        dateRangePicker.addOnDismissListener {
            // Respond to dismiss events.
            Log.d("TAG", "showDatePicker: Dismiss")
        }
    }

    private fun calendarConstraint(): CalendarConstraints.Builder {
        val today = MaterialDatePicker.todayInUtcMilliseconds()
        val calendar = Calendar.getInstance(TimeZone.getDefault())
        calendar.timeInMillis = today

        calendar[Calendar.MONTH] = Calendar.JANUARY
        val janThisYear = calendar.timeInMillis

        calendar[Calendar.MONTH] = Calendar.DECEMBER
        val decThisYear = calendar.timeInMillis

        return CalendarConstraints.Builder()
            .setStart(janThisYear)
            .setEnd(decThisYear)
            .setValidator(DateValidatorPointForward.now())
    }
}