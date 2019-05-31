package com.example.josegarcia.todaymeal.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.josegarcia.todaymeal.R
import com.example.josegarcia.todaymeal.model.Ingredient
import kotlinx.android.synthetic.main.item_ingredient.view.ingredient_view as ingredientView

class IngredientsDelegateAdapter : ViewTypeDelegateAdapter {
    override fun getLayoutId() = R.layout.item_ingredient

    override fun onCreateViewHolder(viewGroup: ViewGroup): IngredientViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_ingredient, viewGroup, false)
        return IngredientViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, model: Any) =
        (viewHolder as IngredientViewHolder).bind(model as Ingredient)

    class IngredientViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(ingredient: Ingredient) {
            itemView.ingredientView.createIngredients(ingredient)
        }
    }
}
