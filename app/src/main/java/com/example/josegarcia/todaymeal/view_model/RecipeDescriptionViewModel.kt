package com.example.josegarcia.todaymeal.view_model

import androidx.lifecycle.ViewModel
import com.example.josegarcia.todaymeal.model.Ingredient
import com.example.josegarcia.todaymeal.model.Recipe
import com.example.josegarcia.todaymeal.model.Step
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject

class RecipeDescriptionViewModel @AssistedInject constructor(
    @Assisted private val recipe: Recipe
) : ViewModel() {
    companion object {
        const val proportion = 3
    }

    val ingredients: List<Ingredient>
        get() = recipe.ingredients
    val steps: List<Step>
        get() = recipe.steps
    val imageUrl: String
        get() = recipe.image

    @AssistedInject.Factory
    interface Factory {
        fun create(recipe: Recipe): RecipeDescriptionViewModel
    }
}