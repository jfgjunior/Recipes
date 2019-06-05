package com.example.josegarcia.todaymeal.views

import android.graphics.Point
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.josegarcia.todaymeal.R
import com.example.josegarcia.todaymeal.adapter.DescriptionAdapter
import com.example.josegarcia.todaymeal.helper.ImageCache
import com.example.josegarcia.todaymeal.model.Recipe
import kotlinx.android.synthetic.main.fragment_recipe_description.*
import kotlinx.android.synthetic.main.fragment_recipe_description.appbar as appBar
import kotlinx.android.synthetic.main.fragment_recipe_description.recipe_description as recipeDescription
import kotlinx.android.synthetic.main.fragment_recipe_description.recipe_image as recipeImage

class RecipeDescriptionFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        inflater.inflate(R.layout.fragment_recipe_description, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        collapsing_toolbar_layout.setupWithNavController(toolbar, findNavController())
        arguments?.let {
            val recipe = RecipeDescriptionFragmentArgs.fromBundle(it).recipe
            populateLayout(recipe)
            setUpActionBar(recipe.name)
        }
    }

    private fun populateLayout(recipe: Recipe) {
        val adapter = DescriptionAdapter()
        adapter.submit(recipe.ingredients, recipe.steps)
        recipeDescription.adapter = adapter
    }

    private fun setUpActionBar(title: String) {
        setActionBarImage()
        setToolbarSize()
        work_arround_while_no_solution_is_provided()
    }

    private fun setActionBarImage() =
        ImageCache.bitmap?.let { recipeImage.setImageBitmap(it) }
            ?: recipeImage.setImageResource(R.drawable.cooking_table)

    private fun setToolbarSize() {
        val dimens = Point()
        requireActivity().windowManager.defaultDisplay.getSize(dimens)
        val lp = appBar.layoutParams as ViewGroup.LayoutParams
        lp.height = dimens.y / 3
    }

    /* TODO
    * Discussion link: https://issuetracker.google.com/issues/121078028
    * remove it when solution comes up
    */
    private fun work_arround_while_no_solution_is_provided() {
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
    }
}
