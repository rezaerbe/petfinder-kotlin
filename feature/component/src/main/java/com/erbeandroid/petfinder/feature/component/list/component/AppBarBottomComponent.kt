package com.erbeandroid.petfinder.feature.component.list.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.erbeandroid.petfinder.core.common.base.click
import com.erbeandroid.petfinder.core.common.base.toolbarMenuItemClick
import com.erbeandroid.petfinder.feature.component.R
import com.erbeandroid.petfinder.feature.component.databinding.ComponentAppBarBottomBinding

class AppBarBottomComponent @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding =
        ComponentAppBarBottomBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        binding.bottomAppBar.setNavigationOnClickListener(click {
            // Handle navigation icon press
        })

        binding.bottomAppBarFab.setOnClickListener(click {
            // Respond to FAB click
        })

        binding.bottomAppBar.setOnMenuItemClickListener(toolbarMenuItemClick { menuItem ->
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

/*        // CustomAppBarBottom
        val topEdge = BottomAppBarCutCornersTopEdge(
            binding.bottomAppBar.fabCradleMargin,
            binding.bottomAppBar.fabCradleRoundedCornerRadius,
            binding.bottomAppBar.cradleVerticalOffset
        )
        val background = binding.bottomAppBar.background as MaterialShapeDrawable
        background.shapeAppearanceModel =
            background.shapeAppearanceModel.toBuilder().setTopEdge(topEdge).build()*/
    }
}