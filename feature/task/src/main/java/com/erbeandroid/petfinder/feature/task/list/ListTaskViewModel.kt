package com.erbeandroid.petfinder.feature.task.list

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
class ListTaskViewModel @Inject constructor(
    private val localRepository: LocalRepository
) : ViewModel() {

    private val _postListState = MutableStateFlow<StateData<List<Post>>>(StateData.Loading)
    val postListState: StateFlow<StateData<List<Post>>> = _postListState

    init {
        getPosts()
    }

    private fun getPosts() {
        viewModelScope.launch {
            try {
                localRepository.loadPost()
                    .collect { posts ->
                        _postListState.value = StateData.Success(posts)
                    }
            } catch (exception: Exception) {
                _postListState.value = StateData.Error(exception)
            }
        }
    }
}