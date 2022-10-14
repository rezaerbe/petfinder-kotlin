package com.erbeandroid.petfinder.feature.task.add

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erbeandroid.petfinder.core.data.model.Post
import com.erbeandroid.petfinder.core.data.repository.LocalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddTaskViewModel @Inject constructor(
    private val localRepository: LocalRepository
) : ViewModel() {

    fun insertPost(post: Post) {
        viewModelScope.launch {
            try {
                localRepository.insertPost(post)
            } catch (exception: Exception) {
                Log.d("TAG", "insertPost: Error")
            }
        }
    }
}