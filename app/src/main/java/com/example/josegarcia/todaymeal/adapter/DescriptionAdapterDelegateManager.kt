package com.example.josegarcia.todaymeal.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.josegarcia.todaymeal.R
import com.example.josegarcia.todaymeal.model.Ingredient
import com.example.josegarcia.todaymeal.model.Step

class DescriptionAdapterDelegateManager(private val items: List<Any>) {
    private val adapterDelegates = listOf(IngredientsDelegateAdapter(), StepsDelegateAdapter(), TitleDelegateAdapter())

    fun onCreateViewHolder(viewGroup: ViewGroup, layoutId: Int): RecyclerView.ViewHolder =
            getDelegator(layoutId).onCreateViewHolder(viewGroup)


    fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val layoutId= getViewType(position)
        getDelegator(layoutId).onBindViewHolder(viewHolder, items[position])
    }

    fun getDelegator(layoutId: Int) = adapterDelegates.first {
        it.getLayoutId() == layoutId
    }

    fun getViewType(position: Int) =
            when(items[position]) {
                is String -> R.layout.item_title
                is Step -> R.layout.item_step
                is Ingredient -> R.layout.item_ingredient
                else -> throw ClassNotFoundException()
            }

    fun getItemCount() = items.size
}