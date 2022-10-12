package com.erbeandroid.petfinder.feature.discussion.list

import android.util.Log
import androidx.fragment.app.viewModels
import com.erbeandroid.petfinder.core.common.util.BaseAdapter
import com.erbeandroid.petfinder.core.common.util.BaseFragment
import com.erbeandroid.petfinder.core.common.util.click
import com.erbeandroid.petfinder.core.common.util.launchAndCollectIn
import com.erbeandroid.petfinder.core.firebase.database.model.Post
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

    override fun initObserver() {
        listPostViewModel.listPost.launchAndCollectIn(viewLifecycleOwner) { listPost ->
            Log.d("TAG", listPost.toString())
            listPostAdapter.submitList(listPost)
        }
    }

    override fun initInteraction() {
        listPostAdapter = listPostAdapter { post ->
            post.key?.let { key ->
                listPostToDetailPost(this@ListPostFragment, key)
            }
        }
        binding.recyclerView.adapter = listPostAdapter

        binding.fab.setOnClickListener(click {
            listPostToAddPost(this@ListPostFragment)
        })
    }
}