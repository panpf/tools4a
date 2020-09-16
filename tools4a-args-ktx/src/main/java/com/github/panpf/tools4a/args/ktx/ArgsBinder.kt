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
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty


class ActivityArgLazy<in REF : Activity, out OUT : Any>(private val argNameResId: Int, private val initializer: (REF, KProperty<*>) -> OUT?) : ReadOnlyProperty<REF, OUT> {
    private object EMPTY

    private var arg: Any = EMPTY

    override fun getValue(thisRef: REF, property: KProperty<*>): OUT {
        if (arg == EMPTY) {
            arg = requireNotNull(initializer(thisRef, property)) { "Not found arg '${thisRef.getString(argNameResId)}' from arguments." }
        }
        @Suppress("UNCHECKED_CAST")
        return arg as OUT
    }
}

class SupportFragmentArgLazy<in REF : Fragment, out OUT : Any>(private val argNameResId: Int, private val initializer: (REF, KProperty<*>) -> OUT?) : ReadOnlyProperty<REF, OUT> {
    private object EMPTY

    private var arg: Any = EMPTY

    override fun getValue(thisRef: REF, property: KProperty<*>): OUT {
        if (arg == EMPTY) {
            arg = requireNotNull(initializer(thisRef, property)) { "Not found arg '${thisRef.resources.getString(argNameResId)}' from arguments." }
        }
        @Suppress("UNCHECKED_CAST")
        return arg as OUT
    }
}

class ArgLazy<in REF, out OUT : Any>(private val argName: String, private val initializer: (REF, KProperty<*>) -> OUT?) : ReadOnlyProperty<REF, OUT> {
    private object EMPTY

    private var arg: Any = EMPTY

    override fun getValue(thisRef: REF, property: KProperty<*>): OUT {
        if (arg == EMPTY) {
            arg = requireNotNull(initializer(thisRef, property)) { "Not found arg '$argName' from arguments." }
        }
        @Suppress("UNCHECKED_CAST")
        return arg as OUT
    }
}

class OptionalArgLazy<in REF, out OUT>(private val initializer: (REF, KProperty<*>) -> OUT?) : ReadOnlyProperty<REF, OUT?> {
    private object EMPTY

    private var arg: Any? = EMPTY

    override fun getValue(thisRef: REF, property: KProperty<*>): OUT? {
        if (arg == EMPTY) {
            arg = initializer(thisRef, property)
        }
        @Suppress("UNCHECKED_CAST")
        return arg as OUT
    }
}


/* ************************************* Activity Intent Arg ***************************************** */


fun Activity.bindByteArgOrThrow(argName: String): ReadOnlyProperty<Activity, Byte> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readByteArgOrThrow(argName) }

fun Activity.bindByteArgOr(argName: String, defaultValue: Byte = 0.toByte()): ReadOnlyProperty<Activity, Byte> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readByteArgOr(argName, defaultValue) }

fun Activity.bindByteArgOrNull(argName: String): ReadOnlyProperty<Activity, Byte?> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readByteArgOrNull(argName) }


fun Activity.bindByteArrayArgOrThrow(argName: String): ReadOnlyProperty<Activity, ByteArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readByteArrayArgOrThrow(argName) }

fun Activity.bindByteArrayArgOr(argName: String, defaultValue: ByteArray): ReadOnlyProperty<Activity, ByteArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readByteArrayArgOr(argName, defaultValue) }

fun Activity.bindByteArrayArgOrNull(argName: String): ReadOnlyProperty<Activity, ByteArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readByteArrayArgOrNull(argName) }


fun Activity.bindShortArgOrThrow(argName: String): ReadOnlyProperty<Activity, Short> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readShortArgOrThrow(argName) }

fun Activity.bindShortArgOr(argName: String, defaultValue: Short = 0.toShort()): ReadOnlyProperty<Activity, Short> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readShortArgOr(argName, defaultValue) }

fun Activity.bindShortArgOrNull(argName: String): ReadOnlyProperty<Activity, Short?> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readShortArgOrNull(argName) }


fun Activity.bindShortArrayArgOrThrow(argName: String): ReadOnlyProperty<Activity, ShortArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readShortArrayArgOrThrow(argName) }

fun Activity.bindShortArrayArgOr(argName: String, defaultValue: ShortArray): ReadOnlyProperty<Activity, ShortArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readShortArrayArgOr(argName, defaultValue) }

fun Activity.bindShortArrayArgOrNull(argName: String): ReadOnlyProperty<Activity, ShortArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readShortArrayArgOrNull(argName) }


fun Activity.bindIntArgOrThrow(argName: String): ReadOnlyProperty<Activity, Int> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readIntArgOrThrow(argName) }

fun Activity.bindIntArgOr(argName: String, defaultValue: Int = 0): ReadOnlyProperty<Activity, Int> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readIntArgOr(argName, defaultValue) }

fun Activity.bindIntArgOrNull(argName: String): ReadOnlyProperty<Activity, Int?> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readIntArgOrNull(argName) }


fun Activity.bindIntArrayArgOrThrow(argName: String): ReadOnlyProperty<Activity, IntArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readIntArrayArgOrThrow(argName) }

fun Activity.bindIntArrayArgOr(argName: String, defaultValue: IntArray): ReadOnlyProperty<Activity, IntArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readIntArrayArgOr(argName, defaultValue) }

fun Activity.bindIntArrayArgOrNull(argName: String): ReadOnlyProperty<Activity, IntArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readIntArrayArgOrNull(argName) }


fun Activity.bindIntArrayListArgOrThrow(argName: String): ReadOnlyProperty<Activity, ArrayList<Int>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readIntArrayListArgOrThrow(argName) }

fun Activity.bindIntArrayListArgOr(argName: String, defaultValue: ArrayList<Int>): ReadOnlyProperty<Activity, ArrayList<Int>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readIntArrayListArgOr(argName, defaultValue) }

fun Activity.bindIntArrayListArgOrNull(argName: String): ReadOnlyProperty<Activity, ArrayList<Int>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readIntArrayListArgOrNull(argName) }


fun Activity.bindLongArgOrThrow(argName: String): ReadOnlyProperty<Activity, Long> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readLongArgOrThrow(argName) }

fun Activity.bindLongArgOr(argName: String, defaultValue: Long = 0L): ReadOnlyProperty<Activity, Long> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readLongArgOr(argName, defaultValue) }

fun Activity.bindLongArgOrNull(argName: String): ReadOnlyProperty<Activity, Long?> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readLongArgOrNull(argName) }


fun Activity.bindLongArrayArgOrThrow(argName: String): ReadOnlyProperty<Activity, LongArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readLongArrayArgOrThrow(argName) }

fun Activity.bindLongArrayArgOr(argName: String, defaultValue: LongArray): ReadOnlyProperty<Activity, LongArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readLongArrayArgOr(argName, defaultValue) }

fun Activity.bindLongArrayArgOrNull(argName: String): ReadOnlyProperty<Activity, LongArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readLongArrayArgOrNull(argName) }


fun Activity.bindFloatArgOrThrow(argName: String): ReadOnlyProperty<Activity, Float> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readFloatArgOrThrow(argName) }

fun Activity.bindFloatArgOr(argName: String, defaultValue: Float = 0.toFloat()): ReadOnlyProperty<Activity, Float> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readFloatArgOr(argName, defaultValue) }

fun Activity.bindFloatArgOrNull(argName: String): ReadOnlyProperty<Activity, Float?> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readFloatArgOrNull(argName) }


fun Activity.bindFloatArrayArgOrThrow(argName: String): ReadOnlyProperty<Activity, FloatArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readFloatArrayArgOrThrow(argName) }

fun Activity.bindFloatArrayArgOr(argName: String, defaultValue: FloatArray): ReadOnlyProperty<Activity, FloatArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readFloatArrayArgOr(argName, defaultValue) }

fun Activity.bindFloatArrayArgOrNull(argName: String): ReadOnlyProperty<Activity, FloatArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readFloatArrayArgOrNull(argName) }


fun Activity.bindDoubleArgOrThrow(argName: String): ReadOnlyProperty<Activity, Double> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readDoubleArgOrThrow(argName) }

fun Activity.bindDoubleArgOr(argName: String, defaultValue: Double = 0.toDouble()): ReadOnlyProperty<Activity, Double> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readDoubleArgOr(argName, defaultValue) }

fun Activity.bindDoubleArgOrNull(argName: String): ReadOnlyProperty<Activity, Double?> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readDoubleArgOrNull(argName) }


fun Activity.bindDoubleArrayArgOrThrow(argName: String): ReadOnlyProperty<Activity, DoubleArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readDoubleArrayArgOrThrow(argName) }

fun Activity.bindDoubleArrayArgOr(argName: String, defaultValue: DoubleArray): ReadOnlyProperty<Activity, DoubleArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readDoubleArrayArgOr(argName, defaultValue) }

fun Activity.bindDoubleArrayArgOrNull(argName: String): ReadOnlyProperty<Activity, DoubleArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readDoubleArrayArgOrNull(argName) }


fun Activity.bindBooleanArgOrThrow(argName: String): ReadOnlyProperty<Activity, Boolean> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readBooleanArgOrThrow(argName) }

fun Activity.bindBooleanArgOr(argName: String, defaultValue: Boolean = false): ReadOnlyProperty<Activity, Boolean> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readBooleanArgOr(argName, defaultValue) }

fun Activity.bindBooleanArgOrNull(argName: String): ReadOnlyProperty<Activity, Boolean?> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readBooleanArgOrNull(argName) }


fun Activity.bindBooleanArrayArgOrThrow(argName: String): ReadOnlyProperty<Activity, BooleanArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readBooleanArrayArgOrThrow(argName) }

fun Activity.bindBooleanArrayArgOr(argName: String, defaultValue: BooleanArray): ReadOnlyProperty<Activity, BooleanArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readBooleanArrayArgOr(argName, defaultValue) }

fun Activity.bindBooleanArrayArgOrNull(argName: String): ReadOnlyProperty<Activity, BooleanArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readBooleanArrayArgOrNull(argName) }


fun Activity.bindCharArgOrThrow(argName: String): ReadOnlyProperty<Activity, Char> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readCharArgOrThrow(argName) }

fun Activity.bindCharArgOr(argName: String, defaultValue: Char = 0.toChar()): ReadOnlyProperty<Activity, Char> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readCharArgOr(argName, defaultValue) }

fun Activity.bindCharArgOrNull(argName: String): ReadOnlyProperty<Activity, Char?> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readCharArgOrNull(argName) }


fun Activity.bindCharArrayArgOrThrow(argName: String): ReadOnlyProperty<Activity, CharArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readCharArrayArgOrThrow(argName) }

fun Activity.bindCharArrayArgOr(argName: String, defaultValue: CharArray): ReadOnlyProperty<Activity, CharArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readCharArrayArgOr(argName, defaultValue) }

fun Activity.bindCharArrayArgOrNull(argName: String): ReadOnlyProperty<Activity, CharArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readCharArrayArgOrNull(argName) }


fun Activity.bindCharSequenceArgOrThrow(argName: String): ReadOnlyProperty<Activity, CharSequence> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readCharSequenceArgOrThrow(argName) }

fun Activity.bindCharSequenceArgOr(argName: String, defaultValue: CharSequence): ReadOnlyProperty<Activity, CharSequence> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readCharSequenceArgOr(argName, defaultValue) }

fun Activity.bindCharSequenceArgOrNull(argName: String): ReadOnlyProperty<Activity, CharSequence?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readCharSequenceArgOrNull(argName) }


fun Activity.bindCharSequenceArrayArgOrThrow(argName: String): ReadOnlyProperty<Activity, Array<CharSequence>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readCharSequenceArrayArgOrThrow(argName) }

fun Activity.bindCharSequenceArrayArgOr(argName: String, defaultValue: Array<CharSequence>): ReadOnlyProperty<Activity, Array<CharSequence>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readCharSequenceArrayArgOr(argName, defaultValue) }

fun Activity.bindCharSequenceArrayArgOrNull(argName: String): ReadOnlyProperty<Activity, Array<CharSequence>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readCharSequenceArrayArgOrNull(argName) }


fun Activity.bindCharSequenceArrayListArgOrThrow(argName: String): ReadOnlyProperty<Activity, ArrayList<CharSequence>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readCharSequenceArrayListArgOrThrow(argName) }

fun Activity.bindCharSequenceArrayListArgOr(argName: String, defaultValue: ArrayList<CharSequence>): ReadOnlyProperty<Activity, ArrayList<CharSequence>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readCharSequenceArrayListArgOr(argName, defaultValue) }

fun Activity.bindCharSequenceArrayListArgOrNull(argName: String): ReadOnlyProperty<Activity, ArrayList<CharSequence>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readCharSequenceArrayListArgOrNull(argName) }


fun Activity.bindStringArgOrThrow(argName: String): ReadOnlyProperty<Activity, String> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readStringArgOrThrow(argName) }

fun Activity.bindStringArgOr(argName: String, defaultValue: String): ReadOnlyProperty<Activity, String> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readStringArgOr(argName, defaultValue) }

fun Activity.bindStringArgOrNull(argName: String): ReadOnlyProperty<Activity, String?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readStringArgOrNull(argName) }


fun Activity.bindStringArrayArgOrThrow(argName: String): ReadOnlyProperty<Activity, Array<String>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readStringArrayArgOrThrow(argName) }

fun Activity.bindStringArrayArgOr(argName: String, defaultValue: Array<String>): ReadOnlyProperty<Activity, Array<String>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readStringArrayArgOr(argName, defaultValue) }

fun Activity.bindStringArrayArgOrNull(argName: String): ReadOnlyProperty<Activity, Array<String>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readStringArrayArgOrNull(argName) }


fun Activity.bindStringArrayListArgOrThrow(argName: String): ReadOnlyProperty<Activity, ArrayList<String>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readStringArrayListArgOrThrow(argName) }

fun Activity.bindStringArrayListArgOr(argName: String, defaultValue: ArrayList<String>): ReadOnlyProperty<Activity, ArrayList<String>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readStringArrayListArgOr(argName, defaultValue) }

fun Activity.bindStringArrayListArgOrNull(argName: String): ReadOnlyProperty<Activity, ArrayList<String>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readStringArrayListArgOrNull(argName) }


fun <V : Parcelable> Activity.bindParcelableArgOrThrow(argName: String): ReadOnlyProperty<Activity, V> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readParcelableArgOrThrow(argName) }

fun <V : Parcelable> Activity.bindParcelableArgOr(argName: String, defaultValue: V): ReadOnlyProperty<Activity, V> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readParcelableArgOr(argName, defaultValue) }

fun <V : Parcelable> Activity.bindParcelableArgOrNull(argName: String): ReadOnlyProperty<Activity, V?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readParcelableArgOrNull(argName) }


@Suppress("UNCHECKED_CAST")
fun <V : Parcelable> Activity.bindParcelableArrayArgOrThrow(argName: String): ReadOnlyProperty<Activity, Array<V>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readParcelableArrayArgOrThrow(argName) }

@Suppress("UNCHECKED_CAST")
fun <V : Parcelable> Activity.bindParcelableArrayArgOr(argName: String, defaultValue: Array<V>): ReadOnlyProperty<Activity, Array<V>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readParcelableArrayArgOr(argName, defaultValue) }

@Suppress("UNCHECKED_CAST")
fun <V : Parcelable> Activity.bindParcelableArrayArgOrNull(argName: String): ReadOnlyProperty<Activity, Array<V>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readParcelableArrayArgOrNull(argName) }


fun <V : Parcelable> Activity.bindParcelableArrayListArgOrThrow(argName: String): ReadOnlyProperty<Activity, ArrayList<V>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readParcelableArrayListArgOrThrow(argName) }

fun <V : Parcelable> Activity.bindParcelableArrayListArgOr(argName: String, defaultValue: ArrayList<V>): ReadOnlyProperty<Activity, ArrayList<V>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readParcelableArrayListArgOr(argName, defaultValue) }

fun <V : Parcelable> Activity.bindParcelableArrayListArgOrNull(argName: String): ReadOnlyProperty<Activity, ArrayList<V>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readParcelableArrayListArgOrNull(argName) }


@Suppress("UNCHECKED_CAST")
fun <V : java.io.Serializable> Activity.bindSerializableArgOrThrow(argName: String): ReadOnlyProperty<Activity, V> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readSerializableArgOrThrow(argName) }

@Suppress("UNCHECKED_CAST")
fun <V : java.io.Serializable> Activity.bindSerializableArgOr(argName: String, defaultValue: V): ReadOnlyProperty<Activity, V> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readSerializableArgOr(argName, defaultValue) }

@Suppress("UNCHECKED_CAST")
fun <V : java.io.Serializable> Activity.bindSerializableArgOrNull(argName: String): ReadOnlyProperty<Activity, V?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readSerializableArgOrNull(argName) }


fun Activity.bindBundleArgOrThrow(argName: String): ReadOnlyProperty<Activity, Bundle> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readBundleArgOrThrow(argName) }

fun Activity.bindBundleArgOr(argName: String, defaultValue: Bundle): ReadOnlyProperty<Activity, Bundle> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readBundleArgOr(argName, defaultValue) }

fun Activity.bindBundleArgOrNull(argName: String): ReadOnlyProperty<Activity, Bundle?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readBundleArgOrNull(argName) }


fun Activity.bindExtrasArgOrThrow(): ReadOnlyProperty<Activity, Bundle> =
        ArgLazy("extras") { _, _: KProperty<*> -> this.readExtrasArgOrThrow() }

fun Activity.bindExtrasArgOr(defaultValue: Bundle): ReadOnlyProperty<Activity, Bundle> =
        ArgLazy("extras") { _, _: KProperty<*> -> this.readExtrasArgOr(defaultValue) }

fun Activity.bindExtrasArgOrNull(): ReadOnlyProperty<Activity, Bundle?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readExtrasArgOrNull() }


/* ************************************* Activity Intent Arg ***************************************** */


fun Activity.bindByteArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, Byte> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readByteArgOrThrow(argNameResId) }

fun Activity.bindByteArgOr(@StringRes argNameResId: Int, defaultValue: Byte = 0.toByte()): ReadOnlyProperty<Activity, Byte> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readByteArgOr(argNameResId, defaultValue) }

fun Activity.bindByteArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, Byte?> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readByteArgOrNull(argNameResId) }


fun Activity.bindByteArrayArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, ByteArray> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readByteArrayArgOrThrow(argNameResId) }

fun Activity.bindByteArrayArgOr(@StringRes argNameResId: Int, defaultValue: ByteArray): ReadOnlyProperty<Activity, ByteArray> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readByteArrayArgOr(argNameResId, defaultValue) }

fun Activity.bindByteArrayArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, ByteArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readByteArrayArgOrNull(argNameResId) }


fun Activity.bindShortArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, Short> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readShortArgOrThrow(argNameResId) }

fun Activity.bindShortArgOr(@StringRes argNameResId: Int, defaultValue: Short = 0.toShort()): ReadOnlyProperty<Activity, Short> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readShortArgOr(argNameResId, defaultValue) }

fun Activity.bindShortArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, Short?> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readShortArgOrNull(argNameResId) }


fun Activity.bindShortArrayArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, ShortArray> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readShortArrayArgOrThrow(argNameResId) }

fun Activity.bindShortArrayArgOr(@StringRes argNameResId: Int, defaultValue: ShortArray): ReadOnlyProperty<Activity, ShortArray> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readShortArrayArgOr(argNameResId, defaultValue) }

fun Activity.bindShortArrayArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, ShortArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readShortArrayArgOrNull(argNameResId) }


fun Activity.bindIntArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, Int> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readIntArgOrThrow(argNameResId) }

fun Activity.bindIntArgOr(@StringRes argNameResId: Int, defaultValue: Int = 0): ReadOnlyProperty<Activity, Int> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readIntArgOr(argNameResId, defaultValue) }

fun Activity.bindIntArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, Int?> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readIntArgOrNull(argNameResId) }


fun Activity.bindIntArrayArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, IntArray> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readIntArrayArgOrThrow(argNameResId) }

fun Activity.bindIntArrayArgOr(@StringRes argNameResId: Int, defaultValue: IntArray): ReadOnlyProperty<Activity, IntArray> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readIntArrayArgOr(argNameResId, defaultValue) }

fun Activity.bindIntArrayArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, IntArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readIntArrayArgOrNull(argNameResId) }


fun Activity.bindIntArrayListArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, ArrayList<Int>> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readIntArrayListArgOrThrow(argNameResId) }

fun Activity.bindIntArrayListArgOr(@StringRes argNameResId: Int, defaultValue: ArrayList<Int>): ReadOnlyProperty<Activity, ArrayList<Int>> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readIntArrayListArgOr(argNameResId, defaultValue) }

fun Activity.bindIntArrayListArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, ArrayList<Int>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readIntArrayListArgOrNull(argNameResId) }


fun Activity.bindLongArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, Long> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readLongArgOrThrow(argNameResId) }

fun Activity.bindLongArgOr(@StringRes argNameResId: Int, defaultValue: Long = 0L): ReadOnlyProperty<Activity, Long> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readLongArgOr(argNameResId, defaultValue) }

fun Activity.bindLongArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, Long?> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readLongArgOrNull(argNameResId) }


fun Activity.bindLongArrayArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, LongArray> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readLongArrayArgOrThrow(argNameResId) }

fun Activity.bindLongArrayArgOr(@StringRes argNameResId: Int, defaultValue: LongArray): ReadOnlyProperty<Activity, LongArray> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readLongArrayArgOr(argNameResId, defaultValue) }

fun Activity.bindLongArrayArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, LongArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readLongArrayArgOrNull(argNameResId) }


fun Activity.bindFloatArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, Float> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readFloatArgOrThrow(argNameResId) }

fun Activity.bindFloatArgOr(@StringRes argNameResId: Int, defaultValue: Float = 0.toFloat()): ReadOnlyProperty<Activity, Float> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readFloatArgOr(argNameResId, defaultValue) }

fun Activity.bindFloatArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, Float?> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readFloatArgOrNull(argNameResId) }


fun Activity.bindFloatArrayArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, FloatArray> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readFloatArrayArgOrThrow(argNameResId) }

fun Activity.bindFloatArrayArgOr(@StringRes argNameResId: Int, defaultValue: FloatArray): ReadOnlyProperty<Activity, FloatArray> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readFloatArrayArgOr(argNameResId, defaultValue) }

fun Activity.bindFloatArrayArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, FloatArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readFloatArrayArgOrNull(argNameResId) }


fun Activity.bindDoubleArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, Double> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readDoubleArgOrThrow(argNameResId) }

fun Activity.bindDoubleArgOr(@StringRes argNameResId: Int, defaultValue: Double = 0.toDouble()): ReadOnlyProperty<Activity, Double> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readDoubleArgOr(argNameResId, defaultValue) }

fun Activity.bindDoubleArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, Double?> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readDoubleArgOrNull(argNameResId) }


fun Activity.bindDoubleArrayArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, DoubleArray> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readDoubleArrayArgOrThrow(argNameResId) }

fun Activity.bindDoubleArrayArgOr(@StringRes argNameResId: Int, defaultValue: DoubleArray): ReadOnlyProperty<Activity, DoubleArray> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readDoubleArrayArgOr(argNameResId, defaultValue) }

fun Activity.bindDoubleArrayArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, DoubleArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readDoubleArrayArgOrNull(argNameResId) }


fun Activity.bindBooleanArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, Boolean> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readBooleanArgOrThrow(argNameResId) }

fun Activity.bindBooleanArgOr(@StringRes argNameResId: Int, defaultValue: Boolean = false): ReadOnlyProperty<Activity, Boolean> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readBooleanArgOr(argNameResId, defaultValue) }

fun Activity.bindBooleanArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, Boolean?> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readBooleanArgOrNull(argNameResId) }


fun Activity.bindBooleanArrayArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, BooleanArray> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readBooleanArrayArgOrThrow(argNameResId) }

fun Activity.bindBooleanArrayArgOr(@StringRes argNameResId: Int, defaultValue: BooleanArray): ReadOnlyProperty<Activity, BooleanArray> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readBooleanArrayArgOr(argNameResId, defaultValue) }

fun Activity.bindBooleanArrayArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, BooleanArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readBooleanArrayArgOrNull(argNameResId) }


fun Activity.bindCharArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, Char> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readCharArgOrThrow(argNameResId) }

fun Activity.bindCharArgOr(@StringRes argNameResId: Int, defaultValue: Char = 0.toChar()): ReadOnlyProperty<Activity, Char> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readCharArgOr(argNameResId, defaultValue) }

fun Activity.bindCharArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, Char?> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readCharArgOrNull(argNameResId) }


fun Activity.bindCharArrayArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, CharArray> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readCharArrayArgOrThrow(argNameResId) }

fun Activity.bindCharArrayArgOr(@StringRes argNameResId: Int, defaultValue: CharArray): ReadOnlyProperty<Activity, CharArray> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readCharArrayArgOr(argNameResId, defaultValue) }

fun Activity.bindCharArrayArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, CharArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readCharArrayArgOrNull(argNameResId) }


fun Activity.bindCharSequenceArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, CharSequence> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readCharSequenceArgOrThrow(argNameResId) }

fun Activity.bindCharSequenceArgOr(@StringRes argNameResId: Int, defaultValue: CharSequence): ReadOnlyProperty<Activity, CharSequence> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readCharSequenceArgOr(argNameResId, defaultValue) }

fun Activity.bindCharSequenceArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, CharSequence?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readCharSequenceArgOrNull(argNameResId) }


fun Activity.bindCharSequenceArrayArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, Array<CharSequence>> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readCharSequenceArrayArgOrThrow(argNameResId) }

fun Activity.bindCharSequenceArrayArgOr(@StringRes argNameResId: Int, defaultValue: Array<CharSequence>): ReadOnlyProperty<Activity, Array<CharSequence>> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readCharSequenceArrayArgOr(argNameResId, defaultValue) }

fun Activity.bindCharSequenceArrayArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, Array<CharSequence>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readCharSequenceArrayArgOrNull(argNameResId) }


fun Activity.bindCharSequenceArrayListArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, ArrayList<CharSequence>> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readCharSequenceArrayListArgOrThrow(argNameResId) }

fun Activity.bindCharSequenceArrayListArgOr(@StringRes argNameResId: Int, defaultValue: ArrayList<CharSequence>): ReadOnlyProperty<Activity, ArrayList<CharSequence>> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readCharSequenceArrayListArgOr(argNameResId, defaultValue) }

fun Activity.bindCharSequenceArrayListArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, ArrayList<CharSequence>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readCharSequenceArrayListArgOrNull(argNameResId) }


fun Activity.bindStringArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, String> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readStringArgOrThrow(argNameResId) }

fun Activity.bindStringArgOr(@StringRes argNameResId: Int, defaultValue: String): ReadOnlyProperty<Activity, String> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readStringArgOr(argNameResId, defaultValue) }

fun Activity.bindStringArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, String?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readStringArgOrNull(argNameResId) }


fun Activity.bindStringArrayArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, Array<String>> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readStringArrayArgOrThrow(argNameResId) }

fun Activity.bindStringArrayArgOr(@StringRes argNameResId: Int, defaultValue: Array<String>): ReadOnlyProperty<Activity, Array<String>> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readStringArrayArgOr(argNameResId, defaultValue) }

fun Activity.bindStringArrayArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, Array<String>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readStringArrayArgOrNull(argNameResId) }


fun Activity.bindStringArrayListArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, ArrayList<String>> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readStringArrayListArgOrThrow(argNameResId) }

fun Activity.bindStringArrayListArgOr(@StringRes argNameResId: Int, defaultValue: ArrayList<String>): ReadOnlyProperty<Activity, ArrayList<String>> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readStringArrayListArgOr(argNameResId, defaultValue) }

fun Activity.bindStringArrayListArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, ArrayList<String>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readStringArrayListArgOrNull(argNameResId) }


fun <V : Parcelable> Activity.bindParcelableArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, V> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readParcelableArgOrThrow(argNameResId) }

fun <V : Parcelable> Activity.bindParcelableArgOr(@StringRes argNameResId: Int, defaultValue: V): ReadOnlyProperty<Activity, V> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readParcelableArgOr(argNameResId, defaultValue) }

fun <V : Parcelable> Activity.bindParcelableArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, V?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readParcelableArgOrNull(argNameResId) }


@Suppress("UNCHECKED_CAST")
fun <V : Parcelable> Activity.bindParcelableArrayArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, Array<V>> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readParcelableArrayArgOrThrow(argNameResId) }

@Suppress("UNCHECKED_CAST")
fun <V : Parcelable> Activity.bindParcelableArrayArgOr(@StringRes argNameResId: Int, defaultValue: Array<V>): ReadOnlyProperty<Activity, Array<V>> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readParcelableArrayArgOr(argNameResId, defaultValue) }

@Suppress("UNCHECKED_CAST")
fun <V : Parcelable> Activity.bindParcelableArrayArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, Array<V>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readParcelableArrayArgOrNull(argNameResId) }


fun <V : Parcelable> Activity.bindParcelableArrayListArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, ArrayList<V>> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readParcelableArrayListArgOrThrow(argNameResId) }

fun <V : Parcelable> Activity.bindParcelableArrayListArgOr(@StringRes argNameResId: Int, defaultValue: ArrayList<V>): ReadOnlyProperty<Activity, ArrayList<V>> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readParcelableArrayListArgOr(argNameResId, defaultValue) }

fun <V : Parcelable> Activity.bindParcelableArrayListArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, ArrayList<V>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readParcelableArrayListArgOrNull(argNameResId) }


@Suppress("UNCHECKED_CAST")
fun <V : java.io.Serializable> Activity.bindSerializableArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, V> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readSerializableArgOrThrow(argNameResId) }

@Suppress("UNCHECKED_CAST")
fun <V : java.io.Serializable> Activity.bindSerializableArgOr(@StringRes argNameResId: Int, defaultValue: V): ReadOnlyProperty<Activity, V> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readSerializableArgOr(argNameResId, defaultValue) }

@Suppress("UNCHECKED_CAST")
fun <V : java.io.Serializable> Activity.bindSerializableArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, V?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readSerializableArgOrNull(argNameResId) }


fun Activity.bindBundleArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, Bundle> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readBundleArgOrThrow(argNameResId) }

fun Activity.bindBundleArgOr(@StringRes argNameResId: Int, defaultValue: Bundle): ReadOnlyProperty<Activity, Bundle> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readBundleArgOr(argNameResId, defaultValue) }

fun Activity.bindBundleArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, Bundle?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readBundleArgOrNull(argNameResId) }


/* ************************************* Activity Intent Uri Arg ***************************************** */


fun Activity.bindByteIntentUriArgOrThrow(argName: String): ReadOnlyProperty<Activity, Byte> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readByteIntentUriArgOrThrow(argName) }

fun Activity.bindByteIntentUriArgOr(argName: String, defaultValue: Byte): ReadOnlyProperty<Activity, Byte> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readByteIntentUriArgOr(argName, defaultValue) }

fun Activity.bindByteIntentUriArgOrNull(argName: String): ReadOnlyProperty<Activity, Byte?> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readByteIntentUriArgOrNull(argName) }


fun Activity.bindShortIntentUriArgOrThrow(argName: String): ReadOnlyProperty<Activity, Short> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readShortIntentUriArgOrThrow(argName) }

fun Activity.bindShortIntentUriArgOr(argName: String, defaultValue: Short): ReadOnlyProperty<Activity, Short> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readShortIntentUriArgOr(argName, defaultValue) }

fun Activity.bindShortIntentUriArgOrNull(argName: String): ReadOnlyProperty<Activity, Short?> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readShortIntentUriArgOrNull(argName) }


fun Activity.bindIntIntentUriArgOrThrow(argName: String): ReadOnlyProperty<Activity, Int> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readIntIntentUriArgOrThrow(argName) }

fun Activity.bindIntIntentUriArgOr(argName: String, defaultValue: Int): ReadOnlyProperty<Activity, Int> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readIntIntentUriArgOr(argName, defaultValue) }

fun Activity.bindIntIntentUriArgOrNull(argName: String): ReadOnlyProperty<Activity, Int?> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readIntIntentUriArgOrNull(argName) }


fun Activity.bindLongIntentUriArgOrThrow(argName: String): ReadOnlyProperty<Activity, Long> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readLongIntentUriArgOrThrow(argName) }

fun Activity.bindLongIntentUriArgOr(argName: String, defaultValue: Long): ReadOnlyProperty<Activity, Long> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readLongIntentUriArgOr(argName, defaultValue) }

fun Activity.bindLongIntentUriArgOrNull(argName: String): ReadOnlyProperty<Activity, Long?> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readLongIntentUriArgOrNull(argName) }


fun Activity.bindFloatIntentUriArgOrThrow(argName: String): ReadOnlyProperty<Activity, Float> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readFloatIntentUriArgOrThrow(argName) }

fun Activity.bindFloatIntentUriArgOr(argName: String, defaultValue: Float): ReadOnlyProperty<Activity, Float> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readFloatIntentUriArgOr(argName, defaultValue) }

fun Activity.bindFloatIntentUriArgOrNull(argName: String): ReadOnlyProperty<Activity, Float?> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readFloatIntentUriArgOrNull(argName) }


fun Activity.bindDoubleIntentUriArgOrThrow(argName: String): ReadOnlyProperty<Activity, Double> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readDoubleIntentUriArgOrThrow(argName) }

fun Activity.bindDoubleIntentUriArgOr(argName: String, defaultValue: Double): ReadOnlyProperty<Activity, Double> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readDoubleIntentUriArgOr(argName, defaultValue) }

fun Activity.bindDoubleIntentUriArgOrNull(argName: String): ReadOnlyProperty<Activity, Double?> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readDoubleIntentUriArgOrNull(argName) }


fun Activity.bindBooleanIntentUriArgOrThrow(argName: String): ReadOnlyProperty<Activity, Boolean> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readBooleanIntentUriArgOrThrow(argName) }

fun Activity.bindBooleanIntentUriArgOr(argName: String, defaultValue: Boolean): ReadOnlyProperty<Activity, Boolean> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readBooleanIntentUriArgOr(argName, defaultValue) }

fun Activity.bindBooleanIntentUriArgOrNull(argName: String): ReadOnlyProperty<Activity, Boolean?> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readBooleanIntentUriArgOrNull(argName) }


fun Activity.bindStringIntentUriArgOrThrow(argName: String): ReadOnlyProperty<Activity, String> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readStringIntentUriArgOrThrow(argName) }

fun Activity.bindStringIntentUriArgOr(argName: String, defaultValue: String): ReadOnlyProperty<Activity, String> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readStringIntentUriArgOr(argName, defaultValue) }

fun Activity.bindStringIntentUriArgOrNull(argName: String): ReadOnlyProperty<Activity, String?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readStringIntentUriArgOrNull(argName) }


fun Activity.bindByteIntentUriArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, Byte> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readByteIntentUriArgOrThrow(argNameResId) }

fun Activity.bindByteIntentUriArgOr(@StringRes argNameResId: Int, defaultValue: Byte): ReadOnlyProperty<Activity, Byte> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readByteIntentUriArgOr(argNameResId, defaultValue) }

fun Activity.bindByteIntentUriArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, Byte?> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readByteIntentUriArgOrNull(argNameResId) }


fun Activity.bindShortIntentUriArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, Short> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readShortIntentUriArgOrThrow(argNameResId) }

fun Activity.bindShortIntentUriArgOr(@StringRes argNameResId: Int, defaultValue: Short): ReadOnlyProperty<Activity, Short> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readShortIntentUriArgOr(argNameResId, defaultValue) }

fun Activity.bindShortIntentUriArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, Short?> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readShortIntentUriArgOrNull(argNameResId) }


fun Activity.bindIntIntentUriArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, Int> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readIntIntentUriArgOrThrow(argNameResId) }

fun Activity.bindIntIntentUriArgOr(@StringRes argNameResId: Int, defaultValue: Int): ReadOnlyProperty<Activity, Int> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readIntIntentUriArgOr(argNameResId, defaultValue) }

fun Activity.bindIntIntentUriArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, Int?> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readIntIntentUriArgOrNull(argNameResId) }


fun Activity.bindLongIntentUriArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, Long> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readLongIntentUriArgOrThrow(argNameResId) }

fun Activity.bindLongIntentUriArgOr(@StringRes argNameResId: Int, defaultValue: Long): ReadOnlyProperty<Activity, Long> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readLongIntentUriArgOr(argNameResId, defaultValue) }

fun Activity.bindLongIntentUriArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, Long?> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readLongIntentUriArgOrNull(argNameResId) }


fun Activity.bindFloatIntentUriArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, Float> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readFloatIntentUriArgOrThrow(argNameResId) }

fun Activity.bindFloatIntentUriArgOr(@StringRes argNameResId: Int, defaultValue: Float): ReadOnlyProperty<Activity, Float> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readFloatIntentUriArgOr(argNameResId, defaultValue) }

fun Activity.bindFloatIntentUriArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, Float?> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readFloatIntentUriArgOrNull(argNameResId) }


fun Activity.bindDoubleIntentUriArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, Double> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readDoubleIntentUriArgOrThrow(argNameResId) }

fun Activity.bindDoubleIntentUriArgOr(@StringRes argNameResId: Int, defaultValue: Double): ReadOnlyProperty<Activity, Double> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readDoubleIntentUriArgOr(argNameResId, defaultValue) }

fun Activity.bindDoubleIntentUriArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, Double?> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readDoubleIntentUriArgOrNull(argNameResId) }


fun Activity.bindBooleanIntentUriArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, Boolean> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readBooleanIntentUriArgOrThrow(argNameResId) }

fun Activity.bindBooleanIntentUriArgOr(@StringRes argNameResId: Int, defaultValue: Boolean): ReadOnlyProperty<Activity, Boolean> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readBooleanIntentUriArgOr(argNameResId, defaultValue) }

fun Activity.bindBooleanIntentUriArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, Boolean?> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readBooleanIntentUriArgOrNull(argNameResId) }


fun Activity.bindStringIntentUriArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, String> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readStringIntentUriArgOrThrow(argNameResId) }

fun Activity.bindStringIntentUriArgOr(@StringRes argNameResId: Int, defaultValue: String): ReadOnlyProperty<Activity, String> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readStringIntentUriArgOr(argNameResId, defaultValue) }

fun Activity.bindStringIntentUriArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, String?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readStringIntentUriArgOrNull(argNameResId) }


/* ************************************* Activity Uri Arg ***************************************** */


fun Activity.bindByteUriArgOrThrow(argName: String): ReadOnlyProperty<Activity, Byte> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readByteUriArgOrThrow(argName) }

fun Activity.bindByteUriArgOr(argName: String, defaultValue: Byte = 0): ReadOnlyProperty<Activity, Byte> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readByteUriArgOr(argName, defaultValue) }

fun Activity.bindByteUriArgOrNull(argName: String): ReadOnlyProperty<Activity, Byte?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readByteUriArgOrNull(argName) }


fun Activity.bindShortUriArgOrThrow(argName: String): ReadOnlyProperty<Activity, Short> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readShortUriArgOrThrow(argName) }

fun Activity.bindShortUriArgOr(argName: String, defaultValue: Short = 0): ReadOnlyProperty<Activity, Short> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readShortUriArgOr(argName, defaultValue) }

fun Activity.bindShortUriArgOrNull(argName: String): ReadOnlyProperty<Activity, Short?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readShortUriArgOrNull(argName) }


fun Activity.bindIntUriArgOrThrow(argName: String): ReadOnlyProperty<Activity, Int> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readIntUriArgOrThrow(argName) }

fun Activity.bindIntUriArgOr(argName: String, defaultValue: Int = 0): ReadOnlyProperty<Activity, Int> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readIntUriArgOr(argName, defaultValue) }

fun Activity.bindIntUriArgOrNull(argName: String): ReadOnlyProperty<Activity, Int?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readIntUriArgOrNull(argName) }


fun Activity.bindLongUriArgOrThrow(argName: String): ReadOnlyProperty<Activity, Long> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readLongUriArgOrThrow(argName) }

fun Activity.bindLongUriArgOr(argName: String, defaultValue: Long = 0): ReadOnlyProperty<Activity, Long> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readLongUriArgOr(argName, defaultValue) }

fun Activity.bindLongUriArgOrNull(argName: String): ReadOnlyProperty<Activity, Long?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readLongUriArgOrNull(argName) }


fun Activity.bindFloatUriArgOrThrow(argName: String): ReadOnlyProperty<Activity, Float> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readFloatUriArgOrThrow(argName) }

fun Activity.bindFloatUriArgOr(argName: String, defaultValue: Float): ReadOnlyProperty<Activity, Float> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readFloatUriArgOr(argName, defaultValue) }

fun Activity.bindFloatUriArgOrNull(argName: String): ReadOnlyProperty<Activity, Float?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readFloatUriArgOrNull(argName) }


fun Activity.bindDoubleUriArgOrThrow(argName: String): ReadOnlyProperty<Activity, Double> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readDoubleUriArgOrThrow(argName) }

fun Activity.bindDoubleUriArgOr(argName: String, defaultValue: Double): ReadOnlyProperty<Activity, Double> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readDoubleUriArgOr(argName, defaultValue) }

fun Activity.bindDoubleUriArgOrNull(argName: String): ReadOnlyProperty<Activity, Double?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readDoubleUriArgOrNull(argName) }


fun Activity.bindBooleanUriArgOrThrow(argName: String): ReadOnlyProperty<Activity, Boolean> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readBooleanUriArgOrThrow(argName) }

fun Activity.bindBooleanUriArgOr(argName: String, defaultValue: Boolean): ReadOnlyProperty<Activity, Boolean> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readBooleanUriArgOr(argName, defaultValue) }

fun Activity.bindBooleanUriArgOrNull(argName: String): ReadOnlyProperty<Activity, Boolean?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readBooleanUriArgOrNull(argName) }


fun Activity.bindStringUriArgOrThrow(argName: String): ReadOnlyProperty<Activity, String> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readStringUriArgOrThrow(argName) }

fun Activity.bindStringUriArgOr(argName: String, defaultValue: String): ReadOnlyProperty<Activity, String> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readStringUriArgOr(argName, defaultValue) }

fun Activity.bindStringUriArgOrNull(argName: String): ReadOnlyProperty<Activity, String?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readStringUriArgOrNull(argName) }


fun Activity.bindByteUriArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, Byte> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readByteUriArgOrThrow(argNameResId) }

fun Activity.bindByteUriArgOr(@StringRes argNameResId: Int, defaultValue: Byte = 0): ReadOnlyProperty<Activity, Byte> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readByteUriArgOr(argNameResId, defaultValue) }

fun Activity.bindByteUriArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, Byte?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readByteUriArgOrNull(argNameResId) }


fun Activity.bindShortUriArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, Short> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readShortUriArgOrThrow(argNameResId) }

fun Activity.bindShortUriArgOr(@StringRes argNameResId: Int, defaultValue: Short = 0): ReadOnlyProperty<Activity, Short> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readShortUriArgOr(argNameResId, defaultValue) }

fun Activity.bindShortUriArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, Short?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readShortUriArgOrNull(argNameResId) }


fun Activity.bindIntUriArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, Int> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readIntUriArgOrThrow(argNameResId) }

fun Activity.bindIntUriArgOr(@StringRes argNameResId: Int, defaultValue: Int = 0): ReadOnlyProperty<Activity, Int> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readIntUriArgOr(argNameResId, defaultValue) }

fun Activity.bindIntUriArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, Int?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readIntUriArgOrNull(argNameResId) }


fun Activity.bindLongUriArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, Long> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readLongUriArgOrThrow(argNameResId) }

fun Activity.bindLongUriArgOr(@StringRes argNameResId: Int, defaultValue: Long = 0): ReadOnlyProperty<Activity, Long> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readLongUriArgOr(argNameResId, defaultValue) }

fun Activity.bindLongUriArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, Long?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readLongUriArgOrNull(argNameResId) }


fun Activity.bindFloatUriArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, Float> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readFloatUriArgOrThrow(argNameResId) }

fun Activity.bindFloatUriArgOr(@StringRes argNameResId: Int, defaultValue: Float): ReadOnlyProperty<Activity, Float> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readFloatUriArgOr(argNameResId, defaultValue) }

fun Activity.bindFloatUriArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, Float?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readFloatUriArgOrNull(argNameResId) }


fun Activity.bindDoubleUriArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, Double> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readDoubleUriArgOrThrow(argNameResId) }

fun Activity.bindDoubleUriArgOr(@StringRes argNameResId: Int, defaultValue: Double): ReadOnlyProperty<Activity, Double> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readDoubleUriArgOr(argNameResId, defaultValue) }

fun Activity.bindDoubleUriArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, Double?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readDoubleUriArgOrNull(argNameResId) }


fun Activity.bindBooleanUriArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, Boolean> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readBooleanUriArgOrThrow(argNameResId) }

fun Activity.bindBooleanUriArgOr(@StringRes argNameResId: Int, defaultValue: Boolean): ReadOnlyProperty<Activity, Boolean> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readBooleanUriArgOr(argNameResId, defaultValue) }

fun Activity.bindBooleanUriArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, Boolean?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readBooleanUriArgOrNull(argNameResId) }


fun Activity.bindStringUriArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, String> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readStringUriArgOrThrow(argNameResId) }

fun Activity.bindStringUriArgOr(@StringRes argNameResId: Int, defaultValue: String): ReadOnlyProperty<Activity, String> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readStringUriArgOr(argNameResId, defaultValue) }

fun Activity.bindStringUriArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, String?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readStringUriArgOrNull(argNameResId) }


/* ************************************* Activity Uri Intent Arg ***************************************** */


fun Activity.bindByteUriIntentArgOrThrow(argName: String): ReadOnlyProperty<Activity, Byte> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readByteUriIntentArgOrThrow(argName) }

fun Activity.bindByteUriIntentArgOr(argName: String, defaultValue: Byte = 0): ReadOnlyProperty<Activity, Byte> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readByteUriIntentArgOr(argName, defaultValue) }

fun Activity.bindByteUriIntentArgOrNull(argName: String): ReadOnlyProperty<Activity, Byte?> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readByteUriIntentArgOrNull(argName) }


fun Activity.bindShortUriIntentArgOrThrow(argName: String): ReadOnlyProperty<Activity, Short> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readShortUriIntentArgOrThrow(argName) }

fun Activity.bindShortUriIntentArgOr(argName: String, defaultValue: Short = 0): ReadOnlyProperty<Activity, Short> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readShortUriIntentArgOr(argName, defaultValue) }

fun Activity.bindShortUriIntentArgOrNull(argName: String): ReadOnlyProperty<Activity, Short?> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readShortUriIntentArgOrNull(argName) }


fun Activity.bindIntUriIntentArgOrThrow(argName: String): ReadOnlyProperty<Activity, Int> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readIntUriIntentArgOrThrow(argName) }

fun Activity.bindIntUriIntentArgOr(argName: String, defaultValue: Int = 0): ReadOnlyProperty<Activity, Int> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readIntUriIntentArgOr(argName, defaultValue) }

fun Activity.bindIntUriIntentArgOrNull(argName: String): ReadOnlyProperty<Activity, Int?> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readIntUriIntentArgOrNull(argName) }


fun Activity.bindLongUriIntentArgOrThrow(argName: String): ReadOnlyProperty<Activity, Long> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readLongUriIntentArgOrThrow(argName) }

fun Activity.bindLongUriIntentArgOr(argName: String, defaultValue: Long = 0): ReadOnlyProperty<Activity, Long> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readLongUriIntentArgOr(argName, defaultValue) }

fun Activity.bindLongUriIntentArgOrNull(argName: String): ReadOnlyProperty<Activity, Long> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readLongUriIntentArgOrNull(argName) }


fun Activity.bindFloatUriIntentArgOrThrow(argName: String): ReadOnlyProperty<Activity, Float> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readFloatUriIntentArgOrThrow(argName) }

fun Activity.bindFloatUriIntentArgOr(argName: String, defaultValue: Float): ReadOnlyProperty<Activity, Float> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readFloatUriIntentArgOr(argName, defaultValue) }

fun Activity.bindFloatUriIntentArgOrNull(argName: String): ReadOnlyProperty<Activity, Float?> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readFloatUriIntentArgOrNull(argName) }


fun Activity.bindDoubleUriIntentArgOrThrow(argName: String): ReadOnlyProperty<Activity, Double> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readDoubleUriIntentArgOrThrow(argName) }

fun Activity.bindDoubleUriIntentArgOr(argName: String, defaultValue: Double): ReadOnlyProperty<Activity, Double> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readDoubleUriIntentArgOr(argName, defaultValue) }

fun Activity.bindDoubleUriIntentArgOrNull(argName: String): ReadOnlyProperty<Activity, Double?> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readDoubleUriIntentArgOrNull(argName) }


fun Activity.bindBooleanUriIntentArgOrThrow(argName: String): ReadOnlyProperty<Activity, Boolean> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readBooleanUriIntentArgOrThrow(argName) }

fun Activity.bindBooleanUriIntentArgOr(argName: String, defaultValue: Boolean): ReadOnlyProperty<Activity, Boolean> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readBooleanUriIntentArgOr(argName, defaultValue) }

fun Activity.bindBooleanUriIntentArgOrNull(argName: String): ReadOnlyProperty<Activity, Boolean?> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readBooleanUriIntentArgOrNull(argName) }


fun Activity.bindStringUriIntentArgOrThrow(argName: String): ReadOnlyProperty<Activity, String> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readStringUriIntentArgOrThrow(argName) }

fun Activity.bindStringUriIntentArgOr(argName: String, defaultValue: String): ReadOnlyProperty<Activity, String> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readStringUriIntentArgOr(argName, defaultValue) }

fun Activity.bindStringUriIntentArgOrNull(argName: String): ReadOnlyProperty<Activity, String?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readStringUriIntentArgOrNull(argName) }


fun Activity.bindByteUriIntentArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, Byte> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readByteUriIntentArgOrThrow(argNameResId) }

fun Activity.bindByteUriIntentArgOr(@StringRes argNameResId: Int, defaultValue: Byte = 0): ReadOnlyProperty<Activity, Byte> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readByteUriIntentArgOr(argNameResId, defaultValue) }

fun Activity.bindByteUriIntentArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, Byte?> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readByteUriIntentArgOrNull(argNameResId) }


fun Activity.bindShortUriIntentArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, Short> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readShortUriIntentArgOrThrow(argNameResId) }

fun Activity.bindShortUriIntentArgOr(@StringRes argNameResId: Int, defaultValue: Short = 0): ReadOnlyProperty<Activity, Short> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readShortUriIntentArgOr(argNameResId, defaultValue) }

fun Activity.bindShortUriIntentArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, Short?> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readShortUriIntentArgOrNull(argNameResId) }


fun Activity.bindIntUriIntentArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, Int> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readIntUriIntentArgOrThrow(argNameResId) }

fun Activity.bindIntUriIntentArgOr(@StringRes argNameResId: Int, defaultValue: Int = 0): ReadOnlyProperty<Activity, Int> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readIntUriIntentArgOr(argNameResId, defaultValue) }

fun Activity.bindIntUriIntentArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, Int?> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readIntUriIntentArgOrNull(argNameResId) }


fun Activity.bindLongUriIntentArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, Long> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readLongUriIntentArgOrThrow(argNameResId) }

fun Activity.bindLongUriIntentArgOr(@StringRes argNameResId: Int, defaultValue: Long = 0): ReadOnlyProperty<Activity, Long> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readLongUriIntentArgOr(argNameResId, defaultValue) }

fun Activity.bindLongUriIntentArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, Long?> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readLongUriIntentArgOrNull(argNameResId) }


fun Activity.bindFloatUriIntentArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, Float> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readFloatUriIntentArgOrThrow(argNameResId) }

fun Activity.bindFloatUriIntentArgOr(@StringRes argNameResId: Int, defaultValue: Float): ReadOnlyProperty<Activity, Float> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readFloatUriIntentArgOr(argNameResId, defaultValue) }

fun Activity.bindFloatUriIntentArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, Float?> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readFloatUriIntentArgOrNull(argNameResId) }


fun Activity.bindDoubleUriIntentArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, Double> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readDoubleUriIntentArgOrThrow(argNameResId) }

fun Activity.bindDoubleUriIntentArgOr(@StringRes argNameResId: Int, defaultValue: Double): ReadOnlyProperty<Activity, Double> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readDoubleUriIntentArgOr(argNameResId, defaultValue) }

fun Activity.bindDoubleUriIntentArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, Double?> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readDoubleUriIntentArgOrNull(argNameResId) }


fun Activity.bindBooleanUriIntentArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, Boolean> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readBooleanUriIntentArgOrThrow(argNameResId) }

fun Activity.bindBooleanUriIntentArgOr(@StringRes argNameResId: Int, defaultValue: Boolean): ReadOnlyProperty<Activity, Boolean> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readBooleanUriIntentArgOr(argNameResId, defaultValue) }

fun Activity.bindBooleanUriIntentArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, Boolean?> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readBooleanUriIntentArgOrNull(argNameResId) }


fun Activity.bindStringUriIntentArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, String> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readStringUriIntentArgOrThrow(argNameResId) }

fun Activity.bindStringUriIntentArgOr(@StringRes argNameResId: Int, defaultValue: String): ReadOnlyProperty<Activity, String> =
        ActivityArgLazy(argNameResId) { _, _: KProperty<*> -> this.readStringUriIntentArgOr(argNameResId, defaultValue) }

fun Activity.bindStringUriIntentArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Activity, String?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readStringUriIntentArgOrNull(argNameResId) }


/* ************************************* SupportFragment Arg ***************************************** */


fun Fragment.bindByteArgOrThrow(argName: String): ReadOnlyProperty<Fragment, Byte> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readByteArgOrThrow(argName) }

fun Fragment.bindByteArgOr(argName: String, defaultValue: Byte = 0): ReadOnlyProperty<Fragment, Byte> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readByteArgOr(argName, defaultValue) }

fun Fragment.bindByteArgOrNull(argName: String): ReadOnlyProperty<Fragment, Byte?> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readByteArgOrNull(argName) }


fun Fragment.bindByteArrayArgOrThrow(argName: String): ReadOnlyProperty<Fragment, ByteArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readByteArrayArgOrThrow(argName) }

fun Fragment.bindByteArrayArgOr(argName: String, defaultValue: ByteArray): ReadOnlyProperty<Fragment, ByteArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readByteArrayArgOr(argName, defaultValue) }

fun Fragment.bindByteArrayArgOrNull(argName: String): ReadOnlyProperty<Fragment, ByteArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readByteArrayArgOrNull(argName) }


fun Fragment.bindShortArgOrThrow(argName: String): ReadOnlyProperty<Fragment, Short?> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readShortArgOrThrow(argName) }

fun Fragment.bindShortArgOr(argName: String, defaultValue: Short = 0): ReadOnlyProperty<Fragment, Short> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readShortArgOr(argName, defaultValue) }

fun Fragment.bindShortArgOrNull(argName: String): ReadOnlyProperty<Fragment, Short> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readShortArgOrNull(argName) }


fun Fragment.bindShortArrayArgOrThrow(argName: String): ReadOnlyProperty<Fragment, ShortArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readShortArrayArgOrThrow(argName) }

fun Fragment.bindShortArrayArgOr(argName: String, defaultValue: ShortArray): ReadOnlyProperty<Fragment, ShortArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readShortArrayArgOr(argName, defaultValue) }

fun Fragment.bindShortArrayArgOrNull(argName: String): ReadOnlyProperty<Fragment, ShortArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readShortArrayArgOrNull(argName) }


fun Fragment.bindIntArgOrThrow(argName: String): ReadOnlyProperty<Fragment, Int> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readIntArgOrThrow(argName) }

fun Fragment.bindIntArgOr(argName: String, defaultValue: Int = 0): ReadOnlyProperty<Fragment, Int> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readIntArgOr(argName, defaultValue) }

fun Fragment.bindIntArgOrNull(argName: String): ReadOnlyProperty<Fragment, Int?> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readIntArgOrNull(argName) }


fun Fragment.bindIntArrayArgOrThrow(argName: String): ReadOnlyProperty<Fragment, IntArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readIntArrayArgOrThrow(argName) }

fun Fragment.bindIntArrayArgOr(argName: String, defaultValue: IntArray): ReadOnlyProperty<Fragment, IntArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readIntArrayArgOr(argName, defaultValue) }

fun Fragment.bindIntArrayArgOrNull(argName: String): ReadOnlyProperty<Fragment, IntArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readIntArrayArgOrNull(argName) }


fun Fragment.bindIntArrayListArgOrThrow(argName: String): ReadOnlyProperty<Fragment, ArrayList<Int>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readIntArrayListArgOrThrow(argName) }

fun Fragment.bindIntArrayListArgOr(argName: String, defaultValue: ArrayList<Int>): ReadOnlyProperty<Fragment, ArrayList<Int>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readIntArrayListArgOr(argName, defaultValue) }

fun Fragment.bindIntArrayListArgOrNull(argName: String): ReadOnlyProperty<Fragment, ArrayList<Int>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readIntArrayListArgOrNull(argName) }


fun Fragment.bindLongArgOrThrow(argName: String): ReadOnlyProperty<Fragment, Long> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readLongArgOrThrow(argName) }

fun Fragment.bindLongArgOr(argName: String, defaultValue: Long = 0L): ReadOnlyProperty<Fragment, Long> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readLongArgOr(argName, defaultValue) }

fun Fragment.bindLongArgOrNull(argName: String): ReadOnlyProperty<Fragment, Long?> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readLongArgOrNull(argName) }


fun Fragment.bindLongArrayArgOrThrow(argName: String): ReadOnlyProperty<Fragment, LongArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readLongArrayArgOrThrow(argName) }

fun Fragment.bindLongArrayArgOr(argName: String, defaultValue: LongArray): ReadOnlyProperty<Fragment, LongArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readLongArrayArgOr(argName, defaultValue) }

fun Fragment.bindLongArrayArgOrNull(argName: String): ReadOnlyProperty<Fragment, LongArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readLongArrayArgOrNull(argName) }


fun Fragment.bindFloatArgOrThrow(argName: String): ReadOnlyProperty<Fragment, Float> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readFloatArgOrThrow(argName) }

fun Fragment.bindFloatArgOr(argName: String, defaultValue: Float = 0f): ReadOnlyProperty<Fragment, Float> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readFloatArgOr(argName, defaultValue) }

fun Fragment.bindFloatArgOrNull(argName: String): ReadOnlyProperty<Fragment, Float?> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readFloatArgOrNull(argName) }


fun Fragment.bindFloatArrayArgOrThrow(argName: String): ReadOnlyProperty<Fragment, FloatArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readFloatArrayArgOrThrow(argName) }

fun Fragment.bindFloatArrayArgOr(argName: String, defaultValue: FloatArray): ReadOnlyProperty<Fragment, FloatArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readFloatArrayArgOr(argName, defaultValue) }

fun Fragment.bindFloatArrayArgOrNull(argName: String): ReadOnlyProperty<Fragment, FloatArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readFloatArrayArgOrNull(argName) }


fun Fragment.bindDoubleArgOrThrow(argName: String): ReadOnlyProperty<Fragment, Double> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readDoubleArgOrThrow(argName) }

fun Fragment.bindDoubleArgOr(argName: String, defaultValue: Double = 0.toDouble()): ReadOnlyProperty<Fragment, Double> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readDoubleArgOr(argName, defaultValue) }

fun Fragment.bindDoubleArgOrNull(argName: String): ReadOnlyProperty<Fragment, Double?> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readDoubleArgOrNull(argName) }


fun Fragment.bindDoubleArrayArgOrThrow(argName: String): ReadOnlyProperty<Fragment, DoubleArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readDoubleArrayArgOrThrow(argName) }

fun Fragment.bindDoubleArrayArgOr(argName: String, defaultValue: DoubleArray): ReadOnlyProperty<Fragment, DoubleArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readDoubleArrayArgOr(argName, defaultValue) }

fun Fragment.bindDoubleArrayArgOrNull(argName: String): ReadOnlyProperty<Fragment, DoubleArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readDoubleArrayArgOrNull(argName) }


fun Fragment.bindBooleanArgOrThrow(argName: String): ReadOnlyProperty<Fragment, Boolean> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readBooleanArgOrThrow(argName) }

fun Fragment.bindBooleanArgOr(argName: String, defaultValue: Boolean = false): ReadOnlyProperty<Fragment, Boolean> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readBooleanArgOr(argName, defaultValue) }

fun Fragment.bindBooleanArgOrNull(argName: String): ReadOnlyProperty<Fragment, Boolean?> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readBooleanArgOrNull(argName) }


fun Fragment.bindBooleanArrayArgOrThrow(argName: String): ReadOnlyProperty<Fragment, BooleanArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readBooleanArrayArgOrThrow(argName) }

fun Fragment.bindBooleanArrayArgOr(argName: String, defaultValue: BooleanArray): ReadOnlyProperty<Fragment, BooleanArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readBooleanArrayArgOr(argName, defaultValue) }

fun Fragment.bindBooleanArrayArgOrNull(argName: String): ReadOnlyProperty<Fragment, BooleanArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readBooleanArrayArgOrNull(argName) }


fun Fragment.bindCharArgOrThrow(argName: String): ReadOnlyProperty<Fragment, Char> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readCharArgOrThrow(argName) }

fun Fragment.bindCharArgOr(argName: String, defaultValue: Char = 0.toChar()): ReadOnlyProperty<Fragment, Char> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readCharArgOr(argName, defaultValue) }

fun Fragment.bindCharArgOrNull(argName: String): ReadOnlyProperty<Fragment, Char?> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readCharArgOrNull(argName) }


fun Fragment.bindCharArrayArgOrThrow(argName: String): ReadOnlyProperty<Fragment, CharArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readCharArrayArgOrThrow(argName) }

fun Fragment.bindCharArrayArgOr(argName: String, defaultValue: CharArray): ReadOnlyProperty<Fragment, CharArray> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readCharArrayArgOr(argName, defaultValue) }

fun Fragment.bindCharArrayArgOrNull(argName: String): ReadOnlyProperty<Fragment, CharArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readCharArrayArgOrNull(argName) }


fun Fragment.bindCharSequenceArgOrThrow(argName: String): ReadOnlyProperty<Fragment, CharSequence> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readCharSequenceArgOrThrow(argName) }

fun Fragment.bindCharSequenceArgOr(argName: String, defaultValue: CharSequence): ReadOnlyProperty<Fragment, CharSequence> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readCharSequenceArgOr(argName, defaultValue) }

fun Fragment.bindCharSequenceArgOrNull(argName: String): ReadOnlyProperty<Fragment, CharSequence?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readCharSequenceArgOrNull(argName) }


fun Fragment.bindCharSequenceArrayArgOrThrow(argName: String): ReadOnlyProperty<Fragment, Array<CharSequence>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readCharSequenceArrayArgOrThrow(argName) }

fun Fragment.bindCharSequenceArrayArgOr(argName: String, defaultValue: Array<CharSequence>): ReadOnlyProperty<Fragment, Array<CharSequence>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readCharSequenceArrayArgOr(argName, defaultValue) }

fun Fragment.bindCharSequenceArrayArgOrNull(argName: String): ReadOnlyProperty<Fragment, Array<CharSequence>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readCharSequenceArrayArgOrNull(argName) }


fun Fragment.bindCharSequenceArrayListArgOrThrow(argName: String): ReadOnlyProperty<Fragment, ArrayList<CharSequence>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readCharSequenceArrayListArgOrThrow(argName) }

fun Fragment.bindCharSequenceArrayListArgOr(argName: String, defaultValue: ArrayList<CharSequence>): ReadOnlyProperty<Fragment, ArrayList<CharSequence>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readCharSequenceArrayListArgOr(argName, defaultValue) }

fun Fragment.bindCharSequenceArrayListArgOrNull(argName: String): ReadOnlyProperty<Fragment, ArrayList<CharSequence>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readCharSequenceArrayListArgOrNull(argName) }


fun Fragment.bindStringArgOrThrow(argName: String): ReadOnlyProperty<Fragment, String> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readStringArgOrThrow(argName) }

fun Fragment.bindStringArgOr(argName: String, defaultValue: String): ReadOnlyProperty<Fragment, String> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readStringArgOr(argName, defaultValue) }

fun Fragment.bindStringArgOrNull(argName: String): ReadOnlyProperty<Fragment, String?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readStringArgOrNull(argName) }


fun Fragment.bindStringArrayArgOrThrow(argName: String): ReadOnlyProperty<Fragment, Array<String>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readStringArrayArgOrThrow(argName) }

fun Fragment.bindStringArrayArgOr(argName: String, defaultValue: Array<String>): ReadOnlyProperty<Fragment, Array<String>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readStringArrayArgOr(argName, defaultValue) }

fun Fragment.bindStringArrayArgOrNull(argName: String): ReadOnlyProperty<Fragment, Array<String>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readStringArrayArgOrNull(argName) }


fun Fragment.bindStringArrayListArgOrThrow(argName: String): ReadOnlyProperty<Fragment, ArrayList<String>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readStringArrayListArgOrThrow(argName) }

fun Fragment.bindStringArrayListArgOr(argName: String, defaultValue: ArrayList<String>): ReadOnlyProperty<Fragment, ArrayList<String>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readStringArrayListArgOr(argName, defaultValue) }

fun Fragment.bindStringArrayListArgOrNull(argName: String): ReadOnlyProperty<Fragment, ArrayList<String>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readStringArrayListArgOrNull(argName) }


fun <V : Parcelable> Fragment.bindParcelableArgOrThrow(argName: String): ReadOnlyProperty<Fragment, V> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readParcelableArgOrThrow(argName) }

fun <V : Parcelable> Fragment.bindParcelableArgOr(argName: String, defaultValue: V): ReadOnlyProperty<Fragment, V> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readParcelableArgOr(argName, defaultValue) }

fun <V : Parcelable> Fragment.bindParcelableArgOrNull(argName: String): ReadOnlyProperty<Fragment, V?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readParcelableArgOrNull(argName) }


@Suppress("UNCHECKED_CAST")
fun <V : Parcelable> Fragment.bindParcelableArrayArgOrThrow(argName: String): ReadOnlyProperty<Fragment, Array<V>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readParcelableArrayArgOrThrow(argName) }

@Suppress("UNCHECKED_CAST")
fun <V : Parcelable> Fragment.bindParcelableArrayArgOr(argName: String, defaultValue: Array<V>): ReadOnlyProperty<Fragment, Array<V>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readParcelableArrayArgOr(argName, defaultValue) }

@Suppress("UNCHECKED_CAST")
fun <V : Parcelable> Fragment.bindParcelableArrayArgOrNull(argName: String): ReadOnlyProperty<Fragment, Array<V>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readParcelableArrayArgOrNull(argName) }


fun <V : Parcelable> Fragment.bindParcelableArrayListArgOrThrow(argName: String): ReadOnlyProperty<Fragment, ArrayList<V>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readParcelableArrayListArgOrThrow(argName) }

fun <V : Parcelable> Fragment.bindParcelableArrayListArgOr(argName: String, defaultValue: ArrayList<V>): ReadOnlyProperty<Fragment, ArrayList<V>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readParcelableArrayListArgOr(argName, defaultValue) }

fun <V : Parcelable> Fragment.bindParcelableArrayListArgOrNull(argName: String): ReadOnlyProperty<Fragment, ArrayList<V>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readParcelableArrayListArgOrNull(argName) }


fun <V : Parcelable> Fragment.bindSparseParcelableArrayArgOrThrow(argName: String): ReadOnlyProperty<Fragment, SparseArray<V>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readSparseParcelableArrayArgOrThrow(argName) }

fun <V : Parcelable> Fragment.bindSparseParcelableArrayArgOr(argName: String, defaultValue: SparseArray<V>): ReadOnlyProperty<Fragment, SparseArray<V>> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readSparseParcelableArrayArgOr(argName, defaultValue) }

fun <V : Parcelable> Fragment.bindSparseParcelableArrayArgOrNull(argName: String): ReadOnlyProperty<Fragment, SparseArray<V>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readSparseParcelableArrayArgOrNull(argName) }


@Suppress("UNCHECKED_CAST")
fun <V : java.io.Serializable> Fragment.bindSerializableArgOrThrow(argName: String): ReadOnlyProperty<Fragment, V> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readSerializableArgOrThrow(argName) }

@Suppress("UNCHECKED_CAST")
fun <V : java.io.Serializable> Fragment.bindSerializableArgOr(argName: String, defaultValue: V): ReadOnlyProperty<Fragment, V> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readSerializableArgOr(argName, defaultValue) }

@Suppress("UNCHECKED_CAST")
fun <V : java.io.Serializable> Fragment.bindSerializableArgOrNull(argName: String): ReadOnlyProperty<Fragment, V?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readSerializableArgOrNull(argName) }


fun Fragment.bindBundleArgOrThrow(argName: String): ReadOnlyProperty<Fragment, Bundle> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readBundleArgOrThrow(argName) }

fun Fragment.bindBundleArgOr(argName: String, defaultValue: Bundle): ReadOnlyProperty<Fragment, Bundle> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readBundleArgOr(argName, defaultValue) }

fun Fragment.bindBundleArgOrNull(argName: String): ReadOnlyProperty<Fragment, Bundle?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readBundleArgOrNull(argName) }


@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
fun Fragment.bindBinderArgOrThrow(argName: String): ReadOnlyProperty<Fragment, IBinder> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readBinderArgOrThrow(argName) }

@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
fun Fragment.bindBinderArgOr(argName: String, defaultValue: IBinder): ReadOnlyProperty<Fragment, IBinder> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readBinderArgOr(argName, defaultValue) }

@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
fun Fragment.bindBinderArgOrNull(argName: String): ReadOnlyProperty<Fragment, IBinder?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readBinderArgOrNull(argName) }


@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun Fragment.bindSizeArgOrThrow(argName: String): ReadOnlyProperty<Fragment, Size> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readSizeArgOrThrow(argName) }

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun Fragment.bindSizeArgOr(argName: String, defaultValue: Size): ReadOnlyProperty<Fragment, Size> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readSizeArgOr(argName, defaultValue) }

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun Fragment.bindSizeArgOrNull(argName: String): ReadOnlyProperty<Fragment, Size?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readSizeArgOrNull(argName) }


@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun Fragment.bindSizeFArgOrThrow(argName: String): ReadOnlyProperty<Fragment, SizeF> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readSizeFArgOrThrow(argName) }

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun Fragment.bindSizeFArgOr(argName: String, defaultValue: SizeF): ReadOnlyProperty<Fragment, SizeF> =
        ArgLazy(argName) { _, _: KProperty<*> -> this.readSizeFArgOr(argName, defaultValue) }

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun Fragment.bindSizeFArgOrNull(argName: String): ReadOnlyProperty<Fragment, SizeF?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readSizeFArgOrNull(argName) }


/* ************************************* SupportFragment Arg ***************************************** */


fun Fragment.bindByteArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Fragment, Byte> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readByteArgOrThrow(argNameResId) }

fun Fragment.bindByteArgOr(@StringRes argNameResId: Int, defaultValue: Byte = 0): ReadOnlyProperty<Fragment, Byte> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readByteArgOr(argNameResId, defaultValue) }

fun Fragment.bindByteArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Fragment, Byte?> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readByteArgOrNull(argNameResId) }


fun Fragment.bindByteArrayArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Fragment, ByteArray> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readByteArrayArgOrThrow(argNameResId) }

fun Fragment.bindByteArrayArgOr(@StringRes argNameResId: Int, defaultValue: ByteArray): ReadOnlyProperty<Fragment, ByteArray> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readByteArrayArgOr(argNameResId, defaultValue) }

fun Fragment.bindByteArrayArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Fragment, ByteArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readByteArrayArgOrNull(argNameResId) }


fun Fragment.bindShortArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Fragment, Short> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readShortArgOrThrow(argNameResId) }

fun Fragment.bindShortArgOr(@StringRes argNameResId: Int, defaultValue: Short = 0): ReadOnlyProperty<Fragment, Short> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readShortArgOr(argNameResId, defaultValue) }

fun Fragment.bindShortArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Fragment, Short?> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readShortArgOrNull(argNameResId) }


fun Fragment.bindShortArrayArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Fragment, ShortArray> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readShortArrayArgOrThrow(argNameResId) }

fun Fragment.bindShortArrayArgOr(@StringRes argNameResId: Int, defaultValue: ShortArray): ReadOnlyProperty<Fragment, ShortArray> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readShortArrayArgOr(argNameResId, defaultValue) }

fun Fragment.bindShortArrayArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Fragment, ShortArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readShortArrayArgOrNull(argNameResId) }


fun Fragment.bindIntArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Fragment, Int> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readIntArgOrThrow(argNameResId) }

fun Fragment.bindIntArgOr(@StringRes argNameResId: Int, defaultValue: Int = 0): ReadOnlyProperty<Fragment, Int> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readIntArgOr(argNameResId, defaultValue) }

fun Fragment.bindIntArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Fragment, Int?> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readIntArgOrNull(argNameResId) }


fun Fragment.bindIntArrayArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Fragment, IntArray> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readIntArrayArgOrThrow(argNameResId) }

fun Fragment.bindIntArrayArgOr(@StringRes argNameResId: Int, defaultValue: IntArray): ReadOnlyProperty<Fragment, IntArray> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readIntArrayArgOr(argNameResId, defaultValue) }

fun Fragment.bindIntArrayArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Fragment, IntArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readIntArrayArgOrNull(argNameResId) }


fun Fragment.bindIntArrayListArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Fragment, ArrayList<Int>> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readIntArrayListArgOrThrow(argNameResId) }

fun Fragment.bindIntArrayListArgOr(@StringRes argNameResId: Int, defaultValue: ArrayList<Int>): ReadOnlyProperty<Fragment, ArrayList<Int>> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readIntArrayListArgOr(argNameResId, defaultValue) }

fun Fragment.bindIntArrayListArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Fragment, ArrayList<Int>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readIntArrayListArgOrNull(argNameResId) }


fun Fragment.bindLongArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Fragment, Long> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readLongArgOrThrow(argNameResId) }

fun Fragment.bindLongArgOr(@StringRes argNameResId: Int, defaultValue: Long = 0L): ReadOnlyProperty<Fragment, Long> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readLongArgOr(argNameResId, defaultValue) }

fun Fragment.bindLongArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Fragment, Long?> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readLongArgOrNull(argNameResId) }


fun Fragment.bindLongArrayArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Fragment, LongArray> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readLongArrayArgOrThrow(argNameResId) }

fun Fragment.bindLongArrayArgOr(@StringRes argNameResId: Int, defaultValue: LongArray): ReadOnlyProperty<Fragment, LongArray> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readLongArrayArgOr(argNameResId, defaultValue) }

fun Fragment.bindLongArrayArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Fragment, LongArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readLongArrayArgOrNull(argNameResId) }


fun Fragment.bindFloatArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Fragment, Float> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readFloatArgOrThrow(argNameResId) }

fun Fragment.bindFloatArgOr(@StringRes argNameResId: Int, defaultValue: Float = 0f): ReadOnlyProperty<Fragment, Float> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readFloatArgOr(argNameResId, defaultValue) }

fun Fragment.bindFloatArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Fragment, Float?> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readFloatArgOrNull(argNameResId) }


fun Fragment.bindFloatArrayArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Fragment, FloatArray> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readFloatArrayArgOrThrow(argNameResId) }

fun Fragment.bindFloatArrayArgOr(@StringRes argNameResId: Int, defaultValue: FloatArray): ReadOnlyProperty<Fragment, FloatArray> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readFloatArrayArgOr(argNameResId, defaultValue) }

fun Fragment.bindFloatArrayArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Fragment, FloatArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readFloatArrayArgOrNull(argNameResId) }


fun Fragment.bindDoubleArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Fragment, Double> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readDoubleArgOrThrow(argNameResId) }

fun Fragment.bindDoubleArgOr(@StringRes argNameResId: Int, defaultValue: Double = 0.toDouble()): ReadOnlyProperty<Fragment, Double> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readDoubleArgOr(argNameResId, defaultValue) }

fun Fragment.bindDoubleArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Fragment, Double?> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readDoubleArgOrNull(argNameResId) }


fun Fragment.bindDoubleArrayArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Fragment, DoubleArray> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readDoubleArrayArgOrThrow(argNameResId) }

fun Fragment.bindDoubleArrayArgOr(@StringRes argNameResId: Int, defaultValue: DoubleArray): ReadOnlyProperty<Fragment, DoubleArray> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readDoubleArrayArgOr(argNameResId, defaultValue) }

fun Fragment.bindDoubleArrayArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Fragment, DoubleArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readDoubleArrayArgOrNull(argNameResId) }


fun Fragment.bindBooleanArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Fragment, Boolean> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readBooleanArgOrThrow(argNameResId) }

fun Fragment.bindBooleanArgOr(@StringRes argNameResId: Int, defaultValue: Boolean = false): ReadOnlyProperty<Fragment, Boolean> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readBooleanArgOr(argNameResId, defaultValue) }

fun Fragment.bindBooleanArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Fragment, Boolean?> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readBooleanArgOrNull(argNameResId) }


fun Fragment.bindBooleanArrayArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Fragment, BooleanArray> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readBooleanArrayArgOrThrow(argNameResId) }

fun Fragment.bindBooleanArrayArgOr(@StringRes argNameResId: Int, defaultValue: BooleanArray): ReadOnlyProperty<Fragment, BooleanArray> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readBooleanArrayArgOr(argNameResId, defaultValue) }

fun Fragment.bindBooleanArrayArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Fragment, BooleanArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readBooleanArrayArgOrNull(argNameResId) }


fun Fragment.bindCharArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Fragment, Char> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readCharArgOrThrow(argNameResId) }

fun Fragment.bindCharArgOr(@StringRes argNameResId: Int, defaultValue: Char = 0.toChar()): ReadOnlyProperty<Fragment, Char> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readCharArgOr(argNameResId, defaultValue) }

fun Fragment.bindCharArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Fragment, Char?> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readCharArgOrNull(argNameResId) }


fun Fragment.bindCharArrayArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Fragment, CharArray> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readCharArrayArgOrThrow(argNameResId) }

fun Fragment.bindCharArrayArgOr(@StringRes argNameResId: Int, defaultValue: CharArray): ReadOnlyProperty<Fragment, CharArray> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readCharArrayArgOr(argNameResId, defaultValue) }

fun Fragment.bindCharArrayArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Fragment, CharArray?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readCharArrayArgOrNull(argNameResId) }


fun Fragment.bindCharSequenceArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Fragment, CharSequence> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readCharSequenceArgOrThrow(argNameResId) }

fun Fragment.bindCharSequenceArgOr(@StringRes argNameResId: Int, defaultValue: CharSequence): ReadOnlyProperty<Fragment, CharSequence> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readCharSequenceArgOr(argNameResId, defaultValue) }

fun Fragment.bindCharSequenceArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Fragment, CharSequence?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readCharSequenceArgOrNull(argNameResId) }


fun Fragment.bindCharSequenceArrayArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Fragment, Array<CharSequence>> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readCharSequenceArrayArgOrThrow(argNameResId) }

fun Fragment.bindCharSequenceArrayArgOr(@StringRes argNameResId: Int, defaultValue: Array<CharSequence>): ReadOnlyProperty<Fragment, Array<CharSequence>> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readCharSequenceArrayArgOr(argNameResId, defaultValue) }

fun Fragment.bindCharSequenceArrayArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Fragment, Array<CharSequence>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readCharSequenceArrayArgOrNull(argNameResId) }


fun Fragment.bindCharSequenceArrayListArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Fragment, ArrayList<CharSequence>> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readCharSequenceArrayListArgOrThrow(argNameResId) }

fun Fragment.bindCharSequenceArrayListArgOr(@StringRes argNameResId: Int, defaultValue: ArrayList<CharSequence>): ReadOnlyProperty<Fragment, ArrayList<CharSequence>> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readCharSequenceArrayListArgOr(argNameResId, defaultValue) }

fun Fragment.bindCharSequenceArrayListArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Fragment, ArrayList<CharSequence>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readCharSequenceArrayListArgOrNull(argNameResId) }


fun Fragment.bindStringArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Fragment, String> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readStringArgOrThrow(argNameResId) }

fun Fragment.bindStringArgOr(@StringRes argNameResId: Int, defaultValue: String): ReadOnlyProperty<Fragment, String> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readStringArgOr(argNameResId, defaultValue) }

fun Fragment.bindStringArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Fragment, String?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readStringArgOrNull(argNameResId) }


fun Fragment.bindStringArrayArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Fragment, Array<String>> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readStringArrayArgOrThrow(argNameResId) }

fun Fragment.bindStringArrayArgOr(@StringRes argNameResId: Int, defaultValue: Array<String>): ReadOnlyProperty<Fragment, Array<String>> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readStringArrayArgOr(argNameResId, defaultValue) }

fun Fragment.bindStringArrayArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Fragment, Array<String>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readStringArrayArgOrNull(argNameResId) }


fun Fragment.bindStringArrayListArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Fragment, ArrayList<String>> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readStringArrayListArgOrThrow(argNameResId) }

fun Fragment.bindStringArrayListArgOr(@StringRes argNameResId: Int, defaultValue: ArrayList<String>): ReadOnlyProperty<Fragment, ArrayList<String>> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readStringArrayListArgOr(argNameResId, defaultValue) }

fun Fragment.bindStringArrayListArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Fragment, ArrayList<String>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readStringArrayListArgOrNull(argNameResId) }


fun <V : Parcelable> Fragment.bindParcelableArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Fragment, V> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readParcelableArgOrThrow(argNameResId) }

fun <V : Parcelable> Fragment.bindParcelableArgOr(@StringRes argNameResId: Int, defaultValue: V): ReadOnlyProperty<Fragment, V> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readParcelableArgOr(argNameResId, defaultValue) }

fun <V : Parcelable> Fragment.bindParcelableArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Fragment, V?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readParcelableArgOrNull(argNameResId) }


@Suppress("UNCHECKED_CAST")
fun <V : Parcelable> Fragment.bindParcelableArrayArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Fragment, Array<V>> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readParcelableArrayArgOrThrow(argNameResId) }

@Suppress("UNCHECKED_CAST")
fun <V : Parcelable> Fragment.bindParcelableArrayArgOr(@StringRes argNameResId: Int, defaultValue: Array<V>): ReadOnlyProperty<Fragment, Array<V>> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readParcelableArrayArgOr(argNameResId, defaultValue) }

@Suppress("UNCHECKED_CAST")
fun <V : Parcelable> Fragment.bindParcelableArrayArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Fragment, Array<V>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readParcelableArrayArgOrNull(argNameResId) }


fun <V : Parcelable> Fragment.bindParcelableArrayListArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Fragment, ArrayList<V>> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readParcelableArrayListArgOrThrow(argNameResId) }

fun <V : Parcelable> Fragment.bindParcelableArrayListArgOr(@StringRes argNameResId: Int, defaultValue: ArrayList<V>): ReadOnlyProperty<Fragment, ArrayList<V>> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readParcelableArrayListArgOr(argNameResId, defaultValue) }

fun <V : Parcelable> Fragment.bindParcelableArrayListArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Fragment, ArrayList<V>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readParcelableArrayListArgOrNull(argNameResId) }


fun <V : Parcelable> Fragment.bindSparseParcelableArrayArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Fragment, SparseArray<V>> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readSparseParcelableArrayArgOrThrow(argNameResId) }

fun <V : Parcelable> Fragment.bindSparseParcelableArrayArgOr(@StringRes argNameResId: Int, defaultValue: SparseArray<V>): ReadOnlyProperty<Fragment, SparseArray<V>> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readSparseParcelableArrayArgOr(argNameResId, defaultValue) }

fun <V : Parcelable> Fragment.bindSparseParcelableArrayArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Fragment, SparseArray<V>?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readSparseParcelableArrayArgOrNull(argNameResId) }


@Suppress("UNCHECKED_CAST")
fun <V : java.io.Serializable> Fragment.bindSerializableArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Fragment, V> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readSerializableArgOrThrow(argNameResId) }

@Suppress("UNCHECKED_CAST")
fun <V : java.io.Serializable> Fragment.bindSerializableArgOr(@StringRes argNameResId: Int, defaultValue: V): ReadOnlyProperty<Fragment, V> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readSerializableArgOr(argNameResId, defaultValue) }

@Suppress("UNCHECKED_CAST")
fun <V : java.io.Serializable> Fragment.bindSerializableArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Fragment, V?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readSerializableArgOrNull(argNameResId) }


fun Fragment.bindBundleArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Fragment, Bundle> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readBundleArgOrThrow(argNameResId) }

fun Fragment.bindBundleArgOr(@StringRes argNameResId: Int, defaultValue: Bundle): ReadOnlyProperty<Fragment, Bundle> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readBundleArgOr(argNameResId, defaultValue) }

fun Fragment.bindBundleArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Fragment, Bundle?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readBundleArgOrNull(argNameResId) }


@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
fun Fragment.bindBinderArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Fragment, IBinder> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readBinderArgOrThrow(argNameResId) }

@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
fun Fragment.bindBinderArgOr(@StringRes argNameResId: Int, defaultValue: IBinder): ReadOnlyProperty<Fragment, IBinder> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readBinderArgOr(argNameResId, defaultValue) }

@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
fun Fragment.bindBinderArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Fragment, IBinder?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readBinderArgOrNull(argNameResId) }


@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun Fragment.bindSizeArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Fragment, Size> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readSizeArgOrThrow(argNameResId) }

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun Fragment.bindSizeArgOr(@StringRes argNameResId: Int, defaultValue: Size): ReadOnlyProperty<Fragment, Size> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readSizeArgOr(argNameResId, defaultValue) }

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun Fragment.bindSizeArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Fragment, Size?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readSizeArgOrNull(argNameResId) }


@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun Fragment.bindSizeFArgOrThrow(@StringRes argNameResId: Int): ReadOnlyProperty<Fragment, SizeF> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readSizeFArgOrThrow(argNameResId) }

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun Fragment.bindSizeFArgOr(@StringRes argNameResId: Int, defaultValue: SizeF): ReadOnlyProperty<Fragment, SizeF> =
        SupportFragmentArgLazy(argNameResId) { _, _: KProperty<*> -> this.readSizeFArgOr(argNameResId, defaultValue) }

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun Fragment.bindSizeFArgOrNull(@StringRes argNameResId: Int): ReadOnlyProperty<Fragment, SizeF?> =
        OptionalArgLazy { _, _: KProperty<*> -> this.readSizeFArgOrNull(argNameResId) }