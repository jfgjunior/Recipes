package com.example.josegarcia.todaymeal.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.josegarcia.todaymeal.model.Recipe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.net.UnknownHostException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor() {
    private val recipesList = MutableLiveData<List<Recipe>>()
    private val downloading = MutableLiveData<Boolean>()
    private val connectionStatus = MutableLiveData<Boolean>()

    val recipes: LiveData<List<Recipe>>
        get() = recipesList
    val isDownloading: LiveData<Boolean>
        get() = downloading
    val hasConnection: LiveData<Boolean>
        get() = connectionStatus

    init {
        downloadRecipes()
        connectionStatus.value = true
        downloading.value = true
    }

    private fun downloadRecipes() {
        val d = RecipeClient.recipes
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .retry { error -> retryConnection(error) }
            .doOnSubscribe { downloading.value = connectionStatus.value }
            .doOnTerminate { hideStatusIndicators() }
            .subscribe { recipesList.value = it }
    }

    private fun retryConnection(error: Throwable): Boolean {
        if (error is UnknownHostException) {
            downloading.value = false
            connectionStatus.value = false
            return true
        }
        return false
    }

    private fun hideStatusIndicators() {
        downloading.value = false
        connectionStatus.value = true
    }
}