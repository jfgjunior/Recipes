package com.example.josegarcia.todaymeal.view_model

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.josegarcia.todaymeal.model.Recipe
import com.example.josegarcia.todaymeal.repository.Repository
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject

class RecipeListViewModel @AssistedInject constructor(
    @Assisted private val repository: Repository
) : ViewModel() {
    val recipes: LiveData<List<Recipe>>
        get() = repository.recipes
    val progressBarVisibility: LiveData<Int>
        get() = Transformations.map(repository.isDownloading) { if (it) View.VISIBLE else View.GONE }
    val connectionMessageVisibility: LiveData<Int>
        get() = Transformations.map(repository.hasConnection) { if (it) View.GONE else View.VISIBLE }

    @AssistedInject.Factory
    interface Factory {
        fun create(repository: Repository): RecipeListViewModel
    }
}