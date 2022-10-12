package com.erbeandroid.petfinder.feature.discussion.list

import com.erbeandroid.petfinder.core.common.util.BaseAdapter
import com.erbeandroid.petfinder.core.common.util.click
import com.erbeandroid.petfinder.core.firebase.database.model.Post
import com.erbeandroid.petfinder.feature.discussion.databinding.ItemPostBinding

fun listPostAdapter(
    onPostClick: (Post) -> Unit
) = BaseAdapter<Post, ItemPostBinding>(
    ItemPostBinding::inflate,
    onItemBind = { item, binding ->
        binding.title.text = item.title
        binding.title.setOnClickListener(click {
            onPostClick(item)
        })
    }
)