package com.example.josegarcia.todaymeal.adapter

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.josegarcia.todaymeal.R
import com.example.josegarcia.todaymeal.model.Ingredient
import com.example.josegarcia.todaymeal.views.IngredientView

class IngredientsListAdapter :
    ListAdapter<Ingredient, IngredientsListAdapter.IngredientViewHolder>(IngredientDiff()) {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): IngredientViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.ingredient_view_holder, viewGroup, false)
        return IngredientViewHolder(view)
    }

    override fun onBindViewHolder(ingredientViewHolder: IngredientViewHolder, position: Int) =
        ingredientViewHolder.bind(getItem(position))

    class IngredientViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ingredientView: IngredientView = itemView.findViewById(R.id.ingredient_view)

        fun bind(ingredient: Ingredient) {
            ingredientView.createIngredients(ingredient)
        }
    }
}
