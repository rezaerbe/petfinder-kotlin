package com.erbeandroid.petfinder.feature.animal.type

import com.erbeandroid.petfinder.core.common.base.BaseAdapter
import com.erbeandroid.petfinder.core.common.base.click
import com.erbeandroid.petfinder.core.data.model.remote.Type
import com.erbeandroid.petfinder.feature.animal.databinding.ItemTypeBinding

class TypeAdapter(
    onTypeClick: (Type) -> Unit
) : BaseAdapter<Type, ItemTypeBinding>(
    ItemTypeBinding::inflate,
    onItemBind = { item, binding ->
        binding.type.text = item.name
        binding.type.setOnClickListener(click {
            onTypeClick(item)
        })
    }
)