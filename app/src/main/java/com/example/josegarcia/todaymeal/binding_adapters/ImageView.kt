package com.example.josegarcia.todaymeal.binding_adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.josegarcia.todaymeal.R
import com.squareup.picasso.Picasso

@BindingAdapter("android:setImage")
fun ImageView.setImage(url: String) {
    if (url.isEmpty()) {
        setImageResource(R.drawable.cooking_table)
    } else {
        Picasso.get()
            .load(url)
            .error(R.drawable.cooking_table)
            .into(this)
    }
}