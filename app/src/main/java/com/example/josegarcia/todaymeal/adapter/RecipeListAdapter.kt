package com.example.josegarcia.todaymeal.adapter

import android.graphics.drawable.BitmapDrawable
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.josegarcia.todaymeal.R
import com.example.josegarcia.todaymeal.helper.ImageCache
import com.example.josegarcia.todaymeal.model.Recipe
import com.example.josegarcia.todaymeal.views.SelectRecipe
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.recipe_card.view.recipe_image as recipeImage
import kotlinx.android.synthetic.main.recipe_card.view.recipe_name as recipeName

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

        fun bind(recipe: Recipe) {
            loadImage(recipe)
            setClickListener(recipe)
            itemView.recipeName.text = recipe.name
        }

        private fun loadImage(recipe: Recipe) {
            if (recipe.image.isEmpty()) {
                itemView.recipeImage.setImageResource(R.drawable.cooking_table)
            } else {
                Picasso.get()
                    .load(recipe.image)
                    .placeholder(R.drawable.loading_image_animation)
                    .into(itemView.recipeImage)
            }
        }

        private fun setClickListener(recipe: Recipe) {
            itemView.setOnClickListener {
                if (recipe.image.isNotEmpty()) {
                    ImageCache.bitmap = (itemView.recipeImage.drawable as BitmapDrawable)
                        .bitmap
                }
                onSelectRecipeListener.onSelectRecipeListener(recipe)
            }
        }
    }
}
