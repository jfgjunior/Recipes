package com.example.josegarcia.todaymeal.custom_views

import android.net.Uri
import android.view.View.VISIBLE
import com.example.josegarcia.todaymeal.model.Step
import com.google.android.exoplayer2.SimpleExoPlayer
import java.util.*

class StepPresenter(private val view: StepContract.View) : StepContract.Presenter {

    private lateinit var step: Step

    override val shortDescription: String by lazy { step.shortDescription }
    override val description: String by lazy { step.description }
    override val videoUri: Uri by lazy { Uri.parse(step.videoURL) }
    override val stepNumber: String by lazy { String.format(Locale.ENGLISH, "%d", step.number) }

    override fun isShortDescriptionVisible(visibility: Int) = visibility == VISIBLE

    override fun setStep(step: Step) {
        this.step = step
        view.initializeViews()
    }

    override fun initializePlayer(exoPlayer: SimpleExoPlayer?) {
        if (step.videoURL.isEmpty()) {
            view.hidePlayer()
        } else if (exoPlayer == null) {
            view.startPlayer()
        }
    }

    override fun handleClick(shouldExpand: Boolean) {
        if (shouldExpand) {
            view.expandStep()
        } else {
            view.contractStep()
        }
    }
}