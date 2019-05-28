package com.example.josegarcia.todaymeal.views

import android.content.Context
import android.support.v7.widget.CardView
import android.util.AttributeSet
import android.view.LayoutInflater
import com.example.josegarcia.todaymeal.R
import com.example.josegarcia.todaymeal.model.Ingredient
import kotlinx.android.synthetic.main.view_ingredient.view.quantity
import kotlinx.android.synthetic.main.view_ingredient.view.item_name as itemName
import kotlinx.android.synthetic.main.view_ingredient.view.measure_image as measureImage

class IngredientView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : CardView(context, attrs, defStyleAttr) {

    fun createIngredients(ingredient: Ingredient) = setParameters(ingredient)

    init {
        LayoutInflater.from(context)
            .inflate(R.layout.view_ingredient, this, true)
    }

    private fun setParameters(ingredient: Ingredient) {
        quantity.text = ingredient.quantity.toString()
        itemName.text = ingredient.name
        measureImage.setImageResource(getMeasureImage(ingredient.measure))
    }

    private fun getMeasureImage(measure: String) = R.drawable.ic_noun_one_tablespoon_111987
        //TODO: Return more images related to measure
        //when (measure) {
        //    else -> R.drawable.ic_noun_one_tablespoon_111987
        //}
}
