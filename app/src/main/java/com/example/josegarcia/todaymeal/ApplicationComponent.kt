package com.example.josegarcia.todaymeal

import com.example.josegarcia.todaymeal.repository.RecipeRepository
import com.example.josegarcia.todaymeal.view_model.RecipeDescriptionViewModel
import com.example.josegarcia.todaymeal.view_model.RecipeListViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AssistedInjectModule::class])
interface ApplicationComponent {
    val recipeRepository: RecipeRepository
    val recipeListViewModelFactory: RecipeListViewModel.Factory
    val recipeDescriptionViewModelFactory: RecipeDescriptionViewModel.Factory
}