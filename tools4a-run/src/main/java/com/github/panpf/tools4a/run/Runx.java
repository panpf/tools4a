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
import java.util.concurrent.CountDownLatch;

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
     * Execute the specified code block in the main thread
     */
    public static void runOnUiThread(@NonNull Runnable block) {
        if (isOnMainThread()) {
            block.run();
        } else {
            getMainHandler().post(block);
        }
    }

    /**
     * Execute the specified code block in the main thread
     */
    public static void runOnUiThreadAndWait(@NonNull final Runnable block) {
        if (isOnMainThread()) {
            block.run();
        } else {
            final CountDownLatch countDownLatch = new CountDownLatch(1);
            getMainHandler().post(() -> {
                block.run();
                countDownLatch.countDown();
            });
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Execute the specified code block in the main thread
     */
    @NonNull
    public static <T> T runOnUiThreadAndWaitResult(@NonNull final ResultRunnable<T> block) {
        if (isOnMainThread()) {
            return block.run();
        } else {
            final Object[] results = new Object[1];
            final CountDownLatch countDownLatch = new CountDownLatch(1);
            getMainHandler().post(() -> {
                results[0] = block.run();
                countDownLatch.countDown();
            });
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //noinspection unchecked
            T result = (T) results[0];
            if (result != null) {
                return result;
            } else {
                throw new IllegalArgumentException("return result cannot be null");
            }
        }
    }

    /**
     * Execute the specified code block in the main thread
     */
    @Nullable
    public static <T> T runOnUiThreadAndWaitNullableResult(@NonNull final ResultNullableRunnable<T> block) {
        if (isOnMainThread()) {
            return block.run();
        } else {
            final Object[] results = new Object[1];
            final CountDownLatch countDownLatch = new CountDownLatch(1);
            getMainHandler().post(() -> {
                results[0] = block.run();
                countDownLatch.countDown();
            });
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //noinspection unchecked
            return (T) results[0];
        }
    }

    /**
     * Is on main thread?
     */
    public static boolean isOnMainThread() {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }

    /**
     * Is on main process?
     */
    public static boolean isOnMainProcess(@NonNull Context context) {
        return context.getPackageName().equals(getProcessName(context));
    }

    /**
     * Get the name of the current process
     */
    @Nullable
    public static String getProcessName(@NonNull Context context) {
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
     * Get the suffix of the current process name, for example, the process name is 'com.my.app:push', then the suffix is ​​':push'
     */
    @Nullable
    public static String getProcessNameSuffix(@NonNull Context context) {
        String processName = getProcessName(context);
        if (processName == null) return null;
        String packageName = context.getPackageName();
        int lastIndex = processName.lastIndexOf(packageName, 0);
        return lastIndex != -1 ? processName.substring(lastIndex + packageName.length()) : null;
    }

    private static class MainHandlerHolder {
        private static final Handler MAIN_HANDLER = new Handler(Looper.getMainLooper());
    }
}
