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

package com.github.panpf.tools4a.args.ktx.test.bind

import android.os.Bundle
import android.os.Parcelable
import android.util.SparseArray
import com.github.panpf.tools4a.args.ktx.*
import org.junit.Assert

class TestSupportFragment : androidx.fragment.app.Fragment() {

    private val byteRequired by bindByteArgOr("byteRequired")
    private val byteArrayRequired by bindByteArrayArgOrThrow("byteArrayRequired")
    private val byteArrayOptional by bindByteArrayArgOrNull("byteArrayOptional")

    private val shortRequired by bindShortArgOr("shortRequired")
    private val shortArrayRequired by bindShortArrayArgOrThrow("shortArrayRequired")
    private val shortArrayOptional by bindShortArrayArgOrNull("shortArrayOptional")

    private val intRequired by bindIntArgOr("intRequired")
    private val intArrayRequired by bindIntArrayArgOrThrow("intArrayRequired")
    private val intArrayOptional by bindIntArrayArgOrNull("intArrayOptional")
    private val intArrayListRequired by bindIntArrayListArgOrThrow("intArrayListRequired")
    private val intArrayListOptional by bindIntArrayListArgOrNull("intArrayListOptional")

    private val longRequired by bindLongArgOr("longRequired")
    private val longArrayRequired by bindLongArrayArgOrThrow("longArrayRequired")
    private val longArrayOptional by bindLongArrayArgOrNull("longArrayOptional")

    private val floatRequired by bindFloatArgOr("floatRequired")
    private val floatArrayRequired by bindFloatArrayArgOrThrow("floatArrayRequired")
    private val floatArrayOptional by bindFloatArrayArgOrNull("floatArrayOptional")

    private val doubleRequired by bindDoubleArgOr("doubleRequired")
    private val doubleArrayRequired by bindDoubleArrayArgOrThrow("doubleArrayRequired")
    private val doubleArrayOptional by bindDoubleArrayArgOrNull("doubleArrayOptional")

    private val booleanRequired by bindBooleanArgOr("booleanRequired")
    private val booleanArrayRequired by bindBooleanArrayArgOrThrow("booleanArrayRequired")
    private val booleanArrayOptional by bindBooleanArrayArgOrNull("booleanArrayOptional")

    private val charRequired by bindCharArgOr("charRequired")
    private val charArrayRequired by bindCharArrayArgOrThrow("charArrayRequired")
    private val charArrayOptional by bindCharArrayArgOrNull("charArrayOptional")

    private val charSequenceRequired by bindCharSequenceArgOrThrow("charSequenceRequired")
    private val charSequenceOptional by bindCharSequenceArgOrNull("charSequenceOptional")
    private val charSequenceArrayRequired by bindCharSequenceArrayArgOrThrow("charSequenceArrayRequired")
    private val charSequenceArrayOptional by bindCharSequenceArrayArgOrNull("charSequenceArrayOptional")

    private val stringRequired by bindStringArgOrThrow("stringRequired")
    private val stringOptional by bindStringArgOrNull("stringOptional")
    private val stringArrayRequired by bindStringArrayArgOrThrow("stringArrayRequired")
    private val stringArrayOptional by bindStringArrayArgOrNull("stringArrayOptional")
    private val stringArrayListRequired by bindStringArrayListArgOrThrow("stringArrayListRequired")
    private val stringArrayListOptional by bindStringArrayListArgOrNull("stringArrayListOptional")

    private val parcelableRequired by bindParcelableArgOrThrow<TestParcelable>("parcelableRequired")
    private val parcelableOptional by bindParcelableArgOrNull<TestParcelable>("parcelableOptional")
    private val parcelableArrayRequired by bindParcelableArrayArgOrThrow<Parcelable>("parcelableArrayRequired")
    private val parcelableArrayOptional by bindParcelableArrayArgOrNull<Parcelable>("parcelableArrayOptional")
    private val parcelableArrayListRequired by bindParcelableArrayListArgOrThrow<TestParcelable>("parcelableArrayListRequired")
    private val parcelableArrayListOptional by bindParcelableArrayListArgOrNull<TestParcelable>("parcelableArrayListOptional")

    private val serializableRequired by bindSerializableArgOrThrow<TestSerializable>("serializableRequired")
    private val serializableOptional by bindSerializableArgOrNull<TestSerializable>("serializableOptional")

    private val bundleRequired by bindBundleArgOrThrow("bundleRequired")
    private val bundleOptional by bindBundleArgOrNull("bundleOptional")

    private val byteArrayOrDefault by bindByteArrayArgOr("byteArrayOrDefault", byteArrayOf())

    private val shortArrayOrDefault by bindShortArrayArgOr("shortArrayOrDefault", shortArrayOf())

    private val intArrayOrDefault by bindIntArrayArgOr("intArrayOrDefault", intArrayOf())

    private val intArrayListOrDefault by bindIntArrayListArgOr("intArrayListOrDefault", arrayListOf())

    private val longArrayOrDefault by bindLongArrayArgOr("longArrayOrDefault", longArrayOf())

    private val floatArrayOrDefault by bindFloatArrayArgOr("floatArrayOrDefault", floatArrayOf())

    private val doubleArrayOrDefault by bindDoubleArrayArgOr("doubleArrayOrDefault", doubleArrayOf())

    private val booleanArrayOrDefault by bindBooleanArrayArgOr("booleanArrayOrDefault", booleanArrayOf())

    private val charArrayOrDefault by bindCharArrayArgOr("charArrayOrDefault", charArrayOf())

    private val charSequenceOrDefault by bindCharSequenceArgOr("charSequenceOrDefault", "default")

    private val charSequenceArrayOrDefault by bindCharSequenceArrayArgOr("charSequenceArrayOrDefault", arrayOf())

    private val charSequenceArrayListRequired by bindCharSequenceArrayListArgOrThrow("charSequenceArrayListRequired")
    private val charSequenceArrayListOptional by bindCharSequenceArrayListArgOrNull("charSequenceArrayListOptional")
    private val charSequenceArrayListOrDefault by bindCharSequenceArrayListArgOr("charSequenceArrayListOrDefault", arrayListOf())

    private val stringOrDefault by bindStringArgOr("stringOrDefault", "")

    private val stringArrayOrDefault by bindStringArrayArgOr("stringArrayOrDefault", arrayOf())

    private val stringArrayListOrDefault by bindStringArrayListArgOr("stringArrayListOrDefault", arrayListOf())

    private val parcelableOrDefault by bindParcelableArgOr("parcelableOrDefault", TestParcelable("default"))

    private val parcelableArrayOrDefault by bindParcelableArrayArgOr("parcelableArrayOrDefault", arrayOf())

    private val parcelableArrayListOrDefault by bindParcelableArrayListArgOr("parcelableArrayListOrDefault", arrayListOf())

    private val serializableOrDefault by bindSerializableArgOr("serializableOrDefault", TestSerializable("default"))

    private val bundleOrDefault by bindBundleArgOr("bundleOrDefault", Bundle())

    private val sparseParcelableArrayRequired by bindSparseParcelableArrayArgOrThrow<TestParcelable>("sparseParcelableArrayRequired")
    private val sparseParcelableArrayOptional by bindSparseParcelableArrayArgOrNull<TestParcelable>("sparseParcelableArrayOptional")
    private val sparseParcelableArrayOrDefault by bindSparseParcelableArrayArgOr<TestParcelable>("sparseParcelableArrayOrDefault", SparseArray())
    private val sparseParcelableArrayOrDefaultErrKey by bindSparseParcelableArrayArgOr("sparseParcelableArrayOrDefaultErrKey",
            SparseArray<TestParcelable>().apply {
                put(-4, TestParcelable("-4"))
                put(4, TestParcelable("4"))
            })

    private val stringOptionalErrKey by bindStringArgOrNull("KeyNotExist")
    private val stringArrayOptionalErrKey by bindStringArrayArgOrNull("KeyNotExist")
    private val stringArrayListOptionalErrKey by bindStringArrayListArgOrNull("KeyNotExist")
    private val booleanArrayOptionalErrKey by bindBooleanArrayArgOrNull("KeyNotExist")
    private val charArrayOptionalErrKEy by bindCharArrayArgOrNull("KeyNotExist")
    private val charSequenceOptionalErrKet by bindCharSequenceArgOrNull("KeyNotExist")
    private val charSequenceArrayOptionalErrKey by bindCharSequenceArrayArgOrNull("KeyNotExist")
    private val parcelableOptionalErrKey by bindParcelableArgOrNull<TestParcelable>("KeyNotExist")
    private val parcelableArrayOptionalErrKey by bindParcelableArrayArgOrNull<Parcelable>("KeyNotExist")
    private val parcelableArrayListOptionalErrKey by bindParcelableArrayListArgOrNull<TestParcelable>("KeyNotExist")
    private val serializableOptionalErrKey by bindSerializableArgOrNull<TestSerializable>("KeyNotExist")
    private val bundleOptionalErrKey by bindBundleArgOrNull("KeyNotExist")
    private val charSequenceArrayListOptionalErrKey by bindCharSequenceArrayListArgOrNull("KeyNotExist")
    private val byteArrayOptionalErrKey by bindByteArrayArgOrNull("KeyNotExist")
    private val shortArrayOptionalErrKey by bindShortArrayArgOrNull("KeyNotExist")
    private val intArrayOptionalErrKey by bindIntArrayArgOrNull("KeyNotExist")
    private val intArrayListOptionalErrKey by bindIntArrayListArgOrNull("KeyNotExist")
    private val longArrayOptionalErrKey by bindLongArrayArgOrNull("KeyNotExist")
    private val floatArrayOptionalErrKey by bindFloatArrayArgOrNull("KeyNotExist")
    private val doubleArrayOptionalErrKey by bindDoubleArrayArgOrNull("KeyNotExist")
    private val sparseParcelableArrayOptionalErrKey by bindSparseParcelableArrayArgOrNull<TestParcelable>("KeyNotExist")

    fun checkParams() {
        Assert.assertNull(stringOptionalErrKey)
        Assert.assertNull(stringArrayOptionalErrKey)
        Assert.assertNull(stringArrayListOptionalErrKey)
        Assert.assertNull(booleanArrayOptionalErrKey)
        Assert.assertNull(byteArrayOptionalErrKey)
        Assert.assertNull(shortArrayOptionalErrKey)
        Assert.assertNull(intArrayOptionalErrKey)
        Assert.assertNull(intArrayListOptionalErrKey)
        Assert.assertNull(floatArrayOptionalErrKey)
        Assert.assertNull(longArrayOptionalErrKey)
        Assert.assertNull(doubleArrayOptionalErrKey)
        Assert.assertNull(charArrayOptionalErrKEy)
        Assert.assertNull(charSequenceOptionalErrKet)
        Assert.assertNull(charSequenceArrayOptionalErrKey)
        Assert.assertNull(charSequenceArrayListOptionalErrKey)
        Assert.assertNull(parcelableOptionalErrKey)
        Assert.assertNull(parcelableArrayOptionalErrKey)
        Assert.assertNull(parcelableArrayListOptionalErrKey)
        Assert.assertNull(serializableOptionalErrKey)
        Assert.assertNull(bundleOptionalErrKey)
        Assert.assertNull(sparseParcelableArrayOptionalErrKey)

        Assert.assertTrue(booleanRequired)
        Assert.assertTrue(booleanArrayRequired[0] && !booleanArrayRequired[1])
        Assert.assertTrue(booleanArrayOptional?.get(0) != true
                && booleanArrayOptional?.get(1) ?: false)

        Assert.assertTrue(byteRequired == 2.toByte())
        Assert.assertTrue(byteArrayRequired[0] == 2.toByte()
                && byteArrayRequired[1] == (-2).toByte())
        Assert.assertTrue(byteArrayOptional?.get(0) ?: 0.toByte() == (-2).toByte()
                && byteArrayOptional?.get(1) ?: 0.toByte() == 2.toByte())

        Assert.assertTrue(charRequired == 'a')
        Assert.assertTrue(charArrayRequired[0] == 'a'
                && charArrayRequired[1] == 'b')
        Assert.assertTrue(charArrayOptional?.get(0) ?: 0.toByte() == 'b'
                && charArrayOptional?.get(1) ?: 0.toByte() == 'a')

        Assert.assertTrue(shortRequired == 3.toShort())
        Assert.assertTrue(shortArrayRequired[0] == 3.toShort()
                && shortArrayRequired[1] == (-3).toShort())
        Assert.assertTrue(shortArrayOptional?.get(0) ?: 0.toShort() == (-3).toShort()
                && shortArrayOptional?.get(1) ?: 0.toShort() == 3.toShort())

        Assert.assertTrue(floatRequired == 4f)
        Assert.assertTrue(floatArrayRequired[0] == 4f
                && floatArrayRequired[1] == (-4f))
        Assert.assertTrue(floatArrayOptional?.get(0) ?: 0f == (-4f)
                && floatArrayOptional?.get(1) ?: 0f == 4f)

        Assert.assertTrue(longRequired == 1000L)
        Assert.assertTrue(longArrayRequired[0] == 1000L
                && longArrayRequired[1] == (-1000L))
        Assert.assertTrue(longArrayOptional?.get(0) ?: 0L == (-1000L)
                && longArrayOptional?.get(1) ?: 0L == 1000L)

        Assert.assertTrue(intRequired == 500)
        Assert.assertTrue(intArrayRequired[0] == 500
                && intArrayRequired[1] == (-500))
        Assert.assertTrue(intArrayOptional?.get(0) ?: 0 == (-500)
                && intArrayOptional?.get(1) ?: 0 == 500)
        Assert.assertTrue(intArrayListRequired[0] == 500
                && intArrayListRequired[1] == (-500))
        Assert.assertTrue(intArrayListOptional?.get(0) ?: 0 == (-500)
                && intArrayListOptional?.get(1) ?: 0 == 500)

        Assert.assertTrue(doubleRequired == 6.toDouble())
        Assert.assertTrue(doubleArrayRequired[0] == 6.toDouble()
                && doubleArrayRequired[1] == (-6).toDouble())
        Assert.assertTrue(doubleArrayOptional?.get(0) ?: 0.toDouble() == (-6).toDouble()
                && doubleArrayOptional?.get(1) ?: 0.toDouble() == 6.toDouble())

        Assert.assertTrue(stringRequired == "stringRequired")
        Assert.assertTrue(stringOptional == "stringOptional")
        Assert.assertTrue(stringArrayRequired[0] == "stringRequired"
                && stringArrayRequired[1] == "stringOptional")
        Assert.assertTrue(stringArrayOptional?.get(0) == "stringOptional"
                && stringArrayOptional?.get(1) == "stringRequired")
        Assert.assertTrue(stringArrayListRequired[0] == "stringRequired"
                && stringArrayListRequired[1] == "stringOptional")
        Assert.assertTrue(stringArrayListOptional?.get(0) == "stringOptional"
                && stringArrayListOptional?.get(1) == "stringRequired")

        Assert.assertTrue(charSequenceRequired == "stringRequired")
        Assert.assertTrue(charSequenceOptional == "stringOptional")
        Assert.assertTrue(charSequenceArrayRequired[0] == "stringRequired"
                && stringArrayRequired[1] == "stringOptional")
        Assert.assertTrue(charSequenceArrayOptional?.get(0) == "stringOptional"
                && stringArrayOptional?.get(1) == "stringRequired")

        Assert.assertTrue(parcelableRequired == TestParcelable("parcelableRequired"))
        Assert.assertTrue(parcelableOptional == TestParcelable("parcelableOptional"))
        Assert.assertTrue(parcelableArrayRequired[0] == TestParcelable("parcelableRequired")
                && parcelableArrayRequired[1] == TestParcelable("parcelableOptional"))
        Assert.assertTrue(parcelableArrayOptional?.get(0) == TestParcelable("parcelableOptional")
                && parcelableArrayOptional?.get(1) == TestParcelable("parcelableRequired"))
        Assert.assertTrue(parcelableArrayListRequired[0] == TestParcelable("parcelableRequired")
                && parcelableArrayListRequired[1] == TestParcelable("parcelableOptional"))
        Assert.assertTrue(parcelableArrayListOptional?.get(0) == TestParcelable("parcelableOptional")
                && parcelableArrayListOptional?.get(1) == TestParcelable("parcelableRequired"))

        Assert.assertTrue(serializableRequired == TestSerializable("serializableRequired"))
        Assert.assertTrue(serializableOptional == TestSerializable("serializableOptional"))

        Assert.assertTrue(bundleRequired.getString("bundle") == "bundleRequired")
        Assert.assertTrue(bundleOptional?.getString("bundle") == "bundleOptional")

        Assert.assertTrue(byteArrayOrDefault[0] == 2.toByte() && byteArrayOrDefault[1] == (-2).toByte())

        Assert.assertTrue(shortArrayOrDefault[0] == 3.toShort() && shortArrayOrDefault[1] == (-3).toShort())

        Assert.assertTrue(intArrayOrDefault[0] == 500 && intArrayOrDefault[1] == -500)

        Assert.assertTrue(intArrayListOrDefault[0] == 600 && intArrayListOrDefault[1] == -600)

        Assert.assertTrue(longArrayOrDefault[0] == 1000L && longArrayOrDefault[1] == -1000L)

        Assert.assertTrue(floatArrayOrDefault[0] == 4f && floatArrayOrDefault[1] == -4f)

        Assert.assertTrue(doubleArrayOrDefault[0] == 6.toDouble() && doubleArrayOrDefault[1] == (-6).toDouble())

        Assert.assertTrue(booleanArrayOrDefault[0] && !booleanArrayOrDefault[1])

        Assert.assertTrue(charArrayOrDefault[0] == 'a' && charArrayOrDefault[1] == 'b')

        Assert.assertTrue(charSequenceOrDefault == "charSequenceOrDefault")

        Assert.assertTrue(charSequenceArrayOrDefault[0] == "charSequence" && charSequenceArrayOrDefault[1] == "default")

        Assert.assertTrue(charSequenceArrayListRequired[0] == "charSequenceArrayListRequired" && charSequenceArrayListRequired[1] == "required")
        Assert.assertTrue(charSequenceArrayListOptional?.get(0) == "charSequenceArrayListOptional" && charSequenceArrayListOptional?.get(1) == "optional")
        Assert.assertTrue(charSequenceArrayListOrDefault[0] == "charSequenceArrayListOrDefault" && charSequenceArrayListOrDefault[1] == "default")

        Assert.assertTrue(stringOrDefault == "stringOrDefault")

        Assert.assertTrue(stringArrayOrDefault[0] == "stringArrayOrDefault" && stringArrayOrDefault[1] == "default")

        Assert.assertTrue(stringArrayListOrDefault[0] == "stringArrayListOrDefault" && stringArrayListOrDefault[1] == "default")

        Assert.assertTrue(parcelableOrDefault == TestParcelable("parcelableOrDefault"))

        Assert.assertTrue(parcelableArrayOrDefault[0] == TestParcelable("parcelableArrayOrDefault") && parcelableArrayOrDefault[1] == TestParcelable("default"))

        Assert.assertTrue(parcelableArrayListOrDefault[0] == TestParcelable("parcelableArrayListOrDefault") && parcelableArrayOrDefault[1] == TestParcelable("default"))

        Assert.assertTrue(serializableOrDefault == TestSerializable("serializableOrDefault"))

        Assert.assertTrue(bundleOrDefault.getString("bundle") == "bundleOrDefault")

        Assert.assertTrue(sparseParcelableArrayRequired[-1] == TestParcelable("-1") && sparseParcelableArrayRequired[1] == TestParcelable("1"))
        Assert.assertTrue(sparseParcelableArrayOptional?.get(-2) == TestParcelable("-2") && sparseParcelableArrayOptional?.get(2) == TestParcelable("2"))
        Assert.assertTrue(sparseParcelableArrayOrDefault[-3] == TestParcelable("-3") && sparseParcelableArrayOrDefault[3] == TestParcelable("3"))
        Assert.assertTrue(sparseParcelableArrayOrDefaultErrKey[-4] == TestParcelable("-4") && sparseParcelableArrayOrDefaultErrKey[4] == TestParcelable("4"))
    }

    companion object {
        fun createArguments(activity: TestActivity): Bundle = Bundle().apply {
            putByte("byteRequired", activity.byteRequired)
            putByteArray("byteArrayRequired", activity.byteArrayRequired)
            activity.byteArrayOptional?.let { putByteArray("byteArrayOptional", it) }

            putShort("shortRequired", activity.shortRequired)
            putShortArray("shortArrayRequired", activity.shortArrayRequired)
            activity.shortArrayOptional?.let { putShortArray("shortArrayOptional", it) }

            putInt("intRequired", activity.intRequired)
            putIntArray("intArrayRequired", activity.intArrayRequired)
            activity.intArrayOptional?.let { putIntArray("intArrayOptional", it) }
            putIntegerArrayList("intArrayListRequired", activity.intArrayListRequired)
            activity.intArrayListOptional?.let { putIntegerArrayList("intArrayListOptional", it) }

            putLong("longRequired", activity.longRequired)
            putLongArray("longArrayRequired", activity.longArrayRequired)
            activity.longArrayOptional?.let { putLongArray("longArrayOptional", it) }

            putFloat("floatRequired", activity.floatRequired)
            putFloatArray("floatArrayRequired", activity.floatArrayRequired)
            activity.floatArrayOptional?.let { putFloatArray("floatArrayOptional", it) }

            putDouble("doubleRequired", activity.doubleRequired)
            putDoubleArray("doubleArrayRequired", activity.doubleArrayRequired)
            activity.doubleArrayOptional?.let { putDoubleArray("doubleArrayOptional", it) }

            putBoolean("booleanRequired", activity.booleanRequired)
            putBooleanArray("booleanArrayRequired", activity.booleanArrayRequired)
            activity.booleanArrayOptional?.let { putBooleanArray("booleanArrayOptional", it) }

            putChar("charRequired", activity.charRequired)
            putCharArray("charArrayRequired", activity.charArrayRequired)
            activity.charArrayOptional?.let { putCharArray("charArrayOptional", it) }

            putString("stringRequired", activity.stringRequired)
            activity.stringOptional?.let { putString("stringOptional", it) }
            putStringArray("stringArrayRequired", activity.stringArrayRequired)
            activity.stringArrayOptional?.let { putStringArray("stringArrayOptional", it) }
            putStringArrayList("stringArrayListRequired", activity.stringArrayListRequired)
            activity.stringArrayListOptional?.let { putStringArrayList("stringArrayListOptional", it) }

            putCharSequence("charSequenceRequired", activity.charSequenceRequired)
            activity.charSequenceOptional?.let { putCharSequence("charSequenceOptional", it) }
            putCharSequenceArray("charSequenceArrayRequired", activity.charSequenceArrayRequired)
            activity.charSequenceArrayOptional?.let { putCharSequenceArray("charSequenceArrayOptional", it) }

            putParcelable("parcelableRequired", activity.parcelableRequired)
            activity.parcelableOptional?.let { putParcelable("parcelableOptional", it) }
            putParcelableArray("parcelableArrayRequired", activity.parcelableArrayRequired)
            activity.parcelableArrayOptional?.let { putParcelableArray("parcelableArrayOptional", it) }
            putParcelableArrayList("parcelableArrayListRequired", activity.parcelableArrayListRequired)
            activity.parcelableArrayListOptional?.let { putParcelableArrayList("parcelableArrayListOptional", it) }

            putSerializable("serializableRequired", activity.serializableRequired)
            activity.serializableOptional?.let { putSerializable("serializableOptional", it) }

            putBundle("bundleRequired", activity.bundleRequired)
            activity.bundleOptional?.let { putBundle("bundleOptional", it) }

            putByteArray("byteArrayOrDefault", activity.byteArrayOrDefault)
            putShortArray("shortArrayOrDefault", activity.shortArrayOrDefault)
            putIntArray("intArrayOrDefault", activity.intArrayOrDefault)
            putIntegerArrayList("intArrayListOrDefault", activity.intArrayListOrDefault)
            putLongArray("longArrayOrDefault", activity.longArrayOrDefault)
            putFloatArray("floatArrayOrDefault", activity.floatArrayOrDefault)
            putDoubleArray("doubleArrayOrDefault", activity.doubleArrayOrDefault)
            putBooleanArray("booleanArrayOrDefault", activity.booleanArrayOrDefault)
            putCharArray("charArrayOrDefault", activity.charArrayOrDefault)
            putCharSequence("charSequenceOrDefault", activity.charSequenceOrDefault)
            putCharSequenceArray("charSequenceArrayOrDefault", activity.charSequenceArrayOrDefault)

            putCharSequenceArrayList("charSequenceArrayListRequired", activity.charSequenceArrayListRequired)
            putCharSequenceArrayList("charSequenceArrayListOptional", activity.charSequenceArrayListOptional)
            putCharSequenceArrayList("charSequenceArrayListOrDefault", activity.charSequenceArrayListOrDefault)

            putString("stringOrDefault", activity.stringOrDefault)
            putStringArray("stringArrayOrDefault", activity.stringArrayOrDefault)
            putStringArrayList("stringArrayListOrDefault", activity.stringArrayListOrDefault)

            putParcelable("parcelableOrDefault", activity.parcelableOrDefault)
            putParcelableArray("parcelableArrayOrDefault", activity.parcelableArrayOrDefault)
            putParcelableArrayList("parcelableArrayListOrDefault", activity.parcelableArrayListOrDefault)

            putSerializable("serializableOrDefault", activity.serializableOrDefault)

            putBundle("bundleOrDefault", activity.bundleOrDefault)

            putSparseParcelableArray("sparseParcelableArrayRequired", SparseArray<TestParcelable>()
                    .apply {
                        put(-1, TestParcelable("-1"))
                        put(1, TestParcelable("1"))
                    })
            putSparseParcelableArray("sparseParcelableArrayOptional", SparseArray<TestParcelable>()
                    .apply {
                        put(-2, TestParcelable("-2"))
                        put(2, TestParcelable("2"))
                    })
            putSparseParcelableArray("sparseParcelableArrayOrDefault", SparseArray<TestParcelable>()
                    .apply {
                        put(-3, TestParcelable("-3"))
                        put(3, TestParcelable("3"))
                    })
        }
    }
}