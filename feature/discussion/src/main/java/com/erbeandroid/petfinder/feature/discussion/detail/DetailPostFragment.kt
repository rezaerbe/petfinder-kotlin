package com.erbeandroid.petfinder.feature.discussion.detail

import android.util.Log
import androidx.fragment.app.viewModels
import com.erbeandroid.petfinder.core.common.util.BaseFragment
import com.erbeandroid.petfinder.core.common.util.launchAndCollectIn
import com.erbeandroid.petfinder.feature.discussion.databinding.FragmentDetailPostBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailPostFragment :
    BaseFragment<FragmentDetailPostBinding>(FragmentDetailPostBinding::inflate) {

    private val detailPostViewModel: DetailPostViewModel by viewModels()

    override fun initObserver() {
        detailPostViewModel.post.launchAndCollectIn(viewLifecycleOwner) { post ->
            Log.d("TAG", post.toString())
            // binding.postView.setPostDetailView(post)
        }

        detailPostViewModel.postNew.launchAndCollectIn(viewLifecycleOwner) { post ->
            Log.d("TAG", post.toString())
            binding.postView.setPostDetailView(post)
        }
    }

    override fun initInteraction() = Unit
}