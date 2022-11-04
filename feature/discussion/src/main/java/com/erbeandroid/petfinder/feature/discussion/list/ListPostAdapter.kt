package com.erbeandroid.petfinder.feature.discussion.list

import com.erbeandroid.petfinder.core.common.base.BaseAdapter
import com.erbeandroid.petfinder.core.common.base.click
import com.erbeandroid.petfinder.core.firebase.model.Post
import com.erbeandroid.petfinder.feature.discussion.databinding.ItemPostBinding

class ListPostAdapter(
    onPostClick: (Post) -> Unit
) : BaseAdapter<Post, ItemPostBinding>(
    ItemPostBinding::inflate,
    onItemBind = { item, binding, _, _ ->
        binding.title.text = item.title
        binding.title.setOnClickListener(click {
            onPostClick(item)
        })
    }
)