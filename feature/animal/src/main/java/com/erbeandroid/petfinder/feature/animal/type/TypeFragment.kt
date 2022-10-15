package com.erbeandroid.petfinder.feature.animal.type

import android.util.Log
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.erbeandroid.petfinder.core.common.base.BaseAdapter
import com.erbeandroid.petfinder.core.common.base.BaseFragment
import com.erbeandroid.petfinder.core.common.extension.launchAndCollectIn
import com.erbeandroid.petfinder.core.common.state.StateData
import com.erbeandroid.petfinder.core.data.model.remote.Type
import com.erbeandroid.petfinder.feature.animal.databinding.FragmentTypeBinding
import com.erbeandroid.petfinder.feature.animal.databinding.ItemTypeBinding
import com.erbeandroid.petfinder.feature.animal.util.typeToBreed
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TypeFragment :
    BaseFragment<FragmentTypeBinding>(FragmentTypeBinding::inflate) {

    private val typeViewModel: TypeViewModel by viewModels()
    private lateinit var typeAdapter: BaseAdapter<Type, ItemTypeBinding>

    override fun initInteraction() {
        typeAdapter = typeAdapter { type ->
            type.name?.let { name ->
                typeToBreed(this@TypeFragment, name)
            }
        }
        binding.recyclerView.adapter = typeAdapter

        binding.refreshLayout.setOnRefreshListener {
            typeViewModel.getTypes()
            binding.refreshLayout.isRefreshing = false
        }
    }

    override fun initObservation() {
        typeViewModel.typeUiState.launchAndCollectIn(viewLifecycleOwner) { typeState ->
            binding.progressBar.isVisible = typeState is StateData.Loading
            binding.recyclerView.isVisible = typeState is StateData.Success
            if (typeState is StateData.Success) {
                Log.d("TAG", typeState.data.toString())
                typeAdapter.submitList(typeState.data)
            }
            if (typeState is StateData.Error) {
                Log.d("TAG", typeState.exception.toString())
            }
        }
    }
}