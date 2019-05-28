package com.example.josegarcia.todaymeal.helper

import android.content.Context

val Context.getDpi: Int
    get() = resources.displayMetrics.densityDpi

val Context.getWidth: Int
    get() = resources.displayMetrics.widthPixels

val Context.getheight: Int
    get() = resources.displayMetrics.heightPixels

fun Context.pixelsToDp(px: Int): Int {
    return px / (getDpi / 160)
}

fun Context.dpToPixels(px: Int): Int {
    return px * (getDpi / 160)
}