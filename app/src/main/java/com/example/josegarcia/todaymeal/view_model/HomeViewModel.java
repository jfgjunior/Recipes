package com.example.josegarcia.todaymeal.view_model;

import android.arch.lifecycle.ViewModel;

import com.example.josegarcia.todaymeal.model.Recipe;

public class HomeViewModel extends ViewModel {
    private Recipe recipe;

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Recipe getRecipe() {
        return recipe;
    }
}
