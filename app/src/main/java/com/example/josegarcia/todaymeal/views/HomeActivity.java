package com.example.josegarcia.todaymeal.views;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.josegarcia.todaymeal.R;
import com.example.josegarcia.todaymeal.model.Recipe;
import com.example.josegarcia.todaymeal.view_model.HomeViewModel;

import static com.example.josegarcia.todaymeal.views.RecipeListFragment.RECIPE_KEY;


public class HomeActivity extends AppCompatActivity implements SelectRecipe, RecipeDescriptionFragment.OnComplete {

    private RecipeDescriptionFragment currentFragment;
    private HomeViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        viewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
    }

    @Override
    public void onSelectRecipeListener(Recipe recipe) {
        viewModel.setRecipe(recipe);
        String minScreenWidthSize = getString(R.string.size);
        if (minScreenWidthSize.equals("normal")) {
            startDetailsActivity(recipe);
        } else {
            updateHomeActivity();
        }
    }

    @Override
    public void populateFragment() {
        if (currentFragment != null) {
            currentFragment.populateLayout(viewModel.getRecipe());
        } else {
            updateHomeActivity();
            currentFragment.populateLayout(viewModel.getRecipe());
        }
    }

    private void startDetailsActivity(Recipe recipe) {
        Intent intent = new Intent(this, RecipeDetailsActivity.class);
        intent.putExtra(RECIPE_KEY, recipe);
        startActivity(intent);
    }

    private void updateHomeActivity() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        currentFragment = new RecipeDescriptionFragment();
        fragmentManager.beginTransaction()
                .replace(R.id.description_container, currentFragment)
                .commit();
    }
}
