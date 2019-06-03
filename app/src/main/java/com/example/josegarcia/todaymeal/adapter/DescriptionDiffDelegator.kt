package com.example.josegarcia.todaymeal.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.josegarcia.todaymeal.model.Ingredient
import com.example.josegarcia.todaymeal.model.Step

class DescriptionDiffDelegator : DiffUtil.ItemCallback<Any>() {
    private val ingredientDiff = IngredientDiff()
    private val stepDiff = StepDiff()
    private val titleDiff = TittleDiff()

    override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
        if (!hasSameType(oldItem, newItem)) {
            return false
        }
        return when (oldItem) {
            is Ingredient -> ingredientDiff.areItemsTheSame(oldItem, newItem as Ingredient)
            is Step -> stepDiff.areItemsTheSame(oldItem, newItem as Step)
            is Int -> titleDiff.areItemsTheSame(oldItem, newItem as Int)
            else -> throw ClassNotFoundException()
        }
    }

    override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
        if (!hasSameType(oldItem, newItem)) {
            return false
        }
        return when (oldItem) {
            is Ingredient -> ingredientDiff.areContentsTheSame(oldItem, newItem as Ingredient)
            is Step -> stepDiff.areContentsTheSame(oldItem, newItem as Step)
            is Int -> titleDiff.areContentsTheSame(oldItem, newItem as Int)
            else -> throw ClassNotFoundException()
        }
    }

    private fun hasSameType(oldItem: Any, newItem: Any) =
        oldItem.javaClass.name == newItem.javaClass.name
}