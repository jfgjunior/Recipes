package com.example.josegarcia.todaymeal.repository

import com.example.josegarcia.todaymeal.model.Recipe
import io.reactivex.Observable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

object RecipeClient {
    private const val URL = "https://api.jsonbin.io/b/"
    private val client = OkHttpClient.Builder().addInterceptor { chain ->
        chain.proceed(chain.request())
    }.build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(client)
        .build()

    val recipes: Observable<List<Recipe>>
        get() {
            val fetcher = retrofit.create(RecipeFetcher::class.java)
            return fetcher.recipes
        }

    internal interface RecipeFetcher {
        @get:GET("5d013bc658196b429f52fa80/1")
        val recipes: Observable<List<Recipe>>
    }
}
