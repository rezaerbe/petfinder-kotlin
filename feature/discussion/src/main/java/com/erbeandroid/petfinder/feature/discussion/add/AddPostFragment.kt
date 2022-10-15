package com.erbeandroid.petfinder.feature.discussion.add

import androidx.fragment.app.viewModels
import com.erbeandroid.petfinder.core.common.base.BaseFragment
import com.erbeandroid.petfinder.core.common.base.click
import com.erbeandroid.petfinder.core.common.extension.launchAndCollectIn
import com.erbeandroid.petfinder.feature.discussion.databinding.FragmentAddPostBinding
import com.erbeandroid.petfinder.feature.discussion.util.addPostToListPost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddPostFragment :
    BaseFragment<FragmentAddPostBinding>(FragmentAddPostBinding::inflate) {

    private val addPostViewModel: AddPostViewModel by viewModels()

    override fun initInteraction() {
        binding.addPost.setOnClickListener(click {
            val title = binding.fieldTitle.text.toString()
            val description = binding.fieldDescription.text.toString()
            if (title.isNotEmpty() && description.isNotEmpty()) {
                addPostViewModel.addPost(title, description)
            }
        })

        binding.addPostNew.setOnClickListener(click {
            val title = binding.fieldTitle.text.toString()
            val description = binding.fieldDescription.text.toString()
            if (title.isNotEmpty() && description.isNotEmpty()) {
                addPostViewModel.addPostNew(title, description)
            }
        })
    }

    override fun initObservation() {
        addPostViewModel.state.launchAndCollectIn(viewLifecycleOwner) { state ->
            if (state == "Success") {
                addPostToListPost(this@AddPostFragment)
            }
        }

        addPostViewModel.stateNew.launchAndCollectIn(viewLifecycleOwner) { state ->
            if (state == "Success") {
                addPostToListPost(this@AddPostFragment)
            }
        }
    }
}