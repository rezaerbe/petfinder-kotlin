package com.erbeandroid.petfinder.feature.component.list.component

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.annotation.MenuRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import com.erbeandroid.petfinder.core.common.base.click
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

    val textMenu = binding.textMenu

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

        binding.buttonMenu.setOnClickListener(click { view ->
            showMenu(view, R.menu.option_menu)
        })
    }

    private fun showMenu(view: View, @MenuRes menuRes: Int) {
        val popup = PopupMenu(context, view)
        popup.menuInflater.inflate(menuRes, popup.menu)

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
}