package com.example.josegarcia.todaymeal.repository

import com.example.josegarcia.todaymeal.model.Recipe
import io.reactivex.Observable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

object RecipeClient {
    private const val URL = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/"
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
        @get:GET("baking.json")
        val recipes: Observable<List<Recipe>>
    }
}
