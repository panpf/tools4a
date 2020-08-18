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

package com.github.panpf.tools4a.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.lang.reflect.Field;

public class Dialogx {

    private Dialogx() {
    }

    /**
     * Whether to automatically close the dialog box when setting the click button
     */
    public static boolean setClickButtonClosable(@NonNull Dialog dialog, boolean closable) {
        try {
            Field field = getDeclaredFieldRecursive(dialog.getClass(), "mShowing");
            field.setAccessible(true);
            field.set(dialog, closable);
            return true;
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            return false;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Display a progress dialog
     */
    @NonNull
    public static ProgressDialog showProgressDialog(@NonNull Activity activity, @NonNull String message) {
        ProgressDialog newDialog = new ProgressDialog(activity);
        newDialog.setTitle(null);
        newDialog.setMessage(message);
        newDialog.setIndeterminate(true);
        newDialog.setCancelable(false);
        newDialog.setOnCancelListener(null);
        newDialog.setCanceledOnTouchOutside(false);
        newDialog.show();
        return newDialog;
    }

    /**
     * Display a progress dialog
     */
    @NonNull
    public static ProgressDialog showProgressDialog(@NonNull Activity activity, int messageId) {
        return showProgressDialog(activity, activity.getString(messageId));
    }

    /**
     * Display a progress dialog
     */
    @Nullable
    public static ProgressDialog showProgressDialog(@NonNull Fragment fragment, @NonNull String message) {
        Activity activity = fragment.getActivity();
        return activity != null ? showProgressDialog(activity, message) : null;
    }

    /**
     * Display a progress dialog
     */
    @Nullable
    public static ProgressDialog showProgressDialog(@NonNull Fragment fragment, int messageId) {
        Activity activity = fragment.getActivity();
        return activity != null ? showProgressDialog(activity, messageId) : null;
    }

    /**
     * Get the declared field with the specified name from the specified class
     */
    @NonNull
    private static Field getDeclaredFieldRecursive(@NonNull Class<?> clazz, @SuppressWarnings("SameParameterValue") @NonNull String fieldName) throws NoSuchFieldException {
        Field field = null;

        Class<?> currentClazz = clazz;
        while (field == null && currentClazz != null) {
            try {
                field = currentClazz.getDeclaredField(fieldName);
            } catch (NoSuchFieldException ignored) {
            }

            if (field == null) {
                currentClazz = currentClazz.getSuperclass();
            }
        }

        if (field == null) {
            throw new NoSuchFieldException(String.format("No such field by name '%s' in class '%s' and its parent class", fieldName, clazz.getName()));
        } else {
            return field;
        }
    }
}
