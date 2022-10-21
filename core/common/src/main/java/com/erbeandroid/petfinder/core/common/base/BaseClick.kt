package com.erbeandroid.petfinder.core.common.base

import android.content.DialogInterface
import android.util.Log
import android.view.View
import android.widget.CompoundButton
import com.google.android.material.button.MaterialButtonToggleGroup
import com.google.android.material.chip.ChipGroup

fun click(action: () -> Unit): View.OnClickListener {
    return View.OnClickListener { view ->
        view?.let { v ->
            val name = v.context.resources.getResourceEntryName(v.id)
            Log.d("TAG", "onClick: $name")
        }
        action()
    }
}

fun longClick(action: () -> Unit): View.OnLongClickListener {
    return View.OnLongClickListener { view ->
        view?.let { v ->
            val name = v.context.resources.getResourceEntryName(v.id)
            Log.d("TAG", "onLongClick: $name")
        }
        action()
        true
    }
}

fun toggle(action: () -> Unit): MaterialButtonToggleGroup.OnButtonCheckedListener {
    return MaterialButtonToggleGroup.OnButtonCheckedListener { group, checkedId, isChecked ->
        group?.let { v ->
            val name = v.context.resources.getResourceEntryName(checkedId)
            if (isChecked) {
                Log.d("TAG", "Checked: $name")
            } else {
                Log.d("TAG", "Unchecked: $name")
            }
        }
        action()
    }
}

fun check(action: () -> Unit): CompoundButton.OnCheckedChangeListener {
    return CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
        buttonView?.let { v ->
            val name = v.context.resources.getResourceEntryName(v.id)
            if (isChecked) {
                Log.d("TAG", "Checked: $name")
            } else {
                Log.d("TAG", "Unchecked: $name")
            }
        }
        action()
    }
}

fun checkState(action: () -> Unit): ChipGroup.OnCheckedStateChangeListener {
    return ChipGroup.OnCheckedStateChangeListener { group, checkedIds ->
        if (checkedIds.isNotEmpty()) {
            val names = checkedIds.map { checkedId ->
                group.context.resources.getResourceEntryName(checkedId)
            }
            Log.d("TAG", "Checked: $names")
        }
        action()
    }
}

fun dialogClick(action: () -> Unit): DialogInterface.OnClickListener {
    return DialogInterface.OnClickListener { dialog, which ->
        dialog?.let {
            val name = when (which) {
                -1 -> "positive"
                -2 -> "negative"
                -3 -> "neutral"
                else -> "item $which"
            }
            Log.d("TAG", "Clicked: $name")
        }
        action()
    }
}

fun dialogClickMultiple(action: () -> Unit): DialogInterface.OnMultiChoiceClickListener {
    return DialogInterface.OnMultiChoiceClickListener { dialog, which, checked ->
        dialog?.let {
            if (checked) {
                Log.d("TAG", "Checked: item $which")
            } else {
                Log.d("TAG", "Unchecked: item $which")
            }
        }
        action()
    }
}