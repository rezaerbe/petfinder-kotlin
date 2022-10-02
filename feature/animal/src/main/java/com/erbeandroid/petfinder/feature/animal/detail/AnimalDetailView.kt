package com.erbeandroid.petfinder.feature.animal.detail

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.erbeandroid.petfinder.core.data.model.AnimalDetail
import com.erbeandroid.petfinder.feature.animal.databinding.ViewAnimalDetailBinding

class AnimalDetailView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding = ViewAnimalDetailBinding.inflate(LayoutInflater.from(context), this)

    fun setAnimalDetailView(animalDetail: AnimalDetail?) {
        binding.animalName.text = animalDetail?.name
        binding.animalDescription.text = animalDetail?.description ?: "No description"
    }
}