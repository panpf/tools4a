/*
 * Copyright (C) 2020 panpf <panpfpanpf@outlook.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.panpf.tools4a.other;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class SingletonLazy<P, R> {

    @NonNull
    private final Callback<P, R> callback;
    @Nullable
    private volatile R instance;

    public SingletonLazy(@NonNull Callback<P, R> callback) {
        this.callback = callback;
    }

    @NonNull
    public R get(@NonNull P p) {
        synchronized (this) {
            R tempInstance = instance;
            if (tempInstance != null) return tempInstance;

            synchronized (this) {
                R tempInstance2 = instance;
                if (tempInstance2 != null) return tempInstance2;

                R newInstance = callback.createInstantiate(p);
                this.instance = newInstance;
                return newInstance;
            }
        }
    }

    public interface Callback<P, R> {
        @NonNull
        R createInstantiate(@NonNull P p);
    }
}
