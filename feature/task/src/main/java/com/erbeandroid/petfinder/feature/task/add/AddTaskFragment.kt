package com.erbeandroid.petfinder.feature.task.add

import androidx.fragment.app.viewModels
import com.erbeandroid.petfinder.core.common.base.BaseFragment
import com.erbeandroid.petfinder.core.common.base.click
import com.erbeandroid.petfinder.core.common.extension.launchAndCollectIn
import com.erbeandroid.petfinder.core.common.state.StateData
import com.erbeandroid.petfinder.feature.task.databinding.FragmentAddTaskBinding
import com.erbeandroid.petfinder.feature.task.util.addTaskToListTask
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

    override fun initObservation() {
        addTaskViewModel.state.launchAndCollectIn(viewLifecycleOwner) { state ->
            if (state is StateData.Success) {
                if (state.data == "Success") {
                    addTaskToListTask(this@AddTaskFragment)
                }
            }
        }
    }
}