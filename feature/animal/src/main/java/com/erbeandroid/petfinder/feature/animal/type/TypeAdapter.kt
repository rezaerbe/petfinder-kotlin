package com.erbeandroid.petfinder.feature.animal.type

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.erbeandroid.petfinder.core.common.util.click
import com.erbeandroid.petfinder.core.data.model.Type
import com.erbeandroid.petfinder.feature.animal.databinding.ItemTypeBinding

class TypeAdapter(
    private val onTypeClick: (Type) -> Unit
) : ListAdapter<Type, TypeAdapter.TypeViewHolder>(TypeCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TypeViewHolder {
        val binding = ItemTypeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TypeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TypeViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class TypeViewHolder(
        private val binding: ItemTypeBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Type) {
            binding.type.text = item.name
            binding.type.setOnClickListener(click {
                onTypeClick(item)
            })
        }
    }

    companion object TypeCallback : DiffUtil.ItemCallback<Type>() {
        override fun areItemsTheSame(oldItem: Type, newItem: Type): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Type, newItem: Type): Boolean {
            return oldItem == newItem
        }
    }
}