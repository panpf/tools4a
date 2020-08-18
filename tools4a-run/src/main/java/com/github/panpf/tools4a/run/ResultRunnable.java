package com.github.panpf.tools4a.run;

import androidx.annotation.NonNull;

public interface ResultRunnable<T> {
    @NonNull
    T run();
}
