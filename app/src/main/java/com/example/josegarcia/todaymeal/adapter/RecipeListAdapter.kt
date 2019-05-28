package com.example.josegarcia.todaymeal.adapter

import android.graphics.drawable.BitmapDrawable
import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.example.josegarcia.todaymeal.R
import com.example.josegarcia.todaymeal.helper.ImageCache
import com.example.josegarcia.todaymeal.model.Recipe
import com.example.josegarcia.todaymeal.views.SelectRecipe
import com.squareup.picasso.Picasso

class RecipeListAdapter(private val onSelectRecipeListener: SelectRecipe) :
    ListAdapter<Recipe, RecipeListAdapter.RecipeViewHolder>(RecipeDifferCallback()) {

    override fun onCreateViewHolder(viewGroup: ViewGroup, type: Int): RecipeViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.recipe_card, viewGroup, false)
        return RecipeViewHolder(view, onSelectRecipeListener)
    }

    override fun onBindViewHolder(recipeViewHolder: RecipeViewHolder, position: Int) =
        recipeViewHolder.bind(getItem(position))

    class RecipeViewHolder(itemView: View, private val onSelectRecipeListener: SelectRecipe) :
        RecyclerView.ViewHolder(itemView) {
        private val foodPicture = itemView.findViewById<ImageView>(R.id.food_image)
        private val foodName = itemView.findViewById<TextView>(R.id.recipe_name)

        fun bind(recipe: Recipe) {
            loadImage(recipe)
            setClickListener(recipe)
            foodName.text = recipe.name
        }

        private fun loadImage(recipe: Recipe) {
            if (recipe.image.isEmpty()) {
                foodPicture.setImageResource(R.drawable.cooking_table)
            } else {
                Picasso.get()
                    .load(recipe.image)
                    .placeholder(R.drawable.loading_image_animation)
                    .into(foodPicture)
            }
        }

        private fun setClickListener(recipe: Recipe) {
            itemView.setOnClickListener {
                if (recipe.image.isNotEmpty()) {
                    ImageCache.bitmap = (foodPicture.drawable as BitmapDrawable)
                        .bitmap
                }
                onSelectRecipeListener.onSelectRecipeListener(recipe)
            }
        }
    }
}
