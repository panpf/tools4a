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

package com.github.panpf.tools4a.systemproperties;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.lang.reflect.Method;

public class SystemPropertiesx {

    private SystemPropertiesx() {
    }

    /**
     * Get system properties based on the specified key
     */
    @NonNull
    public static String get(@NonNull String key) {
        String value = GetStringMethodHolder.get(key);
        return value != null ? value : "";
    }

    /**
     * Get system properties based on the specified key
     */
    @NonNull
    public static String getOr(@NonNull String key, @NonNull String defaultValue) {
        String value = GetStringDefMethodHolder.get(key, defaultValue);
        return value != null ? value : "";
    }


    /**
     * Get the system property according to the specified key and convert it to int
     */
    public static int getIntOr(@NonNull String key, int defaultValue) {
        return GetIntMethodHolder.getInt(key, defaultValue);
    }

    /**
     * Get the system property according to the specified key and convert it to long
     */
    public static long getLongOr(@NonNull String key, long defaultValue) {
        return GetLongMethodHolder.getLong(key, defaultValue);
    }

    /**
     * Get the system property according to the specified key and convert it to boolean
     */
    public static boolean getBooleanOr(@NonNull String key, boolean defaultValue) {
        return GetBooleanMethodHolder.getBoolean(key, defaultValue);
    }

    /**
     * Modify system properties based on the specified key
     */
    public static void set(@NonNull String key, @NonNull String value) {
        SetMethodHolder.set(key, value);
    }

    /**
     * Add system property change callback
     */
    public static void addChangeCallback(@NonNull Runnable runnable) {
        AddChangedCallbackMethodHolder.addChangeCallback(runnable);
    }

    /**
     * Callback all system property change callbacks
     */
    public static void callChangeCallbacks() {
        CallChangeCallbacksMethodHolder.callChangeCallbacks();
    }

    @SuppressLint("PrivateApi")
    private static class GetStringMethodHolder {

        @Nullable
        private static final Method METHOD;

        static {
            /*
             * 为何不采用读取 /root/build.prop 文件的方式？
             * 因为在 MIUI 上没有读取这个文件的权限，在华为 EMUI 上读取的属性不全
             */
            Method method = null;
            try {
                method = Class.forName("android.os.SystemProperties").getDeclaredMethod("get", String.class);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            METHOD = method;
        }

        @Nullable
        static String get(@NonNull String key) {
            Method method = METHOD;
            if (method != null) {
                method.setAccessible(true);
                try {
                    return (String) method.invoke(null, key);
                } catch (Exception e) {
                    throw new IllegalStateException(e);
                }
            } else {
                return null;
            }
        }
    }

    @SuppressLint("PrivateApi")
    private static class GetStringDefMethodHolder {

        @Nullable
        private static final Method METHOD;

        static {
            Method method = null;
            try {
                method = Class.forName("android.os.SystemProperties").getDeclaredMethod("get", String.class, String.class);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            METHOD = method;
        }

        @Nullable
        static String get(@NonNull String key, @NonNull String defaultValue) {
            Method method = METHOD;
            if (method != null) {
                method.setAccessible(true);
                try {
                    return (String) method.invoke(null, key, defaultValue);
                } catch (Exception e) {
                    throw new IllegalStateException(e);
                }
            } else {
                return null;
            }
        }
    }

    @SuppressLint("PrivateApi")
    private static class GetIntMethodHolder {
        @Nullable
        private static final Method METHOD;

        static {
            Method method = null;
            try {
                method = Class.forName("android.os.SystemProperties").getDeclaredMethod("getInt", String.class, int.class);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            METHOD = method;
        }

        static int getInt(@NonNull String key, int defaultValue) {
            Method method = METHOD;
            if (method != null) {
                method.setAccessible(true);
                try {
                    return (int) method.invoke(null, key, defaultValue);
                } catch (Exception e) {
                    throw new IllegalStateException(e);
                }
            } else {
                return defaultValue;
            }
        }
    }

    @SuppressLint("PrivateApi")
    private static class GetLongMethodHolder {
        @Nullable
        private static final Method METHOD;

        static {
            Method method = null;
            try {
                method = Class.forName("android.os.SystemProperties").getDeclaredMethod("getLong", String.class, long.class);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            METHOD = method;
        }

        static long getLong(@NonNull String key, long defaultValue) {
            Method method = METHOD;
            if (method != null) {
                method.setAccessible(true);
                try {
                    return (long) method.invoke(null, key, defaultValue);
                } catch (Exception e) {
                    throw new IllegalStateException(e);
                }
            } else {
                return defaultValue;
            }
        }
    }

    @SuppressLint("PrivateApi")
    private static class GetBooleanMethodHolder {
        @Nullable
        private static final Method METHOD;

        static {
            Method method = null;
            try {
                method = Class.forName("android.os.SystemProperties").getDeclaredMethod("getBoolean", String.class, boolean.class);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            METHOD = method;
        }

        static boolean getBoolean(@NonNull String key, boolean defaultValue) {
            Method method = METHOD;
            if (method != null) {
                method.setAccessible(true);
                try {
                    return (boolean) method.invoke(null, key, defaultValue);
                } catch (Exception e) {
                    throw new IllegalStateException(e);
                }
            } else {
                return defaultValue;
            }
        }
    }

    @SuppressLint({"PrivateApi", "DiscouragedPrivateApi"})
    private static class SetMethodHolder {

        @Nullable
        private static final Method METHOD;

        static {
            Method method = null;
            try {
                method = Class.forName("android.os.SystemProperties").getDeclaredMethod("set", String.class, String.class);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            METHOD = method;
        }

        static void set(@NonNull String key, @NonNull String value) {
            Method method = METHOD;
            if (method != null) {
                method.setAccessible(true);
                try {
                    method.invoke(null, key, value);
                } catch (Exception e) {
                    throw new IllegalStateException(e);
                }
            }
        }
    }

    @SuppressLint({"PrivateApi", "DiscouragedPrivateApi"})
    private static class AddChangedCallbackMethodHolder {

        @Nullable
        private static final Method METHOD;

        static {
            Method method = null;
            try {
                method = Class.forName("android.os.SystemProperties").getDeclaredMethod("addChangeCallback", Runnable.class);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            METHOD = method;
        }

        static void addChangeCallback(@NonNull Runnable runnable) {
            Method method = METHOD;
            if (method != null) {
                method.setAccessible(true);
                try {
                    method.invoke(null, runnable);
                } catch (Exception e) {
                    throw new IllegalStateException(e);
                }
            }
        }
    }

    @SuppressLint({"SoonBlockedPrivateApi", "PrivateApi"})
    private static class CallChangeCallbacksMethodHolder {

        @Nullable
        private static final Method METHOD;

        static {
            Method method = null;
            try {
                method = Class.forName("android.os.SystemProperties").getDeclaredMethod("callChangeCallbacks");
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            METHOD = method;
        }

        static void callChangeCallbacks() {
            Method method = METHOD;
            if (method != null) {
                method.setAccessible(true);
                try {
                    method.invoke(null);
                } catch (Exception e) {
                    throw new IllegalStateException(e);
                }
            }
        }
    }
}
