package com.erbeandroid.petfinder.feature.animal.type

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erbeandroid.petfinder.core.data.model.Type
import com.erbeandroid.petfinder.core.data.repository.Repository
import com.erbeandroid.petfinder.feature.animal.common.StateData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TypeViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _typeState = MutableStateFlow<StateData<List<Type>>>(StateData.Loading)
    val typeUiState: StateFlow<StateData<List<Type>>> = _typeState

    init {
        getTypes()
    }

    fun getTypes() {
        viewModelScope.launch {
            try {
                repository.getTypes()
                    .collect { types ->
                        _typeState.value = StateData.Success(types)
                    }
            } catch (exception: Exception) {
                _typeState.value = StateData.Error(exception)
            }
        }
    }
}