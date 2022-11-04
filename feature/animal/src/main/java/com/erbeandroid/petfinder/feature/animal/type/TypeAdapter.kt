package com.erbeandroid.petfinder.feature.animal.type

import android.graphics.Color
import com.erbeandroid.petfinder.core.common.base.BaseAdapterSelection
import com.erbeandroid.petfinder.core.common.base.click
import com.erbeandroid.petfinder.core.data.model.remote.Type
import com.erbeandroid.petfinder.feature.animal.databinding.ItemTypeBinding

class TypeAdapter(
    onTypeClick: (Type) -> Unit
) : BaseAdapterSelection<Type, ItemTypeBinding>(
    ItemTypeBinding::inflate,
    onItemBind = { item, binding, _, selection ->
        binding.type.text = item.name
        binding.type.setOnClickListener(click {
            onTypeClick(item)
        })
        if (selection) {
            binding.type.setBackgroundColor(Color.BLACK)
        } else {
            binding.type.setBackgroundColor(Color.BLUE)
        }
    }
)