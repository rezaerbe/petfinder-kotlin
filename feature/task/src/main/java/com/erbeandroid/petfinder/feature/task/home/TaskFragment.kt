package com.erbeandroid.petfinder.feature.task.home

import com.erbeandroid.petfinder.core.common.base.BaseFragment
import com.erbeandroid.petfinder.core.common.base.click
import com.erbeandroid.petfinder.feature.task.databinding.FragmentTaskBinding
import com.erbeandroid.petfinder.feature.task.util.taskToListTask
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TaskFragment :
    BaseFragment<FragmentTaskBinding>(FragmentTaskBinding::inflate) {

    override fun initInteraction() {
        binding.database.setOnClickListener(click {
            taskToListTask(this@TaskFragment)
        })
    }

    override fun initObservation() = Unit
}