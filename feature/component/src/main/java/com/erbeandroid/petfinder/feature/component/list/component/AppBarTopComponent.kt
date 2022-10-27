package com.erbeandroid.petfinder.feature.component.list.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.erbeandroid.petfinder.core.common.base.click
import com.erbeandroid.petfinder.core.common.base.toolbarMenuItemClick
import com.erbeandroid.petfinder.feature.component.R
import com.erbeandroid.petfinder.feature.component.databinding.ComponentAppBarTopBinding

class AppBarTopComponent @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding =
        ComponentAppBarTopBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        binding.topAppBar.setNavigationOnClickListener(click {
            // Handle navigation icon press
        })

        binding.topAppBar.setOnMenuItemClickListener(toolbarMenuItemClick { menuItem ->
            when (menuItem.itemId) {
                R.id.search -> {
                    // Handle search icon press
                    true
                }
                R.id.more -> {
                    // Handle more item (inside overflow menu) press
                    true
                }
                else -> false
            }
        })

/*        val activity = (context as AppCompatActivity)

        val callback = object : ActionMode.Callback {
            override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                activity.menuInflater.inflate(R.menu.top_app_bar, menu)
                return true
            }

            override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                return false
            }

            override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
                return when (item?.itemId) {
                    R.id.search -> {
                        // Handle search icon press
                        true
                    }
                    R.id.more -> {
                        // Handle more item (inside overflow menu) press
                        true
                    }
                    else -> false
                }
            }

            override fun onDestroyActionMode(mode: ActionMode?) {}
        }

        val actionMode = activity.startSupportActionMode(callback)
        actionMode?.title = "1 selected"*/
    }
}