package com.erbeandroid.petfinder.feature.component.list.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentTransaction
import com.erbeandroid.petfinder.core.common.base.click
import com.erbeandroid.petfinder.core.common.base.dialogClick
import com.erbeandroid.petfinder.core.common.base.dialogMultiChoiceClick
import com.erbeandroid.petfinder.feature.component.R
import com.erbeandroid.petfinder.feature.component.databinding.ComponentDialogBinding
import com.erbeandroid.petfinder.feature.component.list.util.CustomDialogFragment
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

        binding.fullDialog.setOnClickListener(click {
            fullDialog()
        })
    }

    private fun alertDialog() {
        MaterialAlertDialogBuilder(context)
            .setTitle(resources.getString(R.string.title))
            .setMessage(resources.getString(R.string.supporting_text))
            .setNeutralButton(resources.getString(R.string.cancel), dialogClick { _, _ ->
                // Respond to neutral button press
            })
            .setNegativeButton(resources.getString(R.string.decline), dialogClick { _, _ ->
                // Respond to negative button press
            })
            .setPositiveButton(resources.getString(R.string.accept), dialogClick { _, _ ->
                // Respond to positive button press
            })
            .show()
    }

    private fun simpleDialog() {
        val items = arrayOf("Item 1", "Item 2", "Item 3")

        MaterialAlertDialogBuilder(context)
            .setTitle(resources.getString(R.string.title))
            .setItems(items, dialogClick { _, _ ->
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
            .setNeutralButton(resources.getString(R.string.cancel), dialogClick { _, _ ->
                // Respond to neutral button press
            })
            .setPositiveButton(resources.getString(R.string.ok), dialogClick { _, _ ->
                // Respond to positive button press
            })
            .setSingleChoiceItems(singleItems, checkedItem, dialogClick { _, _ ->
                // Respond to item chosen
            })
            .show()
    }

    private fun confirmationDialogMultiple() {
        val multiItems = arrayOf("Item 1", "Item 2", "Item 3")
        val checkedItems = booleanArrayOf(false, false, false)

        MaterialAlertDialogBuilder(context)
            .setTitle(resources.getString(R.string.title))
            .setNeutralButton(resources.getString(R.string.cancel), dialogClick { _, _ ->
                // Respond to neutral button press
            })
            .setPositiveButton(resources.getString(R.string.ok), dialogClick { _, _ ->
                // Respond to positive button press
            })
            .setMultiChoiceItems(multiItems, checkedItems, dialogMultiChoiceClick { _, _, _ ->
                // Respond to item chosen
            })
            .show()
    }

    private fun fullDialog() {
        val fragmentManager = (context as AppCompatActivity).supportFragmentManager
        val customDialogFragment = CustomDialogFragment()

        // The device is using a large layout, so show the fragment as a dialog
        // customDialogFragment.show(fragmentManager, "dialog")

        // The device is smaller, so show the fragment fullscreen
        val transaction = fragmentManager.beginTransaction()
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        transaction
            .add(android.R.id.content, customDialogFragment)
            .addToBackStack(null)
            .commit()
    }
}