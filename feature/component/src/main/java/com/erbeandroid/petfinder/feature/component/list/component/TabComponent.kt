package com.erbeandroid.petfinder.feature.component.list.component

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.erbeandroid.petfinder.feature.component.databinding.ComponentTabBinding
import com.google.android.material.tabs.TabLayout

class TabComponent @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding =
        ComponentTabBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                // Handle tab select
                Log.d("TAG", "onTabSelected: ${tab?.text}")
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                // Handle tab reselect
                Log.d("TAG", "onTabUnselected: ${tab?.text}")
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                // Handle tab unselect
                Log.d("TAG", "onTabReselected: ${tab?.text}")
            }
        })

        val tab1 = binding.tabLayout.getTabAt(0)
        val tab2 = binding.tabLayout.getTabAt(1)
        val tab3 = binding.tabLayout.getTabAt(2)

        tab1?.orCreateBadge
        val badge2 = tab2?.orCreateBadge
        val badge3 = tab3?.orCreateBadge

        badge2?.number = 1
        badge3?.number = 1000

        // tab1.removeBadge()
        // tab2.removeBadge()
        // tab3.removeBadge()
    }
}