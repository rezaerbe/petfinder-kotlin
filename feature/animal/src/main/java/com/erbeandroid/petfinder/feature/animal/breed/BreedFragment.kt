package com.erbeandroid.petfinder.feature.animal.breed

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.erbeandroid.petfinder.feature.animal.common.StateData
import com.erbeandroid.petfinder.feature.animal.common.launchAndCollectIn
import com.erbeandroid.petfinder.feature.animal.databinding.FragmentBreedBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BreedFragment : Fragment() {

    private var _binding: FragmentBreedBinding? = null
    private val binding get() = _binding!!

    private val breedViewModel: BreedViewModel by viewModels()
    private val args: BreedFragmentArgs by navArgs()
    private lateinit var adapter: BreedAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBreedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = BreedAdapter {
            it.name?.let { breed ->
                findNavController().navigate(
                    BreedFragmentDirections.actionBreedFragmentToAnimalFragment(args.type, breed)
                )
            }
        }
        binding.recyclerView.adapter = adapter

        binding.refreshLayout.setOnRefreshListener {
            breedViewModel.getBreeds()
            binding.refreshLayout.isRefreshing = false
        }

        observeData()
    }

    private fun observeData() {
        breedViewModel.breedUiState.launchAndCollectIn(viewLifecycleOwner) { breedState ->
            binding.progressBar.isVisible = breedState is StateData.Loading
            if (breedState is StateData.Success) {
                Log.d("TAG", breedState.data.toString())
                adapter.submitList(breedState.data)
            }
            if (breedState is StateData.Error) {
                Log.d("TAG", breedState.exception.toString())
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}