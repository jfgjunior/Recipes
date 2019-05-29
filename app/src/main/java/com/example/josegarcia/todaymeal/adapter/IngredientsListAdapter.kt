package com.example.josegarcia.todaymeal.adapter

import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.josegarcia.todaymeal.R
import com.example.josegarcia.todaymeal.model.Ingredient
import kotlinx.android.synthetic.main.ingredient_view_holder.view.ingredient_view as ingredientView

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
        fun bind(ingredient: Ingredient) {
            itemView.ingredientView.createIngredients(ingredient)
        }
    }
}
