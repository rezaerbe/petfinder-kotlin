package com.erbeandroid.petfinder.core.common.base

import android.content.DialogInterface
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.CompoundButton
import android.widget.RadioGroup
import androidx.appcompat.widget.PopupMenu
import androidx.appcompat.widget.Toolbar
import com.google.android.material.button.MaterialButtonToggleGroup
import com.google.android.material.chip.ChipGroup

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

fun toolbarMenuItemClick(action: (MenuItem) -> Boolean): Toolbar.OnMenuItemClickListener {
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

fun buttonCheckedChange(action: (CompoundButton, Boolean) -> Unit): CompoundButton.OnCheckedChangeListener {
    return CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
        buttonView?.let { v ->
            if (v.id > 0) {
                val name = v.context.resources.getResourceEntryName(v.id)
                if (isChecked) {
                    Log.d("TAG", "Checked: $name")
                } else {
                    Log.d("TAG", "Unchecked: $name")
                }
            }
        }
        action(buttonView, isChecked)
    }
}

fun checkedStateChange(action: (ChipGroup, MutableList<Int>) -> Unit): ChipGroup.OnCheckedStateChangeListener {
    return ChipGroup.OnCheckedStateChangeListener { group, checkedIds ->
        val names = checkedIds.map { checkedId ->
            group.context.resources.getResourceEntryName(checkedId)
        }
        Log.d("TAG", "Checked: $names")
        action(group, checkedIds)
    }
}

fun dialogClick(action: (DialogInterface, Int) -> Unit): DialogInterface.OnClickListener {
    return DialogInterface.OnClickListener { dialog, which ->
        dialog?.let {
            val name = when (which) {
                -1 -> "positive"
                -2 -> "negative"
                -3 -> "neutral"
                else -> "item $which"
            }
            Log.d("TAG", "onClick: $name")
        }
        action(dialog, which)
    }
}

fun dialogMultiChoiceClick(action: (DialogInterface, Int, Boolean) -> Unit): DialogInterface.OnMultiChoiceClickListener {
    return DialogInterface.OnMultiChoiceClickListener { dialog, which, checked ->
        dialog?.let {
            val name = "item $which"
            if (checked) {
                Log.d("TAG", "Checked: $name")
            } else {
                Log.d("TAG", "Unchecked: $name")
            }
        }
        action(dialog, which, checked)
    }
}

fun popupMenuItemClick(action: (MenuItem) -> Boolean): PopupMenu.OnMenuItemClickListener {
    return PopupMenu.OnMenuItemClickListener { menuItem ->
        menuItem?.let { menu ->
            val name = menu.title
            Log.d("TAG", "onMenuItemClick: $name")
        }
        action(menuItem)
    }
}

fun itemClick(action: (AdapterView<*>, View, Int, Long) -> Unit): AdapterView.OnItemClickListener {
    return AdapterView.OnItemClickListener { parent, view, position, id ->
        parent?.let { v ->
            val name = v.getItemAtPosition(position)
            Log.d("TAG", "onMenuItemClick: $name")
        }
        action(parent, view, position, id)
    }
}

fun radioCheckedChange(action: (RadioGroup, Int) -> Unit): RadioGroup.OnCheckedChangeListener {
    return RadioGroup.OnCheckedChangeListener { group, checkedId ->
        group?.let { v ->
            val name = v.context.resources.getResourceEntryName(checkedId)
            Log.d("TAG", "Checked: $name")
        }
        action(group, checkedId)
    }
}