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

package com.github.panpf.tools4a.permission;

import android.content.Context;
import android.content.pm.PackageManager;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import java.util.LinkedList;
import java.util.List;

/**
 * Permission related tool methods
 */
public class Permissionx {

    private Permissionx() {
    }

    /**
     * Return true if all specified permissions have been granted, false otherwise
     */
    public static boolean isGrantPermissions(@NonNull Context context, @NonNull String... permissions) {
        for (String permission : permissions) {
            if (ActivityCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_DENIED) {
                return false;
            }
        }
        return true;
    }

    /**
     * Filter all denied permissions
     */
    @NonNull
    public static String[] filterDeniedPermissions(@NonNull final Context context, @NonNull String... permissions) {
        List<String> deniedPermissionList = new LinkedList<>();
        //noinspection ConstantConditions
        if (permissions != null && permissions.length > 0) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_DENIED) {
                    deniedPermissionList.add(permission);
                }
            }
        }
        return deniedPermissionList.toArray(new String[0]);
    }
}
