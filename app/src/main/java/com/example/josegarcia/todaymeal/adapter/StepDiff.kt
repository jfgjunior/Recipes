package com.example.josegarcia.todaymeal.adapter

import androidx.recyclerview.widget.DiffUtil

import com.example.josegarcia.todaymeal.model.Step

class StepDiff : DiffUtil.ItemCallback<Step>() {
    override fun areItemsTheSame(oldStep: Step, newStep: Step) = oldStep.id == newStep.id

    override fun areContentsTheSame(oldStep: Step, newStep: Step) =
        areItemsTheSame(oldStep, newStep)
}
