package com.susheelkaram.dating_app.util;

import android.util.Log;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by Susheel Kumar Karam
 * Website - SusheelKaram.com
 */
public class SingleLiveEvent<T> extends MutableLiveData<T> {
    AtomicBoolean isPending = new AtomicBoolean(false);
    String TAG = "SingleLiveEvent";

    @MainThread
    @Override
    public void observe(@NonNull @NotNull LifecycleOwner owner, @NonNull @NotNull Observer<? super T> observer) {
        if (hasActiveObservers()) {
            Log.w(TAG, "Multiple observers registered but only one will be notified of changes.");
        }

        super.observe(owner, new Observer<T>() {
            @Override
            public void onChanged(T t) {
                if (isPending.compareAndSet(true, false)) {
                    observer.onChanged(t);
                }
            }
        });
    }

    @MainThread
    @Override
    public void setValue(T value) {
        isPending.set(true);
        super.setValue(value);
    }
}

