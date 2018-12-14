package com.example.josegarcia.todaymeal.views;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.example.josegarcia.todaymeal.helper.ImageCache;
import com.example.josegarcia.todaymeal.R;
import com.example.josegarcia.todaymeal.model.Recipe;

import static com.example.josegarcia.todaymeal.views.RecipeListFragment.RECIPE_KEY;

public class RecipeDetailsActivity extends AppCompatActivity implements RecipeDescriptionFragment.OnComplete {
    private Recipe recipe;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);
        getRecipe();
        setToolbarSize();
        setUpActionBar();
    }

    private void getRecipe() {
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra(RECIPE_KEY)) {
                recipe = intent.getParcelableExtra(RECIPE_KEY);
                RecipeDescriptionFragment fragment = (RecipeDescriptionFragment) getSupportFragmentManager().findFragmentById(R.id.container);
                fragment.populateLayout(recipe);
                setActionBarImage();
            }
        }
    }

    private void setActionBarImage() {
        ImageView picture = findViewById(R.id.recipe_image);
        Bitmap bitmap = ImageCache.getInstance().getBitmap();
        if (bitmap != null) {
            picture.setImageBitmap(ImageCache.getInstance().getBitmap());
        } else {
            picture.setImageResource(R.drawable.cooking_table);
        }

    }

    private void setUpActionBar() {
        Toolbar toolbar = findViewById(R.id.collapsing_toolbar);
        String title = recipe.getName();
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(title);
        }
    }

    private void setToolbarSize() {
        AppBarLayout appBar = findViewById(R.id.appbar);
        Point dimens = new Point();
        getWindowManager().getDefaultDisplay().getSize(dimens);
        CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) appBar.getLayoutParams();
        lp.height = dimens.y / 3;
    }

    @Override
    public void populateFragment() {
    }
}