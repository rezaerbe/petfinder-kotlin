package com.erbeandroid.petfinder.feature.task.detail

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.erbeandroid.petfinder.core.data.model.local.Post
import com.erbeandroid.petfinder.feature.task.databinding.ViewTaskDetailBinding

class TaskDetailView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding = ViewTaskDetailBinding.inflate(LayoutInflater.from(context), this)

    fun setTaskDetailView(post: Post) {
        binding.taskTitle.text = post.title
        binding.taskDescription.text = post.description
    }
}