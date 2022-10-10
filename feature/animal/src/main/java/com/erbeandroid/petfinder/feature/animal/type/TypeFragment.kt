package com.erbeandroid.petfinder.feature.animal.type

import android.util.Log
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.erbeandroid.petfinder.core.common.util.BaseFragment
import com.erbeandroid.petfinder.core.common.util.StateData
import com.erbeandroid.petfinder.core.common.util.launchAndCollectIn
import com.erbeandroid.petfinder.feature.animal.databinding.FragmentTypeBinding
import com.erbeandroid.petfinder.feature.animal.util.typeToBreed
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TypeFragment :
    BaseFragment<FragmentTypeBinding>(FragmentTypeBinding::inflate) {

    private val typeViewModel: TypeViewModel by viewModels()
    private val adapter: TypeAdapter by lazy {
        TypeAdapter { type ->
            type.name?.let { name ->
                typeToBreed(this@TypeFragment, name)
            }
        }
    }

    override fun initObserver() {
        typeViewModel.typeUiState.launchAndCollectIn(viewLifecycleOwner) { typeState ->
            binding.progressBar.isVisible = typeState is StateData.Loading
            binding.recyclerView.isVisible = typeState is StateData.Success
            if (typeState is StateData.Success) {
                Log.d("TAG", typeState.data.toString())
                adapter.submitList(typeState.data)
            }
            if (typeState is StateData.Error) {
                Log.d("TAG", typeState.exception.toString())
            }
        }
    }

    override fun initInteraction() {
        binding.recyclerView.adapter = adapter

        binding.refreshLayout.setOnRefreshListener {
            typeViewModel.getTypes()
            binding.refreshLayout.isRefreshing = false
        }
    }
}