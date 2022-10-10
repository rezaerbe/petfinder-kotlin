package com.erbeandroid.petfinder.feature.animal.breed

import android.util.Log
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.erbeandroid.petfinder.core.common.util.BaseFragment
import com.erbeandroid.petfinder.core.common.util.StateData
import com.erbeandroid.petfinder.core.common.util.launchAndCollectIn
import com.erbeandroid.petfinder.feature.animal.databinding.FragmentBreedBinding
import com.erbeandroid.petfinder.feature.animal.util.breedToAnimal
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BreedFragment :
    BaseFragment<FragmentBreedBinding>(FragmentBreedBinding::inflate) {

    private val breedViewModel: BreedViewModel by viewModels()
    private val args: BreedFragmentArgs by navArgs()
    private val adapter: BreedAdapter by lazy {
        BreedAdapter { breed ->
            breed.name?.let { name ->
                breedToAnimal(this@BreedFragment, args.type, name)
            }
        }
    }

    override fun initObserver() {
        breedViewModel.breedUiState.launchAndCollectIn(viewLifecycleOwner) { breedState ->
            binding.progressBar.isVisible = breedState is StateData.Loading
            binding.recyclerView.isVisible = breedState is StateData.Success
            if (breedState is StateData.Success) {
                Log.d("TAG", breedState.data.toString())
                adapter.submitList(breedState.data)
            }
            if (breedState is StateData.Error) {
                Log.d("TAG", breedState.exception.toString())
            }
        }
    }

    override fun initInteraction() {
        binding.recyclerView.adapter = adapter

        binding.refreshLayout.setOnRefreshListener {
            breedViewModel.getBreeds()
            binding.refreshLayout.isRefreshing = false
        }
    }
}