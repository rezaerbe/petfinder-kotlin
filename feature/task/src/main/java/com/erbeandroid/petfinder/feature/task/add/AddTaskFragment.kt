package com.erbeandroid.petfinder.feature.task.add

import androidx.fragment.app.viewModels
import com.erbeandroid.petfinder.core.common.base.BaseFragment
import com.erbeandroid.petfinder.core.common.base.click
import com.erbeandroid.petfinder.feature.task.databinding.FragmentAddTaskBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddTaskFragment :
    BaseFragment<FragmentAddTaskBinding>(FragmentAddTaskBinding::inflate) {

    private val addTaskViewModel: AddTaskViewModel by viewModels()

    override fun initInteraction() {
        binding.addTask.setOnClickListener(click {
            val title = binding.fieldTitle.text.toString()
            val description = binding.fieldDescription.text.toString()
            if (title.isNotEmpty() && description.isNotEmpty()) {
                addTaskViewModel.insertPost(title, description)
            }
        })
    }

    override fun initObservation() = Unit
}