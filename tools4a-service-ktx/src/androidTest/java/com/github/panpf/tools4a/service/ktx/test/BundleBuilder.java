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

package com.github.panpf.tools4a.service.ktx.test;

import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.util.Size;
import android.util.SizeF;
import android.util.SparseArray;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.io.Serializable;
import java.util.ArrayList;

public class BundleBuilder {

    @Nullable
    private Bundle bundle;

    @NonNull
    private Bundle getBundle() {
        if (bundle == null) {
            synchronized (BundleBuilder.class) {
                if (bundle == null) {
                    bundle = new Bundle();
                }
            }
        }
        return bundle;
    }

    private void cleanBundle() {
        synchronized (BundleBuilder.class) {
            bundle = null;
        }
    }

    /**
     * Removes any entry with the given key from the mapping of this Bundle.
     *
     * @param key a String key
     */
    @NonNull
    public BundleBuilder remove(String key) {
        getBundle().remove(key);
        return this;
    }

    /**
     * Inserts all mappings from the given PersistableBundle into this BaseBundle.
     *
     * @param otherBundle a PersistableBundle
     */
    @NonNull
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public BundleBuilder putAll(PersistableBundle otherBundle) {
        getBundle().putAll(otherBundle);
        return this;
    }

    /**
     * Inserts all mappings from the given Bundle into this Bundle.
     *
     * @param otherBundle a Bundle
     */
    @NonNull
    public BundleBuilder putAll(Bundle otherBundle) {
        getBundle().putAll(otherBundle);
        return this;
    }

    /**
     * Inserts a Bundle value into the mapping of this Bundle, replacing
     * any existing value for the given key.  Either key or value may be null.
     *
     * @param key   a String, or null
     * @param value a Bundle object, or null
     */
    @NonNull
    public BundleBuilder putBundle(@Nullable String key, @Nullable Bundle value) {
        getBundle().putBundle(key, value);
        return this;
    }

    /**
     * Inserts a Boolean value into the mapping of this Bundle, replacing
     * any existing value for the given key.  Either key or value may be null.
     *
     * @param key   a String, or null
     * @param value a boolean
     */
    @NonNull
    public BundleBuilder putBoolean(@Nullable String key, boolean value) {
        getBundle().putBoolean(key, value);
        return this;
    }

    /**
     * Inserts a boolean array value into the mapping of this Bundle, replacing
     * any existing value for the given key.  Either key or value may be null.
     *
     * @param key   a String, or null
     * @param value a boolean array object, or null
     */
    @NonNull
    public BundleBuilder putBooleanArray(@Nullable String key, @Nullable boolean[] value) {
        getBundle().putBooleanArray(key, value);
        return this;
    }

    /**
     * Inserts a byte value into the mapping of this Bundle, replacing
     * any existing value for the given key.
     *
     * @param key   a String, or null
     * @param value a byte
     */
    @NonNull
    public BundleBuilder putByte(@Nullable String key, byte value) {
        getBundle().putByte(key, value);
        return this;
    }

    /**
     * Inserts a char value into the mapping of this Bundle, replacing
     * any existing value for the given key.
     *
     * @param key   a String, or null
     * @param value a char
     */
    @NonNull
    public BundleBuilder putChar(@Nullable String key, char value) {
        getBundle().putChar(key, value);
        return this;
    }

    /**
     * Inserts a short value into the mapping of this Bundle, replacing
     * any existing value for the given key.
     *
     * @param key   a String, or null
     * @param value a short
     */
    @NonNull
    public BundleBuilder putShort(@Nullable String key, short value) {
        getBundle().putShort(key, value);
        return this;
    }

    /**
     * Inserts an int value into the mapping of this Bundle, replacing
     * any existing value for the given key.
     *
     * @param key   a String, or null
     * @param value an int
     */
    @NonNull
    public BundleBuilder putInt(@Nullable String key, int value) {
        getBundle().putInt(key, value);
        return this;
    }

    /**
     * Inserts a long value into the mapping of this Bundle, replacing
     * any existing value for the given key.
     *
     * @param key   a String, or null
     * @param value a long
     */
    @NonNull
    public BundleBuilder putLong(@Nullable String key, long value) {
        getBundle().putLong(key, value);
        return this;
    }

    /**
     * Inserts a float value into the mapping of this Bundle, replacing
     * any existing value for the given key.
     *
     * @param key   a String, or null
     * @param value a float
     */
    @NonNull
    public BundleBuilder putFloat(@Nullable String key, float value) {
        getBundle().putFloat(key, value);
        return this;
    }

    /**
     * Inserts a double value into the mapping of this Bundle, replacing
     * any existing value for the given key.
     *
     * @param key   a String, or null
     * @param value a double
     */
    @NonNull
    public BundleBuilder putDouble(@Nullable String key, double value) {
        getBundle().putDouble(key, value);
        return this;
    }

    /**
     * Inserts a String value into the mapping of this Bundle, replacing
     * any existing value for the given key.  Either key or value may be null.
     *
     * @param key   a String, or null
     * @param value a String, or null
     */
    @NonNull
    public BundleBuilder putString(@Nullable String key, @Nullable String value) {
        getBundle().putString(key, value);
        return this;
    }

    /**
     * Inserts a CharSequence value into the mapping of this Bundle, replacing
     * any existing value for the given key.  Either key or value may be null.
     *
     * @param key   a String, or null
     * @param value a CharSequence, or null
     */
    @NonNull
    public BundleBuilder putCharSequence(@Nullable String key, @Nullable CharSequence value) {
        getBundle().putCharSequence(key, value);
        return this;
    }

    /**
     * Inserts an ArrayList<Integer> value into the mapping of this Bundle, replacing
     * any existing value for the given key.  Either key or value may be null.
     *
     * @param key   a String, or null
     * @param value an ArrayList<Integer> object, or null
     */
    @NonNull
    public BundleBuilder putIntegerArrayList(@Nullable String key, @Nullable ArrayList<Integer> value) {
        getBundle().putIntegerArrayList(key, value);
        return this;
    }

    /**
     * Inserts an ArrayList<String> value into the mapping of this Bundle, replacing
     * any existing value for the given key.  Either key or value may be null.
     *
     * @param key   a String, or null
     * @param value an ArrayList<String> object, or null
     */
    @NonNull
    public BundleBuilder putStringArrayList(@Nullable String key, @Nullable ArrayList<String> value) {
        getBundle().putStringArrayList(key, value);
        return this;
    }

    /**
     * Inserts an ArrayList<CharSequence> value into the mapping of this Bundle, replacing
     * any existing value for the given key.  Either key or value may be null.
     *
     * @param key   a String, or null
     * @param value an ArrayList<CharSequence> object, or null
     */
    @NonNull
    public BundleBuilder putCharSequenceArrayList(@Nullable String key, @Nullable ArrayList<CharSequence> value) {
        getBundle().putCharSequenceArrayList(key, value);
        return this;
    }

    /**
     * Inserts a byte array value into the mapping of this Bundle, replacing
     * any existing value for the given key.  Either key or value may be null.
     *
     * @param key   a String, or null
     * @param value a byte array object, or null
     */
    @NonNull
    public BundleBuilder putByteArray(@Nullable String key, @Nullable byte[] value) {
        getBundle().putByteArray(key, value);
        return this;
    }

    /**
     * Inserts a short array value into the mapping of this Bundle, replacing
     * any existing value for the given key.  Either key or value may be null.
     *
     * @param key   a String, or null
     * @param value a short array object, or null
     */
    @NonNull
    public BundleBuilder putShortArray(@Nullable String key, @Nullable short[] value) {
        getBundle().putShortArray(key, value);
        return this;
    }

    /**
     * Inserts a char array value into the mapping of this Bundle, replacing
     * any existing value for the given key.  Either key or value may be null.
     *
     * @param key   a String, or null
     * @param value a char array object, or null
     */
    @NonNull
    public BundleBuilder putCharArray(@Nullable String key, @Nullable char[] value) {
        getBundle().putCharArray(key, value);
        return this;
    }

    /**
     * Inserts an int array value into the mapping of this Bundle, replacing
     * any existing value for the given key.  Either key or value may be null.
     *
     * @param key   a String, or null
     * @param value an int array object, or null
     */
    @NonNull
    public BundleBuilder putIntArray(@Nullable String key, @Nullable int[] value) {
        getBundle().putIntArray(key, value);
        return this;
    }

    /**
     * Inserts a long array value into the mapping of this Bundle, replacing
     * any existing value for the given key.  Either key or value may be null.
     *
     * @param key   a String, or null
     * @param value a long array object, or null
     */
    @NonNull
    public BundleBuilder putLongArray(@Nullable String key, @Nullable long[] value) {
        getBundle().putLongArray(key, value);
        return this;
    }

    /**
     * Inserts a float array value into the mapping of this Bundle, replacing
     * any existing value for the given key.  Either key or value may be null.
     *
     * @param key   a String, or null
     * @param value a float array object, or null
     */
    @NonNull
    public BundleBuilder putFloatArray(@Nullable String key, @Nullable float[] value) {
        getBundle().putFloatArray(key, value);
        return this;
    }

    /**
     * Inserts a double array value into the mapping of this Bundle, replacing
     * any existing value for the given key.  Either key or value may be null.
     *
     * @param key   a String, or null
     * @param value a double array object, or null
     */
    @NonNull
    public BundleBuilder putDoubleArray(@Nullable String key, @Nullable double[] value) {
        getBundle().putDoubleArray(key, value);
        return this;
    }

    /**
     * Inserts a String array value into the mapping of this Bundle, replacing
     * any existing value for the given key.  Either key or value may be null.
     *
     * @param key   a String, or null
     * @param value a String array object, or null
     */
    @NonNull
    public BundleBuilder putStringArray(@Nullable String key, @Nullable String[] value) {
        getBundle().putStringArray(key, value);
        return this;
    }

    /**
     * Inserts a CharSequence array value into the mapping of this Bundle, replacing
     * any existing value for the given key.  Either key or value may be null.
     *
     * @param key   a String, or null
     * @param value a CharSequence array object, or null
     */
    @NonNull
    public BundleBuilder putCharSequenceArray(@Nullable String key, @Nullable CharSequence[] value) {
        getBundle().putCharSequenceArray(key, value);
        return this;
    }

    /**
     * Inserts a Size value into the mapping of this Bundle, replacing
     * any existing value for the given key.  Either key or value may be null.
     *
     * @param key   a String, or null
     * @param value a Size object, or null
     */
    @NonNull
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public BundleBuilder putSize(@Nullable String key, @Nullable Size value) {
        getBundle().putSize(key, value);
        return this;
    }

    /**
     * Inserts a SizeF value into the mapping of this Bundle, replacing
     * any existing value for the given key.  Either key or value may be null.
     *
     * @param key   a String, or null
     * @param value a SizeF object, or null
     */
    @NonNull
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public BundleBuilder putSizeF(@Nullable String key, @Nullable SizeF value) {
        getBundle().putSizeF(key, value);
        return this;
    }

    /**
     * Inserts a Serializable value into the mapping of this Bundle, replacing
     * any existing value for the given key.  Either key or value may be null.
     *
     * @param key   a String, or null
     * @param value a Serializable object, or null
     */
    @NonNull
    public BundleBuilder putSerializable(@Nullable String key, @Nullable Serializable value) {
        getBundle().putSerializable(key, value);
        return this;
    }

    /**
     * Inserts a Parcelable value into the mapping of this Bundle, replacing
     * any existing value for the given key.  Either key or value may be null.
     *
     * @param key   a String, or null
     * @param value a Parcelable object, or null
     */
    @NonNull
    public BundleBuilder putParcelable(@Nullable String key, @Nullable Parcelable value) {
        getBundle().putParcelable(key, value);
        return this;
    }

    /**
     * Inserts an array of Parcelable values into the mapping of this Bundle,
     * replacing any existing value for the given key.  Either key or value may
     * be null.
     *
     * @param key   a String, or null
     * @param value an array of Parcelable objects, or null
     */
    @NonNull
    public BundleBuilder putParcelableArray(@Nullable String key, @Nullable Parcelable[] value) {
        getBundle().putParcelableArray(key, value);
        return this;
    }

    /**
     * Inserts a List of Parcelable values into the mapping of this Bundle,
     * replacing any existing value for the given key.  Either key or value may
     * be null.
     *
     * @param key   a String, or null
     * @param value an ArrayList of Parcelable objects, or null
     */
    @NonNull
    public BundleBuilder putParcelableArrayList(@Nullable String key, @Nullable ArrayList<? extends Parcelable> value) {
        getBundle().putParcelableArrayList(key, value);
        return this;
    }

    /**
     * Inserts a SparseArray of Parcelable values into the mapping of this
     * Bundle, replacing any existing value for the given key.  Either key
     * or value may be null.
     *
     * @param key   a String, or null
     * @param value a SparseArray of Parcelable objects, or null
     */
    @NonNull
    public BundleBuilder putSparseParcelableArray(@Nullable String key, @Nullable SparseArray<? extends Parcelable> value) {
        getBundle().putSparseParcelableArray(key, value);
        return this;
    }

    /**
     * Changes the ClassLoader this Bundle uses when instantiating objects.
     *
     * @param loader An explicit ClassLoader to use when instantiating objects
     *               inside of the Bundle.
     */
    @NonNull
    public BundleBuilder setClassLoader(ClassLoader loader) {
        getBundle().setClassLoader(loader);
        return this;
    }

    @NonNull
    public Bundle build() {
        Bundle bundle = getBundle();
        cleanBundle();
        return bundle;
    }
}
