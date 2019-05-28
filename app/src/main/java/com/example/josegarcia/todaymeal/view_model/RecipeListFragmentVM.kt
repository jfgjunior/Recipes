package com.example.josegarcia.todaymeal.view_model

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log

import com.example.josegarcia.todaymeal.internet.RecipeClient
import com.example.josegarcia.todaymeal.model.Recipe

import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class RecipeListFragmentVM : ViewModel() {
    private val recipes: MutableLiveData<List<Recipe>> = MutableLiveData()

    val recipesObservable: LiveData<List<Recipe>>
        get() = recipes

    fun getRecipes() {
        if (recipes.value == null || recipes.value!!.isEmpty()) {
            fetchData()
        }
    }

    private fun fetchData() {
        RecipeClient.recipes
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .retry()
            .subscribe(object : Observer<List<Recipe>> {
                override fun onSubscribe(d: Disposable) {}

                override fun onNext(recipes: List<Recipe>) {
                    this@RecipeListFragmentVM.recipes.value = recipes
                }

                override fun onError(e: Throwable) {
                    Log.e("Error", e.message, e)
                }

                override fun onComplete() {}
            })
    }
}
