package com.erbeandroid.petfinder.feature.task.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erbeandroid.petfinder.core.common.util.StateData
import com.erbeandroid.petfinder.core.data.model.Post
import com.erbeandroid.petfinder.core.data.repository.LocalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListTaskViewModel @Inject constructor(
    private val localRepository: LocalRepository
) : ViewModel() {

    private val _postState = MutableStateFlow<StateData<List<Post>>>(StateData.Loading)
    val postState: StateFlow<StateData<List<Post>>> = _postState

    init {
        getPosts()
    }

    private fun getPosts() {
        viewModelScope.launch {
            try {
                localRepository.loadPost()
                    .collect { posts ->
                        _postState.value = StateData.Success(posts)
                    }
            } catch (exception: Exception) {
                _postState.value = StateData.Error(exception)
            }
        }
    }
}