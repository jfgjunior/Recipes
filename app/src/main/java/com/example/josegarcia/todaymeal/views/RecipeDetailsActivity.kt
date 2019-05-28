package com.example.josegarcia.todaymeal.views

import android.graphics.Point
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CoordinatorLayout
import android.support.v7.app.AppCompatActivity
import com.example.josegarcia.todaymeal.R
import com.example.josegarcia.todaymeal.helper.ImageCache
import com.example.josegarcia.todaymeal.model.Recipe
import com.example.josegarcia.todaymeal.views.RecipeListFragment.Companion.RECIPE_KEY
import kotlinx.android.synthetic.main.activity_recipe_details.collapsing_toolbar as toolbar
import kotlinx.android.synthetic.main.activity_recipe_details.recipe_image as picture

class RecipeDetailsActivity : AppCompatActivity() {
    private val recipe: Recipe by lazy { intent.getParcelableExtra(RECIPE_KEY) as Recipe }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_details)
        setToolbarSize()
        setUpActionBar()
        setActionBarImage()
        inflateFragment()
    }

    private fun setActionBarImage() =
        ImageCache.bitmap?.let { picture.setImageBitmap(it) } ?: picture.setImageResource(R.drawable.cooking_table)


    private fun setUpActionBar() {
        setSupportActionBar(toolbar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.title = recipe.name
        }
    }

    private fun setToolbarSize() {
        val appBar = findViewById<AppBarLayout>(R.id.appbar)
        val dimens = Point()
        windowManager.defaultDisplay.getSize(dimens)
        val lp = appBar.layoutParams as CoordinatorLayout.LayoutParams
        lp.height = dimens.y / 3
    }

    private fun inflateFragment() {
        supportFragmentManager.beginTransaction()
            .add(R.id.container, RecipeDescriptionFragment.newInstance(recipe))
            .commit()
    }
}