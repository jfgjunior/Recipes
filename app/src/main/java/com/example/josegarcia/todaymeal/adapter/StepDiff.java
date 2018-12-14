package com.example.josegarcia.todaymeal.adapter;

import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;

import com.example.josegarcia.todaymeal.model.Step;

public class StepDiff extends DiffUtil.ItemCallback<Step> {
    @Override
    public boolean areItemsTheSame(@NonNull Step step, @NonNull Step t1) {
        return step.getId() == t1.getId();
    }

    @Override
    public boolean areContentsTheSame(@NonNull Step step, @NonNull Step t1) {
        return step.equals(t1);
    }
}
