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

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Map;
import java.util.Set;

@SuppressWarnings({"JavaDoc"})
public class Preferencex {

    private Preferencex() {
    }


    /**
     * Get SharedPreferences with the specified name
     */
    @NonNull
    public static SharedPreferences getPreferences(@NonNull Context context, @NonNull String name, int mode) {
        return context.getSharedPreferences(name, mode);
    }

    /**
     * Get SharedPreferences with the specified name
     */
    @NonNull
    public static SharedPreferences getPreferences(@NonNull Context context, @NonNull String name) {
        return context.getSharedPreferences(name, Context.MODE_PRIVATE);
    }

    /**
     * Get the default SharedPreferences
     */
    @NonNull
    public static SharedPreferences getDefaultPreferences(@NonNull Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }


    /**
     * Set an int value in the specified name preferences editor
     *
     * @param name  The name of the preferences editor.
     * @param key   The name of the preference to modify.
     * @param value The new value for the preference.
     */
    public static void putIntTo(@NonNull Context context, @NonNull String name, @NonNull String key, int value) {
        getPreferences(context, name).edit().putInt(key, value).apply();
    }

    /**
     * Set an int value in the default preferences editor
     *
     * @param key   The name of the preference to modify.
     * @param value The new value for the preference.
     */
    public static void putInt(@NonNull Context context, @NonNull String key, int value) {
        getDefaultPreferences(context).edit().putInt(key, value).apply();
    }

    /**
     * Batch set int value in the specified name preferences editor
     *
     * @param name    The name of the preferences editor.
     * @param dataMap Key-value pair Map.
     */
    public static void putIntsTo(@NonNull Context context, @NonNull String name, @NonNull Map<String, Integer> dataMap) {
        SharedPreferences.Editor editor = getPreferences(context, name).edit();
        for (String key : dataMap.keySet()) {
            Integer value = dataMap.get(key);
            if (value != null) {
                editor.putInt(key, value);
            } else {
                editor.remove(key);
            }
        }
        editor.apply();
    }

    /**
     * Batch set int value in the default preferences editor
     *
     * @param dataMap Key-value pair Map.
     */
    public static void putInts(@NonNull Context context, @NonNull Map<String, Integer> dataMap) {
        SharedPreferences.Editor editor = getDefaultPreferences(context).edit();
        for (String key : dataMap.keySet()) {
            Integer value = dataMap.get(key);
            if (value != null) {
                editor.putInt(key, value);
            } else {
                editor.remove(key);
            }
        }
        editor.apply();
    }


    /**
     * Set an long value in the specified name preferences editor
     *
     * @param name  The name of the preferences editor.
     * @param key   The name of the preference to modify.
     * @param value The new value for the preference.
     */
    public static void putLongTo(@NonNull Context context, @NonNull String name, @NonNull String key, long value) {
        getPreferences(context, name).edit().putLong(key, value).apply();
    }

    /**
     * Set an long value in the default preferences editor
     *
     * @param key   The name of the preference to modify.
     * @param value The new value for the preference.
     */
    public static void putLong(@NonNull Context context, @NonNull String key, long value) {
        getDefaultPreferences(context).edit().putLong(key, value).apply();
    }

    /**
     * Batch set long value in the specified name preferences editor
     *
     * @param name    The name of the preferences editor.
     * @param dataMap Key-value pair Map.
     */
    public static void putLongsTo(@NonNull Context context, @NonNull String name, @NonNull Map<String, Long> dataMap) {
        SharedPreferences.Editor editor = getPreferences(context, name).edit();
        for (String key : dataMap.keySet()) {
            Long value = dataMap.get(key);
            if (value != null) {
                editor.putLong(key, value);
            } else {
                editor.remove(key);
            }
        }
        editor.apply();
    }

    /**
     * Batch set long value in the default preferences editor
     *
     * @param dataMap Key-value pair Map.
     */
    public static void putLongs(@NonNull Context context, @NonNull Map<String, Long> dataMap) {
        SharedPreferences.Editor editor = getDefaultPreferences(context).edit();
        for (String key : dataMap.keySet()) {
            Long value = dataMap.get(key);
            if (value != null) {
                editor.putLong(key, value);
            } else {
                editor.remove(key);
            }
        }
        editor.apply();
    }


    /**
     * Set an boolean value in the specified name preferences editor
     *
     * @param name  The name of the preferences editor.
     * @param key   The name of the preference to modify.
     * @param value The new value for the preference.
     */
    public static void putBooleanTo(@NonNull Context context, @NonNull String name, @NonNull String key, boolean value) {
        getPreferences(context, name).edit().putBoolean(key, value).apply();
    }

    /**
     * Set an boolean value in the default preferences editor
     *
     * @param key   The name of the preference to modify.
     * @param value The new value for the preference.
     */
    public static void putBoolean(@NonNull Context context, @NonNull String key, boolean value) {
        getDefaultPreferences(context).edit().putBoolean(key, value).apply();
    }

    /**
     * Batch set boolean value in the specified name preferences editor
     *
     * @param name    The name of the preferences editor.
     * @param dataMap Key-value pair Map.
     */
    public static void putBooleansTo(@NonNull Context context, @NonNull String name, @NonNull Map<String, Boolean> dataMap) {
        SharedPreferences.Editor editor = getPreferences(context, name).edit();
        for (String key : dataMap.keySet()) {
            Boolean value = dataMap.get(key);
            if (value != null) {
                editor.putBoolean(key, value);
            } else {
                editor.remove(key);
            }
        }
        editor.apply();
    }

    /**
     * Batch set boolean value in the default preferences editor
     *
     * @param dataMap Key-value pair Map.
     */
    public static void putBooleans(@NonNull Context context, @NonNull Map<String, Boolean> dataMap) {
        SharedPreferences.Editor editor = getDefaultPreferences(context).edit();
        for (String key : dataMap.keySet()) {
            Boolean value = dataMap.get(key);
            if (value != null) {
                editor.putBoolean(key, value);
            } else {
                editor.remove(key);
            }
        }
        editor.apply();
    }


    /**
     * Set an float value in the specified name preferences editor
     *
     * @param name  The name of the preferences editor.
     * @param key   The name of the preference to modify.
     * @param value The new value for the preference.
     */
    public static void putFloatTo(@NonNull Context context, @NonNull String name, @NonNull String key, float value) {
        getPreferences(context, name).edit().putFloat(key, value).apply();
    }

    /**
     * Set an float value in the default preferences editor
     *
     * @param key   The name of the preference to modify.
     * @param value The new value for the preference.
     */
    public static void putFloat(@NonNull Context context, @NonNull String key, float value) {
        getDefaultPreferences(context).edit().putFloat(key, value).apply();
    }

    /**
     * Batch set float value in the specified name preferences editor
     *
     * @param name    The name of the preferences editor.
     * @param dataMap Key-value pair Map.
     */
    public static void putFloatsTo(@NonNull Context context, @NonNull String name, @NonNull Map<String, Float> dataMap) {
        SharedPreferences.Editor editor = getPreferences(context, name).edit();
        for (String key : dataMap.keySet()) {
            Float value = dataMap.get(key);
            if (value != null) {
                editor.putFloat(key, value);
            } else {
                editor.remove(key);
            }
        }
        editor.apply();
    }

    /**
     * Batch set float value in the default preferences editor
     *
     * @param dataMap Key-value pair Map.
     */
    public static void putFloats(@NonNull Context context, @NonNull Map<String, Float> dataMap) {
        SharedPreferences.Editor editor = getDefaultPreferences(context).edit();
        for (String key : dataMap.keySet()) {
            Float value = dataMap.get(key);
            if (value != null) {
                editor.putFloat(key, value);
            } else {
                editor.remove(key);
            }
        }
        editor.apply();
    }


    /**
     * Set an string value in the specified name preferences editor
     *
     * @param name  The name of the preferences editor.
     * @param key   The name of the preference to modify.
     * @param value The new value for the preference.
     */
    public static void putStringTo(@NonNull Context context, @NonNull String name, @NonNull String key, @Nullable String value) {
        getPreferences(context, name).edit().putString(key, value).apply();
    }

    /**
     * Set an string value in the default preferences editor
     *
     * @param key   The name of the preference to modify.
     * @param value The new value for the preference.
     */
    public static void putString(@NonNull Context context, @NonNull String key, @Nullable String value) {
        getDefaultPreferences(context).edit().putString(key, value).apply();
    }

    /**
     * Batch set string value in the specified name preferences editor
     *
     * @param name    The name of the preferences editor.
     * @param dataMap Key-value pair Map.
     */
    public static void putStringsTo(@NonNull Context context, @NonNull String name, @NonNull Map<String, String> dataMap) {
        SharedPreferences.Editor editor = getPreferences(context, name).edit();
        for (String key : dataMap.keySet()) {
            String value = dataMap.get(key);
            if (value != null) {
                editor.putString(key, value);
            } else {
                editor.remove(key);
            }
        }
        editor.apply();
    }

    /**
     * Batch set string value in the default preferences editor
     *
     * @param dataMap Key-value pair Map.
     */
    public static void putStrings(@NonNull Context context, @NonNull Map<String, String> dataMap) {
        SharedPreferences.Editor editor = getDefaultPreferences(context).edit();
        for (String key : dataMap.keySet()) {
            String value = dataMap.get(key);
            if (value != null) {
                editor.putString(key, value);
            } else {
                editor.remove(key);
            }
        }
        editor.apply();
    }


    /**
     * Set an Set<String> value in the specified name preferences editor
     *
     * @param name  The name of the preferences editor.
     * @param key   The name of the preference to modify.
     * @param value The new value for the preference.
     */
    public static void putStringSetTo(@NonNull Context context, @NonNull String name, @NonNull String key, @Nullable Set<String> value) {
        getPreferences(context, name).edit().putStringSet(key, value).apply();
    }

    /**
     * Set an Set<String> value in the default preferences editor
     *
     * @param key   The name of the preference to modify.
     * @param value The new value for the preference.
     */
    public static void putStringSet(@NonNull Context context, @NonNull String key, @Nullable Set<String> value) {
        getDefaultPreferences(context).edit().putStringSet(key, value).apply();
    }

    /**
     * Batch set Set<String> value in the specified name preferences editor
     *
     * @param name    The name of the preferences editor.
     * @param dataMap Key-value pair Map.
     */
    public static void putStringSetsTo(@NonNull Context context, @NonNull String name, @NonNull Map<String, Set<String>> dataMap) {
        SharedPreferences.Editor editor = getPreferences(context, name).edit();
        for (String key : dataMap.keySet()) {
            Set<String> value = dataMap.get(key);
            if (value != null) {
                editor.putStringSet(key, value);
            } else {
                editor.remove(key);
            }
        }
        editor.apply();
    }

    /**
     * Batch set Set<String> value in the default preferences editor
     *
     * @param dataMap Key-value pair Map.
     */
    public static void putStringSets(@NonNull Context context, @NonNull Map<String, Set<String>> dataMap) {
        SharedPreferences.Editor editor = getDefaultPreferences(context).edit();
        for (String key : dataMap.keySet()) {
            Set<String> value = dataMap.get(key);
            if (value != null) {
                editor.putStringSet(key, value);
            } else {
                editor.remove(key);
            }
        }
        editor.apply();
    }


    /**
     * Retrieve an int value from the specified name preferences.
     *
     * @param name     The name of the preferences editor.
     * @param key      The name of the preference to retrieve.
     * @param defValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defValue.  Throws
     * ClassCastException if there is a preference with this name that is not
     * an int.
     * @throws ClassCastException
     */
    public static int getIntFrom(@NonNull Context context, @NonNull String name, @NonNull String key, int defValue) {
        return getPreferences(context, name).getInt(key, defValue);
    }

    /**
     * Retrieve an int value from the default preferences.
     *
     * @param key      The name of the preference to retrieve.
     * @param defValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defValue.  Throws
     * ClassCastException if there is a preference with this name that is not
     * an int.
     * @throws ClassCastException
     */
    public static int getInt(@NonNull Context context, @NonNull String key, int defValue) {
        return getDefaultPreferences(context).getInt(key, defValue);
    }

    /**
     * Retrieve an long value from the specified name preferences.
     *
     * @param name     The name of the preferences editor.
     * @param key      The name of the preference to retrieve.
     * @param defValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defValue.  Throws
     * ClassCastException if there is a preference with this name that is not
     * an int.
     * @throws ClassCastException
     */
    public static long getLongFrom(@NonNull Context context, @NonNull String name, @NonNull String key, long defValue) {
        return getPreferences(context, name).getLong(key, defValue);
    }

    /**
     * Retrieve an long value from the default preferences.
     *
     * @param key      The name of the preference to retrieve.
     * @param defValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defValue.  Throws
     * ClassCastException if there is a preference with this name that is not
     * an int.
     * @throws ClassCastException
     */
    public static long getLong(@NonNull Context context, @NonNull String key, long defValue) {
        return getDefaultPreferences(context).getLong(key, defValue);
    }


    /**
     * Retrieve an boolean value from the specified name preferences.
     *
     * @param name     The name of the preferences editor.
     * @param key      The name of the preference to retrieve.
     * @param defValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defValue.  Throws
     * ClassCastException if there is a preference with this name that is not
     * an int.
     * @throws ClassCastException
     */
    public static boolean getBooleanFrom(@NonNull Context context, @NonNull String name, @NonNull String key, boolean defValue) {
        return getPreferences(context, name).getBoolean(key, defValue);
    }

    /**
     * Retrieve an boolean value from the default preferences.
     *
     * @param key      The name of the preference to retrieve.
     * @param defValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defValue.  Throws
     * ClassCastException if there is a preference with this name that is not
     * an int.
     * @throws ClassCastException
     */
    public static boolean getBoolean(@NonNull Context context, @NonNull String key, boolean defValue) {
        return getDefaultPreferences(context).getBoolean(key, defValue);
    }


    /**
     * Retrieve an float value from the specified name preferences.
     *
     * @param name     The name of the preferences editor.
     * @param key      The name of the preference to retrieve.
     * @param defValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defValue.  Throws
     * ClassCastException if there is a preference with this name that is not
     * an int.
     * @throws ClassCastException
     */
    public static float getFloatFrom(@NonNull Context context, @NonNull String name, @NonNull String key, float defValue) {
        return getPreferences(context, name).getFloat(key, defValue);
    }

    /**
     * Retrieve an float value from the default preferences.
     *
     * @param key      The name of the preference to retrieve.
     * @param defValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defValue.  Throws
     * ClassCastException if there is a preference with this name that is not
     * an int.
     * @throws ClassCastException
     */
    public static float getFloat(@NonNull Context context, @NonNull String key, float defValue) {
        return getDefaultPreferences(context).getFloat(key, defValue);
    }


    /**
     * Retrieve an string value from the specified name preferences.
     *
     * @param name     The name of the preferences editor.
     * @param key      The name of the preference to retrieve.
     * @param defValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defValue.  Throws
     * ClassCastException if there is a preference with this name that is not
     * an int.
     * @throws ClassCastException
     */
    @NonNull
    public static String getStringFrom(@NonNull Context context, @NonNull String name, @NonNull String key, @NonNull String defValue) {
        return getPreferences(context, name).getString(key, defValue);
    }

    /**
     * Retrieve an string value from the specified name preferences.
     *
     * @param name The name of the preferences editor.
     * @param key  The name of the preference to retrieve.
     * @return Returns the preference value if it exists, or null.  Throws
     * ClassCastException if there is a preference with this name that is not
     * an int.
     * @throws ClassCastException
     */
    @Nullable
    public static String getStringOrNullFrom(@NonNull Context context, @NonNull String name, @NonNull String key) {
        return getPreferences(context, name).getString(key, null);
    }

    /**
     * Retrieve an string value from the default preferences.
     *
     * @param key      The name of the preference to retrieve.
     * @param defValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defValue.  Throws
     * ClassCastException if there is a preference with this name that is not
     * an int.
     * @throws ClassCastException
     */
    @NonNull
    public static String getString(@NonNull Context context, @NonNull String key, @NonNull String defValue) {
        return getDefaultPreferences(context).getString(key, defValue);
    }

    /**
     * Retrieve an string value from the default preferences.
     *
     * @param key The name of the preference to retrieve.
     * @return Returns the preference value if it exists, or null.  Throws
     * ClassCastException if there is a preference with this name that is not
     * an int.
     * @throws ClassCastException
     */
    @Nullable
    public static String getStringOrNull(@NonNull Context context, @NonNull String key) {
        return getDefaultPreferences(context).getString(key, null);
    }


    /**
     * Retrieve an Set<String> value from the specified name preferences.
     *
     * @param name     The name of the preferences editor.
     * @param key      The name of the preference to retrieve.
     * @param defValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defValue.  Throws
     * ClassCastException if there is a preference with this name that is not
     * an int.
     * @throws ClassCastException
     */
    @NonNull
    public static Set<String> getStringSetFrom(@NonNull Context context, @NonNull String name, @NonNull String key, @NonNull Set<String> defValue) {
        return getPreferences(context, name).getStringSet(key, defValue);
    }

    /**
     * Retrieve an Set<String> value from the specified name preferences.
     *
     * @param name The name of the preferences editor.
     * @param key  The name of the preference to retrieve.
     * @return Returns the preference value if it exists, or null.  Throws
     * ClassCastException if there is a preference with this name that is not
     * an int.
     * @throws ClassCastException
     */
    @Nullable
    public static Set<String> getStringSetOrNullFrom(@NonNull Context context, @NonNull String name, @NonNull String key) {
        return getPreferences(context, name).getStringSet(key, null);
    }

    /**
     * Retrieve an Set<String> value from the default preferences.
     *
     * @param key      The name of the preference to retrieve.
     * @param defValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defValue.  Throws
     * ClassCastException if there is a preference with this name that is not
     * an int.
     * @throws ClassCastException
     */
    @NonNull
    public static Set<String> getStringSet(@NonNull Context context, @NonNull String key, @NonNull Set<String> defValue) {
        return getDefaultPreferences(context).getStringSet(key, defValue);
    }

    /**
     * Retrieve an Set<String> value from the default preferences.
     *
     * @param key The name of the preference to retrieve.
     * @return Returns the preference value if it exists, or null.  Throws
     * ClassCastException if there is a preference with this name that is not
     * an int.
     * @throws ClassCastException
     */
    @Nullable
    public static Set<String> getStringSetOrNull(@NonNull Context context, @NonNull String key) {
        return getDefaultPreferences(context).getStringSet(key, null);
    }


    /**
     * Retrieve all values from the specified name preferences.
     *
     * <p>Note that you <em>must not</em> modify the collection returned
     * by this method, or alter any of its contents.  The consistency of your
     * stored data is not guaranteed if you do.
     *
     * @param name The name of the preferences editor.
     * @return Returns a map containing a list of pairs key/value representing
     * the preferences.
     * @throws NullPointerException
     */
    @NonNull
    public static Map<String, ?> getAllFrom(@NonNull Context context, @NonNull String name) {
        return getPreferences(context, name).getAll();
    }

    /**
     * Retrieve all values from the default preferences.
     *
     * <p>Note that you <em>must not</em> modify the collection returned
     * by this method, or alter any of its contents.  The consistency of your
     * stored data is not guaranteed if you do.
     *
     * @return Returns a map containing a list of pairs key/value representing
     * the preferences.
     * @throws NullPointerException
     */
    @NonNull
    public static Map<String, ?> getAll(@NonNull Context context) {
        return getDefaultPreferences(context).getAll();
    }


    /**
     * Batch mark in the specified name editor that a preference value should be removed.
     *
     * @param name The name of the preferences editor.
     * @param keys The name array of the preference to remove.
     * @return Returns a reference to the same Editor object, so you can
     * chain put calls together.
     */
    public static void removeFrom(@NonNull Context context, @NonNull String name, @NonNull String... keys) {
        SharedPreferences.Editor editor = getPreferences(context, name).edit();
        for (String key : keys) {
            editor.remove(key);
        }
        editor.apply();
    }

    /**
     * Batch mark in the default editor that a preference value should be removed.
     *
     * @param keys The name array of the preference to remove.
     * @return Returns a reference to the same Editor object, so you can
     * chain put calls together.
     */
    public static void remove(@NonNull Context context, String... keys) {
        SharedPreferences.Editor editor = getDefaultPreferences(context).edit();
        for (String key : keys) {
            editor.remove(key);
        }
        editor.apply();
    }


    /**
     * Checks whether the default preferences contains a preference.
     *
     * @param key The name of the preference to check.
     * @return Returns true if the preference exists in the preferences,
     * otherwise false.
     */
    public static boolean contains(@NonNull Context context, @NonNull String key) {
        return getDefaultPreferences(context).contains(key);
    }

    /**
     * Check if the default preference contains any of the specified preference arrays.
     *
     * @param keys The name array of the preference to check.
     * @return Returns true if the preference exists in the preferences,
     * otherwise false.
     */
    public static boolean containsAny(@NonNull Context context, @NonNull String... keys) {
        final SharedPreferences preferences = getDefaultPreferences(context);
        for (String key : keys) {
            if (preferences.contains(key)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if the default preference contains all of the specified preference arrays.
     *
     * @param keys The name array of the preference to check.
     * @return Returns true if the preference exists in the preferences,
     * otherwise false.
     */
    public static boolean containsAll(@NonNull Context context, @NonNull String... keys) {
        final SharedPreferences preferences = getDefaultPreferences(context);
        for (String key : keys) {
            if (!preferences.contains(key)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks whether the specified name preferences contains a preference.
     *
     * @param name The name of the preferences editor.
     * @param key  The name of the preference to check.
     * @return Returns true if the preference exists in the preferences,
     * otherwise false.
     */
    public static boolean containsFrom(@NonNull Context context, @NonNull String name, @NonNull String key) {
        return getPreferences(context, name).contains(key);
    }

    /**
     * Check if the specified name preference contains any of the specified preference arrays.
     *
     * @param name The name of the preferences editor.
     * @param keys The name array of the preference to check.
     * @return Returns true if the preference exists in the preferences,
     * otherwise false.
     */
    public static boolean containsAnyFrom(@NonNull Context context, @NonNull String name, @NonNull String... keys) {
        final SharedPreferences preferences = getPreferences(context, name);
        for (String key : keys) {
            if (preferences.contains(key)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if the specified name preference contains all of the specified preference arrays.
     *
     * @param name The name of the preferences editor.
     * @param keys The name array of the preference to check.
     * @return Returns true if the preference exists in the preferences,
     * otherwise false.
     */
    public static boolean containsAllFrom(@NonNull Context context, @NonNull String name, @NonNull String... keys) {
        final SharedPreferences preferences = getPreferences(context, name);
        for (String key : keys) {
            if (!preferences.contains(key)) {
                return false;
            }
        }
        return true;
    }


    /**
     * Return true if the default preferences is empty
     */
    public static boolean isEmpty(@NonNull Context context) {
        return getAll(context).isEmpty();
    }

    /**
     * Return true if the specified name preferences is empty
     *
     * @param name The name of the preferences editor.
     */
    public static boolean isEmptyFrom(@NonNull Context context, @NonNull String name) {
        return getAllFrom(context, name).isEmpty();
    }


    /**
     * Clear the specified name preferences
     *
     * @param name The name of the preferences editor.
     */
    public static void clearFrom(@NonNull Context context, @NonNull String name) {
        getPreferences(context, name).edit().clear().apply();
    }

    /**
     * Clear the default preferences
     */
    public static void clear(@NonNull Context context) {
        getDefaultPreferences(context).edit().clear().apply();
    }


    /**
     * Registers a callback to be invoked when a change happens to a preference in the default preferences.
     *
     * <p class="caution"><strong>Caution:</strong> The preference manager does
     * not currently store a strong reference to the listener. You must store a
     * strong reference to the listener, or it will be susceptible to garbage
     * collection. We recommend you keep a reference to the listener in the
     * instance data of an object that will exist as long as you need the
     * listener.</p>
     *
     * @param listener The callback that will run.
     * @see #unregisterOnChangeListener
     */
    public static void registerOnChangeListener(@NonNull Context context, @NonNull SharedPreferences.OnSharedPreferenceChangeListener listener) {
        getDefaultPreferences(context).registerOnSharedPreferenceChangeListener(listener);
    }

    /**
     * Registers a callback to be invoked when a change happens to a preference in the specified name preferences.
     *
     * <p class="caution"><strong>Caution:</strong> The preference manager does
     * not currently store a strong reference to the listener. You must store a
     * strong reference to the listener, or it will be susceptible to garbage
     * collection. We recommend you keep a reference to the listener in the
     * instance data of an object that will exist as long as you need the
     * listener.</p>
     *
     * @param name     The name of the preferences editor.
     * @param listener The callback that will run.
     * @see #unregisterOnChangeListener
     */
    public static void registerOnChangeListenerTo(@NonNull Context context, @NonNull String name, @NonNull SharedPreferences.OnSharedPreferenceChangeListener listener) {
        getPreferences(context, name).registerOnSharedPreferenceChangeListener(listener);
    }

    /**
     * Unregisters a previous callback in the default preferences.
     *
     * @param listener The callback that should be unregistered.
     * @see #registerOnChangeListenerTo
     */
    public static void unregisterOnChangeListener(@NonNull Context context, @NonNull SharedPreferences.OnSharedPreferenceChangeListener listener) {
        getDefaultPreferences(context).unregisterOnSharedPreferenceChangeListener(listener);
    }

    /**
     * Unregisters a previous callback in the specified name preferences.
     *
     * @param name     The name of the preferences editor.
     * @param listener The callback that should be unregistered.
     * @see #registerOnChangeListenerTo
     */
    public static void unregisterOnChangeListenerFrom(@NonNull Context context, @NonNull String name, @NonNull SharedPreferences.OnSharedPreferenceChangeListener listener) {
        getPreferences(context, name).unregisterOnSharedPreferenceChangeListener(listener);
    }
}
