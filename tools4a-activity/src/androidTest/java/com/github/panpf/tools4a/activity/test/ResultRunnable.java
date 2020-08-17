package com.github.panpf.tools4a.activity.test;

import androidx.annotation.NonNull;

public interface ResultRunnable<T> {
    @NonNull
    T run();
}
