package com.example.josegarcia.todaymeal.adapter;

import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.josegarcia.todaymeal.R;
import com.example.josegarcia.todaymeal.model.Ingredient;
import com.example.josegarcia.todaymeal.views.IngredientView;

public class IngredientsListAdapter extends ListAdapter<Ingredient, IngredientsListAdapter.IngredientViewHolder> {

    public IngredientsListAdapter() {
        super(new IngredientDiff());
    }

    @NonNull
    @Override
    public IngredientViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.ingredient_view_holder, viewGroup, false);
        return new IngredientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientViewHolder ingredientViewHolder, int i) {
        Ingredient ingredient = getItem(i);
        ingredientViewHolder.bind(ingredient);
    }

    class IngredientViewHolder extends RecyclerView.ViewHolder {
        private final IngredientView ingredientView;

        IngredientViewHolder(@NonNull View itemView) {
            super(itemView);
            ingredientView = itemView.findViewById(R.id.ingredient_view);
        }

        public void bind(Ingredient ingredient) {
            ingredientView.setIngredient(ingredient);
        }
    }
}
