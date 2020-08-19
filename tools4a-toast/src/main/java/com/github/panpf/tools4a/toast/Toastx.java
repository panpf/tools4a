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

package com.github.panpf.tools4a.toast;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;

public class Toastx {

    private Toastx() {
    }

    /* ************************************* Context ***************************************** */


    public static void showLong(@NonNull Context context, @NonNull final CharSequence message) {
        final Context appContext = context.getApplicationContext();
        runInUI(() -> Toast.makeText(appContext, message, Toast.LENGTH_LONG).show());
    }

    public static void showLong(@NonNull Context context, @StringRes final int messageResId) {
        final CharSequence message = context.getResources().getText(messageResId);
        final Context appContext = context.getApplicationContext();
        runInUI(() -> Toast.makeText(appContext, message, Toast.LENGTH_LONG).show());
    }

    public static void showWithFormatLong(@NonNull Context context, @NonNull final String format, @NonNull final Object... params) {
        final CharSequence message = String.format(format, params);
        final Context appContext = context.getApplicationContext();
        runInUI(() -> Toast.makeText(appContext, message, Toast.LENGTH_LONG).show());
    }

    public static void showWithFormatLong(@NonNull Context context, @StringRes final int messageResId, @NonNull final Object... params) {
        final CharSequence message = context.getString(messageResId, params);
        final Context appContext = context.getApplicationContext();
        runInUI(() -> Toast.makeText(appContext, message, Toast.LENGTH_LONG).show());
    }


    public static void showShort(@NonNull Context context, @NonNull final CharSequence message) {
        final Context appContext = context.getApplicationContext();
        runInUI(() -> Toast.makeText(appContext, message, Toast.LENGTH_SHORT).show());
    }

    public static void showShort(@NonNull Context context, @StringRes final int messageResId) {
        final CharSequence message = context.getResources().getText(messageResId);
        final Context appContext = context.getApplicationContext();
        runInUI(() -> Toast.makeText(appContext, message, Toast.LENGTH_SHORT).show());
    }

    public static void showWithFormatShort(@NonNull Context context, @NonNull final String format, @NonNull final Object... params) {
        final String message = String.format(format, params);
        final Context appContext = context.getApplicationContext();
        runInUI(() -> Toast.makeText(appContext, message, Toast.LENGTH_SHORT).show());
    }

    public static void showWithFormatShort(@NonNull Context context, @StringRes final int messageResId, @NonNull final Object... params) {
        final CharSequence message = context.getResources().getString(messageResId, params);
        final Context appContext = context.getApplicationContext();
        runInUI(() -> Toast.makeText(appContext, message, Toast.LENGTH_SHORT).show());
    }


    /* ************************************ Fragment **************************************** */


    public static void showLong(@NonNull Fragment fragment, @NonNull final CharSequence message) {
        final Context context = fragment.requireContext();
        final Context appContext = context.getApplicationContext();
        runInUI(() -> Toast.makeText(appContext, message, Toast.LENGTH_LONG).show());
    }

    public static void showLong(@NonNull Fragment fragment, @StringRes final int messageResId) {
        final Context context = fragment.requireContext();
        final CharSequence message = context.getResources().getText(messageResId);
        final Context appContext = context.getApplicationContext();
        runInUI(() -> Toast.makeText(appContext, message, Toast.LENGTH_LONG).show());
    }

    public static void showWithFormatLong(@NonNull Fragment fragment, @NonNull final String format, @NonNull final Object... params) {
        final Context context = fragment.requireContext();
        final CharSequence message = String.format(format, params);
        final Context appContext = context.getApplicationContext();
        runInUI(() -> Toast.makeText(appContext, message, Toast.LENGTH_LONG).show());
    }

    public static void showWithFormatLong(@NonNull Fragment fragment, @StringRes final int messageResId, @NonNull final Object... params) {
        final Context context = fragment.requireContext();
        final CharSequence message = context.getResources().getString(messageResId, params);
        final Context appContext = context.getApplicationContext();
        runInUI(() -> Toast.makeText(appContext, message, Toast.LENGTH_LONG).show());
    }


    public static void showShort(@NonNull Fragment fragment, @NonNull final CharSequence message) {
        final Context context = fragment.requireContext();
        final Context appContext = context.getApplicationContext();
        runInUI(() -> Toast.makeText(appContext, message, Toast.LENGTH_SHORT).show());
    }

    public static void showShort(@NonNull Fragment fragment, @StringRes final int messageResId) {
        final Context context = fragment.requireContext();
        final CharSequence message = context.getResources().getText(messageResId);
        final Context appContext = context.getApplicationContext();
        runInUI(() -> Toast.makeText(appContext, message, Toast.LENGTH_SHORT).show());
    }

    public static void showWithFormatShort(@NonNull Fragment fragment, @NonNull final String format, @NonNull final Object... params) {
        final Context context = fragment.requireContext();
        final CharSequence message = String.format(format, params);
        final Context appContext = context.getApplicationContext();
        runInUI(() -> Toast.makeText(appContext, message, Toast.LENGTH_SHORT).show());
    }

    public static void showWithFormatShort(@NonNull Fragment fragment, @StringRes final int messageResId, @NonNull final Object... params) {
        final Context context = fragment.requireContext();
        final CharSequence message = context.getResources().getString(messageResId, params);
        final Context appContext = context.getApplicationContext();
        runInUI(() -> Toast.makeText(appContext, message, Toast.LENGTH_SHORT).show());
    }


    /* ************************************* View ***************************************** */


    public static void showLong(@NonNull View view, @NonNull final CharSequence message) {
        final Context context = view.getContext();
        final Context appContext = context.getApplicationContext();
        runInUI(() -> Toast.makeText(appContext, message, Toast.LENGTH_LONG).show());
    }

    public static void showLong(@NonNull View view, @StringRes final int messageResId) {
        final Context context = view.getContext();
        final CharSequence message = context.getResources().getText(messageResId);
        final Context appContext = context.getApplicationContext();
        runInUI(() -> Toast.makeText(appContext, message, Toast.LENGTH_LONG).show());
    }

    public static void showWithFormatLong(@NonNull View view, @NonNull final String format, @NonNull final Object... params) {
        final Context context = view.getContext();
        final CharSequence message = String.format(format, params);
        final Context appContext = context.getApplicationContext();
        runInUI(() -> Toast.makeText(appContext, message, Toast.LENGTH_LONG).show());
    }

    public static void showWithFormatLong(@NonNull View view, @StringRes final int messageResId, @NonNull final Object... params) {
        final Context context = view.getContext();
        final CharSequence message = context.getResources().getString(messageResId, params);
        final Context appContext = context.getApplicationContext();
        runInUI(() -> Toast.makeText(appContext, message, Toast.LENGTH_LONG).show());
    }


    public static void showShort(@NonNull View view, @NonNull final CharSequence message) {
        final Context context = view.getContext();
        final Context appContext = context.getApplicationContext();
        runInUI(() -> Toast.makeText(appContext, message, Toast.LENGTH_SHORT).show());
    }

    public static void showShort(@NonNull View view, @StringRes final int messageResId) {
        final Context context = view.getContext();
        final CharSequence message = context.getResources().getText(messageResId);
        final Context appContext = context.getApplicationContext();
        runInUI(() -> Toast.makeText(appContext, message, Toast.LENGTH_SHORT).show());
    }

    public static void showWithFormatShort(@NonNull View view, @NonNull final String format, @NonNull final Object... params) {
        final Context context = view.getContext();
        final CharSequence message = String.format(format, params);
        final Context appContext = context.getApplicationContext();
        runInUI(() -> Toast.makeText(appContext, message, Toast.LENGTH_SHORT).show());
    }

    public static void showWithFormatShort(@NonNull View view, @StringRes final int messageResId, @NonNull final Object... params) {
        final Context context = view.getContext();
        final CharSequence message = context.getResources().getString(messageResId, params);
        final Context appContext = context.getApplicationContext();
        runInUI(() -> Toast.makeText(appContext, message, Toast.LENGTH_SHORT).show());
    }


    /* ************************************* Show View ***************************************** */


    public static void showLongWithView(@NonNull final View view) {
        final Context context = view.getContext();
        final Context appContext = context.getApplicationContext();
        runInUI(() -> {
            Toast toast = new Toast(appContext);
            toast.setView(view);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.show();
        });
    }

    public static void showShortWithView(@NonNull final View view) {
        final Context context = view.getContext();
        final Context appContext = context.getApplicationContext();
        runInUI(() -> {
            Toast toast = new Toast(appContext);
            toast.setView(view);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.show();
        });
    }

    /**
     * Execute the specified code block in the main thread
     */
    private static void runInUI(@NonNull Runnable block) {
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            block.run();
        } else {
            new Handler(Looper.getMainLooper()).post(block);
        }
    }
}