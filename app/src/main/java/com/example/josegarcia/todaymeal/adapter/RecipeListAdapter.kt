package com.example.josegarcia.todaymeal.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.josegarcia.todaymeal.R
import com.example.josegarcia.todaymeal.model.Recipe
import com.example.josegarcia.todaymeal.views.RecipeListFragmentDirections
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_recipe.view.recipe_score as recipeScore
import kotlinx.android.synthetic.main.recipe_servings.view.recipe_servings as recipeServings
import java.util.*
import kotlinx.android.synthetic.main.item_recipe.view.recipe_image as recipeImage
import kotlinx.android.synthetic.main.item_recipe.view.recipe_name as recipeName

class RecipeListAdapter :
    ListAdapter<Recipe, RecipeListAdapter.RecipeViewHolder>(RecipeDifferCallback()) {

    override fun onCreateViewHolder(viewGroup: ViewGroup, type: Int): RecipeViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_recipe, viewGroup, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(recipeViewHolder: RecipeViewHolder, position: Int) =
        recipeViewHolder.bind(getItem(position))

    class RecipeViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        fun bind(recipe: Recipe) {
            loadImage(recipe)
            setClickListener(recipe)
            itemView.recipeName.text = recipe.name
            itemView.recipeScore.score = recipe.score
            itemView.recipeServings.text = String.format(Locale.US, "%d", recipe.servings)
        }

        private fun loadImage(recipe: Recipe) {
            if (recipe.image.isEmpty()) {
                itemView.recipeImage.setImageResource(R.drawable.cooking_table)
            } else {
                Picasso.get()
                    .load(recipe.image)
                    .into(itemView.recipeImage)
            }
        }

        private fun setClickListener(recipe: Recipe) {
            itemView.setOnClickListener {
                val dest =
                    RecipeListFragmentDirections.actionRecipeListFragmentToRecipeDescriptionFragment(
                        recipe,
                        recipe.name
                    )
                findNavController(itemView).navigate(dest)
            }
        }
    }
}
