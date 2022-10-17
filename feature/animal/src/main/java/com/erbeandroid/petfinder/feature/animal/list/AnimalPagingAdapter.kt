package com.erbeandroid.petfinder.feature.animal.list

import com.erbeandroid.petfinder.core.common.base.BasePagingAdapter
import com.erbeandroid.petfinder.core.common.base.click
import com.erbeandroid.petfinder.core.data.model.remote.Animal
import com.erbeandroid.petfinder.feature.animal.databinding.ItemAnimalBinding

class AnimalPagingAdapter(
    onAnimalClick: (Animal) -> Unit
) : BasePagingAdapter<Animal, ItemAnimalBinding>(
    ItemAnimalBinding::inflate,
    onItemBind = { item, binding ->
        binding.animal.text = item.name
        binding.animal.setOnClickListener(click {
            onAnimalClick(item)
        })
    }
)