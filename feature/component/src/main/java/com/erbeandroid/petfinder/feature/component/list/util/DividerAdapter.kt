package com.erbeandroid.petfinder.feature.component.list.util

import com.erbeandroid.petfinder.core.common.base.BaseAdapter
import com.erbeandroid.petfinder.feature.component.databinding.ItemDividerBinding

class DividerAdapter :
    BaseAdapter<String, ItemDividerBinding>(
        ItemDividerBinding::inflate,
        onItemBind = { item, binding, _ ->
            binding.item.text = item
        }
    )