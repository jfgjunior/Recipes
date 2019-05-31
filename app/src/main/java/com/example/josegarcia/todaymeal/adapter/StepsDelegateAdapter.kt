package com.example.josegarcia.todaymeal.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.josegarcia.todaymeal.R
import com.example.josegarcia.todaymeal.model.Step
import kotlinx.android.synthetic.main.item_step.view.step_view as stepView

class StepsDelegateAdapter : ViewTypeDelegateAdapter {
    override fun getLayoutId() = R.layout.item_step

    override fun onCreateViewHolder(viewGroup: ViewGroup): StepViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_step, viewGroup, false)
        return StepViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, model: Any) {
        (viewHolder as StepViewHolder).bind(model as Step)
    }

    class StepViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(step: Step) {
            itemView.stepView.setStep(step)
        }
    }
}
