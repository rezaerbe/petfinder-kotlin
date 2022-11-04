package com.erbeandroid.petfinder.core.common.base

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseAdapterMultiple<T : Any, VB : ViewBinding>(
    private val itemViewType: (T) -> Int,
    private val inflaterFactory: (LayoutInflater, ViewGroup?, Boolean, Int) -> VB,
    private val onItemBind: (T, VB, View) -> Unit
) : ListAdapter<T, BaseAdapterMultiple.BaseViewHolderMultiple<T, VB>>(BaseItemCallbackMultiple<T>()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolderMultiple<T, VB> {
        val binding = inflaterFactory(LayoutInflater.from(parent.context), parent, false, viewType)
        val view = binding.root
        return BaseViewHolderMultiple(view, binding, onItemBind)
    }

    override fun onBindViewHolder(holder: BaseViewHolderMultiple<T, VB>, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    override fun getItemViewType(position: Int): Int {
        return itemViewType(getItem(position))
    }

    class BaseViewHolderMultiple<T : Any, VB : ViewBinding>(
        view: View,
        private val binding: VB,
        private val onItemBind: (T, VB, View) -> Unit
    ) : RecyclerView.ViewHolder(view) {
        fun bind(item: T) {
            onItemBind(item, binding, itemView)
        }
    }

    class BaseItemCallbackMultiple<T : Any> : DiffUtil.ItemCallback<T>() {
        override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
            return oldItem.toString() == newItem.toString()
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
            return oldItem == newItem
        }
    }
}