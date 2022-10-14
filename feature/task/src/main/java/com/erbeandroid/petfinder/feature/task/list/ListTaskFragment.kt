package com.erbeandroid.petfinder.feature.task.list

import android.util.Log
import androidx.fragment.app.viewModels
import com.erbeandroid.petfinder.core.common.util.BaseFragment
import com.erbeandroid.petfinder.core.common.util.StateData
import com.erbeandroid.petfinder.core.common.util.click
import com.erbeandroid.petfinder.core.common.util.launchAndCollectIn
import com.erbeandroid.petfinder.feature.task.databinding.FragmentListTaskBinding
import com.erbeandroid.petfinder.feature.task.util.listTaskToAddTask
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListTaskFragment :
    BaseFragment<FragmentListTaskBinding>(FragmentListTaskBinding::inflate) {

    private val listTaskViewModel: ListTaskViewModel by viewModels()

    override fun initObserver() {
        listTaskViewModel.postState.launchAndCollectIn(viewLifecycleOwner) { postState ->
            if (postState is StateData.Success) {
                Log.d("TAG", postState.data.toString())
            }
        }
    }

    override fun initInteraction() {
        binding.fab.setOnClickListener(click {
            listTaskToAddTask(this@ListTaskFragment)
        })
    }
}