package com.example.josegarcia.todaymeal.adapter

import androidx.recyclerview.widget.DiffUtil

class TittleDiff : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String) =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: String, newItem: String) =
        areItemsTheSame(oldItem, newItem)
}