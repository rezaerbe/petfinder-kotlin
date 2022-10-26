package com.erbeandroid.petfinder.core.common.base

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseAdapter<T : Any, VB : ViewBinding>(
    private val inflaterFactory: (LayoutInflater, ViewGroup?, Boolean) -> VB,
    private val onItemBind: (T, VB, View) -> Unit
) : ListAdapter<T, BaseAdapter.BaseViewHolder<T, VB>>(BaseItemCallback<T>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T, VB> {
        val binding = inflaterFactory(LayoutInflater.from(parent.context), parent, false)
        val view = binding.root
        return BaseViewHolder(view, binding, onItemBind)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<T, VB>, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class BaseViewHolder<T : Any, VB : ViewBinding>(
        view: View,
        private val binding: VB,
        private val onItemBind: (T, VB, View) -> Unit
    ) : RecyclerView.ViewHolder(view) {
        fun bind(item: T) {
            onItemBind(item, binding, itemView)
        }
    }

    class BaseItemCallback<T : Any> : DiffUtil.ItemCallback<T>() {
        override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
            return oldItem.toString() == newItem.toString()
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
            return oldItem == newItem
        }
    }

    fun itemTouchHelper(): ItemTouchHelper {
        val adapter = this

        val itemTouchCallback = object : ItemTouchHelper.Callback() {
            override fun getMovementFlags(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ): Int {
                val drag = ItemTouchHelper.UP or ItemTouchHelper.DOWN
                val swipe = ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT
                return makeMovementFlags(drag, swipe)
            }

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                val from = viewHolder.bindingAdapterPosition
                val to = target.bindingAdapterPosition

                val list = adapter.currentList.toMutableList()
                list[from] = list[to].also { list[to] = list[from] }
                adapter.submitList(list)

                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.bindingAdapterPosition

                val list = adapter.currentList.toMutableList()
                list.removeAt(position)
                adapter.submitList(list)
            }
        }

        return ItemTouchHelper(itemTouchCallback)
    }
}