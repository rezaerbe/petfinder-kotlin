package com.erbeandroid.petfinder.feature.animal.breed

import com.erbeandroid.petfinder.core.common.util.BaseAdapter
import com.erbeandroid.petfinder.core.common.util.click
import com.erbeandroid.petfinder.core.data.model.Breed
import com.erbeandroid.petfinder.feature.animal.databinding.ItemBreedBinding

fun breedAdapter(
    onBreedClick: (Breed) -> Unit
) = BaseAdapter<Breed, ItemBreedBinding>(
    ItemBreedBinding::inflate,
    onItemBind = { item, binding ->
        binding.breed.text = item.name
        binding.breed.setOnClickListener(click {
            onBreedClick(item)
        })
    }
)