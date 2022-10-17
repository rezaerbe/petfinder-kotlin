package com.erbeandroid.petfinder.feature.animal.breed

import android.util.Log
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.erbeandroid.petfinder.core.common.base.BaseAdapter
import com.erbeandroid.petfinder.core.common.base.BaseFragment
import com.erbeandroid.petfinder.core.common.extension.launchAndCollectIn
import com.erbeandroid.petfinder.core.common.state.StateData
import com.erbeandroid.petfinder.core.data.model.remote.Breed
import com.erbeandroid.petfinder.feature.animal.databinding.FragmentBreedBinding
import com.erbeandroid.petfinder.feature.animal.databinding.ItemBreedBinding
import com.erbeandroid.petfinder.feature.animal.util.breedToAnimal
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BreedFragment :
    BaseFragment<FragmentBreedBinding>(FragmentBreedBinding::inflate) {

    private val breedViewModel: BreedViewModel by viewModels()
    private val args: BreedFragmentArgs by navArgs()
    private lateinit var breedAdapter: BaseAdapter<Breed, ItemBreedBinding>

    override fun initInteraction() {
        breedAdapter = BreedAdapter { breed ->
            breed.name?.let { name ->
                breedToAnimal(this@BreedFragment, args.type, name)
            }
        }
        binding.recyclerView.adapter = breedAdapter

        binding.refreshLayout.setOnRefreshListener {
            breedViewModel.getBreeds()
            binding.refreshLayout.isRefreshing = false
        }
    }

    override fun initObservation() {
        breedViewModel.breedUiState.launchAndCollectIn(viewLifecycleOwner) { breedState ->
            binding.progressBar.isVisible = breedState is StateData.Loading
            binding.recyclerView.isVisible = breedState is StateData.Success
            if (breedState is StateData.Success) {
                Log.d("TAG", breedState.data.toString())
                breedAdapter.submitList(breedState.data)
            }
            if (breedState is StateData.Error) {
                Log.d("TAG", breedState.exception.toString())
            }
        }
    }
}