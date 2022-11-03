package com.erbeandroid.petfinder.feature.animal.detail

import androidx.viewbinding.ViewBinding
import com.erbeandroid.petfinder.core.common.base.BaseAdapterOk
import com.erbeandroid.petfinder.core.common.base.click
import com.erbeandroid.petfinder.feature.animal.databinding.ItemNumberBinding
import com.erbeandroid.petfinder.feature.animal.databinding.ItemTextBinding

class DetailAdapter(
    onItemClick1: (DataModel.Model1) -> Unit,
    onItemClick2: (DataModel.Model2) -> Unit
) : BaseAdapterOk<DataModel, ViewBinding>(
    itemViewType = { item ->
        when (item) {
            is DataModel.Model1 -> 0
            is DataModel.Model2 -> 1
        }
    },
    inflaterFactory = { inflater, parent, attachToParent, viewType ->
        when (viewType) {
            0 -> ItemNumberBinding.inflate(inflater, parent, attachToParent)
            1 -> ItemTextBinding.inflate(inflater, parent, attachToParent)
            else -> throw IllegalArgumentException("Invalid type")
        }
    },
    onItemBind = { item, binding, _ ->
        when (item) {
            is DataModel.Model1 -> {
                val bind = binding as ItemNumberBinding
                bind.number.text = item.number.toString()
                bind.number.setOnClickListener(click {
                    onItemClick1(item)
                })
            }
            is DataModel.Model2 -> {
                val bind = binding as ItemTextBinding
                bind.text.text = item.text
                bind.text.setOnClickListener(click {
                    onItemClick2(item)
                })
            }
        }
    }
)

sealed class DataModel {
    data class Model1(
        val id: Int,
        val number: Int
    ) : DataModel()

    data class Model2(
        val id: Int,
        val text: String
    ) : DataModel()
}