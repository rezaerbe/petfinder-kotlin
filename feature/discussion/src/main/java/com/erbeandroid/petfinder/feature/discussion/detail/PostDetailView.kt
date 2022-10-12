package com.erbeandroid.petfinder.feature.discussion.detail

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.erbeandroid.petfinder.core.firebase.database.model.Post
import com.erbeandroid.petfinder.feature.discussion.databinding.ViewPostDetailBinding

class PostDetailView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding = ViewPostDetailBinding.inflate(LayoutInflater.from(context), this)

    fun setPostDetailView(post: Post?) {
        binding.postTitle.text = post?.title
        binding.postDescription.text = post?.description
    }
}