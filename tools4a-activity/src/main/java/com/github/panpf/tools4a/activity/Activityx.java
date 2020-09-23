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

package com.github.panpf.tools4a.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Lifecycle;

import java.lang.reflect.Method;
import java.util.List;

public class Activityx {

    private Activityx() {
    }

    /**
     * Return true if the activity has been destroyed
     */
    public static boolean isDestroyedCompat(@NonNull Activity activity) {
        // First determine that FragmentActivity can use the compatible isDestroyed method in versions below 17.
        if (activity instanceof FragmentActivity) {
            return ((FragmentActivity) activity).getLifecycle().getCurrentState() == Lifecycle.State.DESTROYED;
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return activity.isDestroyed() || activity.isFinishing();
        } else {
            return activity.isFinishing();
        }
    }

    /**
     * Return true if the activity has been destroyed
     */
    public static boolean isDestroyedCompat(@NonNull FragmentActivity activity) {
        return activity.getLifecycle().getCurrentState() == Lifecycle.State.DESTROYED;
    }


    /**
     * Convert a translucent themed Activity
     * [android.R.attr.windowIsTranslucent] back from opaque to
     * translucent following a call to
     * [.convertActivityFromTranslucent] .
     * <p>
     * <p>
     * Calling this allows the Activity behind this one to be seen again. Once
     * all such Activities have been redrawn
     * <p>
     * <p>
     * This call has no effect on non-translucent activities or on activities
     * with the [android.R.attr.windowIsFloating] attribute.
     */
    @RequiresApi(Build.VERSION_CODES.KITKAT)
    public static boolean convertToTranslucentCompat(@NonNull Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            try {
                //noinspection JavaReflectionMemberAccess
                @SuppressLint("DiscouragedPrivateApi")
                Method getActivityOptions = Activity.class.getDeclaredMethod("getActivityOptions");
                getActivityOptions.setAccessible(true);
                Object options = getActivityOptions.invoke(activity);

                Class<?>[] classes = Activity.class.getDeclaredClasses();
                Class<?> translucentConversionListenerClazz = null;
                for (Class<?> clazz : classes) {
                    if (clazz.getSimpleName().contains("TranslucentConversionListener")) {
                        translucentConversionListenerClazz = clazz;
                    }
                }
                //noinspection JavaReflectionMemberAccess
                @SuppressLint("DiscouragedPrivateApi")
                Method convertToTranslucent = Activity.class.getDeclaredMethod("convertToTranslucent",
                        translucentConversionListenerClazz, ActivityOptions.class);
                convertToTranslucent.setAccessible(true);
                //noinspection JavaReflectionInvocation
                convertToTranslucent.invoke(activity, null, options);
                return true;
            } catch (Throwable e) {
                e.printStackTrace();
                return false;
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            try {
                Class<?>[] classes = Activity.class.getDeclaredClasses();
                Class<?> translucentConversionListenerClazz = null;
                for (Class<?> clazz : classes) {
                    if (clazz.getSimpleName().contains("TranslucentConversionListener")) {
                        translucentConversionListenerClazz = clazz;
                    }
                }
                //noinspection JavaReflectionMemberAccess
                Method method = Activity.class.getDeclaredMethod("convertToTranslucent",
                        translucentConversionListenerClazz);
                method.setAccessible(true);
                method.invoke(activity, new Object[]{
                        null
                });
                return true;
            } catch (Throwable e) {
                e.printStackTrace();
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Convert a translucent themed Activity
     * [android.R.attr.windowIsTranslucent] to a fullscreen opaque
     * Activity.
     * <p>
     * Call this whenever the background of a translucent Activity has changed
     * to become opaque. Doing so will allow the [android.view.Surface] of
     * the Activity behind to be released.
     * <p>
     * This call has no effect on non-translucent activities or on activities
     * with the [android.R.attr.windowIsFloating] attribute.
     */
    @RequiresApi(Build.VERSION_CODES.KITKAT)
    public static boolean convertFromTranslucentCompat(@NonNull Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            try {
                //noinspection JavaReflectionMemberAccess
                Method method = Activity.class.getDeclaredMethod("convertFromTranslucent");
                method.setAccessible(true);
                method.invoke(activity);
                return true;
            } catch (Throwable e) {
                e.printStackTrace();
                return false;
            }
        } else {
            return false;
        }
    }


    /**
     * If the own or parent activity implements the specified [clazz], it returns its implementation.
     */
    @Nullable
    public static <T> T getImplFromParent(@NonNull Activity activity, @NonNull Class<T> clazz) {
        Activity parent = activity;
        while (parent != null) {
            if (clazz.isAssignableFrom(parent.getClass())) {
                //noinspection unchecked
                return (T) parent;
            } else {
                parent = parent.getParent();
            }
        }
        return null;
    }


    /* ************************************* canStart ***************************************** */


    /**
     * Test if you can start Activity
     */
    public static boolean canStart(@NonNull Context context, @NonNull Intent intent) {
        if (!(context instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }

        @SuppressLint("QueryPermissionsNeeded")
        List<ResolveInfo> resolveInfoList = context.getPackageManager().queryIntentActivities(intent, 0);
        return resolveInfoList != null && resolveInfoList.size() > 0;
    }


    /* ************************************* start ***************************************** */

    // todo Remove

    /**
     * Start the activity of the specified intent
     */
    public static void start(@NonNull Context context, @NonNull Intent intent) {
        context.startActivity(intent);
    }

    /**
     * Start the activity of the specified intent
     */
    public static void start(@NonNull Fragment fragment, @NonNull Intent intent) {
        fragment.startActivity(intent);
    }

    /**
     * Start the activity of the specified intent
     */
    public static void start(@NonNull View view, @NonNull Intent intent) {
        view.getContext().startActivity(intent);
    }


    /**
     * Start the activity of the specified Class
     */
    public static void startByClass(@NonNull Context context, @NonNull Class<? extends Activity> clazz, @Nullable Bundle args) {
        Intent intent = new Intent(context, clazz);
        if (args != null) intent.putExtras(args);
        context.startActivity(intent);
    }

    /**
     * Start the activity of the specified Class
     */
    public static void startByClass(@NonNull Context context, @NonNull Class<? extends Activity> clazz) {
        startByClass(context, clazz, null);
    }

    /**
     * Start the activity of the specified Class
     */
    public static void startByClass(@NonNull Fragment fragment,
                                    @NonNull Class<? extends Activity> clazz, @Nullable Bundle args) {
        Intent intent = new Intent(fragment.requireContext(), clazz);
        if (args != null) intent.putExtras(args);
        fragment.startActivity(intent);
    }

    /**
     * Start the activity of the specified Class
     */
    public static void startByClass(@NonNull Fragment fragment,
                                    @NonNull Class<? extends Activity> clazz) {
        startByClass(fragment, clazz, null);
    }

    /**
     * Start the activity of the specified Class
     */
    public static void startByClass(@NonNull View view, @NonNull Class<? extends Activity> clazz, @Nullable Bundle args) {
        Intent intent = new Intent(view.getContext(), clazz);
        if (args != null) intent.putExtras(args);
        view.getContext().startActivity(intent);
    }

    /**
     * Start the activity of the specified Class
     */
    public static void startByClass(@NonNull View view, @NonNull Class<? extends Activity> clazz) {
        startByClass(view, clazz, null);
    }


    /* ************************************* safeStart ***************************************** */

    // todo rename to startOrCatch

    /**
     * Safely launch an Activity, catch ActivityNotFoundException and return false
     */
    public static boolean safeStart(@NonNull Context context, @NonNull Intent intent) {
        if (!(context instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }

        try {
            context.startActivity(intent);
            return true;
            // todo change to Exception
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Safely launch an Activity, catch ActivityNotFoundException and return false
     */
    public static boolean safeStart(@NonNull Fragment fragment, @NonNull Intent intent) {
        try {
            fragment.startActivity(intent);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Safely launch an Activity, catch ActivityNotFoundException and return false
     */
    public static boolean safeStart(@NonNull View view, @NonNull Intent intent) {
        return safeStart(view.getContext(), intent);
    }


    /**
     * Safely launch the activity of the specified Class, catch ActivityNotFoundException and return false
     */
    public static boolean safeStartByClass(@NonNull Context context, @NonNull Class<? extends Activity> clazz, @Nullable Bundle args) {
        Intent intent = new Intent(context, clazz);
        if (args != null) intent.putExtras(args);
        return safeStart(context, intent);
    }

    /**
     * Safely launch the activity of the specified Class, catch ActivityNotFoundException and return false
     */
    public static boolean safeStartByClass(@NonNull Context context, @NonNull Class<? extends Activity> clazz) {
        return safeStartByClass(context, clazz, null);
    }

    /**
     * Safely launch the activity of the specified Class, catch ActivityNotFoundException and return false
     */
    public static boolean safeStartByClass(@NonNull Fragment fragment,
                                           @NonNull Class<? extends Activity> clazz, @Nullable Bundle args) {
        Intent intent = new Intent(fragment.requireContext(), clazz);
        if (args != null) intent.putExtras(args);
        return safeStart(fragment, intent);
    }

    /**
     * Safely launch the activity of the specified Class, catch ActivityNotFoundException and return false
     */
    public static boolean safeStartByClass(@NonNull Fragment fragment,
                                           @NonNull Class<? extends Activity> clazz) {
        return safeStartByClass(fragment, clazz, null);
    }

    /**
     * Safely launch the activity of the specified Class, catch ActivityNotFoundException and return false
     */
    public static boolean safeStartByClass(@NonNull View view, @NonNull Class<? extends Activity> clazz, @Nullable Bundle args) {
        Intent intent = new Intent(view.getContext(), clazz);
        if (args != null) intent.putExtras(args);
        return safeStart(view, intent);
    }

    /**
     * Safely launch the activity of the specified Class, catch ActivityNotFoundException and return false
     */
    public static boolean safeStartByClass(@NonNull View view, @NonNull Class<? extends Activity> clazz) {
        return safeStartByClass(view, clazz, null);
    }
}