package com.erbeandroid.petfinder.feature.animal.detail

import android.util.Log
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.viewbinding.ViewBinding
import com.erbeandroid.petfinder.core.common.base.BaseAdapterMultiple
import com.erbeandroid.petfinder.core.common.base.BaseFragment
import com.erbeandroid.petfinder.core.common.extension.launchAndCollectIn
import com.erbeandroid.petfinder.core.common.state.StateData
import com.erbeandroid.petfinder.feature.animal.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment :
    BaseFragment<FragmentDetailBinding>(FragmentDetailBinding::inflate) {

    private val animalDetailViewModel: DetailViewModel by viewModels()
    private lateinit var detailAdapter: BaseAdapterMultiple<DataModel, ViewBinding>

    override fun initInteraction() {
        detailAdapter = DetailAdapter(
            onItemClick1 = {

            },
            onItemClick2 = {

            }
        )
        binding.recyclerView.adapter = detailAdapter

        val list = listOf(
            DataModel.Model1(1, 1),
            DataModel.Model2(2, "Text 1"),
            DataModel.Model1(3, 2),
            DataModel.Model1(4, 3),
            DataModel.Model2(5, "Text 2"),
            DataModel.Model1(6, 4),
            DataModel.Model1(7, 5),
            DataModel.Model1(8, 6),
            DataModel.Model2(5, "Text 3")
        )

        detailAdapter.submitList(list)
    }

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