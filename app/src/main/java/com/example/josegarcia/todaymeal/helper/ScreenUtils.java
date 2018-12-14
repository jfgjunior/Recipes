package com.example.josegarcia.todaymeal.helper;

import android.content.Context;
import android.util.DisplayMetrics;

public class ScreenUtils {
    public static int getDpi(Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return metrics.densityDpi;
    }

    public static int getWidth(Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return metrics.widthPixels;
    }

    public static int getHeight(Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return metrics.heightPixels;
    }

    public static int pixelsToDp(Context context, int px) {
        return px / (getDpi(context) / 160);
    }

    public static int dpToPixels(Context context, int dp) {
        return dp * (getDpi(context) / 160);
    }
}
