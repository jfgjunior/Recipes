package com.example.josegarcia.todaymeal.adapter

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.josegarcia.todaymeal.R
import com.example.josegarcia.todaymeal.model.Step
import com.example.josegarcia.todaymeal.views.StepView

class StepsListAdapter : ListAdapter<Step, StepsListAdapter.StepViewHolder>(StepDiff()) {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): StepViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.step_view_holder, viewGroup, false)
        return StepViewHolder(view)
    }

    override fun onBindViewHolder(stepViewHolder: StepViewHolder, i: Int) {
        val step = getItem(i)
        stepViewHolder.bind(step)
    }

    class StepViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val stepView: StepView = itemView.findViewById(R.id.step_view)

        fun bind(step: Step) {
            stepView.setStep(step)
        }
    }
}
