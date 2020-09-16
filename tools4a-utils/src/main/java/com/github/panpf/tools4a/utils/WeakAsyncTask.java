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

package com.github.panpf.tools4a.utils;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;

import java.lang.ref.WeakReference;
import java.lang.reflect.Modifier;

/**
 * Dedicated to performing asynchronous tasks in an Activity or Fragment, the WeakAsyncTask has the following differences compared to AsyncTask:
 * <p>
 * 1. If it is an inner class, it must be static.
 * <br>
 * 2. Weak reference holding Page
 * <br>
 * 3. Overloads the onPreExecute, doInBackground, onProgressUpdate, and onPostExecute methods,
 * extending a Page parameter, checking whether the Page has been destroyed before calling back related methods
 *
 * @deprecated Use the standard <code>java.util.concurrent</code> or
 * <a href="https://developer.android.com/topic/libraries/architecture/coroutines">
 * Kotlin concurrency utilities</a> instead.
 */
@Deprecated
public abstract class WeakAsyncTask<Page, Param, Progress, Result> extends AsyncTask<Param, Progress, Result> {

    @NonNull
    private final WeakReference<Page> reference;

    @SuppressWarnings("deprecation")
    public WeakAsyncTask(@NonNull Page page) {
        // The class name contains '$' is the inner class, the inner class must be static
        if (getClass().getName().contains("$") && !Modifier.isStatic(getClass().getModifiers())) {
            throw new IllegalArgumentException("If it is an inner class, it must be static: " + getClass().getName());
        }
        this.reference = new WeakReference<>(page);
    }

    public boolean isUnbind() {
        return getPage() == null;
    }

    public boolean isBinded() {
        return getPage() != null;
    }

    @Nullable
    private Page getPage() {
        Page page = reference.get();
        if (page == null) {
            return null;
        } else if (page instanceof LifecycleOwner) {
            if (((LifecycleOwner) page).getLifecycle().getCurrentState() == Lifecycle.State.DESTROYED) {
                return null;
            }
        } else if (page instanceof Activity) {
            Activity activity = (Activity) page;
            // First determine that FragmentActivity can use the compatible isDestroyed method in versions below 17.
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                if (activity.isDestroyed() || activity.isFinishing()) {
                    return null;
                }
            } else {
                if (activity.isFinishing()) {
                    return null;
                }
            }
        }
        return page;
    }

    @Override
    protected final void onPreExecute() {
        super.onPreExecute();
        Page page = getPage();
        if (page != null) {
            onPreExecute(page);
        }
    }

    protected void onPreExecute(@NonNull Page page) {

    }

    @Nullable
    @Override
    protected final Result doInBackground(Param[] params) {
        Page page = getPage();
        return page != null ? doInBackground(page, params) : null;
    }

    @Nullable
    protected abstract Result doInBackground(@NonNull Page page, @NonNull Param[] params);

    @Override
    protected final void onProgressUpdate(Progress[] values) {
        super.onProgressUpdate(values);
        Page page = getPage();
        if (page != null) {
            onProgressUpdate(page, values);
        }
    }

    protected void onProgressUpdate(@NonNull Page page, @NonNull Progress[] values) {

    }

    @Override
    protected final void onPostExecute(Result result) {
        super.onPostExecute(result);
        Page page = getPage();
        if (page != null) {
            onPostExecute(page, result);
        }
    }

    protected void onPostExecute(@NonNull Page page, @Nullable Result result) {
    }
}
