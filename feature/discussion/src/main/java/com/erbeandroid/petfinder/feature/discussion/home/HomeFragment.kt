package com.erbeandroid.petfinder.feature.discussion.home

import com.erbeandroid.petfinder.core.common.util.BaseFragment
import com.erbeandroid.petfinder.core.common.util.click
import com.erbeandroid.petfinder.feature.discussion.databinding.FragmentHomeBinding
import com.erbeandroid.petfinder.feature.discussion.util.homeToListPost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment :
    BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    override fun initObserver() = Unit

    override fun initInteraction() {
        binding.database.setOnClickListener(click {
            homeToListPost(this@HomeFragment)
        })
    }
}