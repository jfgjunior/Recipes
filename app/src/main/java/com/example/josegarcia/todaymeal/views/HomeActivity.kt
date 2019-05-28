package com.example.josegarcia.todaymeal.views

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.josegarcia.todaymeal.R
import com.example.josegarcia.todaymeal.model.Recipe
import com.example.josegarcia.todaymeal.view_model.HomeViewModel
import com.example.josegarcia.todaymeal.views.RecipeListFragment.Companion.RECIPE_KEY

class HomeActivity : AppCompatActivity(), SelectRecipe {

    private var currentFragment: RecipeDescriptionFragment? = null
    private var viewModel: HomeViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
    }

    override fun onSelectRecipeListener(recipe: Recipe) {
        viewModel!!.recipe = recipe
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
        currentFragment = RecipeDescriptionFragment()
        fragmentManager.beginTransaction()
            .replace(R.id.description_container, currentFragment!!)
            .commit()
    }
}
