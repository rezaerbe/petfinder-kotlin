package com.erbeandroid.petfinder.feature.discussion.detail

import android.util.Log
import androidx.fragment.app.viewModels
import com.erbeandroid.petfinder.core.common.base.BaseFragment
import com.erbeandroid.petfinder.core.common.extension.launchAndCollectIn
import com.erbeandroid.petfinder.core.common.state.StateData
import com.erbeandroid.petfinder.feature.discussion.databinding.FragmentDetailPostBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailPostFragment :
    BaseFragment<FragmentDetailPostBinding>(FragmentDetailPostBinding::inflate) {

    private val detailPostViewModel: DetailPostViewModel by viewModels()

    override fun initInteraction() = Unit

    override fun initObservation() {
        detailPostViewModel.post.launchAndCollectIn(viewLifecycleOwner) { statePost ->
            if (statePost is StateData.Success) {
                Log.d("TAG", statePost.data.toString())
                // binding.postView.setPostDetailView(statePost.data)
            }
        }

        detailPostViewModel.postNew.launchAndCollectIn(viewLifecycleOwner) { statePost ->
            if (statePost is StateData.Success) {
                Log.d("TAG", statePost.data.toString())
                binding.postView.setPostDetailView(statePost.data)
            }
        }
    }
}