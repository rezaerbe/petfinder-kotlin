package com.erbeandroid.petfinder.feature.animal.list

import com.erbeandroid.petfinder.core.common.util.BasePagingAdapter
import com.erbeandroid.petfinder.core.common.util.click
import com.erbeandroid.petfinder.core.data.model.Animal
import com.erbeandroid.petfinder.feature.animal.databinding.ItemAnimalBinding

fun animalPagingAdapter(
    onAnimalClick: (Animal) -> Unit
) = BasePagingAdapter<Animal, ItemAnimalBinding>(
    ItemAnimalBinding::inflate,
    onItemBind = { item, binding ->
        binding.animal.text = item.name
        binding.animal.setOnClickListener(click {
            onAnimalClick(item)
        })
    }
)