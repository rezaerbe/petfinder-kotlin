package com.erbeandroid.petfinder.core.common.util

import android.util.Log
import android.view.View

open class ClickListener : View.OnClickListener {
    override fun onClick(view: View?) {
        view?.let { v ->
            val name = v.context.resources.getResourceEntryName(v.id)
            Log.d("TAG", "onClick: $name")
        }
    }
}

fun click(block: () -> Unit): ClickListener {
    return object : ClickListener() {
        override fun onClick(view: View?) {
            super.onClick(view)
            block()
        }
    }
}