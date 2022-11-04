package com.erbeandroid.petfinder.feature.animal.breed

import com.erbeandroid.petfinder.core.common.base.BaseAdapter
import com.erbeandroid.petfinder.core.common.base.click
import com.erbeandroid.petfinder.core.data.model.remote.Breed
import com.erbeandroid.petfinder.feature.animal.databinding.ItemBreedBinding

class BreedAdapter(
    onBreedClick: (Breed) -> Unit
) : BaseAdapter<Breed, ItemBreedBinding>(
    ItemBreedBinding::inflate,
    onItemBind = { item, binding, _, _ ->
        binding.breed.text = item.name
        binding.breed.setOnClickListener(click {
            onBreedClick(item)
        })
    }
)