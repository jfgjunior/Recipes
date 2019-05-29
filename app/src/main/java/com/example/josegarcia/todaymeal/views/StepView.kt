package com.example.josegarcia.todaymeal.views

import android.content.Context
import android.net.Uri
import android.support.v7.widget.CardView
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
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
) : CardView(context, attrs, defStyleAttr) {
    private var exoPlayer: SimpleExoPlayer? = null
    private lateinit var step: Step

    init {
        LayoutInflater.from(context).inflate(R.layout.view_step, this, true)
        setOnClickListener { this@StepView.onClick() }
    }

    fun setStep(step: Step) {
        this.step = step
        initializeViews()
    }

    private fun onClick() {
        if (shortDescriptionLayout.visibility == View.GONE) {
            shortDescriptionLayout.visibility = View.VISIBLE
            longDescriptionLayout.visibility = View.GONE
            stopPlayer()
        } else {
            shortDescriptionLayout.visibility = View.GONE
            longDescriptionLayout.visibility = View.VISIBLE
            initializePlayer()
        }
    }

    private fun initializeViews() {
        val number = String.format("%d", step.number)
        stepNumberShort.text = number
        stepNumberLong.text = number
        shortDescription.text = step.shortDescription
        longDescription.text = step.description
    }

    private fun initializePlayer() {
        val videoUrl = step.videoURL
        if (step.videoURL.isEmpty()) {
            playerView.visibility = View.GONE
            return
        }
        if (exoPlayer == null) {
            exoPlayer = ExoPlayerFactory.newSimpleInstance(
                context,
                DefaultRenderersFactory(context),
                DefaultTrackSelector(), DefaultLoadControl()
            )

            val uri = Uri.parse(videoUrl)
            val mediaSource = buildMediaSource(uri)
            playerView.player = exoPlayer
            exoPlayer?.playWhenReady = true
            exoPlayer?.prepare(mediaSource, false, true)
        }
    }

    private fun buildMediaSource(uri: Uri): MediaSource =
        ExtractorMediaSource.Factory(
            DefaultHttpDataSourceFactory(
                Util.getUserAgent(
                    context,
                    context.resources.getString(R.string.app_name)
                )
            )
        ).createMediaSource(uri)

    private fun stopPlayer() =
        exoPlayer?.let {
            (exoPlayer as SimpleExoPlayer).stop()
            (exoPlayer as SimpleExoPlayer).release()
            exoPlayer = null
        }

    override fun onDetachedFromWindow() {
        stopPlayer()
        super.onDetachedFromWindow()
    }
}