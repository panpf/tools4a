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

package com.github.panpf.tools4a.args.ktx.test.read

import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import android.util.Size
import android.util.SizeF
import android.util.SparseArray
import com.github.panpf.tools4a.args.ktx.*
import org.junit.Assert

class TestSupportFragment : androidx.fragment.app.Fragment() {
    companion object {
        fun newInstance(args: Bundle?): TestSupportFragment {
            val fragment = TestSupportFragment()
            fragment.arguments = args
            return fragment
        }
    }

    fun checkParams() {
        val fragment = this

        val byteRequired = fragment.readByteArgOr("byteRequired", 0.toByte())
        val byteRequiredErrKey = fragment.readByteArgOr("byteRequiredErrKey", 0.toByte())
        val byteArrayRequired = fragment.readByteArrayArgOrThrow("byteArrayRequired")
        val byteArrayOptional = fragment.readByteArrayArgOrNull("byteArrayOptional")
        val byteArrayOptionalErrKey = fragment.readByteArrayArgOrNull("byteArrayOptionalErrKey")
        val byteArrayOrDefault = fragment.readByteArrayArgOr("byteArrayOrDefault", byteArrayOf(0.toByte(), 0.toByte()))
        val byteArrayOrDefaultErrKey = fragment.readByteArrayArgOr("byteArrayOrDefaultErrKey", byteArrayOf(0.toByte(), (-1).toByte()))

        val shortRequired = fragment.readShortArgOr("shortRequired", 0.toShort())
        val shortRequiredErrKey = fragment.readShortArgOr("shortRequiredErrKey", 0.toShort())
        val shortArrayRequired = fragment.readShortArrayArgOrThrow("shortArrayRequired")
        val shortArrayOptional = fragment.readShortArrayArgOrNull("shortArrayOptional")
        val shortArrayOptionalErrKey = fragment.readShortArrayArgOrNull("shortArrayOptionalErrKey")
        val shortArrayOrDefault = fragment.readShortArrayArgOr("shortArrayOrDefault", shortArrayOf(0.toShort(), 0.toShort()))
        val shortArrayOrDefaultErrKey = fragment.readShortArrayArgOr("shortArrayOrDefaultErrKey", shortArrayOf(0.toShort(), (-1).toShort()))


        val intRequired = fragment.readIntArgOr("intRequired", 0)
        val intRequiredErrKey = fragment.readIntArgOr("intRequiredErrKey", 0)
        val intArrayRequired = fragment.readIntArrayArgOrThrow("intArrayRequired")
        val intArrayOptional = fragment.readIntArrayArgOrNull("intArrayOptional")
        val intArrayOptionalErrKey = fragment.readIntArrayArgOrNull("intArrayOptionalErrKey")
        val intArrayOrDefault = fragment.readIntArrayArgOr("intArrayOrDefault", intArrayOf(0, 0))
        val intArrayOrDefaultErrKey = fragment.readIntArrayArgOr("intArrayOrDefaultErrKey", intArrayOf(0, -1))
        val intArrayListRequired = fragment.readIntArrayListArgOrThrow("intArrayListRequired")
        val intArrayListOrDefault = fragment.readIntArrayListArgOr("intArrayListOrDefault", arrayListOf(0, 0))
        val intArrayListOrDefaultErrKey = fragment.readIntArrayListArgOr("intArrayListOrDefaultErrKey", arrayListOf(0, 0))
        val intArrayListOptional = fragment.readIntArrayListArgOrNull("intArrayListOptional")
        val intArrayListOptionalErrKey = fragment.readIntArrayListArgOrNull("intArrayListOptionalErrKey")

        val longRequired = fragment.readLongArgOr("longRequired", 0)
        val longRequiredErrKey = fragment.readLongArgOr("longRequiredErrKey", 0)
        val longArrayRequired = fragment.readLongArrayArgOrThrow("longArrayRequired")
        val longArrayOptional = fragment.readLongArrayArgOrNull("longArrayOptional")
        val longArrayOptionalErrKey = fragment.readLongArrayArgOrNull("longArrayOptionalErrKey")
        val longArrayOrDefault = fragment.readLongArrayArgOr("longArrayOrDefault", longArrayOf(0, 0))
        val longArrayOrDefaultErrKey = fragment.readLongArrayArgOr("longArrayOrDefaultErrKey", longArrayOf(0, -1))

        val floatRequired = fragment.readFloatArgOr("floatRequired", 0f)
        val floatRequiredErrKey = fragment.readFloatArgOr("floatRequiredErrKey", 0f)
        val floatArrayRequired = fragment.readFloatArrayArgOrThrow("floatArrayRequired")
        val floatArrayOptional = fragment.readFloatArrayArgOrNull("floatArrayOptional")
        val floatArrayOptionalErrKey = fragment.readFloatArrayArgOrNull("DoubleArrayOptionalErrKey")
        val floatArrayOrDefault = fragment.readFloatArrayArgOr("floatArrayOrDefault", floatArrayOf(0f, 0f))
        val floatArrayOrDefaultErrKey = fragment.readFloatArrayArgOr("floatArrayOrDefaultErrKey", floatArrayOf(0f, -1f))

        val doubleRequired = fragment.readDoubleArgOr("doubleRequired", 0.0)
        val doubleRequiredErrKey = fragment.readDoubleArgOr("doubleRequiredErrKey", 0.0)
        val doubleArrayRequired = fragment.readDoubleArrayArgOrThrow("doubleArrayRequired")
        val doubleArrayOptional = fragment.readDoubleArrayArgOrNull("doubleArrayOptional")
        val doubleArrayOptionalErrKey = fragment.readDoubleArrayArgOrNull("doubleArrayOptionalErrKey")
        val doubleArrayOrDefault = fragment.readDoubleArrayArgOr("doubleArrayOrDefault", doubleArrayOf(0.0, 0.0))
        val doubleArrayOrDefaultErrKey = fragment.readDoubleArrayArgOr("doubleArrayOrDefaultErrKey", doubleArrayOf(0.0, -1.0))

        val booleanRequired = fragment.readBooleanArgOr("booleanRequired", false)
        val booleanRequiredErrKey = fragment.readBooleanArgOr("booleanRequiredErrKey", false)
        val booleanArrayRequired = fragment.readBooleanArrayArgOrThrow("booleanArrayRequired")
        val booleanArrayOptional = fragment.readBooleanArrayArgOrNull("booleanArrayOptional")
        val booleanArrayOptionalErrKey = fragment.readBooleanArrayArgOrNull("booleanArrayOptionalErrKey")
        val booleanArrayOrDefault = fragment.readBooleanArrayArgOr("booleanArrayOrDefault", booleanArrayOf(true, false))
        val booleanArrayOrDefaultErrKey = fragment.readBooleanArrayArgOr("booleanArrayOrDefaultErrKey", booleanArrayOf(false, true))

        val charRequired = fragment.readCharArgOr("charRequired", 'a')
        val charRequiredErrKey = fragment.readCharArgOr("charRequiredErrKey", 'b')
        val charArrayRequired = fragment.readCharArrayArgOrThrow("charArrayRequired")
        val charArrayOptional = fragment.readCharArrayArgOrNull("charArrayOptional")
        val charArrayOptionalErrKey = fragment.readCharArrayArgOrNull("charArrayOptionalErrKey")
        val charArrayOrDefault = fragment.readCharArrayArgOr("charArrayOrDefault", charArrayOf('a', 'b'))
        val charArrayOrDefaultErrKey = fragment.readCharArrayArgOr("charArrayOrDefaultErrKey", charArrayOf('b', 'a'))

        val stringRequired = fragment.readStringArgOr("stringRequired", "stringRequired")
        val stringRequiredErrKey = fragment.readStringArgOr("stringRequiredErrKey", "stringRequiredErrKey")
        val stringArrayRequired = fragment.readStringArrayArgOrThrow("stringArrayRequired")
        val stringArrayOptional = fragment.readStringArrayArgOrNull("stringArrayOptional")
        val stringArrayOptionalErrKey = fragment.readStringArrayArgOrNull("stringArrayOptionalErrKey")
        val stringArrayOrDefault = fragment.readStringArrayArgOr("stringArrayOrDefault", arrayOf("array", "dft"))
        val stringArrayOrDefaultErrKey = fragment.readStringArrayArgOr("stringArrayOrDefaultErrKey", arrayOf("error", "erk"))
        val stringArrayListRequired = fragment.readStringArrayListArgOrThrow("stringArrayListRequired")
        val stringArrayListOrDefault = fragment.readStringArrayListArgOr("stringArrayListOrDefault", arrayListOf("list", "default"))
        val stringArrayListOrDefaultErrKey = fragment.readStringArrayListArgOr("stringArrayListOrDefaultErrKey", arrayListOf("stringArrayListOrDefaultErrKey", "errKey"))
        val stringArrayListOptional = fragment.readStringArrayListArgOrNull("stringArrayListOptional")
        val stringArrayListOptionalErrKey = fragment.readStringArrayListArgOrNull("stringArrayListOptionalErrKey")

        val charSequenceRequired = fragment.readCharSequenceArgOr("charSequenceRequired", "charSequenceRequired")
        val charSequenceRequiredErrKey = fragment.readCharSequenceArgOr("charSequenceRequiredErrKey", "charSequenceRequiredErrKey")
        val charSequenceArrayRequired = fragment.readCharSequenceArrayArgOrThrow("charSequenceArrayRequired")
        val charSequenceArrayOptional = fragment.readCharSequenceArrayArgOrNull("charSequenceArrayOptional")
        val charSequenceArrayOptionalErrKey = fragment.readCharSequenceArrayArgOrNull("charSequenceArrayOptionalErrKey")
        val charSequenceArrayOrDefault = fragment.readCharSequenceArrayArgOr("charSequenceArrayOrDefault", arrayOf<CharSequence>("array", "dft"))
        val charSequenceArrayOrDefaultErrKey = fragment.readCharSequenceArrayArgOr("charSequenceArrayOrDefaultErrKey", arrayOf<CharSequence>("error", "erk"))
        val charSequenceArrayListRequired = fragment.readCharSequenceArrayListArgOrThrow("charSequenceArrayListRequired")
        val charSequenceArrayListOrDefault = fragment.readCharSequenceArrayListArgOr("charSequenceArrayListOrDefault", arrayListOf<CharSequence>("list", "default"))
        val charSequenceArrayListOrDefaultErrKey = fragment.readCharSequenceArrayListArgOr("charSequenceArrayListOrDefaultErrKey", arrayListOf<CharSequence>("charSequenceArrayListOrDefaultErrKey", "errKey"))
        val charSequenceArrayListOptional = fragment.readCharSequenceArrayListArgOrNull("charSequenceArrayListOptional")
        val charSequenceArrayListOptionalErrKey = fragment.readCharSequenceArrayListArgOrNull("charSequenceArrayListOptionalErrKey")


        val parcelableRequired = fragment.readParcelableArgOr("parcelableRequired", TestParcelable("required"))
        val parcelableRequiredErrKey = fragment.readParcelableArgOr("parcelableRequiredErrKey", TestParcelable("parcelableRequiredErrKey"))
        val parcelableArrayRequired = fragment.readParcelableArrayArgOrThrow<Parcelable>("parcelableArrayRequired")
        val parcelableArrayOptional = fragment.readParcelableArrayArgOrNull<Parcelable>("parcelableArrayOptional")
        val parcelableArrayOptionalErrKey = fragment.readParcelableArrayArgOrNull<Parcelable>("parcelableArrayOptionalErrKey")
        val parcelableArrayOrDefault = fragment.readParcelableArrayArgOr("parcelableArrayOrDefault", arrayOf())
        val parcelableArrayOrDefaultErrKey = fragment.readParcelableArrayArgOr("parcelableArrayOrDefaultErrKey", arrayOf(TestParcelable("error"), TestParcelable("erk")))
        val parcelableArrayListRequired = fragment.readParcelableArrayListArgOrThrow<Parcelable>("parcelableArrayListRequired")
        val parcelableArrayListOrDefault = fragment.readParcelableArrayListArgOr("parcelableArrayListOrDefault", arrayListOf<Parcelable>(TestParcelable("list"), TestParcelable("default")))
        val parcelableArrayListOrDefaultErrKey = fragment.readParcelableArrayListArgOr("parcelableArrayListOrDefaultErrKey", arrayListOf<Parcelable>(TestParcelable("parcelableArrayListOrDefaultErrKey"), TestParcelable("errKey")))
        val parcelableArrayListOptional = fragment.readParcelableArrayListArgOrNull<Parcelable>("parcelableArrayListOptional")
        val parcelableArrayListOptionalErrKey = fragment.readParcelableArrayListArgOrNull<Parcelable>("parcelableArrayListOptionalErrKey")

        val serializableRequired = fragment.readSerializableArgOrThrow<TestSerializable>("serializableRequired")
        val serializableOptional = fragment.readSerializableArgOrNull<TestSerializable>("serializableOptional")
        val serializableOptionalErrKey = fragment.readSerializableArgOrNull<TestSerializable>("serializableOptionalErrKey")
        val serializableOrDefault = fragment.readSerializableArgOr("serializableOrDefault", TestSerializable("default"))
        val serializableOrDefaultErrKey = fragment.readSerializableArgOr("serializableOrDefaultErrKey", TestSerializable("errKey"))

        val bundleRequired = fragment.readBundleArgOrThrow("bundleRequired")
        val bundleOptional = fragment.readBundleArgOrNull("bundleOptional")
        val bundleOptionalErrKey = fragment.readBundleArgOrNull("bundleOptionalErrKey")
        val bundleOrDefault = fragment.readBundleArgOr("bundleOrDefault", Bundle())
        val defaultBundle = Bundle()
        defaultBundle.putString("bundle", "bundleErrKey")
        val bundleOrDefaultErrKey = fragment.readBundleArgOr("bundleOrDefaultErrKey", defaultBundle)


        val sparseArrayDefault = SparseArray<Parcelable>()
        sparseArrayDefault.put(0, TestParcelable("0"))
        val sparseParcelableArrayRequired = fragment.readSparseParcelableArrayArgOrThrow<Parcelable>("sparseParcelableArrayRequired")
        val sparseParcelableArrayOptional = fragment.readSparseParcelableArrayArgOrNull<Parcelable>("sparseParcelableArrayOptional")
        val sparseParcelableArrayOptionalErrKey = fragment.readSparseParcelableArrayArgOrNull<Parcelable>("sparseParcelableArrayOptionalErrKey")
        val sparseParcelableArrayOrDefault = fragment.readSparseParcelableArrayArgOr("sparseParcelableArrayOrDefault", sparseArrayDefault)
        val sparseParcelableArrayOrDefaultErrKey = fragment.readSparseParcelableArrayArgOr("sparseParcelableArrayOrDefaultErrKey", sparseArrayDefault)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            val binderRequired = fragment.readBinderArgOrThrow("binderRequired")
            val binderOptional = fragment.readBinderArgOrNull("binderOptional")
            val binderOptionalErrKey = fragment.readBinderArgOrNull("binderOptionalErrKey")
            val binderOrDefault = fragment.readBinderArgOr("binderOrDefault", TestBinder(""))
            val binderOrDefaultErrKey = fragment.readBinderArgOr("binderOrDefaultErrKey", TestBinder("binderOrDefaultErrKey"))

            Assert.assertEquals(binderRequired, TestBinder("binderRequired"))
            Assert.assertEquals(binderOptional, TestBinder("binderOptional"))
            Assert.assertNull(binderOptionalErrKey)
            Assert.assertEquals(binderOrDefault, TestBinder("binderOrDefault"))
            Assert.assertEquals(binderOrDefaultErrKey, TestBinder("binderOrDefaultErrKey"))
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val sizeRequired = fragment.readSizeArgOrThrow("sizeRequired")
            val sizeOptional = fragment.readSizeArgOrNull("sizeOptional")
            val sizeOptionalErrKey = fragment.readSizeArgOrNull("sizeOptionalErrKey")
            val sizeOrDefault = fragment.readSizeArgOr("sizeOrDefault", Size(0, 0))
            val sizeOrDefaultErrKey = fragment.readSizeArgOr("sizeOrDefaultErrKey", Size(4, 4))

            val sizeFRequired = fragment.readSizeFArgOrThrow("sizeFRequired")
            val sizeFOptional = fragment.readSizeFArgOrNull("sizeFOptional")
            val sizeFOptionalErrKey = fragment.readSizeFArgOrNull("sizeFOptionalErrKey")
            val sizeFOrDefault = fragment.readSizeFArgOr("sizeFOrDefault", SizeF(0f, 0f))
            val sizeFOrDefaultErrKey = fragment.readSizeFArgOr("sizeFOrDefaultErrKey", SizeF(4f, 4f))

            Assert.assertTrue(sizeFRequired.width == 1f && sizeFRequired.height == 1f)
            Assert.assertTrue(sizeFOptional!!.width == 2f && sizeFOptional.height == 2f)
            Assert.assertNull(sizeFOptionalErrKey)
            Assert.assertTrue(sizeFOrDefault.width == 3f && sizeFOrDefault.height == 3f)
            Assert.assertTrue(sizeFOrDefaultErrKey.width == 4f && sizeFOrDefaultErrKey.height == 4f)

            Assert.assertTrue(sizeRequired.width == 1 && sizeRequired.height == 1)
            Assert.assertTrue(sizeOptional!!.width == 2 && sizeOptional.height == 2)
            Assert.assertNull(sizeOptionalErrKey)
            Assert.assertTrue(sizeOrDefault.width == 3 && sizeOrDefault.height == 3)
            Assert.assertTrue(sizeOrDefaultErrKey.width == 4 && sizeOrDefaultErrKey.height == 4)
        }


        Assert.assertTrue(sparseParcelableArrayRequired.get(-1) == TestParcelable("-1") && sparseParcelableArrayRequired.get(1) == TestParcelable("1"))
        Assert.assertTrue(sparseParcelableArrayOptional!!.get(-2) == TestParcelable("-2") && sparseParcelableArrayOptional.get(2) == TestParcelable("2"))
        Assert.assertNull(sparseParcelableArrayOptionalErrKey)
        Assert.assertTrue(sparseParcelableArrayOrDefault.get(-3) == TestParcelable("-3") && sparseParcelableArrayOrDefault.get(3) == TestParcelable("3"))
        Assert.assertTrue(sparseParcelableArrayOrDefaultErrKey.get(0) == TestParcelable("0"))

        Assert.assertTrue(bundleRequired.getString("bundle") == "bundleRequired")
        Assert.assertTrue(bundleOptional!!.getString("bundle") == "bundleOptional")
        Assert.assertNull(bundleOptionalErrKey)
        Assert.assertTrue(bundleOrDefault.getString("bundle") == "bundleOrDefault")
        Assert.assertTrue(bundleOrDefaultErrKey.getString("bundle") == "bundleErrKey")


        Assert.assertEquals(serializableRequired, TestSerializable("serializableRequired"))
        Assert.assertEquals(serializableOptional, TestSerializable("serializableOptional"))
        Assert.assertNull(serializableOptionalErrKey)
        Assert.assertEquals(serializableOrDefault, TestSerializable("serializableOrDefault"))
        Assert.assertEquals(serializableOrDefaultErrKey, TestSerializable("errKey"))

        Assert.assertEquals(parcelableRequired, TestParcelable("parcelableRequired"))
        Assert.assertEquals(parcelableRequiredErrKey, TestParcelable("parcelableRequiredErrKey"))
        Assert.assertTrue(parcelableArrayRequired[0] == TestParcelable("parcelableRequired") && parcelableArrayRequired[1] == TestParcelable("parcelableOptional"))
        Assert.assertTrue(parcelableArrayOptional!![0] == TestParcelable("parcelableOptional") && parcelableArrayOptional[1] == TestParcelable("parcelableRequired"))
        Assert.assertNull(parcelableArrayOptionalErrKey)
        Assert.assertTrue(parcelableArrayOrDefault[0] == TestParcelable("parcelableArrayOrDefault") && parcelableArrayOrDefault[1] == TestParcelable("default"))
        Assert.assertTrue(parcelableArrayOrDefaultErrKey[0] == TestParcelable("error") && parcelableArrayOrDefaultErrKey[1] == TestParcelable("erk"))
        Assert.assertTrue(parcelableArrayListRequired[0] == TestParcelable("parcelableRequired") && parcelableArrayListRequired[1] == TestParcelable("parcelableOptional"))
        Assert.assertTrue(parcelableArrayListOptional!![0] == TestParcelable("parcelableOptional") && parcelableArrayListOptional[1] == TestParcelable("parcelableRequired"))
        Assert.assertNull(parcelableArrayListOptionalErrKey)
        Assert.assertTrue(parcelableArrayListOrDefault[0] == TestParcelable("parcelableArrayListOrDefault") && parcelableArrayListOrDefault[1] == TestParcelable("default"))
        Assert.assertTrue(parcelableArrayListOrDefaultErrKey[0] == TestParcelable("parcelableArrayListOrDefaultErrKey") && parcelableArrayListOrDefaultErrKey[1] == TestParcelable("errKey"))

        Assert.assertTrue(charSequenceRequired == "stringRequired")
        Assert.assertTrue(charSequenceRequiredErrKey == "charSequenceRequiredErrKey")
        Assert.assertTrue(charSequenceArrayRequired[0] == "stringRequired" && charSequenceArrayRequired[1] == "stringOptional")
        Assert.assertTrue(charSequenceArrayOptional!![0] == "stringOptional" && charSequenceArrayOptional[1] == "stringRequired")
        Assert.assertNull(charSequenceArrayOptionalErrKey)
        Assert.assertTrue(charSequenceArrayOrDefault[0] == "charSequence" && charSequenceArrayOrDefault[1] == "default")
        Assert.assertTrue(charSequenceArrayOrDefaultErrKey[0] == "error" && charSequenceArrayOrDefaultErrKey[1] == "erk")
        Assert.assertTrue(charSequenceArrayListRequired[0] == "charSequenceArrayListRequired" && charSequenceArrayListRequired[1] == "required")
        Assert.assertTrue(charSequenceArrayListOrDefault[0] == "charSequenceArrayListOrDefault" && charSequenceArrayListOrDefault[1] == "default")
        Assert.assertTrue(charSequenceArrayListOrDefaultErrKey[0] == "charSequenceArrayListOrDefaultErrKey" && charSequenceArrayListOrDefaultErrKey[1] == "errKey")
        Assert.assertTrue(charSequenceArrayListOptional!![0] == "charSequenceArrayListOptional" && charSequenceArrayListOptional[1] == "optional")
        Assert.assertNull(charSequenceArrayListOptionalErrKey)

        Assert.assertTrue(stringRequired == "stringRequired")
        Assert.assertTrue(stringRequiredErrKey == "stringRequiredErrKey")
        Assert.assertTrue(stringArrayRequired[0] == "stringRequired" && stringArrayRequired[1] == "stringOptional")
        Assert.assertTrue(stringArrayOptional!![0] == "stringOptional" && stringArrayOptional[1] == "stringRequired")
        Assert.assertNull(stringArrayOptionalErrKey)
        Assert.assertTrue(stringArrayOrDefault[0] == "stringArrayOrDefault" && stringArrayOrDefault[1] == "default")
        Assert.assertTrue(stringArrayOrDefaultErrKey[0] == "error" && stringArrayOrDefaultErrKey[1] == "erk")
        Assert.assertTrue(stringArrayListRequired[0] == "stringRequired" && stringArrayListRequired[1] == "stringOptional")
        Assert.assertTrue(stringArrayListOrDefault[0] == "stringArrayListOrDefault" && stringArrayListOrDefault[1] == "default")
        Assert.assertTrue(stringArrayListOrDefaultErrKey[0] == "stringArrayListOrDefaultErrKey" && stringArrayListOrDefaultErrKey[1] == "errKey")
        Assert.assertTrue(stringArrayListOptional!![0] == "stringOptional" && stringArrayListOptional[1] == "stringRequired")
        Assert.assertNull(stringArrayListOptionalErrKey)

        Assert.assertTrue(byteRequired == 2.toByte())
        Assert.assertTrue(byteRequiredErrKey.toInt() == 0)
        Assert.assertTrue(byteArrayRequired[0] == 2.toByte() && byteArrayRequired[1] == (-2).toByte())
        Assert.assertTrue(byteArrayOptional!![0] == (-2).toByte() && byteArrayOptional[1] == 2.toByte())
        Assert.assertNull(byteArrayOptionalErrKey)
        Assert.assertTrue(byteArrayOrDefault[0] == 2.toByte() && byteArrayOrDefault[1] == (-2).toByte())
        Assert.assertTrue(byteArrayOrDefaultErrKey[0] == 0.toByte() && byteArrayOrDefaultErrKey[1] == (-1).toByte())

        Assert.assertTrue(shortRequired == 3.toShort())
        Assert.assertTrue(shortRequiredErrKey.toInt() == 0)
        Assert.assertTrue(shortArrayRequired[0] == 3.toShort() && shortArrayRequired[1] == (-3).toShort())
        Assert.assertTrue(shortArrayOptional!![0] == (-3).toShort() && shortArrayOptional[1] == 3.toShort())
        Assert.assertNull(shortArrayOptionalErrKey)
        Assert.assertTrue(shortArrayOrDefault[0] == 3.toShort() && shortArrayOrDefault[1] == (-3).toShort())
        Assert.assertTrue(shortArrayOrDefaultErrKey[0] == 0.toShort() && shortArrayOrDefaultErrKey[1] == (-1).toShort())

        Assert.assertTrue(intRequired == 500)
        Assert.assertTrue(intRequiredErrKey == 0)
        Assert.assertTrue(intArrayRequired[0] == 500 && intArrayRequired[1] == -500)
        Assert.assertTrue(intArrayOptional!![0] == -500 && intArrayOptional[1] == 500)
        Assert.assertNull(intArrayOptionalErrKey)
        Assert.assertTrue(intArrayOrDefault[0] == 500 && intArrayOrDefault[1] == -500)
        Assert.assertTrue(intArrayOrDefaultErrKey[0] == 0 && intArrayOrDefaultErrKey[1] == -1)
        Assert.assertTrue(intArrayListRequired[0] == 500 && intArrayListRequired[1] == -500)
        Assert.assertTrue(intArrayListOrDefault[0] == 600 && intArrayListOrDefault[1] == -600)
        Assert.assertTrue(intArrayListOrDefaultErrKey[0] == 0 && intArrayListOrDefaultErrKey[1] == 0)
        Assert.assertTrue(intArrayListOptional!![0] == -500 && intArrayListOptional[1] == 500)
        Assert.assertNull(intArrayListOptionalErrKey)

        Assert.assertTrue(longRequired == 1000L)
        Assert.assertTrue(longRequiredErrKey == 0L)
        Assert.assertTrue(longArrayRequired[0] == 1000L && longArrayRequired[1] == -1000L)
        Assert.assertTrue(longArrayOptional!![0] == -1000L && longArrayOptional[1] == 1000L)
        Assert.assertNull(longArrayOptionalErrKey)
        Assert.assertTrue(longArrayOrDefault[0] == 1000L && longArrayOrDefault[1] == -1000L)
        Assert.assertTrue(longArrayOrDefaultErrKey[0] == 0L && longArrayOrDefaultErrKey[1] == (-1).toLong())

        Assert.assertTrue(floatRequired == 4f)
        Assert.assertTrue(floatRequiredErrKey == 0f)
        Assert.assertTrue(floatArrayRequired[0] == 4f && floatArrayRequired[1] == -4f)
        Assert.assertTrue(floatArrayOptional!![0] == -4f && floatArrayOptional[1] == 4f)
        Assert.assertNull(floatArrayOptionalErrKey)
        Assert.assertTrue(floatArrayOrDefault[0] == 4f && floatArrayOrDefault[1] == -4f)
        Assert.assertTrue(floatArrayOrDefaultErrKey[0] == 0f && floatArrayOrDefaultErrKey[1] == -1f)

        Assert.assertTrue(doubleRequired == 6.0)
        Assert.assertTrue(doubleRequiredErrKey == 0.0)
        Assert.assertTrue(doubleArrayRequired[0] == 6.0 && doubleArrayRequired[1] == -6.0)
        Assert.assertTrue(doubleArrayOptional!![0] == -6.0 && doubleArrayOptional[1] == 6.0)
        Assert.assertNull(doubleArrayOptionalErrKey)
        Assert.assertTrue(doubleArrayOrDefault[0] == 6.0 && doubleArrayOrDefault[1] == -6.0)
        Assert.assertTrue(doubleArrayOrDefaultErrKey[0] == 0.0 && doubleArrayOrDefaultErrKey[1] == -1.0)

        Assert.assertTrue(booleanRequired)
        Assert.assertTrue(!booleanRequiredErrKey)
        Assert.assertTrue(booleanArrayRequired[0] && !booleanArrayRequired[1])
        Assert.assertTrue(!booleanArrayOptional!![0] && booleanArrayOptional[1])
        Assert.assertNull(booleanArrayOptionalErrKey)
        Assert.assertTrue(booleanArrayOrDefault[0] && !booleanArrayOrDefault[1])
        Assert.assertTrue(!booleanArrayOrDefaultErrKey[0] && booleanArrayOrDefaultErrKey[1])

        Assert.assertTrue(charRequired == 'a')
        Assert.assertTrue(charRequiredErrKey == 'b')
        Assert.assertTrue(charArrayRequired[0] == 'a' && charArrayRequired[1] == 'b')
        Assert.assertTrue(charArrayOptional!![0] == 'b' && charArrayOptional[1] == 'a')
        Assert.assertNull(charArrayOptionalErrKey)
        Assert.assertTrue(charArrayOrDefault[0] == 'a' && charArrayOrDefault[1] == 'b')
        Assert.assertTrue(charArrayOrDefaultErrKey[0] == 'b' && charArrayOrDefaultErrKey[1] == 'a')
    }
}