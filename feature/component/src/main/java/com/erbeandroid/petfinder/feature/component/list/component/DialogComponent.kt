package com.erbeandroid.petfinder.feature.component.list.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.erbeandroid.petfinder.core.common.base.click
import com.erbeandroid.petfinder.core.common.base.dialogClick
import com.erbeandroid.petfinder.core.common.base.dialogClickMultiple
import com.erbeandroid.petfinder.feature.component.R
import com.erbeandroid.petfinder.feature.component.databinding.ComponentDialogBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class DialogComponent @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding =
        ComponentDialogBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        binding.alertDialog.setOnClickListener(click {
            alertDialog()
        })

        binding.simpleDialog.setOnClickListener(click {
            simpleDialog()
        })

        binding.confirmationDialog.setOnClickListener(click {
            confirmationDialog()
        })
    }

    private fun alertDialog() {
        MaterialAlertDialogBuilder(context)
            .setTitle(resources.getString(R.string.title))
            .setMessage(resources.getString(R.string.supporting_text))
            .setNeutralButton(resources.getString(R.string.cancel), dialogClick {
                // Respond to neutral button press
            })
            .setNegativeButton(resources.getString(R.string.decline), dialogClick {
                // Respond to negative button press
            })
            .setPositiveButton(resources.getString(R.string.accept), dialogClick {
                // Respond to positive button press
            })
            .show()
    }

    private fun simpleDialog() {
        val items = arrayOf("Item 1", "Item 2", "Item 3")

        MaterialAlertDialogBuilder(context)
            .setTitle(resources.getString(R.string.title))
            .setItems(items, dialogClick {
                // Respond to item chosen
            })
            .show()
    }

    private fun confirmationDialog() {
        // confirmationDialogSingle()
        confirmationDialogMultiple()
    }

    private fun confirmationDialogSingle() {
        val singleItems = arrayOf("Item 1", "Item 2", "Item 3")
        val checkedItem = 0

        MaterialAlertDialogBuilder(context)
            .setTitle(resources.getString(R.string.title))
            .setNeutralButton(resources.getString(R.string.cancel), dialogClick {
                // Respond to neutral button press
            })
            .setPositiveButton(resources.getString(R.string.ok), dialogClick {
                // Respond to positive button press
            })
            .setSingleChoiceItems(singleItems, checkedItem, dialogClick {
                // Respond to item chosen
            })
            .show()
    }

    private fun confirmationDialogMultiple() {
        val multiItems = arrayOf("Item 1", "Item 2", "Item 3")
        val checkedItems = booleanArrayOf(false, false, false)

        MaterialAlertDialogBuilder(context)
            .setTitle(resources.getString(R.string.title))
            .setNeutralButton(resources.getString(R.string.cancel), dialogClick {
                // Respond to neutral button press
            })
            .setPositiveButton(resources.getString(R.string.ok), dialogClick {
                // Respond to positive button press
            })
            .setMultiChoiceItems(multiItems, checkedItems, dialogClickMultiple {
                // Respond to item chosen
            })
            .show()
    }
}