package com.erbeandroid.petfinder.feature.animal.breed

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erbeandroid.petfinder.core.common.state.StateData
import com.erbeandroid.petfinder.core.data.model.remote.Breed
import com.erbeandroid.petfinder.core.data.repository.remote.RemoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BreedViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val remoteRepository: RemoteRepository
) : ViewModel() {

    private val _breedState = MutableStateFlow<StateData<List<Breed>>>(StateData.Loading)
    val breedUiState: StateFlow<StateData<List<Breed>>> = _breedState

    init {
        getBreeds()
    }

    fun getBreeds() {
        _breedState.value = StateData.Loading
        val type = savedStateHandle.get<String>("type") ?: ""
        viewModelScope.launch {
            try {
                remoteRepository.getBreeds(type)
                    .collect { breeds ->
                        _breedState.value = StateData.Success(breeds)
                    }
            } catch (exception: Exception) {
                _breedState.value = StateData.Error(exception)
            }
        }
    }
}