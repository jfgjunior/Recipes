package com.example.josegarcia.todaymeal.views

import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.josegarcia.todaymeal.R
import com.google.android.material.appbar.AppBarLayout
import com.squareup.picasso.Picasso


@BindingAdapter("android:setImage")
fun ImageView.setImage(url: String) {
    if (url.isEmpty()) {
        setImageResource(R.drawable.cooking_table)
    } else {
        Picasso.get()
            .load(url)
            .into(this)
    }
}

@BindingAdapter("android:setCustomHeight")
fun AppBarLayout.setCustomHeight(height: Int) {
    val lp = this.layoutParams as ViewGroup.LayoutParams
    lp.height = height
}