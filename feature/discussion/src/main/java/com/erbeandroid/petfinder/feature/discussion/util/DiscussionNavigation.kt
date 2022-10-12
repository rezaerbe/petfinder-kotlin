package com.erbeandroid.petfinder.feature.discussion.util

import androidx.navigation.fragment.findNavController
import com.erbeandroid.petfinder.feature.discussion.add.AddPostFragment
import com.erbeandroid.petfinder.feature.discussion.add.AddPostFragmentDirections
import com.erbeandroid.petfinder.feature.discussion.home.HomeFragment
import com.erbeandroid.petfinder.feature.discussion.home.HomeFragmentDirections
import com.erbeandroid.petfinder.feature.discussion.list.ListPostFragment
import com.erbeandroid.petfinder.feature.discussion.list.ListPostFragmentDirections

fun homeToListPost(fragment: HomeFragment) {
    fragment.findNavController().navigate(
        HomeFragmentDirections.actionHomeFragmentToListPostFragment()
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

fun listPostToDetailPost(fragment: ListPostFragment, key: String) {
    fragment.findNavController().navigate(
        ListPostFragmentDirections.actionListPostFragmentToDetailPostFragment(key)
    )
}