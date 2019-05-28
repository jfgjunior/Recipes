package com.example.josegarcia.todaymeal.adapter

import android.support.v7.util.DiffUtil

import com.example.josegarcia.todaymeal.model.Recipe

class RecipeDifferCallback : DiffUtil.ItemCallback<Recipe>() {
    override fun areItemsTheSame(oldRecipe: Recipe, newRecipe: Recipe) = oldRecipe == newRecipe

    override fun areContentsTheSame(oldRecipe: Recipe, newRecipe: Recipe) =
        oldRecipe.id == newRecipe.id
}
