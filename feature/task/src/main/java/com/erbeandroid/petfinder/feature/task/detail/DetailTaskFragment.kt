package com.erbeandroid.petfinder.feature.task.detail

import android.util.Log
import androidx.fragment.app.viewModels
import com.erbeandroid.petfinder.core.common.base.BaseFragment
import com.erbeandroid.petfinder.core.common.extension.launchAndCollectIn
import com.erbeandroid.petfinder.core.common.state.StateData
import com.erbeandroid.petfinder.feature.task.databinding.FragmentDetailTaskBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailTaskFragment :
    BaseFragment<FragmentDetailTaskBinding>(FragmentDetailTaskBinding::inflate) {

    private val detailTaskViewModel: DetailTaskViewModel by viewModels()

    override fun initInteraction() = Unit

    override fun initObservation() {
        detailTaskViewModel.postState.launchAndCollectIn(viewLifecycleOwner) { postState ->
            if (postState is StateData.Success) {
                Log.d("TAG", postState.data.toString())
                binding.taskView.setTaskDetailView(postState.data)
            }
        }
    }
}