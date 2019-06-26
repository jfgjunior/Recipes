package com.example.josegarcia.todaymeal.custom_views

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.example.josegarcia.todaymeal.R

class ScoreView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {
    companion object {
        const val MAX_SCORE = 5
        const val SCORE_IMAGE_HEIGHT = 40
    }

    var score: Int = -1
        set(value) {
            field = value
            updateScore(value)
        }

    init {
        inflateView()
    }

    private fun inflateView() {
        val layoutParameters = LayoutParams(
                WRAP_CONTENT,
                SCORE_IMAGE_HEIGHT
        )

        for (i in 0 until MAX_SCORE) {
            val imageView = ImageView(context)
            imageView.setImageResource(R.drawable.ic_food_score)
            imageView.scaleType = ImageView.ScaleType.FIT_XY
            addView(imageView, layoutParameters)
        }
    }

    private fun updateScore(value: Int) {
        for (i in 0 until value) {
            val image = getChildAt(i) as ImageView
            image.setColorFilter(ContextCompat.getColor(context, R.color.yellow))
        }
        invalidate()
    }
}