package com.example.josegarcia.todaymeal.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.josegarcia.todaymeal.R
import com.example.josegarcia.todaymeal.model.Ingredient
import com.example.josegarcia.todaymeal.model.Step

class DescriptionAdapterDelegateManager {
    private val adapterDelegates =
        listOf(IngredientsDelegateAdapter(), StepsDelegateAdapter(), TitleDelegateAdapter())

    fun onCreateViewHolder(viewGroup: ViewGroup, layoutId: Int): RecyclerView.ViewHolder =
        getDelegate(layoutId).onCreateViewHolder(viewGroup)


    fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, value: Any) {
        val layoutId = getViewType(value)
        getDelegate(layoutId).onBindViewHolder(viewHolder, value)
    }

    fun getViewType(value: Any) =
        when (value) {
            is Step -> R.layout.item_step
            is Ingredient -> R.layout.item_ingredient
            is Int -> R.layout.item_title
            else -> throw ClassNotFoundException()
        }

    private fun getDelegate(layoutId: Int) = adapterDelegates.first {
        it.getLayoutId() == layoutId
    }
}