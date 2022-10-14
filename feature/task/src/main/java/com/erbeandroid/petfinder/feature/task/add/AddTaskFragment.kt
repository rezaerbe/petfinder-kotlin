package com.erbeandroid.petfinder.feature.task.add

import androidx.fragment.app.viewModels
import com.erbeandroid.petfinder.core.common.util.BaseFragment
import com.erbeandroid.petfinder.core.common.util.click
import com.erbeandroid.petfinder.core.data.model.Post
import com.erbeandroid.petfinder.feature.task.databinding.FragmentAddTaskBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddTaskFragment :
    BaseFragment<FragmentAddTaskBinding>(FragmentAddTaskBinding::inflate) {

    private val addTaskViewModel: AddTaskViewModel by viewModels()

    override fun initObserver() = Unit

    override fun initInteraction() {
        binding.addTask.setOnClickListener(click {
            val title = binding.fieldTitle.text.toString()
            val description = binding.fieldDescription.text.toString()
            if (title.isNotEmpty() && description.isNotEmpty()) {
                addTaskViewModel.insertPost(
                    Post(
                        uid = "BfhlTcq9NvbNG5h1CpSIdn8itg03",
                        name = "Erbe",
                        title = title,
                        description = description
                    )
                )
            }
        })
    }
}