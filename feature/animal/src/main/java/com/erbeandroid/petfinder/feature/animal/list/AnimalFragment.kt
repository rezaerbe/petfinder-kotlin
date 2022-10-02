package com.erbeandroid.petfinder.feature.animal.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.erbeandroid.petfinder.feature.animal.common.launchAndCollectIn
import com.erbeandroid.petfinder.feature.animal.databinding.FragmentAnimalBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AnimalFragment : Fragment() {

    private var _binding: FragmentAnimalBinding? = null
    private val binding get() = _binding!!

    private val animalViewModel: AnimalViewModel by viewModels()
    private lateinit var adapter: AnimalPagingAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAnimalBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = AnimalPagingAdapter {
            it.id?.let { id ->
                findNavController().navigate(
                    AnimalFragmentDirections.actionAnimalFragmentToDetailFragment(id)
                )
            }
        }
        binding.recyclerView.adapter = adapter

        observeData()
    }

    private fun observeData() {
        adapter.loadStateFlow.launchAndCollectIn(viewLifecycleOwner) { loadState ->
            binding.progressBar.isVisible = loadState.refresh is LoadState.Loading
            if (loadState.refresh !is LoadState.Loading) {
                Log.d("TAG", "Retry")
            }
            if (loadState.refresh is LoadState.Error) {
                Log.d("TAG", "Error")
            }
        }

        animalViewModel.animals.launchAndCollectIn(viewLifecycleOwner) { animals ->
            adapter.submitData(animals)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}