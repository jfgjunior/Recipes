package com.example.josegarcia.todaymeal.views;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.josegarcia.todaymeal.R;
import com.example.josegarcia.todaymeal.adapter.IngredientsListAdapter;
import com.example.josegarcia.todaymeal.adapter.StepsListAdapter;
import com.example.josegarcia.todaymeal.model.Ingredient;
import com.example.josegarcia.todaymeal.model.Recipe;
import com.example.josegarcia.todaymeal.model.Step;
import com.example.josegarcia.todaymeal.widget.IngredientIntentService;
import com.google.android.flexbox.FlexboxLayoutManager;

import java.util.Collections;
import java.util.List;

public class RecipeDescriptionFragment extends Fragment {
    public interface OnComplete {
        void populateFragment();
    }

    private RecyclerView ingredientsContainer;
    private RecyclerView stepsContainer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recipe_description, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ingredientsContainer = view.findViewById(R.id.ingredients_container);
        stepsContainer = view.findViewById(R.id.steps_container);
        ((OnComplete) getContext()).populateFragment();
        super.onViewCreated(view, savedInstanceState);
    }

    public void populateLayout(Recipe recipe) {
        if (getContext() != null && recipe != null) {
            updateWidget(recipe.getIngredients());
            setUpIngredientsContainer(recipe.getIngredients());
            setUpStepsContainer(recipe.getSteps());
        }
    }

    private void updateWidget(List<Ingredient> ingredients) {
        IngredientIntentService.startUpdateWidgetService(getContext(), ingredients);
    }

    private void setUpIngredientsContainer(final List<Ingredient> ingredients) {
        Collections.sort(ingredients, (ingredient, t1) ->
                Integer.compare(ingredient.getLenght(), t1.getLenght()));
        IngredientsListAdapter adapter = new IngredientsListAdapter();
        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(getContext());
        adapter.submitList(ingredients);
        ingredientsContainer.setLayoutManager(layoutManager);
        ingredientsContainer.setAdapter(adapter);
    }

    private void setUpStepsContainer(final List<Step> steps) {
        StepsListAdapter adapter = new StepsListAdapter();
        stepsContainer.setAdapter(adapter);
        adapter.submitList(steps);
    }
}
