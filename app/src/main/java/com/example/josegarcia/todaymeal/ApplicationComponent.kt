package com.example.josegarcia.todaymeal

import com.example.josegarcia.todaymeal.repository.Repository
import com.example.josegarcia.todaymeal.view_model.RecipeDescriptionViewModel
import com.example.josegarcia.todaymeal.view_model.RecipeListViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AssistedInjectModule::class])
interface ApplicationComponent {
    val repository: Repository
    val recipeListViewModelFactory: RecipeListViewModel.Factory
    val recipeDescriptionViewModelFactory: RecipeDescriptionViewModel.Factory
}