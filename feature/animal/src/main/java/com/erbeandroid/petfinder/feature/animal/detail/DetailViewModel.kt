package com.erbeandroid.petfinder.feature.animal.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erbeandroid.petfinder.core.data.model.AnimalDetail
import com.erbeandroid.petfinder.core.data.repository.Repository
import com.erbeandroid.petfinder.feature.animal.common.StateData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: Repository
) : ViewModel() {

    private val _animalDetailState = MutableStateFlow<StateData<AnimalDetail?>>(StateData.Loading)
    val animalDetailState: StateFlow<StateData<AnimalDetail?>> = _animalDetailState

    init {
        getAnimalDetail()
    }

    private fun getAnimalDetail() {
        val id = savedStateHandle.get<Int>("id") ?: 0
        viewModelScope.launch {
            try {
                repository.getAnimalDetail(id)
                    .collect { animalDetail ->
                        _animalDetailState.value = StateData.Success(animalDetail)
                    }
            } catch (exception: Exception) {
                _animalDetailState.value = StateData.Error(exception)
            }
        }
    }
}