package com.example.josegarcia.todaymeal.views

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.josegarcia.todaymeal.R
import com.example.josegarcia.todaymeal.adapter.IngredientsListAdapter
import com.example.josegarcia.todaymeal.adapter.StepsListAdapter
import com.example.josegarcia.todaymeal.model.Ingredient
import com.example.josegarcia.todaymeal.model.Recipe
import com.example.josegarcia.todaymeal.model.Step
import com.google.android.flexbox.FlexboxLayoutManager
import kotlinx.android.synthetic.main.fragment_recipe_description.ingredients_container as ingredientsContainer
import kotlinx.android.synthetic.main.fragment_recipe_description.steps_container as stepsContainer

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
        setUpIngredientsContainer(recipe.ingredients as MutableList<Ingredient>)
        setUpStepsContainer(recipe.steps)
    }

    private fun setUpIngredientsContainer(ingredients: MutableList<Ingredient>) {
        ingredients.sortWith(Comparator { ingredient, t1 ->
            Integer.compare(
                ingredient.length,
                t1.length
            )
        })
        val adapter = IngredientsListAdapter()
        val layoutManager = FlexboxLayoutManager(context)
        adapter.submitList(ingredients)
        ingredientsContainer.layoutManager = layoutManager
        ingredientsContainer.adapter = adapter
    }

    private fun setUpStepsContainer(steps: List<Step>) {
        val adapter = StepsListAdapter()
        stepsContainer!!.adapter = adapter
        adapter.submitList(steps)
    }
}
