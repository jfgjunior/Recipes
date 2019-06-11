package com.example.josegarcia.todaymeal.extensions

import android.app.Application
import com.example.josegarcia.todaymeal.ApplicationComponent
import com.example.josegarcia.todaymeal.Injector

val Application.inject: ApplicationComponent
    get() = (this as Injector).inject