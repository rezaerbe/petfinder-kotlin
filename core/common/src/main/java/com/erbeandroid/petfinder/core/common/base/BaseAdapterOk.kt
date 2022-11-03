package com.erbeandroid.petfinder.core.common.base

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseAdapterOk<T : Any, VB : ViewBinding>(
    private val itemViewType: (T) -> Int,
    private val inflaterFactory: (LayoutInflater, ViewGroup?, Boolean, Int) -> VB,
    private val onItemBind: (T, VB, View) -> Unit
) : ListAdapter<T, BaseAdapterOk.BaseViewHolderOk<T, VB>>(BaseItemCallbackOk<T>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolderOk<T, VB> {
        val binding = inflaterFactory(LayoutInflater.from(parent.context), parent, false, viewType)
        val view = binding.root
        return BaseViewHolderOk(view, binding, onItemBind)
    }

    override fun onBindViewHolder(holder: BaseViewHolderOk<T, VB>, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    override fun getItemViewType(position: Int): Int {
        return itemViewType(getItem(position))
    }

    class BaseViewHolderOk<T : Any, VB : ViewBinding>(
        view: View,
        private val binding: VB,
        private val onItemBind: (T, VB, View) -> Unit
    ) : RecyclerView.ViewHolder(view) {
        fun bind(item: T) {
            onItemBind(item, binding, itemView)
        }
    }

    class BaseItemCallbackOk<T : Any> : DiffUtil.ItemCallback<T>() {
        override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
            return oldItem.toString() == newItem.toString()
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
            return oldItem == newItem
        }
    }
}