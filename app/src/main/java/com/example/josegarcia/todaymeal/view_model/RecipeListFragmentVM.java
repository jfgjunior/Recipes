package com.example.josegarcia.todaymeal.view_model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.example.josegarcia.todaymeal.internet.RecipeClient;
import com.example.josegarcia.todaymeal.model.Recipe;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RecipeListFragmentVM extends ViewModel {
    private MutableLiveData<List<Recipe>> recipes;

    public LiveData<List<Recipe>> getRecipesObservable() {
        return recipes;
    }

    public RecipeListFragmentVM() {
        recipes = new MutableLiveData<>();
    }

    public void getRecipes() {
        if (recipes.getValue() == null || recipes.getValue().isEmpty()) {
            fetchData();
        }
    }

    private void fetchData() {
        RecipeClient.getInstance().getRecipes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retry()
                .subscribe(new Observer<List<Recipe>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(List<Recipe> recipes) {
                        RecipeListFragmentVM.this.recipes.setValue(recipes);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Error", e.getMessage(), e);
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }
}
