package com.example.josegarcia.todaymeal.view_model

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.josegarcia.todaymeal.model.Recipe
import com.example.josegarcia.todaymeal.repository.RecipeRepository
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject

class RecipeListViewModel @AssistedInject constructor(
    @Assisted private val recipeRepository: RecipeRepository
) : ViewModel() {
    val recipes: LiveData<List<Recipe>>
        get() = recipeRepository.data
    val progressBarVisibility: LiveData<Int>
        get() = Transformations.map(recipeRepository.isDownloading) { if (it) View.VISIBLE else View.GONE }
    val connectionMessageVisibility: LiveData<Int>
        get() = Transformations.map(recipeRepository.hasConnection) { if (it) View.GONE else View.VISIBLE }

    @AssistedInject.Factory
    interface Factory {
        fun create(recipeRepository: RecipeRepository): RecipeListViewModel
    }
}