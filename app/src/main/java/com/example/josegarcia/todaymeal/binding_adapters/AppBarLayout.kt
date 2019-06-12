package com.example.josegarcia.todaymeal.binding_adapters

import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import com.google.android.material.appbar.AppBarLayout

@BindingAdapter("android:setCustomHeight")
fun AppBarLayout.setCustomHeight(proportion: Int) {
    val height = rootView.measuredHeight / proportion
    val lp = this.layoutParams as ViewGroup.LayoutParams
    lp.height = height
}