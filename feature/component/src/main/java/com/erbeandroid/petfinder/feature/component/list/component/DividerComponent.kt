package com.erbeandroid.petfinder.feature.component.list.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.erbeandroid.petfinder.feature.component.databinding.ComponentDividerBinding
import com.erbeandroid.petfinder.feature.component.list.util.DividerAdapter
import com.google.android.material.divider.MaterialDividerItemDecoration

class DividerComponent @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding =
        ComponentDividerBinding.inflate(LayoutInflater.from(context), this, true)

    private val dividerAdapter = DividerAdapter().apply {
        submitList(listOf("Item 1", "Item 2", "Item 3"))
    }

    init {
        binding.divider.dividerInsetStart = 16
        binding.divider.dividerInsetEnd = 16

        val divider = MaterialDividerItemDecoration(context, LinearLayoutManager.VERTICAL)
        divider.dividerInsetStart = 16
        divider.dividerInsetEnd = 16

        binding.recyclerView.adapter = dividerAdapter
        binding.recyclerView.addItemDecoration(divider)
    }
}