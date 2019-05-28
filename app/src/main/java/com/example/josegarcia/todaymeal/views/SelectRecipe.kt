package com.example.josegarcia.todaymeal.views

import com.example.josegarcia.todaymeal.model.Recipe

interface SelectRecipe {
    fun onSelectRecipeListener(recipe: Recipe)
}
