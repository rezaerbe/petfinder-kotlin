package com.erbeandroid.petfinder.feature.task.add

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erbeandroid.petfinder.core.data.model.local.Post
import com.erbeandroid.petfinder.core.data.repository.local.LocalRepository
import com.erbeandroid.petfinder.core.firebase.user.FirebaseUserManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddTaskViewModel @Inject constructor(
    private val localRepository: LocalRepository,
    firebaseUserManager: FirebaseUserManager
) : ViewModel() {

    private val user = firebaseUserManager.currentUser()

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
                } catch (exception: Exception) {
                    Log.d("TAG", "insertPost: Error")
                }
            }
        }
    }
}