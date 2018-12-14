package com.example.josegarcia.todaymeal.internet;

import android.support.annotation.VisibleForTesting;

import com.example.josegarcia.todaymeal.SimpleIdlingResource;
import com.example.josegarcia.todaymeal.model.Recipe;

import java.io.IOException;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class RecipeClient {
    private static final String URL = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/";
    private static RecipeClient instance;
    private OkHttpClient client = new OkHttpClient.Builder().addInterceptor(chain -> {
        SimpleIdlingResource.getInstance().setIdleState(false);
        return chain.proceed(chain.request());
    }).build();
    @VisibleForTesting
    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build();


    interface RecipeFetcher {
        @GET("baking.json")
        Observable<List<Recipe>> getRecipes();
    }

    private RecipeClient() {
    }

    public static RecipeClient getInstance() {
        if (instance == null) {
            instance = new RecipeClient();
        }
        return instance;
    }

    public Observable<List<Recipe>> getRecipes() {

        RecipeFetcher fetcher = retrofit.create(RecipeFetcher.class);
        return fetcher.getRecipes();
    }
}
