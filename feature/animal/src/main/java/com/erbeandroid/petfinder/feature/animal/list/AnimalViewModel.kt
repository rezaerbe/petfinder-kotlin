package com.erbeandroid.petfinder.feature.animal.list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.erbeandroid.petfinder.core.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AnimalViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    repository: Repository
) : ViewModel() {

    val animals = repository.getAnimals(
        savedStateHandle.get<String>("type") ?: "",
        savedStateHandle.get<String>("breed") ?: ""
    ).cachedIn(viewModelScope)
}