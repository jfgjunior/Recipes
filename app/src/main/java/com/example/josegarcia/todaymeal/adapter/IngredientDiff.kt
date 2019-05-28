package com.example.josegarcia.todaymeal.adapter

import android.support.v7.util.DiffUtil

import com.example.josegarcia.todaymeal.model.Ingredient

class IngredientDiff : DiffUtil.ItemCallback<Ingredient>() {
    override fun areItemsTheSame(ingredient: Ingredient, t1: Ingredient) =
        ingredient.name == t1.name

    override fun areContentsTheSame(ingredient: Ingredient, t1: Ingredient) =
        ingredient == t1
}
