package com.erbeandroid.petfinder.feature.task.list

import android.util.Log
import androidx.fragment.app.viewModels
import com.erbeandroid.petfinder.core.common.base.BaseAdapter
import com.erbeandroid.petfinder.core.common.base.BaseFragment
import com.erbeandroid.petfinder.core.common.base.click
import com.erbeandroid.petfinder.core.common.extension.launchAndCollectIn
import com.erbeandroid.petfinder.core.common.state.StateData
import com.erbeandroid.petfinder.core.data.model.local.Post
import com.erbeandroid.petfinder.feature.task.databinding.FragmentListTaskBinding
import com.erbeandroid.petfinder.feature.task.databinding.ItemTaskBinding
import com.erbeandroid.petfinder.feature.task.util.listTaskToAddTask
import com.erbeandroid.petfinder.feature.task.util.listTaskToDetailTask
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListTaskFragment :
    BaseFragment<FragmentListTaskBinding>(FragmentListTaskBinding::inflate) {

    private val listTaskViewModel: ListTaskViewModel by viewModels()
    private lateinit var listTaskAdapter: BaseAdapter<Post, ItemTaskBinding>

    override fun initInteraction() {
        listTaskAdapter = listTaskAdapter { post ->
            post.id?.let { id ->
                listTaskToDetailTask(this@ListTaskFragment, id)
            }
        }
        binding.recyclerView.adapter = listTaskAdapter

        binding.fab.setOnClickListener(click {
            listTaskToAddTask(this@ListTaskFragment)
        })
    }

    override fun initObservation() {
        listTaskViewModel.postState.launchAndCollectIn(viewLifecycleOwner) { postState ->
            if (postState is StateData.Success) {
                Log.d("TAG", postState.data.toString())
                listTaskAdapter.submitList(postState.data)
            }
        }
    }
}