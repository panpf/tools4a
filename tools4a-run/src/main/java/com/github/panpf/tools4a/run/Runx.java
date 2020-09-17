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

package com.github.panpf.tools4a.run;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class Runx {

    private Runx() {
    }

    /**
     * Get the main thread Handler
     */
    @NonNull
    public static Handler getMainHandler() {
        return MainHandlerHolder.MAIN_HANDLER;
    }


    /**
     * Is on main thread?
     */
    public static boolean isOnMainThread() {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }

    /**
     * Is on not main thread?
     */
    public static boolean isOnNotMainThread() {
        return Looper.getMainLooper().getThread() != Thread.currentThread();
    }

    /**
     * Checks if the current thread is the main thread, otherwise throws.
     *
     * @throws IllegalStateException if current thread is not the main thread.
     */
    public static void checkMainThread() {
        if (Looper.getMainLooper().getThread() != Thread.currentThread()) {
            throw new IllegalStateException("Method cannot be called on the not main application thread (on: " + Thread.currentThread().getName() + ")");
        }
    }

    /**
     * Checks if the current thread is not the main thread, otherwise throws.
     *
     * @throws IllegalStateException if current thread is the main thread.
     */
    public static void checkNotMainThread() {
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            throw new IllegalStateException("Method cannot be called on the main application thread (on: " + Thread.currentThread().getName() + ")");
        }
    }


    /**
     * Execute the specified code block in the main thread
     */
    public static void runOnMainThread(@NonNull Runnable block) {
        if (isOnMainThread()) {
            block.run();
        } else {
            getMainHandler().post(block);
        }
    }

    /**
     * Execute the specified code block in the main thread
     */
    public static void runOnMainThreadSync(@NonNull final Runnable block) {
        if (isOnMainThread()) {
            block.run();
        } else {
            final SyncRunnable syncRunnable = new SyncRunnable(block);
            getMainHandler().post(syncRunnable);
            syncRunnable.waitForComplete();
        }
    }

    /**
     * Execute the specified code block in the main thread
     */
    @NonNull
    public static <T> T runOnMainThreadSyncResult(@NonNull final ResultRunnable<T> block) {
        if (isOnMainThread()) {
            return block.run();
        } else {
            final SyncResultRunnable<T> syncRunnable = new SyncResultRunnable<>(block);
            getMainHandler().post(syncRunnable);
            return syncRunnable.waitForComplete();
        }
    }

    /**
     * Execute the specified code block in the main thread
     */
    @Nullable
    public static <T> T runOnMainThreadSyncResultNullable(@NonNull final ResultNullableRunnable<T> block) {
        if (isOnMainThread()) {
            return block.run();
        } else {
            final SyncResultNullableRunnable<T> syncRunnable = new SyncResultNullableRunnable<>(block);
            getMainHandler().post(syncRunnable);
            return syncRunnable.waitForComplete();
        }
    }


    /**
     * Is on main process?
     */
    public static boolean isOnMainProcess(@NonNull Context context) {
        return context.getPackageName().equals(getOnProcessNameOrNull(context));
    }

    /**
     * Is on not main process?
     */
    public static boolean isOnNotMainProcess(@NonNull Context context) {
        return !context.getPackageName().equals(getOnProcessNameOrNull(context));
    }


    /**
     * Get the name of the current process
     */
    @Nullable
    public static String getOnProcessNameOrNull(@NonNull Context context) {
        final int myPid = android.os.Process.myPid();
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> processInfoList = activityManager.getRunningAppProcesses();
        if (processInfoList != null) {
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : processInfoList) {
                if (runningAppProcessInfo.pid == myPid) {
                    return runningAppProcessInfo.processName;
                }
            }
        }
        return null;
    }

    /**
     * Get the suffix of the current process name, for example, the process name is 'com.my.app:push', then the suffix is ​​'push'
     */
    @Nullable
    public static String getOnProcessNameSuffixOrNull(@NonNull Context context) {
        String processName = getOnProcessNameOrNull(context);
        if (processName == null) return null;
        String packageName = context.getPackageName();
        int lastIndex = processName.lastIndexOf(packageName, 0);
        String result = lastIndex != -1 ? processName.substring(lastIndex + packageName.length()) : null;
        if (result != null && result.startsWith(":")) {
            result = result.substring(1);
        }
        return result != null && !"".equals(result) ? result : null;
    }


    private static class MainHandlerHolder {
        private static final Handler MAIN_HANDLER = new Handler(Looper.getMainLooper());
    }
}
