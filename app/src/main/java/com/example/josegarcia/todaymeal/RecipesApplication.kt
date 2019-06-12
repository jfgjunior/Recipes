package com.example.josegarcia.todaymeal

import android.app.Application

class RecipesApplication : Application(), Injector {
    override val inject: ApplicationComponent by lazy { DaggerApplicationComponent.create() }
}

interface Injector {
    val inject: ApplicationComponent
}