package com.example.josegarcia.todaymeal.view_model

import android.arch.lifecycle.ViewModel

import com.example.josegarcia.todaymeal.model.Recipe

class HomeViewModel : ViewModel() {
    var recipe: Recipe? = null
}
