package com.example.josegarcia.todaymeal.custom_views

import com.example.josegarcia.todaymeal.R
import com.example.josegarcia.todaymeal.model.Ingredient

class IngredientPresenter(private val view: IngredientContract.View) :
    IngredientContract.Presenter {
    private lateinit var ingredient: Ingredient
    override val name: String by lazy { ingredient.name }
    override val quantity: String by lazy { ingredient.quantity.toString() }

    override fun setIngredient(ingredient: Ingredient) {
        this.ingredient = ingredient
        view.setParameters()
    }

    override fun getMeasureImage() = R.drawable.ic_tablespoon_small
    //TODO: Return more images related to measure
    //when (measure) {
    //    else -> R.drawable.ic_noun_one_tablespoon_111987
    //}
}