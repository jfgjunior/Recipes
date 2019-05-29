package com.example.josegarcia.todaymeal.adapter

import androidx.recyclerview.widget.DiffUtil

import com.example.josegarcia.todaymeal.model.Recipe

class RecipeDifferCallback : DiffUtil.ItemCallback<Recipe>() {
    override fun areItemsTheSame(oldRecipe: Recipe, newRecipe: Recipe) = oldRecipe == newRecipe

    override fun areContentsTheSame(oldRecipe: Recipe, newRecipe: Recipe) =
        oldRecipe.id == newRecipe.id
}
