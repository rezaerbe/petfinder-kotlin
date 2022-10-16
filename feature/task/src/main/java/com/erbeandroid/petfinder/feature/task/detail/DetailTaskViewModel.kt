package com.erbeandroid.petfinder.feature.task.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erbeandroid.petfinder.core.common.state.StateData
import com.erbeandroid.petfinder.core.data.model.local.Post
import com.erbeandroid.petfinder.core.data.repository.local.LocalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailTaskViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val localRepository: LocalRepository
) : ViewModel() {

    private val _postState = MutableStateFlow<StateData<Post>>(StateData.Loading)
    val postState: StateFlow<StateData<Post>> = _postState

    init {
        getDetailPost()
    }

    private fun getDetailPost() {
        val id = savedStateHandle.get<Int>("id") ?: 0
        viewModelScope.launch {
            try {
                localRepository.loadPostDetail(id)
                    .collect { postDetail ->
                        _postState.value = StateData.Success(postDetail)
                    }
            } catch (exception: Exception) {
                _postState.value = StateData.Error(exception)
            }
        }
    }
}