package com.example.josegarcia.todaymeal.custom_views

import android.content.Context
import androidx.cardview.widget.CardView
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
) : CardView(context, attrs, defStyleAttr), IngredientContract.View {
    private val presenter: IngredientContract.Presenter = IngredientPresenter(this)
    var ingredient: Ingredient? = null
        set(value) {
            value?.let {
                presenter.setIngredient(value)
            }
        }

    init {
        LayoutInflater.from(context)
            .inflate(R.layout.view_ingredient, this, true)
    }

    override fun setParameters() {
        quantity.text = presenter.quantity
        itemName.text = presenter.name
        measureImage.setImageResource(presenter.getMeasureImage())
    }
}
