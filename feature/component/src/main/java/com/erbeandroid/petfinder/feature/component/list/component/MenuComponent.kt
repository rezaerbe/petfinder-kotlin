package com.erbeandroid.petfinder.feature.component.list.component

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.InsetDrawable
import android.util.AttributeSet
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import androidx.annotation.MenuRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.menu.MenuBuilder
import androidx.appcompat.widget.ListPopupWindow
import androidx.appcompat.widget.PopupMenu
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import com.erbeandroid.petfinder.core.common.base.click
import com.erbeandroid.petfinder.core.common.base.itemClick
import com.erbeandroid.petfinder.core.common.base.popupMenuItemClick
import com.erbeandroid.petfinder.feature.component.R
import com.erbeandroid.petfinder.feature.component.databinding.ComponentMenuBinding

class MenuComponent @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding =
        ComponentMenuBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        val activity = (context as AppCompatActivity)

        activity.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.option_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                val name = menuItem.title
                Log.d("TAG", "onMenuItemClick: $name")
                return when (menuItem.itemId) {
                    R.id.option_1 -> {
                        // Respond to menu item 1 click.
                        true
                    }
                    R.id.option_2 -> {
                        // Respond to menu item 2 click.
                        true
                    }
                    R.id.option_3 -> {
                        // Respond to menu item 3 click.
                        true
                    }
                    else -> false
                }
            }
        }, context, Lifecycle.State.RESUMED)

        binding.buttonShowMenu.setOnClickListener(click { view ->
            showMenu(view, R.menu.popup_menu)
        })

        binding.buttonListMenu.setOnClickListener(click {
            listMenu()
        })

        dropdownMenu()
    }

    @SuppressLint("RestrictedApi")
    private fun showMenu(view: View, @MenuRes menuRes: Int) {
        val popup = PopupMenu(context, view)
        popup.menuInflater.inflate(menuRes, popup.menu)

        if (popup.menu is MenuBuilder) {
            val menuBuilder = popup.menu as MenuBuilder
            menuBuilder.setOptionalIconsVisible(true)
            for (item in menuBuilder.visibleItems) {
                val iconMarginPx =
                    TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 8f, resources.displayMetrics
                    ).toInt()
                if (item.icon != null) {
                    item.icon = InsetDrawable(item.icon, iconMarginPx, 0, iconMarginPx, 0)
                }
            }
        }

        popup.setOnMenuItemClickListener(popupMenuItemClick { menuItem ->
            when (menuItem.itemId) {
                R.id.option_1 -> {
                    // Respond to menu item 1 click.
                    true
                }
                R.id.option_2 -> {
                    // Respond to menu item 2 click.
                    true
                }
                R.id.option_3 -> {
                    // Respond to menu item 3 click.
                    true
                }
                else -> false
            }
        })

        popup.show()
    }

    private fun listMenu() {
        val listPopupWindow =
            ListPopupWindow(
                context,
                null,
                com.google.android.material.R.attr.listPopupWindowStyle
            )

        listPopupWindow.anchorView = binding.buttonListMenu

        val items = listOf("Option 1", "Option 2", "Option 3")
        val adapter = ArrayAdapter(context, R.layout.list_menu, items)
        listPopupWindow.setAdapter(adapter)

        listPopupWindow.setOnItemClickListener(itemClick { _, _, _, _ ->
            // Respond to list popup window item click.

            // Dismiss popup.
            listPopupWindow.dismiss()
        })

        listPopupWindow.show()
    }

    private fun dropdownMenu() {
        val items = listOf("Option 1", "Option 2", "Option 3")
        val adapter = ArrayAdapter(context, R.layout.list_menu, items)
        binding.menuInput.setAdapter(adapter)
        binding.menuInput.setText(items[0], false)

        binding.menuInput.onItemClickListener = itemClick { _, _, _, _ ->
            // Respond to list item click.
        }
    }
}