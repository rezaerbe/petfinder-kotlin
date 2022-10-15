package com.erbeandroid.petfinder.feature.task.util

import androidx.navigation.fragment.findNavController
import com.erbeandroid.petfinder.feature.task.add.AddTaskFragment
import com.erbeandroid.petfinder.feature.task.add.AddTaskFragmentDirections
import com.erbeandroid.petfinder.feature.task.home.TaskFragment
import com.erbeandroid.petfinder.feature.task.home.TaskFragmentDirections
import com.erbeandroid.petfinder.feature.task.list.ListTaskFragment
import com.erbeandroid.petfinder.feature.task.list.ListTaskFragmentDirections

fun taskToListTask(fragment: TaskFragment) {
    fragment.findNavController().navigate(
        TaskFragmentDirections.actionTaskFragmentToListTaskFragment()
    )
}

fun listTaskToAddTask(fragment: ListTaskFragment) {
    fragment.findNavController().navigate(
        ListTaskFragmentDirections.actionListTaskFragmentToAddTaskFragment()
    )
}

fun addTaskToListTask(fragment: AddTaskFragment) {
    fragment.findNavController().navigate(
        AddTaskFragmentDirections.actionAddTaskFragmentToListTaskFragment()
    )
}

fun listTaskToDetailTask(fragment: ListTaskFragment, id: Int) {
    fragment.findNavController().navigate(
        ListTaskFragmentDirections.actionListTaskFragmentToDetailTaskFragment(id)
    )
}