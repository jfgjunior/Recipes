package com.example.josegarcia.todaymeal.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.josegarcia.todaymeal.R
import com.example.josegarcia.todaymeal.adapter.DescriptionAdapter
import com.example.josegarcia.todaymeal.model.Ingredient
import com.example.josegarcia.todaymeal.model.Recipe
import kotlinx.android.synthetic.main.fragment_recipe_description.recipe_description as recipeDescription

class RecipeDescriptionFragment : Fragment() {
    companion object {
        private const val KEY_RECIPE = "keyRecipe"
        fun newInstance(recipe: Recipe): RecipeDescriptionFragment {
            val bundle = Bundle()
            val fragment = RecipeDescriptionFragment()
            bundle.putParcelable(KEY_RECIPE, recipe)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        inflater.inflate(R.layout.fragment_recipe_description, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            populateLayout(it.getParcelable(KEY_RECIPE) as Recipe)
        }
    }

    private fun populateLayout(recipe: Recipe) {

        recipeDescription.adapter = DescriptionAdapter(recipe.ingredients, recipe.steps)
    }

    private fun organizeIngredients(ingredients: MutableList<Ingredient>) {
        ingredients.sortWith(Comparator { ingredient, t1 ->
            Integer.compare(
                ingredient.length,
                t1.length
            )
        })
    }
}
