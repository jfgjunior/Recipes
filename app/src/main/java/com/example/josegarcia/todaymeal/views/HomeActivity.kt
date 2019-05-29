package com.example.josegarcia.todaymeal.views

import androidx.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.josegarcia.todaymeal.R
import com.example.josegarcia.todaymeal.model.Recipe
import com.example.josegarcia.todaymeal.view_model.HomeViewModel
import com.example.josegarcia.todaymeal.views.RecipeListFragment.Companion.RECIPE_KEY

class HomeActivity : AppCompatActivity(), SelectRecipe {

    private val currentFragment = RecipeDescriptionFragment()
    private val viewModel: HomeViewModel by lazy { ViewModelProviders.of(this).get(HomeViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }

    override fun onSelectRecipeListener(recipe: Recipe) {
        viewModel.recipe = recipe
        val minScreenWidthSize = getString(R.string.size)
        if (minScreenWidthSize == "normal") {
            startDetailsActivity(recipe)
        } else {
            updateHomeActivity()
        }
    }

    private fun startDetailsActivity(recipe: Recipe) {
        val intent = Intent(this, RecipeDetailsActivity::class.java)
        intent.putExtra(RECIPE_KEY, recipe)
        startActivity(intent)
    }

    private fun updateHomeActivity() {
        val fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction()
            .replace(R.id.description_container, currentFragment)
            .commit()
    }
}
