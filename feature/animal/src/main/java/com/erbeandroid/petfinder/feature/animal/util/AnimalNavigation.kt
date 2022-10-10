package com.erbeandroid.petfinder.feature.animal.util

import androidx.navigation.fragment.findNavController
import com.erbeandroid.petfinder.feature.animal.breed.BreedFragment
import com.erbeandroid.petfinder.feature.animal.breed.BreedFragmentDirections
import com.erbeandroid.petfinder.feature.animal.list.AnimalFragment
import com.erbeandroid.petfinder.feature.animal.list.AnimalFragmentDirections
import com.erbeandroid.petfinder.feature.animal.type.TypeFragment
import com.erbeandroid.petfinder.feature.animal.type.TypeFragmentDirections

fun typeToBreed(fragment: TypeFragment, type: String) {
    fragment.findNavController().navigate(
        TypeFragmentDirections.actionTypeFragmentToBreedFragment(type)
    )
}

fun breedToAnimal(fragment: BreedFragment, type: String, breed: String) {
    fragment.findNavController().navigate(
        BreedFragmentDirections.actionBreedFragmentToAnimalFragment(type, breed)
    )
}

fun animalToDetail(fragment: AnimalFragment, id: Int) {
    fragment.findNavController().navigate(
        AnimalFragmentDirections.actionAnimalFragmentToDetailFragment(id)
    )
}