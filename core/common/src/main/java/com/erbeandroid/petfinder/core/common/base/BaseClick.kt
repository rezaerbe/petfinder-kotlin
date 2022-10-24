package com.erbeandroid.petfinder.core.common.base

import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.Toolbar
import com.google.android.material.button.MaterialButtonToggleGroup

fun click(action: (View) -> Unit): View.OnClickListener {
    return View.OnClickListener { view ->
        view?.let { v ->
            if (v.id > 0) {
                val name = v.context.resources.getResourceEntryName(v.id)
                Log.d("TAG", "onClick: $name")
            }
        }
        action(view)
    }
}

fun menuItemClick(action: (MenuItem) -> Boolean): Toolbar.OnMenuItemClickListener {
    return Toolbar.OnMenuItemClickListener { menuItem ->
        menuItem?.let { menu ->
            val name = menu.title
            Log.d("TAG", "onMenuItemClick: $name")
        }
        action(menuItem)
    }
}

fun buttonChecked(action: (MaterialButtonToggleGroup, Int, Boolean) -> Unit): MaterialButtonToggleGroup.OnButtonCheckedListener {
    return MaterialButtonToggleGroup.OnButtonCheckedListener { group, checkedId, isChecked ->
        group?.let { v ->
            val name = v.context.resources.getResourceEntryName(checkedId)
            if (isChecked) {
                Log.d("TAG", "Checked: $name")
            } else {
                Log.d("TAG", "Unchecked: $name")
            }
        }
        action(group, checkedId, isChecked)
    }
}

fun longClick(action: (View) -> Boolean): View.OnLongClickListener {
    return View.OnLongClickListener { view ->
        view?.let { v ->
            if (v.id > 0) {
                val name = v.context.resources.getResourceEntryName(v.id)
                Log.d("TAG", "onLongClick: $name")
            }
        }
        action(view)
    }
}

/*
fun compoundCheckedChange(action: (CompoundButton?, Boolean) -> Unit): CompoundButton.OnCheckedChangeListener {
    return CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
        buttonView?.let { v ->
            val name = v.context.resources.getResourceEntryName(v.id)
            if (isChecked) {
                Log.d("TAG", "Checked: $name")
            } else {
                Log.d("TAG", "Unchecked: $name")
            }
        }
        action(buttonView, isChecked)
    }
}

fun radioCheckedChange(action: () -> Unit): RadioGroup.OnCheckedChangeListener {
    return RadioGroup.OnCheckedChangeListener { group, checkedId ->
        group?.let { v ->
            val name = v.context.resources.getResourceEntryName(checkedId)
            Log.d("TAG", "Checked: $name")
        }
        action()
    }
}

fun checkedStateChange(action: () -> Unit): ChipGroup.OnCheckedStateChangeListener {
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

fun dialogMultiChoiceClick(action: () -> Unit): DialogInterface.OnMultiChoiceClickListener {
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
}*/