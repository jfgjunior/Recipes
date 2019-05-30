package com.example.josegarcia.todaymeal.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.josegarcia.todaymeal.model.Ingredient
import com.example.josegarcia.todaymeal.model.Step

class DescriptionAdapter(ingredients: List<Ingredient>, steps: List<Step>) :
    ListAdapter<Any, RecyclerView.ViewHolder>(DescriptionDiffDelegator()) {
    private val adapterDelegateManager =
        DescriptionAdapterDelegateManager(createList(ingredients, steps))

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        adapterDelegateManager.onCreateViewHolder(parent, viewType)

    override fun getItemCount() = adapterDelegateManager.getItemCount()

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        adapterDelegateManager.onBindViewHolder(holder, position)

    override fun getItemViewType(position: Int) = adapterDelegateManager.getViewType(position)

    private fun createList(ingredients: List<Ingredient>, steps: List<Step>): List<Any> {
        val res = mutableListOf<Any>()
        res.add("Instructions")
        res.addAll(ingredients)
        res.add("Steps")
        res.addAll(steps)
        return res
    }
}