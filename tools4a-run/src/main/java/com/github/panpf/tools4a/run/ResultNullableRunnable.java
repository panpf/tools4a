package com.github.panpf.tools4a.run;

import androidx.annotation.Nullable;

public interface ResultNullableRunnable<T> {
    @Nullable
    T run();
}
