package com.example.josegarcia.todaymeal;

import android.support.test.espresso.IdlingResource;

import java.util.concurrent.atomic.AtomicBoolean;

public class SimpleIdlingResource implements IdlingResource {
    private static SimpleIdlingResource instance;
    private SimpleIdlingResource() {}
    AtomicBoolean isIdle = new AtomicBoolean(false);
    ResourceCallback callback;

    @Override
    public String getName() {
        return getClass().getName();
    }

    @Override
    public boolean isIdleNow() {
        return isIdle.get();
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback callback) {
        this.callback = callback;
    }

    public void setIdleState(boolean idle) {
        isIdle.set(idle);
        if (isIdle.get() && callback != null) {
            callback.onTransitionToIdle();
        }
    }

    public static SimpleIdlingResource getInstance() {
        if (instance == null) {
            instance = new SimpleIdlingResource();
        }
        return instance;
    }
}
