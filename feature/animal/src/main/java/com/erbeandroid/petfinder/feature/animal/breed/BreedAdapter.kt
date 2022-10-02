package com.erbeandroid.petfinder.feature.animal.breed

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.erbeandroid.petfinder.core.data.model.Breed
import com.erbeandroid.petfinder.feature.animal.databinding.ItemBreedBinding

class BreedAdapter(
    private val onBreedClick: (Breed) -> Unit
) : ListAdapter<Breed, BreedAdapter.BreedViewHolder>(BreedCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreedViewHolder {
        val binding = ItemBreedBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return BreedViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BreedViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class BreedViewHolder(
        private val binding: ItemBreedBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Breed) {
            binding.breed.text = item.name
            binding.breed.setOnClickListener {
                onBreedClick(item)
            }
        }
    }

    companion object BreedCallback : DiffUtil.ItemCallback<Breed>() {
        override fun areItemsTheSame(oldItem: Breed, newItem: Breed): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Breed, newItem: Breed): Boolean {
            return oldItem == newItem
        }
    }
}