package com.erbeandroid.petfinder.feature.task.list

import com.erbeandroid.petfinder.core.common.util.BaseAdapter
import com.erbeandroid.petfinder.core.common.util.click
import com.erbeandroid.petfinder.core.data.model.Post
import com.erbeandroid.petfinder.feature.task.databinding.ItemTaskBinding

fun listTaskAdapter(
    onTaskClick: (Post) -> Unit
) = BaseAdapter<Post, ItemTaskBinding>(
    ItemTaskBinding::inflate,
    onItemBind = { item, binding ->
        binding.title.text = item.title
        binding.title.setOnClickListener(click {
            onTaskClick(item)
        })
    }
)