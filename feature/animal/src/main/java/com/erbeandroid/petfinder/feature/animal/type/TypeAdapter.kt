package com.erbeandroid.petfinder.feature.animal.type

import com.erbeandroid.petfinder.core.common.util.BaseAdapter
import com.erbeandroid.petfinder.core.common.util.click
import com.erbeandroid.petfinder.core.data.model.Type
import com.erbeandroid.petfinder.feature.animal.databinding.ItemTypeBinding

fun typeAdapter(
    onTypeClick: (Type) -> Unit
) = BaseAdapter<Type, ItemTypeBinding>(
    ItemTypeBinding::inflate,
    onItemBind = { item, binding ->
        binding.type.text = item.name
        binding.type.setOnClickListener(click {
            onTypeClick(item)
        })
    }
)