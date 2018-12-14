package com.example.josegarcia.todaymeal.adapter;

import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.josegarcia.todaymeal.R;
import com.example.josegarcia.todaymeal.model.Step;
import com.example.josegarcia.todaymeal.views.StepView;

public class StepsListAdapter extends ListAdapter<Step, StepsListAdapter.StepViewHolder> {

    public StepsListAdapter() {
        super(new StepDiff());
    }

    @NonNull
    @Override
    public StepViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.step_view_holder, viewGroup, false);
        return new StepViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StepViewHolder stepViewHolder, int i) {
        Step step = getItem(i);
        stepViewHolder.bind(step);
    }

    class StepViewHolder extends RecyclerView.ViewHolder {
        private final StepView stepView;

        public StepViewHolder(@NonNull View itemView) {
            super(itemView);
            stepView = itemView.findViewById(R.id.step_view);
        }

        public void bind(Step step) {
            stepView.setStep(step);
        }
    }
}
