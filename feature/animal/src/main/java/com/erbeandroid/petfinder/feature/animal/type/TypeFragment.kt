package com.erbeandroid.petfinder.feature.animal.type

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.erbeandroid.petfinder.core.common.util.StateData
import com.erbeandroid.petfinder.core.common.util.launchAndCollectIn
import com.erbeandroid.petfinder.feature.animal.databinding.FragmentTypeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TypeFragment : Fragment() {

    private var _binding: FragmentTypeBinding? = null
    private val binding get() = _binding!!

    private val typeViewModel: TypeViewModel by viewModels()
    private lateinit var adapter: TypeAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTypeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = TypeAdapter {
            it.name?.let { type ->
                findNavController().navigate(
                    TypeFragmentDirections.actionTypeFragmentToBreedFragment(type)
                )
            }
        }
        binding.recyclerView.adapter = adapter

        binding.refreshLayout.setOnRefreshListener {
            typeViewModel.getTypes()
            binding.refreshLayout.isRefreshing = false
        }

        observeData()
    }

    private fun observeData() {
        typeViewModel.typeUiState.launchAndCollectIn(viewLifecycleOwner) { typeState ->
            binding.progressBar.isVisible = typeState is StateData.Loading
            if (typeState is StateData.Success) {
                Log.d("TAG", typeState.data.toString())
                adapter.submitList(typeState.data)
            }
            if (typeState is StateData.Error) {
                Log.d("TAG", typeState.exception.toString())
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}