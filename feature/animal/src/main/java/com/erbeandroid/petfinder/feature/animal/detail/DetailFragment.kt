package com.erbeandroid.petfinder.feature.animal.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.erbeandroid.petfinder.feature.animal.common.StateData
import com.erbeandroid.petfinder.feature.animal.common.launchAndCollectIn
import com.erbeandroid.petfinder.feature.animal.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val animalDetailViewModel: DetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeData()
    }

    private fun observeData() {
        animalDetailViewModel.animalDetailState.launchAndCollectIn(viewLifecycleOwner) { animalDetailState ->
            binding.progressBar.isVisible = animalDetailState is StateData.Loading
            if (animalDetailState is StateData.Success) {
                Log.d("TAG", animalDetailState.data.toString())
                binding.animalView.setAnimalDetailView(animalDetailState.data)
            }
            if (animalDetailState is StateData.Error) {
                Log.d("TAG", animalDetailState.exception.toString())
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}