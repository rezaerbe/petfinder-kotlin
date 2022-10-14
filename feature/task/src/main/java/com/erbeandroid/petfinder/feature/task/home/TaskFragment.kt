package com.erbeandroid.petfinder.feature.task.home

import com.erbeandroid.petfinder.core.common.util.BaseFragment
import com.erbeandroid.petfinder.core.common.util.click
import com.erbeandroid.petfinder.feature.task.databinding.FragmentTaskBinding
import com.erbeandroid.petfinder.feature.task.util.taskToList
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TaskFragment :
    BaseFragment<FragmentTaskBinding>(FragmentTaskBinding::inflate) {

    override fun initObserver() = Unit

    override fun initInteraction() {
        binding.database.setOnClickListener(click {
            taskToList(this@TaskFragment)
        })
    }
}