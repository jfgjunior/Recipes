package com.example.josegarcia.todaymeal.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.josegarcia.todaymeal.R;
import com.example.josegarcia.todaymeal.model.Step;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

public class StepView extends CardView {
    private LinearLayout shortDescriptionLayout;
    private ConstraintLayout longDescriptionLayout;
    private PlayerView playerView;
    private SimpleExoPlayer exoPlayer;
    private Step step;

    public StepView(Context context) {
        super(context);
        init(null);
    }

    public StepView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public StepView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @Override
    public void setOnClickListener(@Nullable OnClickListener l) {
        super.setOnClickListener(view -> StepView.this.onClick());
    }

    public void onClick() {
        if (shortDescriptionLayout.getVisibility() == View.GONE) {
            shortDescriptionLayout.setVisibility(View.VISIBLE);
            longDescriptionLayout.setVisibility(View.GONE);
            stopPlayer();
        } else {
            shortDescriptionLayout.setVisibility(View.GONE);
            longDescriptionLayout.setVisibility(View.VISIBLE);
            initializePlayer();
        }
    }

    private void init(@Nullable AttributeSet set) {
        LayoutInflater.from(getContext()).inflate(R.layout.view_step, this, true);
        shortDescriptionLayout = findViewById(R.id.short_description_layout);
        longDescriptionLayout = findViewById(R.id.long_description_layout);
        setOnClickListener(null);

        if (set == null) {
            return;
        }
        TypedArray typedArray = getContext().obtainStyledAttributes(set, R.styleable.StepView);
        int stepNumber = typedArray.getInteger(R.styleable.StepView_step_number, -1);
        String shortDescription = typedArray.getString(R.styleable.StepView_short_description);
        String longDescription = typedArray.getString(R.styleable.StepView_long_description);
        String videoUrl = typedArray.getString(R.styleable.StepView_video_url);
        String thumbnailUrl = typedArray.getString(R.styleable.StepView_thumbnail_url);
        typedArray.recycle();
        Step step = new Step(stepNumber, shortDescription, longDescription, videoUrl, thumbnailUrl);
        setStep(step);
    }

    public void setStep(Step step) {
        this.step = step;
        initializeViews();
    }

    private void initializeViews() {
        String number = String.format("%d", step.getNumber());
        TextView stepNumberShort = shortDescriptionLayout.findViewById(R.id.step_number_short);
        stepNumberShort.setText(number);
        TextView shortDescription = shortDescriptionLayout.findViewById(R.id.short_description);
        shortDescription.setText(step.getShortDescription());

        TextView stepNumberLong = longDescriptionLayout.findViewById(R.id.step_number_long);
        stepNumberLong.setText(number);
        TextView longDescription = longDescriptionLayout.findViewById(R.id.long_description);
        longDescription.setText(step.getDescription());

        playerView = longDescriptionLayout.findViewById(R.id.step_content);
    }

    private void initializePlayer() {
        String videoUrl = step.getVideoURL();
        if (step.getVideoURL() == null || step.getVideoURL().isEmpty()) {
            playerView.setVisibility(View.GONE);
        } else {
            if (exoPlayer == null) {
                exoPlayer = ExoPlayerFactory.newSimpleInstance(getContext(),
                        new DefaultRenderersFactory(getContext()),
                        new DefaultTrackSelector(), new DefaultLoadControl());

                playerView.setPlayer(exoPlayer);
                exoPlayer.setPlayWhenReady(true);
                Uri uri = Uri.parse(videoUrl);
                MediaSource mediaSource = buildMediaSource(uri);
                exoPlayer.prepare(mediaSource, false, true);
            }
        }
    }

    private MediaSource buildMediaSource(Uri uri) {
        String userAgent = Util.getUserAgent(getContext(), getContext().getResources().getString(R.string.app_name));
        return new ExtractorMediaSource.Factory(
                new DefaultHttpDataSourceFactory(userAgent))
                .createMediaSource(uri);
    }

    private void stopPlayer() {
        if (exoPlayer != null) {
            exoPlayer.stop();
            exoPlayer.release();
            exoPlayer = null;
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        stopPlayer();
        super.onDetachedFromWindow();
    }
}

