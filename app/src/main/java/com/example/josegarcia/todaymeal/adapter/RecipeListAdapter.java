package com.example.josegarcia.todaymeal.adapter;

import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.josegarcia.todaymeal.R;
import com.example.josegarcia.todaymeal.helper.ImageCache;
import com.example.josegarcia.todaymeal.model.Recipe;
import com.example.josegarcia.todaymeal.views.SelectRecipe;
import com.squareup.picasso.Picasso;

public class RecipeListAdapter extends ListAdapter<Recipe, RecipeListAdapter.RecipeViewHolder> {
    private SelectRecipe onSelectRecipeListener;

    public RecipeListAdapter(SelectRecipe onSelectRecipeListener) {
        super(new RecipeDifferCallback());
        this.onSelectRecipeListener = onSelectRecipeListener;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int type) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recipe_card, viewGroup, false);
        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder recipeViewHolder, int position) {
        Recipe recipe = getItem(position);
        recipeViewHolder.bind(recipe);
    }

    class RecipeViewHolder extends RecyclerView.ViewHolder {
        private final ImageView foodPicture = itemView.findViewById(R.id.food_image);
        private final TextView foodName = itemView.findViewById(R.id.recipe_name);

        RecipeViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        void bind(final Recipe recipe) {
            if (recipe.getImage().isEmpty()) {
                foodPicture.setImageResource(R.drawable.cooking_table);
            } else {
                Picasso.get()
                        .load(recipe.getImage())
                        .placeholder(R.drawable.loading_image_animation)
                        .into(foodPicture);
            }
            foodName.setText(recipe.getName());
            itemView.setOnClickListener(view -> {
                if (!recipe.getImage().isEmpty()) {
                    ImageCache.getInstance().setBitmap(((BitmapDrawable) foodPicture.getDrawable())
                            .getBitmap());
                }
                onSelectRecipeListener.onSelectRecipeListener(recipe);
            });
        }
    }
}
