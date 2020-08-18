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

package com.github.panpf.tools4a.service;

import android.accessibilityservice.AccessibilityService;
import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Servicex {

    private Servicex() {
    }

    /**
     * Return true if the service specifying Class is running
     */
    public static boolean isRunning(@NonNull Context context, @NonNull Class<? extends Service> serviceClass, final @Nullable String packageName) {
        try {
            ArrayList<ActivityManager.RunningServiceInfo> runningServiceInfos = (ArrayList<ActivityManager.RunningServiceInfo>)
                    ((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE)).getRunningServices(Integer.MAX_VALUE);
            if (runningServiceInfos != null) {
                final String serviceClassName = serviceClass.getName();
                for (ActivityManager.RunningServiceInfo serviceInfo : runningServiceInfos) {
                    if (serviceInfo.service.getClassName().equals(serviceClassName)
                            && (packageName == null || packageName.equals(serviceInfo.service.getPackageName()))) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Return true if the service specifying Class is running
     */
    public static boolean isRunning(@NonNull Context context, @NonNull Class<? extends Service> serviceClass) {
        return isRunning(context, serviceClass, null);
    }

    /**
     * Start Service
     */
    public static void start(@NonNull Context context, @NonNull Class<? extends Service> serviceClass, @Nullable Bundle extras) {
        Intent intent = new Intent(context, serviceClass);
        if (extras != null) {
            intent.putExtras(extras);
        }
        context.startService(intent);
    }

    /**
     * Start Service
     */
    public static void start(@NonNull Context context, @NonNull Class<? extends Service> serviceClass) {
        start(context, serviceClass, null);
    }

    /**
     * Stop Service
     */
    public static void stop(@NonNull Context context, @NonNull Class<? extends Service> serviceClass) {
        context.stopService(new Intent(context, serviceClass));
    }

    /**
     * Return true if the specified AccessibilityService service has been enabled
     */
    public static boolean isAccessibilityServiceEnabled(
            @NonNull Context context, @NonNull Class<? extends AccessibilityService> service, final @Nullable String packageName) {
        try {
            String services = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES);
            if (!TextUtils.isEmpty(services)) {
                TextUtils.SimpleStringSplitter split = new TextUtils.SimpleStringSplitter(':');
                split.setString(services);
                final String targetServiceInfo = (packageName != null ? packageName : "") + "/" + service.getName();
                while (split.hasNext()) {
                    final String serviceInfo = split.next();
                    if (packageName != null && serviceInfo.equalsIgnoreCase(targetServiceInfo)) {
                        return true;
                    } else if (packageName == null && serviceInfo.endsWith(targetServiceInfo)) {
                        return true;
                    }
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Return true if the specified AccessibilityService service has been enabled
     */
    public static boolean isAccessibilityServiceEnabled(@NonNull Context context, @NonNull Class<? extends AccessibilityService> service) {
        return isAccessibilityServiceEnabled(context, service, null);
    }
}