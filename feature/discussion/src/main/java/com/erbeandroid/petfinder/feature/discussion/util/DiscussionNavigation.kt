package com.erbeandroid.petfinder.feature.discussion.util

import androidx.navigation.fragment.findNavController
import com.erbeandroid.petfinder.feature.discussion.add.AddPostFragment
import com.erbeandroid.petfinder.feature.discussion.add.AddPostFragmentDirections
import com.erbeandroid.petfinder.feature.discussion.home.DiscussionFragment
import com.erbeandroid.petfinder.feature.discussion.home.DiscussionFragmentDirections
import com.erbeandroid.petfinder.feature.discussion.list.ListPostFragment
import com.erbeandroid.petfinder.feature.discussion.list.ListPostFragmentDirections

fun discussionToListPost(fragment: DiscussionFragment) {
    fragment.findNavController().navigate(
        DiscussionFragmentDirections.actionDiscussionFragmentToListPostFragment()
    )
}

fun listPostToAddPost(fragment: ListPostFragment) {
    fragment.findNavController().navigate(
        ListPostFragmentDirections.actionListPostFragmentToAddPostFragment()
    )
}

fun addPostToListPost(fragment: AddPostFragment) {
    fragment.findNavController().navigate(
        AddPostFragmentDirections.actionAddPostFragmentToListPostFragment()
    )
}

fun listPostToDetailPost(fragment: ListPostFragment, id: String) {
    fragment.findNavController().navigate(
        ListPostFragmentDirections.actionListPostFragmentToDetailPostFragment(id)
    )
}