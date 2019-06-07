package com.example.josegarcia.todaymeal.view_model

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.josegarcia.todaymeal.repository.Repository
import com.example.josegarcia.todaymeal.model.Recipe

class RecipeListViewModel : ViewModel() {
    private val repository = Repository()

    val recipes: LiveData<List<Recipe>>
        get() = repository.recipes
    val progressBarVisibility: LiveData<Int>
        get() = Transformations.map(repository.isDownloading) { if (it) View.VISIBLE else View.GONE }
    val connectionMessageVisibility: LiveData<Int>
        get() = Transformations.map(repository.hasConnection) { if (it) View.GONE else View.VISIBLE }
}