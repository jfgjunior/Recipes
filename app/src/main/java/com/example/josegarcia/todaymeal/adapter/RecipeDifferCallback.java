package com.example.josegarcia.todaymeal.adapter;

import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;

import com.example.josegarcia.todaymeal.model.Recipe;

public class RecipeDifferCallback extends DiffUtil.ItemCallback<Recipe> {
    @Override
    public boolean areItemsTheSame(@NonNull Recipe r1, @NonNull Recipe r2) {
        return r1.equals(r2);
    }

    @Override
    public boolean areContentsTheSame(@NonNull Recipe r1, @NonNull Recipe r2) {
        return r1.getId() == r2.getId();
    }
}
