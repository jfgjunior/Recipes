package com.example.josegarcia.todaymeal.adapter;

import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;

import com.example.josegarcia.todaymeal.model.Ingredient;

public class IngredientDiff extends DiffUtil.ItemCallback<Ingredient> {
    @Override
    public boolean areItemsTheSame(@NonNull Ingredient ingredient, @NonNull Ingredient t1) {
        return ingredient.getName().equals(t1.getName());
    }

    @Override
    public boolean areContentsTheSame(@NonNull Ingredient ingredient, @NonNull Ingredient t1) {
        return ingredient.equals(t1);
    }
}
