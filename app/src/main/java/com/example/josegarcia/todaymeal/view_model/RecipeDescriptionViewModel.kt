package com.example.josegarcia.todaymeal.view_model

import android.graphics.Point
import androidx.lifecycle.ViewModel
import com.example.josegarcia.todaymeal.model.Ingredient
import com.example.josegarcia.todaymeal.model.Recipe
import com.example.josegarcia.todaymeal.model.Step

class RecipeDescriptionViewModel: ViewModel() {
    lateinit var recipe: Recipe
    val activitySize = Point()
    val ingredients: List<Ingredient>
        get() = recipe.ingredients
    val steps: List<Step>
        get() = recipe.steps
    val height: Int by lazy {
        activitySize.y / 3
    }
    val imageUrl: String
        get() = recipe.image
}