package com.example.josegarcia.todaymeal.adapter

import androidx.recyclerview.widget.DiffUtil

class TittleDiff : DiffUtil.ItemCallback<Int>() {
    override fun areItemsTheSame(oldItem: Int, newItem: Int) =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: Int, newItem: Int) =
        areItemsTheSame(oldItem, newItem)
}