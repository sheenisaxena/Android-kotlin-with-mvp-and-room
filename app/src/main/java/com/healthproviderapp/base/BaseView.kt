package com.healthproviderapp.base

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner

interface BaseView<P : LifecycleObserver> : LifecycleOwner {

    fun showingLoading()

    fun dismissLoading()
}