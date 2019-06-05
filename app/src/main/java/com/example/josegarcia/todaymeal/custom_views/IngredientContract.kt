package com.example.josegarcia.todaymeal.custom_views

import com.example.josegarcia.todaymeal.model.Ingredient

interface IngredientContract {
    interface Presenter {
        val quantity: String
        val name: String
        fun getMeasureImage(): Int
        fun setIngredient(ingredient: Ingredient)
    }

    interface View {
        fun setParameters()
    }
}