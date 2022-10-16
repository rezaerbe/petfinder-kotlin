package com.erbeandroid.petfinder.feature.task.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erbeandroid.petfinder.core.common.state.StateData
import com.erbeandroid.petfinder.core.data.model.local.Post
import com.erbeandroid.petfinder.core.data.repository.local.LocalRepository
import com.erbeandroid.petfinder.core.firebase.user.FirebaseUserManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddTaskViewModel @Inject constructor(
    private val localRepository: LocalRepository,
    firebaseUserManager: FirebaseUserManager
) : ViewModel() {

    private val user = firebaseUserManager.currentUser()

    private val _state = MutableStateFlow<StateData<String>>(StateData.Loading)
    val state: StateFlow<StateData<String>> = _state

    fun insertPost(title: String, description: String) {
        user?.let {
            viewModelScope.launch {
                try {
                    localRepository.insertPost(
                        Post(
                            uid = user.uid,
                            name = user.name,
                            title = title,
                            description = description
                        )
                    )
                    _state.value = StateData.Success("Success")
                } catch (exception: Exception) {
                    _state.value = StateData.Error(exception)
                }
            }
        }
    }
}