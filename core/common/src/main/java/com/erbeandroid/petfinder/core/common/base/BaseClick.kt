package com.erbeandroid.petfinder.core.common.base

import android.util.Log
import android.view.View
import android.widget.CompoundButton
import com.google.android.material.button.MaterialButtonToggleGroup

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