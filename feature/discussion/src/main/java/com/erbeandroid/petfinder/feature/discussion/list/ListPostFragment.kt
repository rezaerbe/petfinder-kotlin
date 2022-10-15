package com.erbeandroid.petfinder.feature.discussion.list

import android.util.Log
import androidx.fragment.app.viewModels
import com.erbeandroid.petfinder.core.common.base.BaseAdapter
import com.erbeandroid.petfinder.core.common.base.BaseFragment
import com.erbeandroid.petfinder.core.common.base.click
import com.erbeandroid.petfinder.core.common.extension.launchAndCollectIn
import com.erbeandroid.petfinder.core.common.state.StateData
import com.erbeandroid.petfinder.core.firebase.model.Post
import com.erbeandroid.petfinder.feature.discussion.databinding.FragmentListPostBinding
import com.erbeandroid.petfinder.feature.discussion.databinding.ItemPostBinding
import com.erbeandroid.petfinder.feature.discussion.util.listPostToAddPost
import com.erbeandroid.petfinder.feature.discussion.util.listPostToDetailPost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListPostFragment :
    BaseFragment<FragmentListPostBinding>(FragmentListPostBinding::inflate) {

    private val listPostViewModel: ListPostViewModel by viewModels()
    private lateinit var listPostAdapter: BaseAdapter<Post, ItemPostBinding>

    override fun initInteraction() {
        listPostAdapter = listPostAdapter { post ->
            post.id?.let { id ->
                listPostToDetailPost(this@ListPostFragment, id)
            }
        }
        binding.recyclerView.adapter = listPostAdapter

        binding.fab.setOnClickListener(click {
            listPostToAddPost(this@ListPostFragment)
        })
    }

    override fun initObservation() {
        listPostViewModel.listPost.launchAndCollectIn(viewLifecycleOwner) { stateListPost ->
            if (stateListPost is StateData.Success) {
                Log.d("TAG", stateListPost.data.toString())
                // listPostAdapter.submitList(stateListPost.data)
            }
        }

        listPostViewModel.listPostNew.launchAndCollectIn(viewLifecycleOwner) { stateListPost ->
            if (stateListPost is StateData.Success) {
                Log.d("TAG", stateListPost.data.toString())
                listPostAdapter.submitList(stateListPost.data)
            }
        }
    }
}