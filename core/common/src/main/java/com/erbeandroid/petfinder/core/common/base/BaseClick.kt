package com.erbeandroid.petfinder.core.common.base

import android.util.Log
import android.view.View

open class BaseClick : View.OnClickListener {
    override fun onClick(view: View?) {
        view?.let { v ->
            val name = v.context.resources.getResourceEntryName(v.id)
            Log.d("TAG", "onClick: $name")
        }
    }
}

fun click(action: () -> Unit): BaseClick {
    return object : BaseClick() {
        override fun onClick(view: View?) {
            super.onClick(view)
            action()
        }
    }
}