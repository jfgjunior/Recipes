package com.example.josegarcia.todaymeal.custom_views

import android.content.Context
import android.net.Uri
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.cardview.widget.CardView
import com.example.josegarcia.todaymeal.R
import com.example.josegarcia.todaymeal.model.Step
import com.google.android.exoplayer2.DefaultLoadControl
import com.google.android.exoplayer2.DefaultRenderersFactory
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory
import com.google.android.exoplayer2.util.Util
import kotlinx.android.synthetic.main.view_step.view.long_description as longDescription
import kotlinx.android.synthetic.main.view_step.view.long_description_layout as longDescriptionLayout
import kotlinx.android.synthetic.main.view_step.view.short_description as shortDescription
import kotlinx.android.synthetic.main.view_step.view.short_description_layout as shortDescriptionLayout
import kotlinx.android.synthetic.main.view_step.view.step_content as playerView
import kotlinx.android.synthetic.main.view_step.view.step_number_long as stepNumberLong
import kotlinx.android.synthetic.main.view_step.view.step_number_short as stepNumberShort

class StepView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int = 0
) : CardView(context, attrs, defStyleAttr), StepContract.View {
    private var exoPlayer: SimpleExoPlayer? = null
    private val presenter: StepContract.Presenter = StepPresenter(this)

    init {
        LayoutInflater.from(context).inflate(R.layout.view_step, this, true)
        setOnClickListener {
            presenter.handleClick(
                presenter.isShortDescriptionVisible(
                    shortDescriptionLayout.visibility
                )
            )
        }
    }

    fun setStep(step: Step) {
        presenter.setStep(step)
    }

    override fun expandStep() {
        shortDescriptionLayout.visibility = View.GONE
        longDescriptionLayout.visibility = View.VISIBLE
        presenter.initializePlayer(exoPlayer)
    }

    override fun contractStep() {
        shortDescriptionLayout.visibility = View.VISIBLE
        longDescriptionLayout.visibility = View.GONE
        stopPlayer()
    }

    override fun startPlayer() {
        exoPlayer = ExoPlayerFactory.newSimpleInstance(
            context,
            DefaultRenderersFactory(context),
            DefaultTrackSelector(), DefaultLoadControl()
        )
        exoPlayer?.playWhenReady = true
        exoPlayer?.prepare(buildMediaSource(), false, true)
        playerView.player = exoPlayer
    }

    override fun hidePlayer() {
        playerView.visibility = View.GONE
    }

    override fun onDetachedFromWindow() {
        stopPlayer()
        super.onDetachedFromWindow()
    }

    override fun initializeViews() {
        stepNumberShort.text = presenter.stepNumber
        stepNumberLong.text = presenter.stepNumber
        shortDescription.text = presenter.shortDescription
        longDescription.text = presenter.description
    }

    private fun buildMediaSource(): MediaSource =
        ExtractorMediaSource.Factory(
            DefaultHttpDataSourceFactory(
                Util.getUserAgent(
                    context,
                    context.resources.getString(R.string.app_name)
                )
            )
        ).createMediaSource(presenter.videoUri)

    private fun stopPlayer() =
        exoPlayer?.let {
            (exoPlayer as SimpleExoPlayer).stop()
            (exoPlayer as SimpleExoPlayer).release()
            exoPlayer = null
        }
}