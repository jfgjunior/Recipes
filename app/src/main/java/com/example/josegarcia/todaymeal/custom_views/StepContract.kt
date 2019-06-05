package com.example.josegarcia.todaymeal.custom_views

import android.net.Uri
import com.example.josegarcia.todaymeal.model.Step
import com.google.android.exoplayer2.SimpleExoPlayer

interface StepContract {
    interface Presenter {
        fun isShortDescriptionVisible(visibility: Int): Boolean
        fun setStep(step: Step)
        fun initializePlayer(exoPlayer: SimpleExoPlayer?)
        fun handleClick(shouldExpand: Boolean)
        val shortDescription: String
        val description: String
        val videoUri: Uri
        val stepNumber: String
    }

    interface View {
        fun startPlayer()
        fun hidePlayer()
        fun expandStep()
        fun contractStep()
        fun initializeViews()
    }
}