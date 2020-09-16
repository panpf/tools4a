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

package com.github.panpf.tools4a.args.ktx

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import android.os.Parcelable
import android.util.Size
import android.util.SizeF
import android.util.SparseArray
import androidx.annotation.RequiresApi
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.github.panpf.tools4a.args.Argsx
import java.io.Serializable
import java.util.*


/* ************************************* Activity Intent Args ***************************************** */


inline fun Activity.readByteArgOrThrow(argName: String): Byte = Argsx.readByteArgOrThrow(this, argName)

inline fun Activity.readByteArgOr(argName: String, defaultValue: Byte): Byte = Argsx.readByteArgOr(this, argName, defaultValue)

inline fun Activity.readByteArgOrNull(argName: String): Byte? = Argsx.readByteArgOrNull(this, argName)


inline fun Activity.readByteArrayArgOrThrow(argName: String): ByteArray = Argsx.readByteArrayArgOrThrow(this, argName)

inline fun Activity.readByteArrayArgOr(argName: String, defaultValue: ByteArray): ByteArray = Argsx.readByteArrayArgOr(this, argName, defaultValue)

inline fun Activity.readByteArrayArgOrNull(argName: String): ByteArray? = Argsx.readByteArrayArgOrNull(this, argName)


inline fun Activity.readShortArgOrThrow(argName: String): Short = Argsx.readShortArgOrThrow(this, argName)

inline fun Activity.readShortArgOr(argName: String, defaultValue: Short): Short = Argsx.readShortArgOr(this, argName, defaultValue)

inline fun Activity.readShortArgOrNull(argName: String): Short? = Argsx.readShortArgOrNull(this, argName)


inline fun Activity.readShortArrayArgOrThrow(argName: String): ShortArray = Argsx.readShortArrayArgOrThrow(this, argName)

inline fun Activity.readShortArrayArgOr(argName: String, defaultValue: ShortArray): ShortArray = Argsx.readShortArrayArgOr(this, argName, defaultValue)

inline fun Activity.readShortArrayArgOrNull(argName: String): ShortArray? = Argsx.readShortArrayArgOrNull(this, argName)


inline fun Activity.readIntArgOrThrow(argName: String): Int = Argsx.readIntArgOrThrow(this, argName)

inline fun Activity.readIntArgOr(argName: String, defaultValue: Int): Int = Argsx.readIntArgOr(this, argName, defaultValue)

inline fun Activity.readIntArgOrNull(argName: String): Int? = Argsx.readIntArgOrNull(this, argName)


inline fun Activity.readIntArrayArgOrThrow(argName: String): IntArray = Argsx.readIntArrayArgOrThrow(this, argName)

inline fun Activity.readIntArrayArgOr(argName: String, defaultValue: IntArray): IntArray = Argsx.readIntArrayArgOr(this, argName, defaultValue)

inline fun Activity.readIntArrayArgOrNull(argName: String): IntArray? = Argsx.readIntArrayArgOrNull(this, argName)


inline fun Activity.readIntArrayListArgOrThrow(argName: String): ArrayList<Int> = Argsx.readIntArrayListArgOrThrow(this, argName)

inline fun Activity.readIntArrayListArgOr(argName: String, defaultValue: ArrayList<Int>): ArrayList<Int> = Argsx.readIntArrayListArgOr(this, argName, defaultValue)

inline fun Activity.readIntArrayListArgOrNull(argName: String): ArrayList<Int>? = Argsx.readIntArrayListArgOrNull(this, argName)


inline fun Activity.readLongArgOrThrow(argName: String): Long = Argsx.readLongArgOrThrow(this, argName)

inline fun Activity.readLongArgOr(argName: String, defaultValue: Long): Long = Argsx.readLongArgOr(this, argName, defaultValue)

inline fun Activity.readLongArgOrNull(argName: String): Long? = Argsx.readLongArgOrNull(this, argName)


inline fun Activity.readLongArrayArgOrThrow(argName: String): LongArray = Argsx.readLongArrayArgOrThrow(this, argName)

inline fun Activity.readLongArrayArgOr(argName: String, defaultValue: LongArray): LongArray = Argsx.readLongArrayArgOr(this, argName, defaultValue)

inline fun Activity.readLongArrayArgOrNull(argName: String): LongArray? = Argsx.readLongArrayArgOrNull(this, argName)


inline fun Activity.readFloatArgOrThrow(argName: String): Float = Argsx.readFloatArgOrThrow(this, argName)

inline fun Activity.readFloatArgOr(argName: String, defaultValue: Float): Float = Argsx.readFloatArgOr(this, argName, defaultValue)

inline fun Activity.readFloatArgOrNull(argName: String): Float? = Argsx.readFloatArgOrNull(this, argName)


inline fun Activity.readFloatArrayArgOrThrow(argName: String): FloatArray = Argsx.readFloatArrayArgOrThrow(this, argName)

inline fun Activity.readFloatArrayArgOr(argName: String, defaultValue: FloatArray): FloatArray = Argsx.readFloatArrayArgOr(this, argName, defaultValue)

inline fun Activity.readFloatArrayArgOrNull(argName: String): FloatArray? = Argsx.readFloatArrayArgOrNull(this, argName)


inline fun Activity.readDoubleArgOrThrow(argName: String): Double = Argsx.readDoubleArgOrThrow(this, argName)

inline fun Activity.readDoubleArgOr(argName: String, defaultValue: Double): Double = Argsx.readDoubleArgOr(this, argName, defaultValue)

inline fun Activity.readDoubleArgOrNull(argName: String): Double? = Argsx.readDoubleArgOrNull(this, argName)


inline fun Activity.readDoubleArrayArgOrThrow(argName: String): DoubleArray = Argsx.readDoubleArrayArgOrThrow(this, argName)

inline fun Activity.readDoubleArrayArgOr(argName: String, defaultValue: DoubleArray): DoubleArray = Argsx.readDoubleArrayArgOr(this, argName, defaultValue)

inline fun Activity.readDoubleArrayArgOrNull(argName: String): DoubleArray? = Argsx.readDoubleArrayArgOrNull(this, argName)


inline fun Activity.readBooleanArgOrThrow(argName: String): Boolean = Argsx.readBooleanArgOrThrow(this, argName)

inline fun Activity.readBooleanArgOr(argName: String, defaultValue: Boolean): Boolean = Argsx.readBooleanArgOr(this, argName, defaultValue)

inline fun Activity.readBooleanArgOrNull(argName: String): Boolean? = Argsx.readBooleanArgOrNull(this, argName)


inline fun Activity.readBooleanArrayArgOrThrow(argName: String): BooleanArray = Argsx.readBooleanArrayArgOrThrow(this, argName)

inline fun Activity.readBooleanArrayArgOr(argName: String, defaultValue: BooleanArray): BooleanArray = Argsx.readBooleanArrayArgOr(this, argName, defaultValue)

inline fun Activity.readBooleanArrayArgOrNull(argName: String): BooleanArray? = Argsx.readBooleanArrayArgOrNull(this, argName)


inline fun Activity.readCharArgOrThrow(argName: String): Char = Argsx.readCharArgOrThrow(this, argName)

inline fun Activity.readCharArgOr(argName: String, defaultValue: Char): Char = Argsx.readCharArgOr(this, argName, defaultValue)

inline fun Activity.readCharArgOrNull(argName: String): Char? = Argsx.readCharArgOrNull(this, argName)


inline fun Activity.readCharArrayArgOrThrow(argName: String): CharArray = Argsx.readCharArrayArgOrThrow(this, argName)

inline fun Activity.readCharArrayArgOr(argName: String, defaultValue: CharArray): CharArray = Argsx.readCharArrayArgOr(this, argName, defaultValue)

inline fun Activity.readCharArrayArgOrNull(argName: String): CharArray? = Argsx.readCharArrayArgOrNull(this, argName)


inline fun Activity.readCharSequenceArgOrThrow(argName: String): CharSequence = Argsx.readCharSequenceArgOrThrow(this, argName)

inline fun Activity.readCharSequenceArgOr(argName: String, defaultValue: CharSequence): CharSequence = Argsx.readCharSequenceArgOr(this, argName, defaultValue)

inline fun Activity.readCharSequenceArgOrNull(argName: String): CharSequence? = Argsx.readCharSequenceArgOrNull(this, argName)


inline fun Activity.readCharSequenceArrayArgOrThrow(argName: String): Array<CharSequence> = Argsx.readCharSequenceArrayArgOrThrow(this, argName)

inline fun Activity.readCharSequenceArrayArgOr(argName: String, defaultValue: Array<CharSequence>): Array<CharSequence> = Argsx.readCharSequenceArrayArgOr(this, argName, defaultValue)

inline fun Activity.readCharSequenceArrayArgOrNull(argName: String): Array<CharSequence>? = Argsx.readCharSequenceArrayArgOrNull(this, argName)


inline fun Activity.readCharSequenceArrayListArgOrThrow(argName: String): ArrayList<CharSequence> = Argsx.readCharSequenceArrayListArgOrThrow(this, argName)

inline fun Activity.readCharSequenceArrayListArgOr(argName: String, defaultValue: ArrayList<CharSequence>): ArrayList<CharSequence> = Argsx.readCharSequenceArrayListArgOr(this, argName, defaultValue)

inline fun Activity.readCharSequenceArrayListArgOrNull(argName: String): ArrayList<CharSequence>? = Argsx.readCharSequenceArrayListArgOrNull(this, argName)


inline fun Activity.readStringArgOrThrow(argName: String): String = Argsx.readStringArgOrThrow(this, argName)

inline fun Activity.readStringArgOr(argName: String, defaultValue: String): String = Argsx.readStringArgOr(this, argName, defaultValue)

inline fun Activity.readStringArgOrNull(argName: String): String? = Argsx.readStringArgOrNull(this, argName)


inline fun Activity.readStringArrayArgOrThrow(argName: String): Array<String> = Argsx.readStringArrayArgOrThrow(this, argName)

inline fun Activity.readStringArrayArgOr(argName: String, defaultValue: Array<String>): Array<String> = Argsx.readStringArrayArgOr(this, argName, defaultValue)

inline fun Activity.readStringArrayArgOrNull(argName: String): Array<String>? = Argsx.readStringArrayArgOrNull(this, argName)


inline fun Activity.readStringArrayListArgOrThrow(argName: String): ArrayList<String> = Argsx.readStringArrayListArgOrThrow(this, argName)

inline fun Activity.readStringArrayListArgOr(argName: String, defaultValue: ArrayList<String>): ArrayList<String> = Argsx.readStringArrayListArgOr(this, argName, defaultValue)

inline fun Activity.readStringArrayListArgOrNull(argName: String): ArrayList<String>? = Argsx.readStringArrayListArgOrNull(this, argName)


inline fun <V : Parcelable> Activity.readParcelableArgOrThrow(argName: String): V = Argsx.readParcelableArgOrThrow(this, argName)

inline fun <V : Parcelable> Activity.readParcelableArgOr(argName: String, defaultValue: V): V = Argsx.readParcelableArgOr(this, argName, defaultValue)

inline fun <V : Parcelable> Activity.readParcelableArgOrNull(argName: String): V? = Argsx.readParcelableArgOrNull(this, argName)


inline fun <V : Parcelable> Activity.readParcelableArrayArgOrThrow(argName: String): Array<V> = Argsx.readParcelableArrayArgOrThrow(this, argName)

inline fun <V : Parcelable> Activity.readParcelableArrayArgOr(argName: String, defaultValue: Array<V>): Array<V> = Argsx.readParcelableArrayArgOr(this, argName, defaultValue)

inline fun <V : Parcelable> Activity.readParcelableArrayArgOrNull(argName: String): Array<V>? = Argsx.readParcelableArrayArgOrNull(this, argName)


inline fun <V : Parcelable> Activity.readParcelableArrayListArgOrThrow(argName: String): ArrayList<V> = Argsx.readParcelableArrayListArgOrThrow(this, argName)

inline fun <V : Parcelable> Activity.readParcelableArrayListArgOr(argName: String, defaultValue: ArrayList<V>): ArrayList<V> = Argsx.readParcelableArrayListArgOr(this, argName, defaultValue)

inline fun <V : Parcelable> Activity.readParcelableArrayListArgOrNull(argName: String): ArrayList<V>? = Argsx.readParcelableArrayListArgOrNull(this, argName)


inline fun <V : Serializable> Activity.readSerializableArgOrThrow(argName: String): V = Argsx.readSerializableArgOrThrow(this, argName)

inline fun <V : Serializable> Activity.readSerializableArgOr(argName: String, defaultValue: V): V = Argsx.readSerializableArgOr(this, argName, defaultValue)

inline fun <V : Serializable> Activity.readSerializableArgOrNull(argName: String): V? = Argsx.readSerializableArgOrNull(this, argName)


inline fun Activity.readBundleArgOrThrow(argName: String): Bundle = Argsx.readBundleArgOrThrow(this, argName)

inline fun Activity.readBundleArgOr(argName: String, defaultValue: Bundle): Bundle = Argsx.readBundleArgOr(this, argName, defaultValue)

inline fun Activity.readBundleArgOrNull(argName: String): Bundle? = Argsx.readBundleArgOrNull(this, argName)


/* ************************************* Activity Uri Args ***************************************** */


inline fun Activity.readByteUriArgOrThrow(argName: String): Byte = Argsx.readByteUriArgOrThrow(this, argName)

inline fun Activity.readByteUriArgOr(argName: String, defaultValue: Byte): Byte = Argsx.readByteUriArgOr(this, argName, defaultValue)

inline fun Activity.readByteUriArgOrNull(argName: String): Byte? = Argsx.readByteUriArgOrNull(this, argName)


inline fun Activity.readShortUriArgOrThrow(argName: String): Short = Argsx.readShortUriArgOrThrow(this, argName)

inline fun Activity.readShortUriArgOr(argName: String, defaultValue: Short): Short = Argsx.readShortUriArgOr(this, argName, defaultValue)

inline fun Activity.readShortUriArgOrNull(argName: String): Short? = Argsx.readShortUriArgOrNull(this, argName)


inline fun Activity.readIntUriArgOrThrow(argName: String): Int = Argsx.readIntUriArgOrThrow(this, argName)

inline fun Activity.readIntUriArgOr(argName: String, defaultValue: Int): Int = Argsx.readIntUriArgOr(this, argName, defaultValue)

inline fun Activity.readIntUriArgOrNull(argName: String): Int? = Argsx.readIntUriArgOrNull(this, argName)


inline fun Activity.readLongUriArgOrThrow(argName: String): Long = Argsx.readLongUriArgOrThrow(this, argName)

inline fun Activity.readLongUriArgOr(argName: String, defaultValue: Long): Long = Argsx.readLongUriArgOr(this, argName, defaultValue)

inline fun Activity.readLongUriArgOrNull(argName: String): Long? = Argsx.readLongUriArgOrNull(this, argName)


inline fun Activity.readFloatUriArgOrThrow(argName: String): Float = Argsx.readFloatUriArgOrThrow(this, argName)

inline fun Activity.readFloatUriArgOr(argName: String, defaultValue: Float): Float = Argsx.readFloatUriArgOr(this, argName, defaultValue)

inline fun Activity.readFloatUriArgOrNull(argName: String): Float? = Argsx.readFloatUriArgOrNull(this, argName)


inline fun Activity.readDoubleUriArgOrThrow(argName: String): Double = Argsx.readDoubleUriArgOrThrow(this, argName)

inline fun Activity.readDoubleUriArgOr(argName: String, defaultValue: Double): Double = Argsx.readDoubleUriArgOr(this, argName, defaultValue)

inline fun Activity.readDoubleUriArgOrNull(argName: String): Double? = Argsx.readDoubleUriArgOrNull(this, argName)


inline fun Activity.readBooleanUriArgOrThrow(argName: String): Boolean = Argsx.readBooleanUriArgOrThrow(this, argName)

inline fun Activity.readBooleanUriArgOr(argName: String, defaultValue: Boolean): Boolean = Argsx.readBooleanUriArgOr(this, argName, defaultValue)

inline fun Activity.readBooleanUriArgOrNull(argName: String): Boolean? = Argsx.readBooleanUriArgOrNull(this, argName)


inline fun Activity.readStringUriArgOrThrow(argName: String): String = Argsx.readStringUriArgOrThrow(this, argName)

inline fun Activity.readStringUriArgOr(argName: String, defaultValue: String): String = Argsx.readStringUriArgOr(this, argName, defaultValue)

inline fun Activity.readStringUriArgOrNull(argName: String): String? = Argsx.readStringUriArgOrNull(this, argName)


/* ************************************* Activity Uri Intent Args ***************************************** */


inline fun Activity.readByteUriIntentArgOrThrow(argName: String): Byte = Argsx.readByteUriIntentArgOrThrow(this, argName)

inline fun Activity.readByteUriIntentArgOr(argName: String, defaultValue: Byte): Byte = Argsx.readByteUriIntentArgOr(this, argName, defaultValue)

inline fun Activity.readByteUriIntentArgOrNull(argName: String): Byte? = Argsx.readByteUriIntentArgOrNull(this, argName)


inline fun Activity.readShortUriIntentArgOrThrow(argName: String): Short = Argsx.readShortUriIntentArgOrThrow(this, argName)

inline fun Activity.readShortUriIntentArgOr(argName: String, defaultValue: Short): Short = Argsx.readShortUriIntentArgOr(this, argName, defaultValue)

inline fun Activity.readShortUriIntentArgOrNull(argName: String): Short? = Argsx.readShortUriIntentArgOrNull(this, argName)


inline fun Activity.readIntUriIntentArgOrThrow(argName: String): Int = Argsx.readIntUriIntentArgOrThrow(this, argName)

inline fun Activity.readIntUriIntentArgOr(argName: String, defaultValue: Int): Int = Argsx.readIntUriIntentArgOr(this, argName, defaultValue)

inline fun Activity.readIntUriIntentArgOrNull(argName: String): Int? = Argsx.readIntUriIntentArgOrNull(this, argName)


inline fun Activity.readLongUriIntentArgOrThrow(argName: String): Long = Argsx.readLongUriIntentArgOrThrow(this, argName)

inline fun Activity.readLongUriIntentArgOr(argName: String, defaultValue: Long): Long = Argsx.readLongUriIntentArgOr(this, argName, defaultValue)

inline fun Activity.readLongUriIntentArgOrNull(argName: String): Long? = Argsx.readLongUriIntentArgOrNull(this, argName)


inline fun Activity.readFloatUriIntentArgOrThrow(argName: String): Float = Argsx.readFloatUriIntentArgOrThrow(this, argName)

inline fun Activity.readFloatUriIntentArgOr(argName: String, defaultValue: Float): Float = Argsx.readFloatUriIntentArgOr(this, argName, defaultValue)

inline fun Activity.readFloatUriIntentArgOrNull(argName: String): Float? = Argsx.readFloatUriIntentArgOrNull(this, argName)


inline fun Activity.readDoubleUriIntentArgOrThrow(argName: String): Double = Argsx.readDoubleUriIntentArgOrThrow(this, argName)

inline fun Activity.readDoubleUriIntentArgOr(argName: String, defaultValue: Double): Double = Argsx.readDoubleUriIntentArgOr(this, argName, defaultValue)

inline fun Activity.readDoubleUriIntentArgOrNull(argName: String): Double? = Argsx.readDoubleUriIntentArgOrNull(this, argName)


inline fun Activity.readBooleanUriIntentArgOrThrow(argName: String): Boolean = Argsx.readBooleanUriIntentArgOrThrow(this, argName)

inline fun Activity.readBooleanUriIntentArgOr(argName: String, defaultValue: Boolean): Boolean = Argsx.readBooleanUriIntentArgOr(this, argName, defaultValue)

inline fun Activity.readBooleanUriIntentArgOrNull(argName: String): Boolean? = Argsx.readBooleanUriIntentArgOrNull(this, argName)


inline fun Activity.readStringUriIntentArgOrThrow(argName: String): String = Argsx.readStringUriIntentArgOrThrow(this, argName)

inline fun Activity.readStringUriIntentArgOr(argName: String, defaultValue: String): String = Argsx.readStringUriIntentArgOr(this, argName, defaultValue)

inline fun Activity.readStringUriIntentArgOrNull(argName: String): String? = Argsx.readStringUriIntentArgOrNull(this, argName)


/* ************************************* Activity Intent Uri Args ***************************************** */


inline fun Activity.readByteIntentUriArgOrThrow(argName: String): Byte = Argsx.readByteIntentUriArgOrThrow(this, argName)

inline fun Activity.readByteIntentUriArgOr(argName: String, defaultValue: Byte): Byte = Argsx.readByteIntentUriArgOr(this, argName, defaultValue)

inline fun Activity.readByteIntentUriArgOrNull(argName: String): Byte? = Argsx.readByteIntentUriArgOrNull(this, argName)


inline fun Activity.readShortIntentUriArgOrThrow(argName: String): Short = Argsx.readShortIntentUriArgOrThrow(this, argName)

inline fun Activity.readShortIntentUriArgOr(argName: String, defaultValue: Short): Short = Argsx.readShortIntentUriArgOr(this, argName, defaultValue)

inline fun Activity.readShortIntentUriArgOrNull(argName: String): Short? = Argsx.readShortIntentUriArgOrNull(this, argName)


inline fun Activity.readIntIntentUriArgOrThrow(argName: String): Int = Argsx.readIntIntentUriArgOrThrow(this, argName)

inline fun Activity.readIntIntentUriArgOr(argName: String, defaultValue: Int): Int = Argsx.readIntIntentUriArgOr(this, argName, defaultValue)

inline fun Activity.readIntIntentUriArgOrNull(argName: String): Int? = Argsx.readIntIntentUriArgOrNull(this, argName)


inline fun Activity.readLongIntentUriArgOrThrow(argName: String): Long = Argsx.readLongIntentUriArgOrThrow(this, argName)

inline fun Activity.readLongIntentUriArgOr(argName: String, defaultValue: Long): Long = Argsx.readLongIntentUriArgOr(this, argName, defaultValue)

inline fun Activity.readLongIntentUriArgOrNull(argName: String): Long? = Argsx.readLongIntentUriArgOrNull(this, argName)


inline fun Activity.readFloatIntentUriArgOrThrow(argName: String): Float = Argsx.readFloatIntentUriArgOrThrow(this, argName)

inline fun Activity.readFloatIntentUriArgOr(argName: String, defaultValue: Float): Float = Argsx.readFloatIntentUriArgOr(this, argName, defaultValue)

inline fun Activity.readFloatIntentUriArgOrNull(argName: String): Float? = Argsx.readFloatIntentUriArgOrNull(this, argName)


inline fun Activity.readDoubleIntentUriArgOrThrow(argName: String): Double = Argsx.readDoubleIntentUriArgOrThrow(this, argName)

inline fun Activity.readDoubleIntentUriArgOr(argName: String, defaultValue: Double): Double = Argsx.readDoubleIntentUriArgOr(this, argName, defaultValue)

inline fun Activity.readDoubleIntentUriArgOrNull(argName: String): Double? = Argsx.readDoubleIntentUriArgOrNull(this, argName)


inline fun Activity.readBooleanIntentUriArgOrThrow(argName: String): Boolean = Argsx.readBooleanIntentUriArgOrThrow(this, argName)

inline fun Activity.readBooleanIntentUriArgOr(argName: String, defaultValue: Boolean): Boolean = Argsx.readBooleanIntentUriArgOr(this, argName, defaultValue)

inline fun Activity.readBooleanIntentUriArgOrNull(argName: String): Boolean? = Argsx.readBooleanIntentUriArgOrNull(this, argName)


inline fun Activity.readStringIntentUriArgOrThrow(argName: String): String = Argsx.readStringIntentUriArgOrThrow(this, argName)

inline fun Activity.readStringIntentUriArgOr(argName: String, defaultValue: String): String = Argsx.readStringIntentUriArgOr(this, argName, defaultValue)

inline fun Activity.readStringIntentUriArgOrNull(argName: String): String? = Argsx.readStringIntentUriArgOrNull(this, argName)


/* ************************************* Activity Intent Args ***************************************** */


inline fun Activity.readByteArgOrThrow(@StringRes argNameResId: Int): Byte = Argsx.readByteArgOrThrow(this, argNameResId)

inline fun Activity.readByteArgOr(@StringRes argNameResId: Int, defaultValue: Byte): Byte = Argsx.readByteArgOr(this, argNameResId, defaultValue)

inline fun Activity.readByteArgOrNull(@StringRes argNameResId: Int): Byte? = Argsx.readByteArgOrNull(this, argNameResId)


inline fun Activity.readByteArrayArgOrThrow(@StringRes argNameResId: Int): ByteArray = Argsx.readByteArrayArgOrThrow(this, argNameResId)

inline fun Activity.readByteArrayArgOr(@StringRes argNameResId: Int, defaultValue: ByteArray): ByteArray = Argsx.readByteArrayArgOr(this, argNameResId, defaultValue)

inline fun Activity.readByteArrayArgOrNull(@StringRes argNameResId: Int): ByteArray? = Argsx.readByteArrayArgOrNull(this, argNameResId)


inline fun Activity.readShortArgOrThrow(@StringRes argNameResId: Int): Short = Argsx.readShortArgOrThrow(this, argNameResId)

inline fun Activity.readShortArgOr(@StringRes argNameResId: Int, defaultValue: Short): Short = Argsx.readShortArgOr(this, argNameResId, defaultValue)

inline fun Activity.readShortArgOrNull(@StringRes argNameResId: Int): Short? = Argsx.readShortArgOrNull(this, argNameResId)


inline fun Activity.readShortArrayArgOrThrow(@StringRes argNameResId: Int): ShortArray = Argsx.readShortArrayArgOrThrow(this, argNameResId)

inline fun Activity.readShortArrayArgOr(@StringRes argNameResId: Int, defaultValue: ShortArray): ShortArray = Argsx.readShortArrayArgOr(this, argNameResId, defaultValue)

inline fun Activity.readShortArrayArgOrNull(@StringRes argNameResId: Int): ShortArray? = Argsx.readShortArrayArgOrNull(this, argNameResId)


inline fun Activity.readIntArgOrThrow(@StringRes argNameResId: Int): Int = Argsx.readIntArgOrThrow(this, argNameResId)

inline fun Activity.readIntArgOr(@StringRes argNameResId: Int, defaultValue: Int): Int = Argsx.readIntArgOr(this, argNameResId, defaultValue)

inline fun Activity.readIntArgOrNull(@StringRes argNameResId: Int): Int? = Argsx.readIntArgOrNull(this, argNameResId)


inline fun Activity.readIntArrayArgOrThrow(@StringRes argNameResId: Int): IntArray = Argsx.readIntArrayArgOrThrow(this, argNameResId)

inline fun Activity.readIntArrayArgOr(@StringRes argNameResId: Int, defaultValue: IntArray): IntArray = Argsx.readIntArrayArgOr(this, argNameResId, defaultValue)

inline fun Activity.readIntArrayArgOrNull(@StringRes argNameResId: Int): IntArray? = Argsx.readIntArrayArgOrNull(this, argNameResId)


inline fun Activity.readIntArrayListArgOrThrow(@StringRes argNameResId: Int): ArrayList<Int> = Argsx.readIntArrayListArgOrThrow(this, argNameResId)

inline fun Activity.readIntArrayListArgOr(@StringRes argNameResId: Int, defaultValue: ArrayList<Int>): ArrayList<Int> = Argsx.readIntArrayListArgOr(this, argNameResId, defaultValue)

inline fun Activity.readIntArrayListArgOrNull(@StringRes argNameResId: Int): ArrayList<Int>? = Argsx.readIntArrayListArgOrNull(this, argNameResId)


inline fun Activity.readLongArgOrThrow(@StringRes argNameResId: Int): Long = Argsx.readLongArgOrThrow(this, argNameResId)

inline fun Activity.readLongArgOr(@StringRes argNameResId: Int, defaultValue: Long): Long = Argsx.readLongArgOr(this, argNameResId, defaultValue)

inline fun Activity.readLongArgOrNull(@StringRes argNameResId: Int): Long? = Argsx.readLongArgOrNull(this, argNameResId)


inline fun Activity.readLongArrayArgOrThrow(@StringRes argNameResId: Int): LongArray = Argsx.readLongArrayArgOrThrow(this, argNameResId)

inline fun Activity.readLongArrayArgOr(@StringRes argNameResId: Int, defaultValue: LongArray): LongArray = Argsx.readLongArrayArgOr(this, argNameResId, defaultValue)

inline fun Activity.readLongArrayArgOrNull(@StringRes argNameResId: Int): LongArray? = Argsx.readLongArrayArgOrNull(this, argNameResId)


inline fun Activity.readFloatArgOrThrow(@StringRes argNameResId: Int): Float = Argsx.readFloatArgOrThrow(this, argNameResId)

inline fun Activity.readFloatArgOr(@StringRes argNameResId: Int, defaultValue: Float): Float = Argsx.readFloatArgOr(this, argNameResId, defaultValue)

inline fun Activity.readFloatArgOrNull(@StringRes argNameResId: Int): Float? = Argsx.readFloatArgOrNull(this, argNameResId)


inline fun Activity.readFloatArrayArgOrThrow(@StringRes argNameResId: Int): FloatArray = Argsx.readFloatArrayArgOrThrow(this, argNameResId)

inline fun Activity.readFloatArrayArgOr(@StringRes argNameResId: Int, defaultValue: FloatArray): FloatArray = Argsx.readFloatArrayArgOr(this, argNameResId, defaultValue)

inline fun Activity.readFloatArrayArgOrNull(@StringRes argNameResId: Int): FloatArray? = Argsx.readFloatArrayArgOrNull(this, argNameResId)


inline fun Activity.readDoubleArgOrThrow(@StringRes argNameResId: Int): Double = Argsx.readDoubleArgOrThrow(this, argNameResId)

inline fun Activity.readDoubleArgOr(@StringRes argNameResId: Int, defaultValue: Double): Double = Argsx.readDoubleArgOr(this, argNameResId, defaultValue)

inline fun Activity.readDoubleArgOrNull(@StringRes argNameResId: Int): Double? = Argsx.readDoubleArgOrNull(this, argNameResId)


inline fun Activity.readDoubleArrayArgOrThrow(@StringRes argNameResId: Int): DoubleArray = Argsx.readDoubleArrayArgOrThrow(this, argNameResId)

inline fun Activity.readDoubleArrayArgOr(@StringRes argNameResId: Int, defaultValue: DoubleArray): DoubleArray = Argsx.readDoubleArrayArgOr(this, argNameResId, defaultValue)

inline fun Activity.readDoubleArrayArgOrNull(@StringRes argNameResId: Int): DoubleArray? = Argsx.readDoubleArrayArgOrNull(this, argNameResId)


inline fun Activity.readBooleanArgOrThrow(@StringRes argNameResId: Int): Boolean = Argsx.readBooleanArgOrThrow(this, argNameResId)

inline fun Activity.readBooleanArgOr(@StringRes argNameResId: Int, defaultValue: Boolean): Boolean = Argsx.readBooleanArgOr(this, argNameResId, defaultValue)

inline fun Activity.readBooleanArgOrNull(@StringRes argNameResId: Int): Boolean? = Argsx.readBooleanArgOrNull(this, argNameResId)


inline fun Activity.readBooleanArrayArgOrThrow(@StringRes argNameResId: Int): BooleanArray = Argsx.readBooleanArrayArgOrThrow(this, argNameResId)

inline fun Activity.readBooleanArrayArgOr(@StringRes argNameResId: Int, defaultValue: BooleanArray): BooleanArray = Argsx.readBooleanArrayArgOr(this, argNameResId, defaultValue)

inline fun Activity.readBooleanArrayArgOrNull(@StringRes argNameResId: Int): BooleanArray? = Argsx.readBooleanArrayArgOrNull(this, argNameResId)


inline fun Activity.readCharArgOrThrow(@StringRes argNameResId: Int): Char = Argsx.readCharArgOrThrow(this, argNameResId)

inline fun Activity.readCharArgOr(@StringRes argNameResId: Int, defaultValue: Char): Char = Argsx.readCharArgOr(this, argNameResId, defaultValue)

inline fun Activity.readCharArgOrNull(@StringRes argNameResId: Int): Char? = Argsx.readCharArgOrNull(this, argNameResId)


inline fun Activity.readCharArrayArgOrThrow(@StringRes argNameResId: Int): CharArray = Argsx.readCharArrayArgOrThrow(this, argNameResId)

inline fun Activity.readCharArrayArgOr(@StringRes argNameResId: Int, defaultValue: CharArray): CharArray = Argsx.readCharArrayArgOr(this, argNameResId, defaultValue)

inline fun Activity.readCharArrayArgOrNull(@StringRes argNameResId: Int): CharArray? = Argsx.readCharArrayArgOrNull(this, argNameResId)


inline fun Activity.readCharSequenceArgOrThrow(@StringRes argNameResId: Int): CharSequence = Argsx.readCharSequenceArgOrThrow(this, argNameResId)

inline fun Activity.readCharSequenceArgOr(@StringRes argNameResId: Int, defaultValue: CharSequence): CharSequence = Argsx.readCharSequenceArgOr(this, argNameResId, defaultValue)

inline fun Activity.readCharSequenceArgOrNull(@StringRes argNameResId: Int): CharSequence? = Argsx.readCharSequenceArgOrNull(this, argNameResId)


inline fun Activity.readCharSequenceArrayArgOrThrow(@StringRes argNameResId: Int): Array<CharSequence> = Argsx.readCharSequenceArrayArgOrThrow(this, argNameResId)

inline fun Activity.readCharSequenceArrayArgOr(@StringRes argNameResId: Int, defaultValue: Array<CharSequence>): Array<CharSequence> = Argsx.readCharSequenceArrayArgOr(this, argNameResId, defaultValue)

inline fun Activity.readCharSequenceArrayArgOrNull(@StringRes argNameResId: Int): Array<CharSequence>? = Argsx.readCharSequenceArrayArgOrNull(this, argNameResId)


inline fun Activity.readCharSequenceArrayListArgOrThrow(@StringRes argNameResId: Int): ArrayList<CharSequence> = Argsx.readCharSequenceArrayListArgOrThrow(this, argNameResId)

inline fun Activity.readCharSequenceArrayListArgOr(@StringRes argNameResId: Int, defaultValue: ArrayList<CharSequence>): ArrayList<CharSequence> = Argsx.readCharSequenceArrayListArgOr(this, argNameResId, defaultValue)

inline fun Activity.readCharSequenceArrayListArgOrNull(@StringRes argNameResId: Int): ArrayList<CharSequence>? = Argsx.readCharSequenceArrayListArgOrNull(this, argNameResId)


inline fun Activity.readStringArgOrThrow(@StringRes argNameResId: Int): String = Argsx.readStringArgOrThrow(this, argNameResId)

inline fun Activity.readStringArgOr(@StringRes argNameResId: Int, defaultValue: String): String = Argsx.readStringArgOr(this, argNameResId, defaultValue)

inline fun Activity.readStringArgOrNull(@StringRes argNameResId: Int): String? = Argsx.readStringArgOrNull(this, argNameResId)


inline fun Activity.readStringArrayArgOrThrow(@StringRes argNameResId: Int): Array<String> = Argsx.readStringArrayArgOrThrow(this, argNameResId)

inline fun Activity.readStringArrayArgOr(@StringRes argNameResId: Int, defaultValue: Array<String>): Array<String> = Argsx.readStringArrayArgOr(this, argNameResId, defaultValue)

inline fun Activity.readStringArrayArgOrNull(@StringRes argNameResId: Int): Array<String>? = Argsx.readStringArrayArgOrNull(this, argNameResId)


inline fun Activity.readStringArrayListArgOrThrow(@StringRes argNameResId: Int): ArrayList<String> = Argsx.readStringArrayListArgOrThrow(this, argNameResId)

inline fun Activity.readStringArrayListArgOr(@StringRes argNameResId: Int, defaultValue: ArrayList<String>): ArrayList<String> = Argsx.readStringArrayListArgOr(this, argNameResId, defaultValue)

inline fun Activity.readStringArrayListArgOrNull(@StringRes argNameResId: Int): ArrayList<String>? = Argsx.readStringArrayListArgOrNull(this, argNameResId)


inline fun <V : Parcelable> Activity.readParcelableArgOrThrow(@StringRes argNameResId: Int): V = Argsx.readParcelableArgOrThrow(this, argNameResId)

inline fun <V : Parcelable> Activity.readParcelableArgOr(@StringRes argNameResId: Int, defaultValue: V): V = Argsx.readParcelableArgOr(this, argNameResId, defaultValue)

inline fun <V : Parcelable> Activity.readParcelableArgOrNull(@StringRes argNameResId: Int): V? = Argsx.readParcelableArgOrNull(this, argNameResId)


inline fun <V : Parcelable> Activity.readParcelableArrayArgOrThrow(@StringRes argNameResId: Int): Array<V> = Argsx.readParcelableArrayArgOrThrow(this, argNameResId)

inline fun <V : Parcelable> Activity.readParcelableArrayArgOr(@StringRes argNameResId: Int, defaultValue: Array<V>): Array<V> = Argsx.readParcelableArrayArgOr(this, argNameResId, defaultValue)

inline fun <V : Parcelable> Activity.readParcelableArrayArgOrNull(@StringRes argNameResId: Int): Array<V>? = Argsx.readParcelableArrayArgOrNull(this, argNameResId)


inline fun <V : Parcelable> Activity.readParcelableArrayListArgOrThrow(@StringRes argNameResId: Int): ArrayList<V> = Argsx.readParcelableArrayListArgOrThrow(this, argNameResId)

inline fun <V : Parcelable> Activity.readParcelableArrayListArgOr(@StringRes argNameResId: Int, defaultValue: ArrayList<V>): ArrayList<V> = Argsx.readParcelableArrayListArgOr(this, argNameResId, defaultValue)

inline fun <V : Parcelable> Activity.readParcelableArrayListArgOrNull(@StringRes argNameResId: Int): ArrayList<V>? = Argsx.readParcelableArrayListArgOrNull(this, argNameResId)


inline fun <V : Serializable> Activity.readSerializableArgOrThrow(@StringRes argNameResId: Int): V = Argsx.readSerializableArgOrThrow(this, argNameResId)

inline fun <V : Serializable> Activity.readSerializableArgOr(@StringRes argNameResId: Int, defaultValue: V): V = Argsx.readSerializableArgOr(this, argNameResId, defaultValue)

inline fun <V : Serializable> Activity.readSerializableArgOrNull(@StringRes argNameResId: Int): V? = Argsx.readSerializableArgOrNull(this, argNameResId)


inline fun Activity.readBundleArgOrThrow(@StringRes argNameResId: Int): Bundle = Argsx.readBundleArgOrThrow(this, argNameResId)

inline fun Activity.readBundleArgOr(@StringRes argNameResId: Int, defaultValue: Bundle): Bundle = Argsx.readBundleArgOr(this, argNameResId, defaultValue)

inline fun Activity.readBundleArgOrNull(@StringRes argNameResId: Int): Bundle? = Argsx.readBundleArgOrNull(this, argNameResId)


inline fun Activity.readExtrasArgOrThrow(): Bundle = Argsx.readExtrasArgOrThrow(this)

inline fun Activity.readExtrasArgOr(defaultValue: Bundle): Bundle = Argsx.readExtrasArgOr(this, defaultValue)

inline fun Activity.readExtrasArgOrNull(): Bundle? = Argsx.readExtrasArgOrNull(this)


/* ************************************* Activity Uri Args ***************************************** */


inline fun Activity.readByteUriArgOrThrow(@StringRes argNameResId: Int): Byte = Argsx.readByteUriArgOrThrow(this, argNameResId)

inline fun Activity.readByteUriArgOr(@StringRes argNameResId: Int, defaultValue: Byte): Byte = Argsx.readByteUriArgOr(this, argNameResId, defaultValue)

inline fun Activity.readByteUriArgOrNull(@StringRes argNameResId: Int): Byte? = Argsx.readByteUriArgOrNull(this, argNameResId)


inline fun Activity.readShortUriArgOrThrow(@StringRes argNameResId: Int): Short = Argsx.readShortUriArgOrThrow(this, argNameResId)

inline fun Activity.readShortUriArgOr(@StringRes argNameResId: Int, defaultValue: Short): Short = Argsx.readShortUriArgOr(this, argNameResId, defaultValue)

inline fun Activity.readShortUriArgOrNull(@StringRes argNameResId: Int): Short? = Argsx.readShortUriArgOrNull(this, argNameResId)


inline fun Activity.readIntUriArgOrThrow(@StringRes argNameResId: Int): Int = Argsx.readIntUriArgOrThrow(this, argNameResId)

inline fun Activity.readIntUriArgOr(@StringRes argNameResId: Int, defaultValue: Int): Int = Argsx.readIntUriArgOr(this, argNameResId, defaultValue)

inline fun Activity.readIntUriArgOrNull(@StringRes argNameResId: Int): Int? = Argsx.readIntUriArgOrNull(this, argNameResId)


inline fun Activity.readLongUriArgOrThrow(@StringRes argNameResId: Int): Long = Argsx.readLongUriArgOrThrow(this, argNameResId)

inline fun Activity.readLongUriArgOr(@StringRes argNameResId: Int, defaultValue: Long): Long = Argsx.readLongUriArgOr(this, argNameResId, defaultValue)

inline fun Activity.readLongUriArgOrNull(@StringRes argNameResId: Int): Long? = Argsx.readLongUriArgOrNull(this, argNameResId)


inline fun Activity.readFloatUriArgOrThrow(@StringRes argNameResId: Int): Float = Argsx.readFloatUriArgOrThrow(this, argNameResId)

inline fun Activity.readFloatUriArgOr(@StringRes argNameResId: Int, defaultValue: Float): Float = Argsx.readFloatUriArgOr(this, argNameResId, defaultValue)

inline fun Activity.readFloatUriArgOrNull(@StringRes argNameResId: Int): Float? = Argsx.readFloatUriArgOrNull(this, argNameResId)


inline fun Activity.readDoubleUriArgOrThrow(@StringRes argNameResId: Int): Double = Argsx.readDoubleUriArgOrThrow(this, argNameResId)

inline fun Activity.readDoubleUriArgOr(@StringRes argNameResId: Int, defaultValue: Double): Double = Argsx.readDoubleUriArgOr(this, argNameResId, defaultValue)

inline fun Activity.readDoubleUriArgOrNull(@StringRes argNameResId: Int): Double? = Argsx.readDoubleUriArgOrNull(this, argNameResId)


inline fun Activity.readBooleanUriArgOrThrow(@StringRes argNameResId: Int): Boolean = Argsx.readBooleanUriArgOrThrow(this, argNameResId)

inline fun Activity.readBooleanUriArgOr(@StringRes argNameResId: Int, defaultValue: Boolean): Boolean = Argsx.readBooleanUriArgOr(this, argNameResId, defaultValue)

inline fun Activity.readBooleanUriArgOrNull(@StringRes argNameResId: Int): Boolean? = Argsx.readBooleanUriArgOrNull(this, argNameResId)


inline fun Activity.readStringUriArgOrThrow(@StringRes argNameResId: Int): String = Argsx.readStringUriArgOrThrow(this, argNameResId)

inline fun Activity.readStringUriArgOr(@StringRes argNameResId: Int, defaultValue: String): String = Argsx.readStringUriArgOr(this, argNameResId, defaultValue)

inline fun Activity.readStringUriArgOrNull(@StringRes argNameResId: Int): String? = Argsx.readStringUriArgOrNull(this, argNameResId)


/* ************************************* Activity Uri Intent Args ***************************************** */


inline fun Activity.readByteUriIntentArgOrThrow(@StringRes argNameResId: Int): Byte = Argsx.readByteUriIntentArgOrThrow(this, argNameResId)

inline fun Activity.readByteUriIntentArgOr(@StringRes argNameResId: Int, defaultValue: Byte): Byte = Argsx.readByteUriIntentArgOr(this, argNameResId, defaultValue)

inline fun Activity.readByteUriIntentArgOrNull(@StringRes argNameResId: Int): Byte? = Argsx.readByteUriIntentArgOrNull(this, argNameResId)


inline fun Activity.readShortUriIntentArgOrThrow(@StringRes argNameResId: Int): Short = Argsx.readShortUriIntentArgOrThrow(this, argNameResId)

inline fun Activity.readShortUriIntentArgOr(@StringRes argNameResId: Int, defaultValue: Short): Short = Argsx.readShortUriIntentArgOr(this, argNameResId, defaultValue)

inline fun Activity.readShortUriIntentArgOrNull(@StringRes argNameResId: Int): Short? = Argsx.readShortUriIntentArgOrNull(this, argNameResId)


inline fun Activity.readIntUriIntentArgOrThrow(@StringRes argNameResId: Int): Int = Argsx.readIntUriIntentArgOrThrow(this, argNameResId)

inline fun Activity.readIntUriIntentArgOr(@StringRes argNameResId: Int, defaultValue: Int): Int = Argsx.readIntUriIntentArgOr(this, argNameResId, defaultValue)

inline fun Activity.readIntUriIntentArgOrNull(@StringRes argNameResId: Int): Int? = Argsx.readIntUriIntentArgOrNull(this, argNameResId)


inline fun Activity.readLongUriIntentArgOrThrow(@StringRes argNameResId: Int): Long = Argsx.readLongUriIntentArgOrThrow(this, argNameResId)

inline fun Activity.readLongUriIntentArgOr(@StringRes argNameResId: Int, defaultValue: Long): Long = Argsx.readLongUriIntentArgOr(this, argNameResId, defaultValue)

inline fun Activity.readLongUriIntentArgOrNull(@StringRes argNameResId: Int): Long? = Argsx.readLongUriIntentArgOrNull(this, argNameResId)


inline fun Activity.readFloatUriIntentArgOrThrow(@StringRes argNameResId: Int): Float = Argsx.readFloatUriIntentArgOrThrow(this, argNameResId)

inline fun Activity.readFloatUriIntentArgOr(@StringRes argNameResId: Int, defaultValue: Float): Float = Argsx.readFloatUriIntentArgOr(this, argNameResId, defaultValue)

inline fun Activity.readFloatUriIntentArgOrNull(@StringRes argNameResId: Int): Float? = Argsx.readFloatUriIntentArgOrNull(this, argNameResId)


inline fun Activity.readDoubleUriIntentArgOrThrow(@StringRes argNameResId: Int): Double = Argsx.readDoubleUriIntentArgOrThrow(this, argNameResId)

inline fun Activity.readDoubleUriIntentArgOr(@StringRes argNameResId: Int, defaultValue: Double): Double = Argsx.readDoubleUriIntentArgOr(this, argNameResId, defaultValue)

inline fun Activity.readDoubleUriIntentArgOrNull(@StringRes argNameResId: Int): Double? = Argsx.readDoubleUriIntentArgOrNull(this, argNameResId)


inline fun Activity.readBooleanUriIntentArgOrThrow(@StringRes argNameResId: Int): Boolean = Argsx.readBooleanUriIntentArgOrThrow(this, argNameResId)

inline fun Activity.readBooleanUriIntentArgOr(@StringRes argNameResId: Int, defaultValue: Boolean): Boolean = Argsx.readBooleanUriIntentArgOr(this, argNameResId, defaultValue)

inline fun Activity.readBooleanUriIntentArgOrNull(@StringRes argNameResId: Int): Boolean? = Argsx.readBooleanUriIntentArgOrNull(this, argNameResId)


inline fun Activity.readStringUriIntentArgOrThrow(@StringRes argNameResId: Int): String = Argsx.readStringUriIntentArgOrThrow(this, argNameResId)

inline fun Activity.readStringUriIntentArgOr(@StringRes argNameResId: Int, defaultValue: String): String = Argsx.readStringUriIntentArgOr(this, argNameResId, defaultValue)

inline fun Activity.readStringUriIntentArgOrNull(@StringRes argNameResId: Int): String? = Argsx.readStringUriIntentArgOrNull(this, argNameResId)


/* ************************************* Activity Intent Uri Args ***************************************** */


inline fun Activity.readByteIntentUriArgOrThrow(@StringRes argNameResId: Int): Byte = Argsx.readByteIntentUriArgOrThrow(this, argNameResId)

inline fun Activity.readByteIntentUriArgOr(@StringRes argNameResId: Int, defaultValue: Byte): Byte = Argsx.readByteIntentUriArgOr(this, argNameResId, defaultValue)

inline fun Activity.readByteIntentUriArgOrNull(@StringRes argNameResId: Int): Byte? = Argsx.readByteIntentUriArgOrNull(this, argNameResId)


inline fun Activity.readShortIntentUriArgOrThrow(@StringRes argNameResId: Int): Short = Argsx.readShortIntentUriArgOrThrow(this, argNameResId)

inline fun Activity.readShortIntentUriArgOr(@StringRes argNameResId: Int, defaultValue: Short): Short = Argsx.readShortIntentUriArgOr(this, argNameResId, defaultValue)

inline fun Activity.readShortIntentUriArgOrNull(@StringRes argNameResId: Int): Short? = Argsx.readShortIntentUriArgOrNull(this, argNameResId)


inline fun Activity.readIntIntentUriArgOrThrow(@StringRes argNameResId: Int): Int = Argsx.readIntIntentUriArgOrThrow(this, argNameResId)

inline fun Activity.readIntIntentUriArgOr(@StringRes argNameResId: Int, defaultValue: Int): Int = Argsx.readIntIntentUriArgOr(this, argNameResId, defaultValue)

inline fun Activity.readIntIntentUriArgOrNull(@StringRes argNameResId: Int): Int? = Argsx.readIntIntentUriArgOrNull(this, argNameResId)


inline fun Activity.readLongIntentUriArgOrThrow(@StringRes argNameResId: Int): Long = Argsx.readLongIntentUriArgOrThrow(this, argNameResId)

inline fun Activity.readLongIntentUriArgOr(@StringRes argNameResId: Int, defaultValue: Long): Long = Argsx.readLongIntentUriArgOr(this, argNameResId, defaultValue)

inline fun Activity.readLongIntentUriArgOrNull(@StringRes argNameResId: Int): Long? = Argsx.readLongIntentUriArgOrNull(this, argNameResId)


inline fun Activity.readFloatIntentUriArgOrThrow(@StringRes argNameResId: Int): Float = Argsx.readFloatIntentUriArgOrThrow(this, argNameResId)

inline fun Activity.readFloatIntentUriArgOr(@StringRes argNameResId: Int, defaultValue: Float): Float = Argsx.readFloatIntentUriArgOr(this, argNameResId, defaultValue)

inline fun Activity.readFloatIntentUriArgOrNull(@StringRes argNameResId: Int): Float? = Argsx.readFloatIntentUriArgOrNull(this, argNameResId)


inline fun Activity.readDoubleIntentUriArgOrThrow(@StringRes argNameResId: Int): Double = Argsx.readDoubleIntentUriArgOrThrow(this, argNameResId)

inline fun Activity.readDoubleIntentUriArgOr(@StringRes argNameResId: Int, defaultValue: Double): Double = Argsx.readDoubleIntentUriArgOr(this, argNameResId, defaultValue)

inline fun Activity.readDoubleIntentUriArgOrNull(@StringRes argNameResId: Int): Double? = Argsx.readDoubleIntentUriArgOrNull(this, argNameResId)


inline fun Activity.readBooleanIntentUriArgOrThrow(@StringRes argNameResId: Int): Boolean = Argsx.readBooleanIntentUriArgOrThrow(this, argNameResId)

inline fun Activity.readBooleanIntentUriArgOr(@StringRes argNameResId: Int, defaultValue: Boolean): Boolean = Argsx.readBooleanIntentUriArgOr(this, argNameResId, defaultValue)

inline fun Activity.readBooleanIntentUriArgOrNull(@StringRes argNameResId: Int): Boolean? = Argsx.readBooleanIntentUriArgOrNull(this, argNameResId)


inline fun Activity.readStringIntentUriArgOrThrow(@StringRes argNameResId: Int): String = Argsx.readStringIntentUriArgOrThrow(this, argNameResId)

inline fun Activity.readStringIntentUriArgOr(@StringRes argNameResId: Int, defaultValue: String): String = Argsx.readStringIntentUriArgOr(this, argNameResId, defaultValue)

inline fun Activity.readStringIntentUriArgOrNull(@StringRes argNameResId: Int): String? = Argsx.readStringIntentUriArgOrNull(this, argNameResId)


/* ************************************* SupportFragment Args ***************************************** */


inline fun Fragment.readByteArgOrThrow(argName: String): Byte = Argsx.readByteArgOrThrow(this, argName)

inline fun Fragment.readByteArgOr(argName: String, defaultValue: Byte): Byte = Argsx.readByteArgOr(this, argName, defaultValue)

inline fun Fragment.readByteArgOrNull(argName: String): Byte? = Argsx.readByteArgOrNull(this, argName)


inline fun Fragment.readByteArrayArgOrThrow(argName: String): ByteArray = Argsx.readByteArrayArgOrThrow(this, argName)

inline fun Fragment.readByteArrayArgOr(argName: String, defaultValue: ByteArray): ByteArray = Argsx.readByteArrayArgOr(this, argName, defaultValue)

inline fun Fragment.readByteArrayArgOrNull(argName: String): ByteArray? = Argsx.readByteArrayArgOrNull(this, argName)


inline fun Fragment.readShortArgOrThrow(argName: String): Short = Argsx.readShortArgOrThrow(this, argName)

inline fun Fragment.readShortArgOr(argName: String, defaultValue: Short): Short = Argsx.readShortArgOr(this, argName, defaultValue)

inline fun Fragment.readShortArgOrNull(argName: String): Short? = Argsx.readShortArgOrNull(this, argName)


inline fun Fragment.readShortArrayArgOrThrow(argName: String): ShortArray = Argsx.readShortArrayArgOrThrow(this, argName)

inline fun Fragment.readShortArrayArgOr(argName: String, defaultValue: ShortArray): ShortArray = Argsx.readShortArrayArgOr(this, argName, defaultValue)

inline fun Fragment.readShortArrayArgOrNull(argName: String): ShortArray? = Argsx.readShortArrayArgOrNull(this, argName)


inline fun Fragment.readIntArgOrThrow(argName: String): Int = Argsx.readIntArgOrThrow(this, argName)

inline fun Fragment.readIntArgOr(argName: String, defaultValue: Int): Int = Argsx.readIntArgOr(this, argName, defaultValue)

inline fun Fragment.readIntArgOrNull(argName: String): Int? = Argsx.readIntArgOrNull(this, argName)


inline fun Fragment.readIntArrayArgOrThrow(argName: String): IntArray = Argsx.readIntArrayArgOrThrow(this, argName)

inline fun Fragment.readIntArrayArgOr(argName: String, defaultValue: IntArray): IntArray = Argsx.readIntArrayArgOr(this, argName, defaultValue)

inline fun Fragment.readIntArrayArgOrNull(argName: String): IntArray? = Argsx.readIntArrayArgOrNull(this, argName)


inline fun Fragment.readIntArrayListArgOrThrow(argName: String): ArrayList<Int> = Argsx.readIntArrayListArgOrThrow(this, argName)

inline fun Fragment.readIntArrayListArgOr(argName: String, defaultValue: ArrayList<Int>): ArrayList<Int> = Argsx.readIntArrayListArgOr(this, argName, defaultValue)

inline fun Fragment.readIntArrayListArgOrNull(argName: String): ArrayList<Int>? = Argsx.readIntArrayListArgOrNull(this, argName)


inline fun Fragment.readLongArgOrThrow(argName: String): Long = Argsx.readLongArgOrThrow(this, argName)

inline fun Fragment.readLongArgOr(argName: String, defaultValue: Long): Long = Argsx.readLongArgOr(this, argName, defaultValue)

inline fun Fragment.readLongArgOrNull(argName: String): Long? = Argsx.readLongArgOrNull(this, argName)


inline fun Fragment.readLongArrayArgOrThrow(argName: String): LongArray = Argsx.readLongArrayArgOrThrow(this, argName)

inline fun Fragment.readLongArrayArgOr(argName: String, defaultValue: LongArray): LongArray = Argsx.readLongArrayArgOr(this, argName, defaultValue)

inline fun Fragment.readLongArrayArgOrNull(argName: String): LongArray? = Argsx.readLongArrayArgOrNull(this, argName)


inline fun Fragment.readFloatArgOrThrow(argName: String): Float = Argsx.readFloatArgOrThrow(this, argName)

inline fun Fragment.readFloatArgOr(argName: String, defaultValue: Float): Float = Argsx.readFloatArgOr(this, argName, defaultValue)

inline fun Fragment.readFloatArgOrNull(argName: String): Float? = Argsx.readFloatArgOrNull(this, argName)


inline fun Fragment.readFloatArrayArgOrThrow(argName: String): FloatArray = Argsx.readFloatArrayArgOrThrow(this, argName)

inline fun Fragment.readFloatArrayArgOr(argName: String, defaultValue: FloatArray): FloatArray = Argsx.readFloatArrayArgOr(this, argName, defaultValue)

inline fun Fragment.readFloatArrayArgOrNull(argName: String): FloatArray? = Argsx.readFloatArrayArgOrNull(this, argName)


inline fun Fragment.readDoubleArgOrThrow(argName: String): Double = Argsx.readDoubleArgOrThrow(this, argName)

inline fun Fragment.readDoubleArgOr(argName: String, defaultValue: Double): Double = Argsx.readDoubleArgOr(this, argName, defaultValue)

inline fun Fragment.readDoubleArgOrNull(argName: String): Double? = Argsx.readDoubleArgOrNull(this, argName)


inline fun Fragment.readDoubleArrayArgOrThrow(argName: String): DoubleArray = Argsx.readDoubleArrayArgOrThrow(this, argName)

inline fun Fragment.readDoubleArrayArgOr(argName: String, defaultValue: DoubleArray): DoubleArray = Argsx.readDoubleArrayArgOr(this, argName, defaultValue)

inline fun Fragment.readDoubleArrayArgOrNull(argName: String): DoubleArray? = Argsx.readDoubleArrayArgOrNull(this, argName)


inline fun Fragment.readBooleanArgOrThrow(argName: String): Boolean = Argsx.readBooleanArgOrThrow(this, argName)

inline fun Fragment.readBooleanArgOr(argName: String, defaultValue: Boolean): Boolean = Argsx.readBooleanArgOr(this, argName, defaultValue)

inline fun Fragment.readBooleanArgOrNull(argName: String): Boolean? = Argsx.readBooleanArgOrNull(this, argName)


inline fun Fragment.readBooleanArrayArgOrThrow(argName: String): BooleanArray = Argsx.readBooleanArrayArgOrThrow(this, argName)

inline fun Fragment.readBooleanArrayArgOr(argName: String, defaultValue: BooleanArray): BooleanArray = Argsx.readBooleanArrayArgOr(this, argName, defaultValue)

inline fun Fragment.readBooleanArrayArgOrNull(argName: String): BooleanArray? = Argsx.readBooleanArrayArgOrNull(this, argName)


inline fun Fragment.readCharArgOrThrow(argName: String): Char = Argsx.readCharArgOrThrow(this, argName)

inline fun Fragment.readCharArgOr(argName: String, defaultValue: Char): Char = Argsx.readCharArgOr(this, argName, defaultValue)

inline fun Fragment.readCharArgOrNull(argName: String): Char? = Argsx.readCharArgOrNull(this, argName)


inline fun Fragment.readCharArrayArgOrThrow(argName: String): CharArray = Argsx.readCharArrayArgOrThrow(this, argName)

inline fun Fragment.readCharArrayArgOr(argName: String, defaultValue: CharArray): CharArray = Argsx.readCharArrayArgOr(this, argName, defaultValue)

inline fun Fragment.readCharArrayArgOrNull(argName: String): CharArray? = Argsx.readCharArrayArgOrNull(this, argName)


inline fun Fragment.readCharSequenceArgOrThrow(argName: String): CharSequence = Argsx.readCharSequenceArgOrThrow(this, argName)

inline fun Fragment.readCharSequenceArgOr(argName: String, defaultValue: CharSequence): CharSequence = Argsx.readCharSequenceArgOr(this, argName, defaultValue)

inline fun Fragment.readCharSequenceArgOrNull(argName: String): CharSequence? = Argsx.readCharSequenceArgOrNull(this, argName)


inline fun Fragment.readCharSequenceArrayArgOrThrow(argName: String): Array<CharSequence> = Argsx.readCharSequenceArrayArgOrThrow(this, argName)

inline fun Fragment.readCharSequenceArrayArgOr(argName: String, defaultValue: Array<CharSequence>): Array<CharSequence> = Argsx.readCharSequenceArrayArgOr(this, argName, defaultValue)

inline fun Fragment.readCharSequenceArrayArgOrNull(argName: String): Array<CharSequence>? = Argsx.readCharSequenceArrayArgOrNull(this, argName)


inline fun Fragment.readCharSequenceArrayListArgOrThrow(argName: String): ArrayList<CharSequence> = Argsx.readCharSequenceArrayListArgOrThrow(this, argName)

inline fun Fragment.readCharSequenceArrayListArgOr(argName: String, defaultValue: ArrayList<CharSequence>): ArrayList<CharSequence> = Argsx.readCharSequenceArrayListArgOr(this, argName, defaultValue)

inline fun Fragment.readCharSequenceArrayListArgOrNull(argName: String): ArrayList<CharSequence>? = Argsx.readCharSequenceArrayListArgOrNull(this, argName)


inline fun Fragment.readStringArgOrThrow(argName: String): String = Argsx.readStringArgOrThrow(this, argName)

inline fun Fragment.readStringArgOr(argName: String, defaultValue: String): String = Argsx.readStringArgOr(this, argName, defaultValue)

inline fun Fragment.readStringArgOrNull(argName: String): String? = Argsx.readStringArgOrNull(this, argName)


inline fun Fragment.readStringArrayArgOrThrow(argName: String): Array<String> = Argsx.readStringArrayArgOrThrow(this, argName)

inline fun Fragment.readStringArrayArgOr(argName: String, defaultValue: Array<String>): Array<String> = Argsx.readStringArrayArgOr(this, argName, defaultValue)

inline fun Fragment.readStringArrayArgOrNull(argName: String): Array<String>? = Argsx.readStringArrayArgOrNull(this, argName)


inline fun Fragment.readStringArrayListArgOrThrow(argName: String): ArrayList<String> = Argsx.readStringArrayListArgOrThrow(this, argName)

inline fun Fragment.readStringArrayListArgOr(argName: String, defaultValue: ArrayList<String>): ArrayList<String> = Argsx.readStringArrayListArgOr(this, argName, defaultValue)

inline fun Fragment.readStringArrayListArgOrNull(argName: String): ArrayList<String>? = Argsx.readStringArrayListArgOrNull(this, argName)


inline fun <V : Parcelable> Fragment.readParcelableArgOrThrow(argName: String): V = Argsx.readParcelableArgOrThrow(this, argName)

inline fun <V : Parcelable> Fragment.readParcelableArgOr(argName: String, defaultValue: V): V = Argsx.readParcelableArgOr(this, argName, defaultValue)

inline fun <V : Parcelable> Fragment.readParcelableArgOrNull(argName: String): V? = Argsx.readParcelableArgOrNull(this, argName)


inline fun <V : Parcelable> Fragment.readParcelableArrayArgOrThrow(argName: String): Array<V> = Argsx.readParcelableArrayArgOrThrow(this, argName)

inline fun <V : Parcelable> Fragment.readParcelableArrayArgOr(argName: String, defaultValue: Array<V>): Array<V> = Argsx.readParcelableArrayArgOr(this, argName, defaultValue)

inline fun <V : Parcelable> Fragment.readParcelableArrayArgOrNull(argName: String): Array<V>? = Argsx.readParcelableArrayArgOrNull(this, argName)


inline fun <V : Parcelable> Fragment.readParcelableArrayListArgOrThrow(argName: String): ArrayList<V> = Argsx.readParcelableArrayListArgOrThrow(this, argName)

inline fun <V : Parcelable> Fragment.readParcelableArrayListArgOr(argName: String, defaultValue: ArrayList<V>): ArrayList<V> = Argsx.readParcelableArrayListArgOr(this, argName, defaultValue)

inline fun <V : Parcelable> Fragment.readParcelableArrayListArgOrNull(argName: String): ArrayList<V>? = Argsx.readParcelableArrayListArgOrNull(this, argName)


inline fun <V : Parcelable> Fragment.readSparseParcelableArrayArgOrThrow(argName: String): SparseArray<V> = Argsx.readSparseParcelableArrayArgOrThrow(this, argName)

inline fun <V : Parcelable> Fragment.readSparseParcelableArrayArgOr(argName: String, defaultValue: SparseArray<V>): SparseArray<V> = Argsx.readSparseParcelableArrayArgOr(this, argName, defaultValue)

inline fun <V : Parcelable> Fragment.readSparseParcelableArrayArgOrNull(argName: String): SparseArray<V>? = Argsx.readSparseParcelableArrayArgOrNull(this, argName)


inline fun <V : Serializable> Fragment.readSerializableArgOrThrow(argName: String): V = Argsx.readSerializableArgOrThrow(this, argName)

inline fun <V : Serializable> Fragment.readSerializableArgOr(argName: String, defaultValue: V): V = Argsx.readSerializableArgOr(this, argName, defaultValue)

inline fun <V : Serializable> Fragment.readSerializableArgOrNull(argName: String): V? = Argsx.readSerializableArgOrNull(this, argName)


inline fun Fragment.readBundleArgOrThrow(argName: String): Bundle = Argsx.readBundleArgOrThrow(this, argName)

inline fun Fragment.readBundleArgOr(argName: String, defaultValue: Bundle): Bundle = Argsx.readBundleArgOr(this, argName, defaultValue)

inline fun Fragment.readBundleArgOrNull(argName: String): Bundle? = Argsx.readBundleArgOrNull(this, argName)


@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
inline fun Fragment.readBinderArgOrThrow(argName: String): IBinder = Argsx.readBinderArgOrThrow(this, argName)

@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
inline fun Fragment.readBinderArgOr(argName: String, defaultValue: IBinder): IBinder = Argsx.readBinderArgOr(this, argName, defaultValue)

@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
inline fun Fragment.readBinderArgOrNull(argName: String): IBinder? = Argsx.readBinderArgOrNull(this, argName)


@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
inline fun Fragment.readSizeArgOrThrow(argName: String): Size = Argsx.readSizeArgOrThrow(this, argName)

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
inline fun Fragment.readSizeArgOr(argName: String, defaultValue: Size): Size = Argsx.readSizeArgOr(this, argName, defaultValue)

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
inline fun Fragment.readSizeArgOrNull(argName: String): Size? = Argsx.readSizeArgOrNull(this, argName)


@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
inline fun Fragment.readSizeFArgOrThrow(argName: String): SizeF = Argsx.readSizeFArgOrThrow(this, argName)

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
inline fun Fragment.readSizeFArgOr(argName: String, defaultValue: SizeF): SizeF = Argsx.readSizeFArgOr(this, argName, defaultValue)

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
inline fun Fragment.readSizeFArgOrNull(argName: String): SizeF? = Argsx.readSizeFArgOrNull(this, argName)


/* ************************************* SupportFragment Args ***************************************** */


inline fun Fragment.readByteArgOrThrow(@StringRes argNameResId: Int): Byte = Argsx.readByteArgOrThrow(this, argNameResId)

inline fun Fragment.readByteArgOr(@StringRes argNameResId: Int, defaultValue: Byte): Byte = Argsx.readByteArgOr(this, argNameResId, defaultValue)

inline fun Fragment.readByteArgOrNull(@StringRes argNameResId: Int): Byte? = Argsx.readByteArgOrNull(this, argNameResId)


inline fun Fragment.readByteArrayArgOrThrow(@StringRes argNameResId: Int): ByteArray = Argsx.readByteArrayArgOrThrow(this, argNameResId)

inline fun Fragment.readByteArrayArgOr(@StringRes argNameResId: Int, defaultValue: ByteArray): ByteArray = Argsx.readByteArrayArgOr(this, argNameResId, defaultValue)

inline fun Fragment.readByteArrayArgOrNull(@StringRes argNameResId: Int): ByteArray? = Argsx.readByteArrayArgOrNull(this, argNameResId)


inline fun Fragment.readShortArgOrThrow(@StringRes argNameResId: Int): Short = Argsx.readShortArgOrThrow(this, argNameResId)

inline fun Fragment.readShortArgOr(@StringRes argNameResId: Int, defaultValue: Short): Short = Argsx.readShortArgOr(this, argNameResId, defaultValue)

inline fun Fragment.readShortArgOrNull(@StringRes argNameResId: Int): Short? = Argsx.readShortArgOrNull(this, argNameResId)


inline fun Fragment.readShortArrayArgOrThrow(@StringRes argNameResId: Int): ShortArray = Argsx.readShortArrayArgOrThrow(this, argNameResId)

inline fun Fragment.readShortArrayArgOr(@StringRes argNameResId: Int, defaultValue: ShortArray): ShortArray = Argsx.readShortArrayArgOr(this, argNameResId, defaultValue)

inline fun Fragment.readShortArrayArgOrNull(@StringRes argNameResId: Int): ShortArray? = Argsx.readShortArrayArgOrNull(this, argNameResId)


inline fun Fragment.readIntArgOrThrow(@StringRes argNameResId: Int): Int = Argsx.readIntArgOrThrow(this, argNameResId)

inline fun Fragment.readIntArgOr(@StringRes argNameResId: Int, defaultValue: Int): Int = Argsx.readIntArgOr(this, argNameResId, defaultValue)

inline fun Fragment.readIntArgOrNull(@StringRes argNameResId: Int): Int? = Argsx.readIntArgOrNull(this, argNameResId)


inline fun Fragment.readIntArrayArgOrThrow(@StringRes argNameResId: Int): IntArray = Argsx.readIntArrayArgOrThrow(this, argNameResId)

inline fun Fragment.readIntArrayArgOr(@StringRes argNameResId: Int, defaultValue: IntArray): IntArray = Argsx.readIntArrayArgOr(this, argNameResId, defaultValue)

inline fun Fragment.readIntArrayArgOrNull(@StringRes argNameResId: Int): IntArray? = Argsx.readIntArrayArgOrNull(this, argNameResId)


inline fun Fragment.readIntArrayListArgOrThrow(@StringRes argNameResId: Int): ArrayList<Int> = Argsx.readIntArrayListArgOrThrow(this, argNameResId)

inline fun Fragment.readIntArrayListArgOr(@StringRes argNameResId: Int, defaultValue: ArrayList<Int>): ArrayList<Int> = Argsx.readIntArrayListArgOr(this, argNameResId, defaultValue)

inline fun Fragment.readIntArrayListArgOrNull(@StringRes argNameResId: Int): ArrayList<Int>? = Argsx.readIntArrayListArgOrNull(this, argNameResId)


inline fun Fragment.readLongArgOrThrow(@StringRes argNameResId: Int): Long = Argsx.readLongArgOrThrow(this, argNameResId)

inline fun Fragment.readLongArgOr(@StringRes argNameResId: Int, defaultValue: Long): Long = Argsx.readLongArgOr(this, argNameResId, defaultValue)

inline fun Fragment.readLongArgOrNull(@StringRes argNameResId: Int): Long? = Argsx.readLongArgOrNull(this, argNameResId)


inline fun Fragment.readLongArrayArgOrThrow(@StringRes argNameResId: Int): LongArray = Argsx.readLongArrayArgOrThrow(this, argNameResId)

inline fun Fragment.readLongArrayArgOr(@StringRes argNameResId: Int, defaultValue: LongArray): LongArray = Argsx.readLongArrayArgOr(this, argNameResId, defaultValue)

inline fun Fragment.readLongArrayArgOrNull(@StringRes argNameResId: Int): LongArray? = Argsx.readLongArrayArgOrNull(this, argNameResId)


inline fun Fragment.readFloatArgOrThrow(@StringRes argNameResId: Int): Float = Argsx.readFloatArgOrThrow(this, argNameResId)

inline fun Fragment.readFloatArgOr(@StringRes argNameResId: Int, defaultValue: Float): Float = Argsx.readFloatArgOr(this, argNameResId, defaultValue)

inline fun Fragment.readFloatArgOrNull(@StringRes argNameResId: Int): Float? = Argsx.readFloatArgOrNull(this, argNameResId)


inline fun Fragment.readFloatArrayArgOrThrow(@StringRes argNameResId: Int): FloatArray = Argsx.readFloatArrayArgOrThrow(this, argNameResId)

inline fun Fragment.readFloatArrayArgOr(@StringRes argNameResId: Int, defaultValue: FloatArray): FloatArray = Argsx.readFloatArrayArgOr(this, argNameResId, defaultValue)

inline fun Fragment.readFloatArrayArgOrNull(@StringRes argNameResId: Int): FloatArray? = Argsx.readFloatArrayArgOrNull(this, argNameResId)


inline fun Fragment.readDoubleArgOrThrow(@StringRes argNameResId: Int): Double = Argsx.readDoubleArgOrThrow(this, argNameResId)

inline fun Fragment.readDoubleArgOr(@StringRes argNameResId: Int, defaultValue: Double): Double = Argsx.readDoubleArgOr(this, argNameResId, defaultValue)

inline fun Fragment.readDoubleArgOrNull(@StringRes argNameResId: Int): Double? = Argsx.readDoubleArgOrNull(this, argNameResId)


inline fun Fragment.readDoubleArrayArgOrThrow(@StringRes argNameResId: Int): DoubleArray = Argsx.readDoubleArrayArgOrThrow(this, argNameResId)

inline fun Fragment.readDoubleArrayArgOr(@StringRes argNameResId: Int, defaultValue: DoubleArray): DoubleArray = Argsx.readDoubleArrayArgOr(this, argNameResId, defaultValue)

inline fun Fragment.readDoubleArrayArgOrNull(@StringRes argNameResId: Int): DoubleArray? = Argsx.readDoubleArrayArgOrNull(this, argNameResId)


inline fun Fragment.readBooleanArgOrThrow(@StringRes argNameResId: Int): Boolean = Argsx.readBooleanArgOrThrow(this, argNameResId)

inline fun Fragment.readBooleanArgOr(@StringRes argNameResId: Int, defaultValue: Boolean): Boolean = Argsx.readBooleanArgOr(this, argNameResId, defaultValue)

inline fun Fragment.readBooleanArgOrNull(@StringRes argNameResId: Int): Boolean? = Argsx.readBooleanArgOrNull(this, argNameResId)


inline fun Fragment.readBooleanArrayArgOrThrow(@StringRes argNameResId: Int): BooleanArray = Argsx.readBooleanArrayArgOrThrow(this, argNameResId)

inline fun Fragment.readBooleanArrayArgOr(@StringRes argNameResId: Int, defaultValue: BooleanArray): BooleanArray = Argsx.readBooleanArrayArgOr(this, argNameResId, defaultValue)

inline fun Fragment.readBooleanArrayArgOrNull(@StringRes argNameResId: Int): BooleanArray? = Argsx.readBooleanArrayArgOrNull(this, argNameResId)


inline fun Fragment.readCharArgOrThrow(@StringRes argNameResId: Int): Char = Argsx.readCharArgOrThrow(this, argNameResId)

inline fun Fragment.readCharArgOr(@StringRes argNameResId: Int, defaultValue: Char): Char = Argsx.readCharArgOr(this, argNameResId, defaultValue)

inline fun Fragment.readCharArgOrNull(@StringRes argNameResId: Int): Char? = Argsx.readCharArgOrNull(this, argNameResId)


inline fun Fragment.readCharArrayArgOrThrow(@StringRes argNameResId: Int): CharArray = Argsx.readCharArrayArgOrThrow(this, argNameResId)

inline fun Fragment.readCharArrayArgOr(@StringRes argNameResId: Int, defaultValue: CharArray): CharArray = Argsx.readCharArrayArgOr(this, argNameResId, defaultValue)

inline fun Fragment.readCharArrayArgOrNull(@StringRes argNameResId: Int): CharArray? = Argsx.readCharArrayArgOrNull(this, argNameResId)


inline fun Fragment.readCharSequenceArgOrThrow(@StringRes argNameResId: Int): CharSequence = Argsx.readCharSequenceArgOrThrow(this, argNameResId)

inline fun Fragment.readCharSequenceArgOr(@StringRes argNameResId: Int, defaultValue: CharSequence): CharSequence = Argsx.readCharSequenceArgOr(this, argNameResId, defaultValue)

inline fun Fragment.readCharSequenceArgOrNull(@StringRes argNameResId: Int): CharSequence? = Argsx.readCharSequenceArgOrNull(this, argNameResId)


inline fun Fragment.readCharSequenceArrayArgOrThrow(@StringRes argNameResId: Int): Array<CharSequence> = Argsx.readCharSequenceArrayArgOrThrow(this, argNameResId)

inline fun Fragment.readCharSequenceArrayArgOr(@StringRes argNameResId: Int, defaultValue: Array<CharSequence>): Array<CharSequence> = Argsx.readCharSequenceArrayArgOr(this, argNameResId, defaultValue)

inline fun Fragment.readCharSequenceArrayArgOrNull(@StringRes argNameResId: Int): Array<CharSequence>? = Argsx.readCharSequenceArrayArgOrNull(this, argNameResId)


inline fun Fragment.readCharSequenceArrayListArgOrThrow(@StringRes argNameResId: Int): ArrayList<CharSequence> = Argsx.readCharSequenceArrayListArgOrThrow(this, argNameResId)

inline fun Fragment.readCharSequenceArrayListArgOr(@StringRes argNameResId: Int, defaultValue: ArrayList<CharSequence>): ArrayList<CharSequence> = Argsx.readCharSequenceArrayListArgOr(this, argNameResId, defaultValue)

inline fun Fragment.readCharSequenceArrayListArgOrNull(@StringRes argNameResId: Int): ArrayList<CharSequence>? = Argsx.readCharSequenceArrayListArgOrNull(this, argNameResId)


inline fun Fragment.readStringArgOrThrow(@StringRes argNameResId: Int): String = Argsx.readStringArgOrThrow(this, argNameResId)

inline fun Fragment.readStringArgOr(@StringRes argNameResId: Int, defaultValue: String): String = Argsx.readStringArgOr(this, argNameResId, defaultValue)

inline fun Fragment.readStringArgOrNull(@StringRes argNameResId: Int): String? = Argsx.readStringArgOrNull(this, argNameResId)


inline fun Fragment.readStringArrayArgOrThrow(@StringRes argNameResId: Int): Array<String> = Argsx.readStringArrayArgOrThrow(this, argNameResId)

inline fun Fragment.readStringArrayArgOr(@StringRes argNameResId: Int, defaultValue: Array<String>): Array<String> = Argsx.readStringArrayArgOr(this, argNameResId, defaultValue)

inline fun Fragment.readStringArrayArgOrNull(@StringRes argNameResId: Int): Array<String>? = Argsx.readStringArrayArgOrNull(this, argNameResId)


inline fun Fragment.readStringArrayListArgOrThrow(@StringRes argNameResId: Int): ArrayList<String> = Argsx.readStringArrayListArgOrThrow(this, argNameResId)

inline fun Fragment.readStringArrayListArgOr(@StringRes argNameResId: Int, defaultValue: ArrayList<String>): ArrayList<String> = Argsx.readStringArrayListArgOr(this, argNameResId, defaultValue)

inline fun Fragment.readStringArrayListArgOrNull(@StringRes argNameResId: Int): ArrayList<String>? = Argsx.readStringArrayListArgOrNull(this, argNameResId)


inline fun <V : Parcelable> Fragment.readParcelableArgOrThrow(@StringRes argNameResId: Int): V = Argsx.readParcelableArgOrThrow(this, argNameResId)

inline fun <V : Parcelable> Fragment.readParcelableArgOr(@StringRes argNameResId: Int, defaultValue: V): V = Argsx.readParcelableArgOr(this, argNameResId, defaultValue)

inline fun <V : Parcelable> Fragment.readParcelableArgOrNull(@StringRes argNameResId: Int): V? = Argsx.readParcelableArgOrNull(this, argNameResId)


inline fun <V : Parcelable> Fragment.readParcelableArrayArgOrThrow(@StringRes argNameResId: Int): Array<V> = Argsx.readParcelableArrayArgOrThrow(this, argNameResId)

inline fun <V : Parcelable> Fragment.readParcelableArrayArgOr(@StringRes argNameResId: Int, defaultValue: Array<V>): Array<V> = Argsx.readParcelableArrayArgOr(this, argNameResId, defaultValue)

inline fun <V : Parcelable> Fragment.readParcelableArrayArgOrNull(@StringRes argNameResId: Int): Array<V>? = Argsx.readParcelableArrayArgOrNull(this, argNameResId)


inline fun <V : Parcelable> Fragment.readParcelableArrayListArgOrThrow(@StringRes argNameResId: Int): ArrayList<V> = Argsx.readParcelableArrayListArgOrThrow(this, argNameResId)

inline fun <V : Parcelable> Fragment.readParcelableArrayListArgOr(@StringRes argNameResId: Int, defaultValue: ArrayList<V>): ArrayList<V> = Argsx.readParcelableArrayListArgOr(this, argNameResId, defaultValue)

inline fun <V : Parcelable> Fragment.readParcelableArrayListArgOrNull(@StringRes argNameResId: Int): ArrayList<V>? = Argsx.readParcelableArrayListArgOrNull(this, argNameResId)


inline fun <V : Parcelable> Fragment.readSparseParcelableArrayArgOrThrow(@StringRes argNameResId: Int): SparseArray<V> = Argsx.readSparseParcelableArrayArgOrThrow(this, argNameResId)

inline fun <V : Parcelable> Fragment.readSparseParcelableArrayArgOr(@StringRes argNameResId: Int, defaultValue: SparseArray<V>): SparseArray<V> = Argsx.readSparseParcelableArrayArgOr(this, argNameResId, defaultValue)

inline fun <V : Parcelable> Fragment.readSparseParcelableArrayArgOrNull(@StringRes argNameResId: Int): SparseArray<V>? = Argsx.readSparseParcelableArrayArgOrNull(this, argNameResId)


inline fun <V : Serializable> Fragment.readSerializableArgOrThrow(@StringRes argNameResId: Int): V = Argsx.readSerializableArgOrThrow(this, argNameResId)

inline fun <V : Serializable> Fragment.readSerializableArgOr(@StringRes argNameResId: Int, defaultValue: V): V = Argsx.readSerializableArgOr(this, argNameResId, defaultValue)

inline fun <V : Serializable> Fragment.readSerializableArgOrNull(@StringRes argNameResId: Int): V? = Argsx.readSerializableArgOrNull(this, argNameResId)


inline fun Fragment.readBundleArgOrThrow(@StringRes argNameResId: Int): Bundle = Argsx.readBundleArgOrThrow(this, argNameResId)

inline fun Fragment.readBundleArgOr(@StringRes argNameResId: Int, defaultValue: Bundle): Bundle = Argsx.readBundleArgOr(this, argNameResId, defaultValue)

inline fun Fragment.readBundleArgOrNull(@StringRes argNameResId: Int): Bundle? = Argsx.readBundleArgOrNull(this, argNameResId)


@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
inline fun Fragment.readBinderArgOrThrow(@StringRes argNameResId: Int): IBinder = Argsx.readBinderArgOrThrow(this, argNameResId)

@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
inline fun Fragment.readBinderArgOr(@StringRes argNameResId: Int, defaultValue: IBinder): IBinder = Argsx.readBinderArgOr(this, argNameResId, defaultValue)

@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
inline fun Fragment.readBinderArgOrNull(@StringRes argNameResId: Int): IBinder? = Argsx.readBinderArgOrNull(this, argNameResId)


@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
inline fun Fragment.readSizeArgOrThrow(@StringRes argNameResId: Int): Size = Argsx.readSizeArgOrThrow(this, argNameResId)

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
inline fun Fragment.readSizeArgOr(@StringRes argNameResId: Int, defaultValue: Size): Size = Argsx.readSizeArgOr(this, argNameResId, defaultValue)

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
inline fun Fragment.readSizeArgOrNull(@StringRes argNameResId: Int): Size? = Argsx.readSizeArgOrNull(this, argNameResId)


@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
inline fun Fragment.readSizeFArgOrThrow(@StringRes argNameResId: Int): SizeF = Argsx.readSizeFArgOrThrow(this, argNameResId)

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
inline fun Fragment.readSizeFArgOr(@StringRes argNameResId: Int, defaultValue: SizeF): SizeF = Argsx.readSizeFArgOr(this, argNameResId, defaultValue)

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
inline fun Fragment.readSizeFArgOrNull(@StringRes argNameResId: Int): SizeF? = Argsx.readSizeFArgOrNull(this, argNameResId)