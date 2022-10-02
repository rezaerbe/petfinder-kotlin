package com.erbeandroid.petfinder.feature.animal.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.erbeandroid.petfinder.core.data.model.Animal
import com.erbeandroid.petfinder.feature.animal.databinding.ItemAnimalBinding

class AnimalPagingAdapter(
    private val onAnimalClick: (Animal) -> Unit
) : PagingDataAdapter<Animal, AnimalPagingAdapter.AnimalPagingViewHolder>(AnimalCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalPagingViewHolder {
        val binding = ItemAnimalBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return AnimalPagingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AnimalPagingViewHolder, position: Int) {
        val item = getItem(position)
        item?.let { holder.bind(item) }
    }

    inner class AnimalPagingViewHolder(
        private val binding: ItemAnimalBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Animal) {
            binding.animal.text = item.name
            binding.animal.setOnClickListener {
                onAnimalClick(item)
            }
        }
    }

    companion object AnimalCallback : DiffUtil.ItemCallback<Animal>() {
        override fun areItemsTheSame(oldItem: Animal, newItem: Animal): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Animal, newItem: Animal): Boolean {
            return oldItem == newItem
        }
    }
}