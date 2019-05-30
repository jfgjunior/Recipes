package com.example.josegarcia.todaymeal.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

interface ViewTypeDelegateAdapter {
    fun onCreateViewHolder(viewGroup: ViewGroup): RecyclerView.ViewHolder
    fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, model: Any)
    fun getLayoutId(): Int
}