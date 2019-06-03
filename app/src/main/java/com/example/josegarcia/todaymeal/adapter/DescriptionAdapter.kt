package com.example.josegarcia.todaymeal.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.josegarcia.todaymeal.R
import com.example.josegarcia.todaymeal.model.Ingredient
import com.example.josegarcia.todaymeal.model.Step

class DescriptionAdapter :
    ListAdapter<Any, RecyclerView.ViewHolder>(DescriptionDiffDelegator()) {
    private val adapterDelegateManager =
        DescriptionAdapterDelegateManager()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        adapterDelegateManager.onCreateViewHolder(parent, viewType)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        adapterDelegateManager.onBindViewHolder(holder, position, getItem(position))

    override fun getItemViewType(position: Int) = adapterDelegateManager.getViewType(getItem(position))

    fun submit(ingredients: List<Ingredient>, steps: List<Step>) {
        val res = mutableListOf<Any>()
        res.add(R.string.ingredients)
        res.addAll(ingredients)
        res.add(R.string.steps)
        res.addAll(steps)
        submitList(res)
        notifyDataSetChanged()
    }
}