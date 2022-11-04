package com.erbeandroid.petfinder.core.common.base

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseAdapterSelection<T : Any, VB : ViewBinding>(
    private val inflaterFactory: (LayoutInflater, ViewGroup?, Boolean) -> VB,
    private val onItemBind: (T, VB, View, Boolean) -> Unit
) : ListAdapter<T, BaseAdapterSelection.BaseViewHolderSelection<T, VB>>(BaseItemCallbackSelection<T>()) {

    init {
        this.setHasStableIds(true)
    }

    var selectionTracker: SelectionTracker<Long>? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolderSelection<T, VB> {
        val binding = inflaterFactory(LayoutInflater.from(parent.context), parent, false)
        val view = binding.root
        return BaseViewHolderSelection(view, binding, onItemBind)
    }

    override fun onBindViewHolder(holder: BaseViewHolderSelection<T, VB>, position: Int) {
        val item = getItem(position)
        holder.bind(item, selectionTracker?.isSelected(position.toLong()) ?: false)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    class BaseViewHolderSelection<T : Any, VB : ViewBinding>(
        view: View,
        private val binding: VB,
        private val onItemBind: (T, VB, View, Boolean) -> Unit
    ) : RecyclerView.ViewHolder(view) {
        fun bind(item: T, selection: Boolean) {
            onItemBind(item, binding, itemView, selection)
        }

        fun getItemDetails() = object : ItemDetailsLookup.ItemDetails<Long>() {
            override fun getPosition(): Int = bindingAdapterPosition
            override fun getSelectionKey(): Long = itemId
        }
    }

    class BaseItemCallbackSelection<T : Any> : DiffUtil.ItemCallback<T>() {
        override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
            return oldItem.toString() == newItem.toString()
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
            return oldItem == newItem
        }
    }
}