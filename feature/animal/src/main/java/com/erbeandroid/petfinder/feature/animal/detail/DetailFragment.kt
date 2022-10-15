package com.erbeandroid.petfinder.feature.animal.detail

import android.util.Log
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.erbeandroid.petfinder.core.common.base.BaseFragment
import com.erbeandroid.petfinder.core.common.extension.launchAndCollectIn
import com.erbeandroid.petfinder.core.common.state.StateData
import com.erbeandroid.petfinder.feature.animal.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment :
    BaseFragment<FragmentDetailBinding>(FragmentDetailBinding::inflate) {

    private val animalDetailViewModel: DetailViewModel by viewModels()

    override fun initInteraction() = Unit

    override fun initObservation() {
        animalDetailViewModel.animalDetailState.launchAndCollectIn(viewLifecycleOwner) { animalDetailState ->
            binding.progressBar.isVisible = animalDetailState is StateData.Loading
            binding.animalView.isVisible = animalDetailState is StateData.Success
            if (animalDetailState is StateData.Success) {
                Log.d("TAG", animalDetailState.data.toString())
                binding.animalView.setAnimalDetailView(animalDetailState.data)
            }
            if (animalDetailState is StateData.Error) {
                Log.d("TAG", animalDetailState.exception.toString())
            }
        }
    }
}