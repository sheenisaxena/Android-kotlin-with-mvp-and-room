package com.healthproviderapp.base;


import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

public abstract class LifecycleAwarePresenter<V extends androidx.lifecycle.LifecycleOwner> implements LifecycleObserver {
    protected V view;
    private boolean isResumed;

    public LifecycleAwarePresenter(V view) {
        this.view = view;
        this.view.getLifecycle().addObserver(this);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {
        isResumed = true;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause() {
        isResumed = false;
    }

    public boolean isNotSafeToCallViewBack() {
        return !isResumed; // TODO: 5/13/2019 find out best way to do this
    }
}
