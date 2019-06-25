package com.example.josegarcia.todaymeal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.josegarcia.todaymeal.model.Recipe
import com.example.josegarcia.todaymeal.repository.RecipeClient
import com.example.josegarcia.todaymeal.repository.Repository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.net.UnknownHostException

class FakeRepository: Repository<Recipe> {
    private val recipesList = MutableLiveData<List<Recipe>>()
    private val downloading = MutableLiveData<Boolean>()
    private val connectionStatus = MutableLiveData<Boolean>()

    override val data: LiveData<List<Recipe>>
        get() = recipesList
    override val isDownloading: LiveData<Boolean>
        get() = downloading
    override val hasConnection: LiveData<Boolean>
        get() = connectionStatus

    init {
        fetchData()
        connectionStatus.value = true
        downloading.value = true
    }

    override fun fetchData() {
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