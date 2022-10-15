package com.erbeandroid.petfinder.feature.discussion.home

import com.erbeandroid.petfinder.core.common.base.BaseFragment
import com.erbeandroid.petfinder.core.common.base.click
import com.erbeandroid.petfinder.feature.discussion.databinding.FragmentDiscussionBinding
import com.erbeandroid.petfinder.feature.discussion.util.discussionToListPost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DiscussionFragment :
    BaseFragment<FragmentDiscussionBinding>(FragmentDiscussionBinding::inflate) {

    override fun initInteraction() {
        binding.database.setOnClickListener(click {
            discussionToListPost(this@DiscussionFragment)
        })
    }

    override fun initObservation() = Unit
}