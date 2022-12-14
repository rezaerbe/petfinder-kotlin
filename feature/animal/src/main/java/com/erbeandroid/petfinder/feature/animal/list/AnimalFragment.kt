package com.erbeandroid.petfinder.feature.animal.list

import android.util.Log
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.paging.LoadState
import com.erbeandroid.petfinder.core.common.base.BaseFragment
import com.erbeandroid.petfinder.core.common.base.BasePagingAdapter
import com.erbeandroid.petfinder.core.common.extension.launchAndCollectIn
import com.erbeandroid.petfinder.core.data.model.remote.Animal
import com.erbeandroid.petfinder.feature.animal.databinding.FragmentAnimalBinding
import com.erbeandroid.petfinder.feature.animal.databinding.ItemAnimalBinding
import com.erbeandroid.petfinder.feature.animal.util.animalToDetail
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AnimalFragment :
    BaseFragment<FragmentAnimalBinding>(FragmentAnimalBinding::inflate) {

    private val animalViewModel: AnimalViewModel by viewModels()
    private lateinit var animalPagingAdapter: BasePagingAdapter<Animal, ItemAnimalBinding>

    override fun initInteraction() {
        animalPagingAdapter = AnimalPagingAdapter { animal ->
            animal.id?.let { id ->
                animalToDetail(this@AnimalFragment, id)
            }
        }
        binding.recyclerView.adapter = animalPagingAdapter
    }

    override fun initObservation() {
        animalPagingAdapter.loadStateFlow.launchAndCollectIn(viewLifecycleOwner) { loadState ->
            binding.progressBar.isVisible = loadState.refresh is LoadState.Loading
            if (loadState.refresh !is LoadState.Loading) {
                Log.d("TAG", "Retry")
            }
            if (loadState.refresh is LoadState.Error) {
                Log.d("TAG", "Error")
            }
        }

        animalViewModel.animals.launchAndCollectIn(viewLifecycleOwner) { animals ->
            animalPagingAdapter.submitData(animals)
        }
    }
}