package com.erbeandroid.petfinder.feature.task.util

import androidx.navigation.fragment.findNavController
import com.erbeandroid.petfinder.feature.task.home.TaskFragment
import com.erbeandroid.petfinder.feature.task.home.TaskFragmentDirections
import com.erbeandroid.petfinder.feature.task.list.ListTaskFragment
import com.erbeandroid.petfinder.feature.task.list.ListTaskFragmentDirections

fun taskToList(fragment: TaskFragment) {
    fragment.findNavController().navigate(
        TaskFragmentDirections.actionTaskFragmentToListTaskFragment()
    )
}

fun listTaskToAddTask(fragment: ListTaskFragment) {
    fragment.findNavController().navigate(
        ListTaskFragmentDirections.actionListTaskFragmentToAddTaskFragment()
    )
}