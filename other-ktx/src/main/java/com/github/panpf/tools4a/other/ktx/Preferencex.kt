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

@file:Suppress("NOTHING_TO_INLINE")

package com.github.panpf.tools4a.other.ktx

import android.content.Context
import android.content.SharedPreferences
import com.github.panpf.tools4a.other.Preferencex


///**
// * Get SharedPreferences with the specified name
// */
//inline fun Context.getSharedPreferences(name: String, mod: Int): SharedPreferences = Preferencex.getPreferences(this, name, mod)

/**
 * Get SharedPreferences with the specified name
 */
inline fun Context.getSharedPreferences(name: String): SharedPreferences = Preferencex.getPreferences(this, name)

/**
 * Get the default SharedPreferences
 */
inline fun Context.getDefaultSharedPreferences(): SharedPreferences = Preferencex.getDefaultPreferences(this)


/**
 * Set an int value in the specified name preferences editor
 *
 * @param name  The name of the preferences editor.
 * @param key   The name of the preference to modify.
 * @param value The new value for the preference.
 */
inline fun Context.putIntPreferenceTo(name: String, key: String, value: Int) = Preferencex.putIntTo(this, name, key, value)

/**
 * Set an int value in the default preferences editor
 *
 * @param key   The name of the preference to modify.
 * @param value The new value for the preference.
 */
inline fun Context.putIntPreference(key: String, value: Int) = Preferencex.putInt(this, key, value)

/**
 * Batch set int value in the specified name preferences editor
 *
 * @param name    The name of the preferences editor.
 * @param dataMap Key-value pair Map.
 */
inline fun Context.putIntsPreferenceTo(name: String, dataMap: Map<String, Int>) = Preferencex.putIntsTo(this, name, dataMap)

/**
 * Batch set int value in the default preferences editor
 *
 * @param dataMap Key-value pair Map.
 */
inline fun Context.putIntsPreference(dataMap: Map<String, Int>) = Preferencex.putInts(this, dataMap)


/**
 * Set an long value in the specified name preferences editor
 *
 * @param name  The name of the preferences editor.
 * @param key   The name of the preference to modify.
 * @param value The new value for the preference.
 */
inline fun Context.putLongPreferenceTo(name: String, key: String, value: Long) = Preferencex.putLongTo(this, name, key, value)

/**
 * Set an long value in the default preferences editor
 *
 * @param key   The name of the preference to modify.
 * @param value The new value for the preference.
 */
inline fun Context.putLongPreference(key: String, value: Long) = Preferencex.putLong(this, key, value)

/**
 * Batch set long value in the specified name preferences editor
 *
 * @param name    The name of the preferences editor.
 * @param dataMap Key-value pair Map.
 */
inline fun Context.putLongsPreferenceTo(name: String, dataMap: Map<String, Long>) = Preferencex.putLongsTo(this, name, dataMap)

/**
 * Batch set long value in the default preferences editor
 *
 * @param dataMap Key-value pair Map.
 */
inline fun Context.putLongsPreference(dataMap: Map<String, Long>) = Preferencex.putLongs(this, dataMap)


/**
 * Set an boolean value in the specified name preferences editor
 *
 * @param name  The name of the preferences editor.
 * @param key   The name of the preference to modify.
 * @param value The new value for the preference.
 */
inline fun Context.putBooleanPreferenceTo(name: String, key: String, value: Boolean) = Preferencex.putBooleanTo(this, name, key, value)

/**
 * Set an boolean value in the default preferences editor
 *
 * @param key   The name of the preference to modify.
 * @param value The new value for the preference.
 */
inline fun Context.putBooleanPreference(key: String, value: Boolean) = Preferencex.putBoolean(this, key, value)

/**
 * Batch set boolean value in the specified name preferences editor
 *
 * @param name    The name of the preferences editor.
 * @param dataMap Key-value pair Map.
 */
inline fun Context.putBooleansPreferenceTo(name: String, dataMap: Map<String, Boolean>) = Preferencex.putBooleansTo(this, name, dataMap)

/**
 * Batch set boolean value in the default preferences editor
 *
 * @param dataMap Key-value pair Map.
 */
inline fun Context.putBooleansPreference(dataMap: Map<String, Boolean>) = Preferencex.putBooleans(this, dataMap)


/**
 * Set an float value in the specified name preferences editor
 *
 * @param name  The name of the preferences editor.
 * @param key   The name of the preference to modify.
 * @param value The new value for the preference.
 */
inline fun Context.putFloatPreferenceTo(name: String, key: String, value: Float) = Preferencex.putFloatTo(this, name, key, value)

/**
 * Set an float value in the default preferences editor
 *
 * @param key   The name of the preference to modify.
 * @param value The new value for the preference.
 */
inline fun Context.putFloatPreference(key: String, value: Float) = Preferencex.putFloat(this, key, value)

/**
 * Batch set float value in the specified name preferences editor
 *
 * @param name    The name of the preferences editor.
 * @param dataMap Key-value pair Map.
 */
inline fun Context.putFloatsPreferenceTo(name: String, dataMap: Map<String, Float>) = Preferencex.putFloatsTo(this, name, dataMap)

/**
 * Batch set float value in the default preferences editor
 *
 * @param dataMap Key-value pair Map.
 */
inline fun Context.putFloatsPreference(dataMap: Map<String, Float>) = Preferencex.putFloats(this, dataMap)


/**
 * Set an string value in the specified name preferences editor
 *
 * @param name  The name of the preferences editor.
 * @param key   The name of the preference to modify.
 * @param value The new value for the preference.
 */
inline fun Context.putStringPreferenceTo(name: String, key: String, value: String?) = Preferencex.putStringTo(this, name, key, value)

/**
 * Set an string value in the default preferences editor
 *
 * @param key   The name of the preference to modify.
 * @param value The new value for the preference.
 */
inline fun Context.putStringPreference(key: String, value: String?) = Preferencex.putString(this, key, value)

/**
 * Batch set string value in the specified name preferences editor
 *
 * @param name    The name of the preferences editor.
 * @param dataMap Key-value pair Map.
 */
inline fun Context.putStringsPreferenceTo(name: String, dataMap: Map<String, String>) = Preferencex.putStringsTo(this, name, dataMap)

/**
 * Batch set string value in the default preferences editor
 *
 * @param dataMap Key-value pair Map.
 */
inline fun Context.putStringsPreference(dataMap: Map<String, String>) = Preferencex.putStrings(this, dataMap)


/**
 * Set an Set<String> value in the specified name preferences editor
 *
 * @param name  The name of the preferences editor.
 * @param key   The name of the preference to modify.
 * @param value The new value for the preference.
 */
inline fun Context.putStringSetPreferenceTo(name: String, key: String, value: Set<String>?) = Preferencex.putStringSetTo(this, name, key, value)

/**
 * Set an Set<String> value in the default preferences editor
 *
 * @param key   The name of the preference to modify.
 * @param value The new value for the preference.
 */
inline fun Context.putStringSetPreference(key: String, value: Set<String>?) = Preferencex.putStringSet(this, key, value)

/**
 * Batch set Set<String> value in the specified name preferences editor
 *
 * @param name    The name of the preferences editor.
 * @param dataMap Key-value pair Map.
 */
inline fun Context.putStringSetsPreferenceTo(name: String, dataMap: Map<String, Set<String>>) = Preferencex.putStringSetsTo(this, name, dataMap)

/**
 * Batch set Set<String> value in the default preferences editor
 *
 * @param dataMap Key-value pair Map.
 */
inline fun Context.putStringSetsPreference(dataMap: Map<String, Set<String>>) = Preferencex.putStringSets(this, dataMap)


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
inline fun Context.getIntPreferenceFrom(name: String, key: String, defValue: Int): Int = Preferencex.getIntFrom(this, name, key, defValue)

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
inline fun Context.getIntPreference(key: String, defValue: Int): Int = Preferencex.getInt(this, key, defValue)


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
inline fun Context.getLongPreferenceFrom(name: String, key: String, defValue: Long): Long = Preferencex.getLongFrom(this, name, key, defValue)

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
inline fun Context.getLongPreference(key: String, defValue: Long): Long = Preferencex.getLong(this, key, defValue)


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
inline fun Context.getBooleanPreferenceFrom(name: String, key: String, defValue: Boolean): Boolean = Preferencex.getBooleanFrom(this, name, key, defValue)

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
inline fun Context.getBooleanPreference(key: String, defValue: Boolean): Boolean = Preferencex.getBoolean(this, key, defValue)


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
inline fun Context.getFloatPreferenceFrom(name: String, key: String, defValue: Float): Float = Preferencex.getFloatFrom(this, name, key, defValue)

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
inline fun Context.getFloatPreference(key: String, defValue: Float): Float = Preferencex.getFloat(this, key, defValue)


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
inline fun Context.getStringPreferenceFrom(name: String, key: String, defValue: String): String = Preferencex.getStringFrom(this, name, key, defValue)

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
inline fun Context.getStringPreferenceOrNullFrom(name: String, key: String): String? = Preferencex.getStringOrNullFrom(this, name, key)

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
inline fun Context.getStringPreference(key: String, defValue: String): String = Preferencex.getString(this, key, defValue)

/**
 * Retrieve an string value from the default preferences.
 *
 * @param key The name of the preference to retrieve.
 * @return Returns the preference value if it exists, or null.  Throws
 * ClassCastException if there is a preference with this name that is not
 * an int.
 * @throws ClassCastException
 */
inline fun Context.getStringPreferenceOrNull(key: String): String? = Preferencex.getStringOrNull(this, key)


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
inline fun Context.getStringSetPreferenceFrom(name: String, key: String, defValue: Set<String>): Set<String> = Preferencex.getStringSetFrom(this, name, key, defValue)

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
inline fun Context.getStringSetPreferenceOrNullFrom(name: String, key: String): Set<String>? = Preferencex.getStringSetOrNullFrom(this, name, key)

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
inline fun Context.getStringSetPreference(key: String, defValue: Set<String>): Set<String> = Preferencex.getStringSet(this, key, defValue)

/**
 * Retrieve an Set<String> value from the default preferences.
 *
 * @param key The name of the preference to retrieve.
 * @return Returns the preference value if it exists, or null.  Throws
 * ClassCastException if there is a preference with this name that is not
 * an int.
 * @throws ClassCastException
 */
inline fun Context.getStringSetPreferenceOrNull(key: String): Set<String>? = Preferencex.getStringSetOrNull(this, key)


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
inline fun Context.getAllPreferenceFrom(name: String): Map<String, *> = Preferencex.getAllFrom(this, name)

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
inline fun Context.getAllPreference(): Map<String, *> = Preferencex.getAll(this)


/**
 * Batch mark in the specified name editor that a preference value should be removed.
 *
 * @param name The name of the preferences editor.
 * @param keys The name array of the preference to remove.
 * @return Returns a reference to the same Editor object, so you can
 * chain put calls together.
 */
inline fun Context.removePreferenceFrom(name: String, vararg keys: String) = Preferencex.removeFrom(this, name, *keys)

/**
 * Batch mark in the default editor that a preference value should be removed.
 *
 * @param keys The name array of the preference to remove.
 * @return Returns a reference to the same Editor object, so you can
 * chain put calls together.
 */
inline fun Context.removePreference(vararg keys: String) = Preferencex.remove(this, *keys)


/**
 * Checks whether the default preferences contains a preference.
 *
 * @param key The name of the preference to check.
 * @return Returns true if the preference exists in the preferences,
 * otherwise false.
 */
inline fun Context.containsPreference(key: String): Boolean = Preferencex.contains(this, key)

/**
 * Check if the default preference contains any of the specified preference arrays.
 *
 * @param keys The name array of the preference to check.
 * @return Returns true if the preference exists in the preferences,
 * otherwise false.
 */
inline fun Context.containsPreferenceAny(vararg keys: String): Boolean = Preferencex.containsAny(this, *keys)

/**
 * Check if the default preference contains all of the specified preference arrays.
 *
 * @param keys The name array of the preference to check.
 * @return Returns true if the preference exists in the preferences,
 * otherwise false.
 */
inline fun Context.containsPreferenceAll(vararg keys: String): Boolean = Preferencex.containsAll(this, *keys)

/**
 * Checks whether the specified name preferences contains a preference.
 *
 * @param name The name of the preferences editor.
 * @param key  The name of the preference to check.
 * @return Returns true if the preference exists in the preferences,
 * otherwise false.
 */
inline fun Context.containsPreferenceFrom(name: String, key: String): Boolean = Preferencex.containsFrom(this, name, key)

/**
 * Check if the specified name preference contains any of the specified preference arrays.
 *
 * @param name The name of the preferences editor.
 * @param keys The name array of the preference to check.
 * @return Returns true if the preference exists in the preferences,
 * otherwise false.
 */
inline fun Context.containsPreferenceAnyFrom(name: String, vararg keys: String): Boolean = Preferencex.containsAnyFrom(this, name, *keys)

/**
 * Check if the specified name preference contains all of the specified preference arrays.
 *
 * @param name The name of the preferences editor.
 * @param keys The name array of the preference to check.
 * @return Returns true if the preference exists in the preferences,
 * otherwise false.
 */
inline fun Context.containsPreferenceAllFrom(name: String, vararg keys: String): Boolean = Preferencex.containsAllFrom(this, name, *keys)


/**
 * Return true if the default preferences is empty
 */
inline fun Context.isEmptyPreferencesFrom(name: String) = Preferencex.isEmptyFrom(this, name)

/**
 * Return true if the specified name preferences is empty
 *
 * @param name The name of the preferences editor.
 */
inline fun Context.isEmptyPreferences() = Preferencex.isEmpty(this)


/**
 * Clear the specified name preferences
 *
 * @param name The name of the preferences editor.
 */
inline fun Context.clearPreferencesFrom(name: String) = Preferencex.clearFrom(this, name)

/**
 * Clear the default preferences
 */
inline fun Context.clearPreferences() = Preferencex.clear(this)


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
inline fun Context.registerOnPreferenceChangeListener(listener: SharedPreferences.OnSharedPreferenceChangeListener) = Preferencex.registerOnChangeListener(this, listener)

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
inline fun Context.registerOnPreferenceChangeListenerTo(name: String, listener: SharedPreferences.OnSharedPreferenceChangeListener) = Preferencex.registerOnChangeListenerTo(this, name, listener)

/**
 * Unregisters a previous callback in the default preferences.
 *
 * @param listener The callback that should be unregistered.
 * @see #registerOnChangeListenerTo
 */
inline fun Context.unregisterOnPreferenceChangeListener(listener: SharedPreferences.OnSharedPreferenceChangeListener) = Preferencex.unregisterOnChangeListener(this, listener)

/**
 * Unregisters a previous callback in the specified name preferences.
 *
 * @param name     The name of the preferences editor.
 * @param listener The callback that should be unregistered.
 * @see #registerOnChangeListenerTo
 */
inline fun Context.unregisterOnPreferenceChangeListenerFrom(name: String, listener: SharedPreferences.OnSharedPreferenceChangeListener) = Preferencex.unregisterOnChangeListenerFrom(this, name, listener)