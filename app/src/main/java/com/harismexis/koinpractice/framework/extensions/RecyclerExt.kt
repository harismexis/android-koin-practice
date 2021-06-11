package com.harismexis.koinpractice.framework.extensions

import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.harismexis.koinpractice.framework.divider.RecyclerDivider

fun RecyclerView.setDivider(
    @DrawableRes divider: Int
){
    ContextCompat.getDrawable(this.context, divider)?.let {
        this.addItemDecoration(RecyclerDivider(it))
    }
}