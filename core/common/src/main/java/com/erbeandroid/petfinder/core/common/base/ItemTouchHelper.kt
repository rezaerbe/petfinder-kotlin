package com.erbeandroid.petfinder.core.common.base

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

fun <T : Any, VB : ViewBinding> itemTouchHelper(adapter: BaseAdapter<T, VB>): ItemTouchHelper {

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