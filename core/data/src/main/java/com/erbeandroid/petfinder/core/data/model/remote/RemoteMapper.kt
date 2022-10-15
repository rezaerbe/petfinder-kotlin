package com.erbeandroid.petfinder.core.data.model.remote

import com.erbeandroid.petfinder.core.network.model.AnimalResponse
import com.erbeandroid.petfinder.core.network.model.BreedResponse
import com.erbeandroid.petfinder.core.network.model.TypeResponse

fun TypeResponse.asDomain() = Type(name = name)

fun BreedResponse.asDomain() = Breed(name = name)

fun AnimalResponse.asDomain() = Animal(
    id = id,
    name = name
)

fun AnimalResponse.asDomainDetail() = AnimalDetail(
    id = id,
    organizationId = organizationId,
    url = url,
    type = type,
    species = species,
    age = age,
    gender = gender,
    size = size,
    coat = coat,
    name = name,
    description = description
)