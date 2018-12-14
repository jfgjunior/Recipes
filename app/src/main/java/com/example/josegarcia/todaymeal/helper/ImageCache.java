package com.example.josegarcia.todaymeal.helper;

import android.graphics.Bitmap;

public class ImageCache {
    private static ImageCache instance;
    private Bitmap bitmap;

    private ImageCache() {}

    public static ImageCache getInstance() {
        if (instance == null) {
            instance = new ImageCache();
        }
        return instance;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }
}
