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
import com.github.panpf.tools4a.args.ktx.test.R
import org.junit.Assert

class ResTestSupportFragment : androidx.fragment.app.Fragment() {
    companion object {
        fun newInstance(args: Bundle?): ResTestSupportFragment {
            val fragment = ResTestSupportFragment()
            fragment.arguments = args
            return fragment
        }
    }

    fun checkParams() {
        val fragment = this

        val byteRequired = fragment.readByteArgOr(R.string.byte_required, 0.toByte())
        val byteRequiredErrKey = fragment.readByteArgOr(R.string.not_exist_key, 0.toByte())
        val byteArrayRequired = fragment.readByteArrayArgOrThrow(R.string.byte_array_required)
        val byteArrayOptional = fragment.readByteArrayArgOrNull(R.string.byte_array_optional)
        val byteArrayOptionalErrKey = fragment.readByteArrayArgOrNull(R.string.not_exist_key)
        val byteArrayOrDefault = fragment.readByteArrayArgOr(R.string.byte_array_or_default, byteArrayOf(0.toByte(), 0.toByte()))
        val byteArrayOrDefaultErrKey = fragment.readByteArrayArgOr(R.string.not_exist_key, byteArrayOf(0.toByte(), (-1).toByte()))

        val shortRequired = fragment.readShortArgOr(R.string.short_required, 0.toShort())
        val shortRequiredErrKey = fragment.readShortArgOr(R.string.not_exist_key, 0.toShort())
        val shortArrayRequired = fragment.readShortArrayArgOrThrow(R.string.short_array_required)
        val shortArrayOptional = fragment.readShortArrayArgOrNull(R.string.short_array_optional)
        val shortArrayOptionalErrKey = fragment.readShortArrayArgOrNull(R.string.not_exist_key)
        val shortArrayOrDefault = fragment.readShortArrayArgOr(R.string.short_array_or_default, shortArrayOf(0.toShort(), 0.toShort()))
        val shortArrayOrDefaultErrKey = fragment.readShortArrayArgOr(R.string.not_exist_key, shortArrayOf(0.toShort(), (-1).toShort()))


        val intRequired = fragment.readIntArgOr(R.string.int_required, 0)
        val intRequiredErrKey = fragment.readIntArgOr(R.string.not_exist_key, 0)
        val intArrayRequired = fragment.readIntArrayArgOrThrow(R.string.int_array_required)
        val intArrayOptional = fragment.readIntArrayArgOrNull(R.string.int_array_optional)
        val intArrayOptionalErrKey = fragment.readIntArrayArgOrNull(R.string.not_exist_key)
        val intArrayOrDefault = fragment.readIntArrayArgOr(R.string.int_array_or_default, intArrayOf(0, 0))
        val intArrayOrDefaultErrKey = fragment.readIntArrayArgOr(R.string.not_exist_key, intArrayOf(0, -1))
        val intArrayListRequired = fragment.readIntArrayListArgOrThrow(R.string.int_array_list_required)
        val intArrayListOrDefault = fragment.readIntArrayListArgOr(R.string.int_array_list_or_default, arrayListOf(0, 0))
        val intArrayListOrDefaultErrKey = fragment.readIntArrayListArgOr(R.string.not_exist_key, arrayListOf(0, 0))
        val intArrayListOptional = fragment.readIntArrayListArgOrNull(R.string.int_array_list_optional)
        val intArrayListOptionalErrKey = fragment.readIntArrayListArgOrNull(R.string.not_exist_key)

        val longRequired = fragment.readLongArgOr(R.string.long_required, 0)
        val longRequiredErrKey = fragment.readLongArgOr(R.string.not_exist_key, 0)
        val longArrayRequired = fragment.readLongArrayArgOrThrow(R.string.long_array_required)
        val longArrayOptional = fragment.readLongArrayArgOrNull(R.string.long_array_optional)
        val longArrayOptionalErrKey = fragment.readLongArrayArgOrNull(R.string.not_exist_key)
        val longArrayOrDefault = fragment.readLongArrayArgOr(R.string.long_array_or_default, longArrayOf(0, 0))
        val longArrayOrDefaultErrKey = fragment.readLongArrayArgOr(R.string.not_exist_key, longArrayOf(0, -1))

        val floatRequired = fragment.readFloatArgOr(R.string.float_required, 0f)
        val floatRequiredErrKey = fragment.readFloatArgOr(R.string.not_exist_key, 0f)
        val floatArrayRequired = fragment.readFloatArrayArgOrThrow(R.string.float_array_required)
        val floatArrayOptional = fragment.readFloatArrayArgOrNull(R.string.float_array_optional)
        val floatArrayOptionalErrKey = fragment.readFloatArrayArgOrNull(R.string.not_exist_key)
        val floatArrayOrDefault = fragment.readFloatArrayArgOr(R.string.float_array_or_default, floatArrayOf(0f, 0f))
        val floatArrayOrDefaultErrKey = fragment.readFloatArrayArgOr(R.string.not_exist_key, floatArrayOf(0f, -1f))

        val doubleRequired = fragment.readDoubleArgOr(R.string.double_required, 0.0)
        val doubleRequiredErrKey = fragment.readDoubleArgOr(R.string.not_exist_key, 0.0)
        val doubleArrayRequired = fragment.readDoubleArrayArgOrThrow(R.string.double_array_required)
        val doubleArrayOptional = fragment.readDoubleArrayArgOrNull(R.string.double_array_optional)
        val doubleArrayOptionalErrKey = fragment.readDoubleArrayArgOrNull(R.string.not_exist_key)
        val doubleArrayOrDefault = fragment.readDoubleArrayArgOr(R.string.double_array_or_default, doubleArrayOf(0.0, 0.0))
        val doubleArrayOrDefaultErrKey = fragment.readDoubleArrayArgOr(R.string.not_exist_key, doubleArrayOf(0.0, -1.0))

        val booleanRequired = fragment.readBooleanArgOr(R.string.boolean_required, false)
        val booleanRequiredErrKey = fragment.readBooleanArgOr(R.string.not_exist_key, false)
        val booleanArrayRequired = fragment.readBooleanArrayArgOrThrow(R.string.boolean_array_required)
        val booleanArrayOptional = fragment.readBooleanArrayArgOrNull(R.string.boolean_array_optional)
        val booleanArrayOptionalErrKey = fragment.readBooleanArrayArgOrNull(R.string.not_exist_key)
        val booleanArrayOrDefault = fragment.readBooleanArrayArgOr(R.string.boolean_array_or_default, booleanArrayOf(true, false))
        val booleanArrayOrDefaultErrKey = fragment.readBooleanArrayArgOr(R.string.not_exist_key, booleanArrayOf(false, true))

        val charRequired = fragment.readCharArgOr(R.string.char_required, 'a')
        val charRequiredErrKey = fragment.readCharArgOr(R.string.not_exist_key, 'b')
        val charArrayRequired = fragment.readCharArrayArgOrThrow(R.string.char_array_required)
        val charArrayOptional = fragment.readCharArrayArgOrNull(R.string.char_array_optional)
        val charArrayOptionalErrKey = fragment.readCharArrayArgOrNull(R.string.not_exist_key)
        val charArrayOrDefault = fragment.readCharArrayArgOr(R.string.char_array_or_default, charArrayOf('a', 'b'))
        val charArrayOrDefaultErrKey = fragment.readCharArrayArgOr(R.string.not_exist_key, charArrayOf('b', 'a'))

        val stringRequired = fragment.readStringArgOr(R.string.string_required, "stringRequired")
        val stringRequiredErrKey = fragment.readStringArgOr(R.string.not_exist_key, "stringRequiredErrKey")
        val stringArrayRequired = fragment.readStringArrayArgOrThrow(R.string.string_array_required)
        val stringArrayOptional = fragment.readStringArrayArgOrNull(R.string.string_array_optional)
        val stringArrayOptionalErrKey = fragment.readStringArrayArgOrNull(R.string.not_exist_key)
        val stringArrayOrDefault = fragment.readStringArrayArgOr(R.string.string_array_or_default, arrayOf("array", "dft"))
        val stringArrayOrDefaultErrKey = fragment.readStringArrayArgOr(R.string.not_exist_key, arrayOf("error", "erk"))
        val stringArrayListRequired = fragment.readStringArrayListArgOrThrow(R.string.string_array_list_required)
        val stringArrayListOrDefault = fragment.readStringArrayListArgOr(R.string.string_array_list_or_default, arrayListOf("list", "default"))
        val stringArrayListOrDefaultErrKey = fragment.readStringArrayListArgOr(R.string.not_exist_key, arrayListOf("stringArrayListOrDefaultErrKey", "errKey"))
        val stringArrayListOptional = fragment.readStringArrayListArgOrNull(R.string.string_array_list_optional)
        val stringArrayListOptionalErrKey = fragment.readStringArrayListArgOrNull(R.string.not_exist_key)

        val charSequenceRequired = fragment.readCharSequenceArgOr(R.string.char_sequence_required, "charSequenceRequired")
        val charSequenceRequiredErrKey = fragment.readCharSequenceArgOr(R.string.not_exist_key, "charSequenceRequiredErrKey")
        val charSequenceArrayRequired = fragment.readCharSequenceArrayArgOrThrow(R.string.char_sequence_array_required)
        val charSequenceArrayOptional = fragment.readCharSequenceArrayArgOrNull(R.string.char_sequence_array_optional)
        val charSequenceArrayOptionalErrKey = fragment.readCharSequenceArrayArgOrNull(R.string.not_exist_key)
        val charSequenceArrayOrDefault = fragment.readCharSequenceArrayArgOr(R.string.char_sequence_array_or_default, arrayOf<CharSequence>("array", "dft"))
        val charSequenceArrayOrDefaultErrKey = fragment.readCharSequenceArrayArgOr(R.string.not_exist_key, arrayOf<CharSequence>("error", "erk"))
        val charSequenceArrayListRequired = fragment.readCharSequenceArrayListArgOrThrow(R.string.char_sequence_array_list_required)
        val charSequenceArrayListOrDefault = fragment.readCharSequenceArrayListArgOr(R.string.char_sequence_array_list_or_default, arrayListOf<CharSequence>("list", "default"))
        val charSequenceArrayListOrDefaultErrKey = fragment.readCharSequenceArrayListArgOr(R.string.not_exist_key, arrayListOf<CharSequence>("charSequenceArrayListOrDefaultErrKey", "errKey"))
        val charSequenceArrayListOptional = fragment.readCharSequenceArrayListArgOrNull(R.string.char_sequence_array_list_optional)
        val charSequenceArrayListOptionalErrKey = fragment.readCharSequenceArrayListArgOrNull(R.string.not_exist_key)


        val parcelableRequired = fragment.readParcelableArgOr(R.string.parcelable_required, TestParcelable("required"))
        val parcelableRequiredErrKey = fragment.readParcelableArgOr(R.string.not_exist_key, TestParcelable("parcelableRequiredErrKey"))
        val parcelableArrayRequired = fragment.readParcelableArrayArgOrThrow<Parcelable>(R.string.parcelable_array_required)
        val parcelableArrayOptional = fragment.readParcelableArrayArgOrNull<Parcelable>(R.string.parcelable_array_optional)
        val parcelableArrayOptionalErrKey = fragment.readParcelableArrayArgOrNull<Parcelable>(R.string.not_exist_key)
        val parcelableArrayOrDefault = fragment.readParcelableArrayArgOr(R.string.parcelable_array_or_default, arrayOf())
        val parcelableArrayOrDefaultErrKey = fragment.readParcelableArrayArgOr(R.string.not_exist_key, arrayOf(TestParcelable("error"), TestParcelable("erk")))
        val parcelableArrayListRequired = fragment.readParcelableArrayListArgOrThrow<Parcelable>(R.string.parcelable_array_list_required)
        val parcelableArrayListOrDefault = fragment.readParcelableArrayListArgOr(R.string.parcelable_array_list_or_default, arrayListOf<Parcelable>(TestParcelable("list"), TestParcelable("default")))
        val parcelableArrayListOrDefaultErrKey = fragment.readParcelableArrayListArgOr(R.string.not_exist_key, arrayListOf<Parcelable>(TestParcelable("parcelableArrayListOrDefaultErrKey"), TestParcelable("errKey")))
        val parcelableArrayListOptional = fragment.readParcelableArrayListArgOrNull<Parcelable>(R.string.parcelable_array_list_optional)
        val parcelableArrayListOptionalErrKey = fragment.readParcelableArrayListArgOrNull<Parcelable>(R.string.not_exist_key)

        val serializableRequired = fragment.readSerializableArgOrThrow<TestSerializable>(R.string.serializable_required)
        val serializableOptional = fragment.readSerializableArgOrNull<TestSerializable>(R.string.serializable_optional)
        val serializableOptionalErrKey = fragment.readSerializableArgOrNull<TestSerializable>(R.string.not_exist_key)
        val serializableOrDefault = fragment.readSerializableArgOr(R.string.serializable_or_default, TestSerializable("default"))
        val serializableOrDefaultErrKey = fragment.readSerializableArgOr(R.string.not_exist_key, TestSerializable("errKey"))

        val bundleRequired = fragment.readBundleArgOrThrow(R.string.bundle_required)
        val bundleOptional = fragment.readBundleArgOrNull(R.string.bundle_optional)
        val bundleOptionalErrKey = fragment.readBundleArgOrNull(R.string.not_exist_key)
        val bundleOrDefault = fragment.readBundleArgOr(R.string.bundle_or_default, Bundle())
        val defaultBundle = Bundle()
        defaultBundle.putString("bundle", "bundleErrKey")
        val bundleOrDefaultErrKey = fragment.readBundleArgOr(R.string.not_exist_key, defaultBundle)


        val sparseArrayDefault = SparseArray<Parcelable>()
        sparseArrayDefault.put(0, TestParcelable("0"))
        val sparseParcelableArrayRequired = fragment.readSparseParcelableArrayArgOrThrow<Parcelable>(R.string.sparse_parcelable_array_required)
        val sparseParcelableArrayOptional = fragment.readSparseParcelableArrayArgOrNull<Parcelable>(R.string.sparse_parcelable_array_optional)
        val sparseParcelableArrayOptionalErrKey = fragment.readSparseParcelableArrayArgOrNull<Parcelable>(R.string.not_exist_key)
        val sparseParcelableArrayOrDefault = fragment.readSparseParcelableArrayArgOr(R.string.sparse_parcelable_array_or_default, sparseArrayDefault)
        val sparseParcelableArrayOrDefaultErrKey = fragment.readSparseParcelableArrayArgOr(R.string.not_exist_key, sparseArrayDefault)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            val binderRequired = fragment.readBinderArgOrThrow(R.string.binder_required)
            val binderOptional = fragment.readBinderArgOrNull(R.string.binder_optional)
            val binderOptionalErrKey = fragment.readBinderArgOrNull(R.string.not_exist_key)
            val binderOrDefault = fragment.readBinderArgOr(R.string.binder_or_default, TestBinder(""))
            val binderOrDefaultErrKey = fragment.readBinderArgOr(R.string.not_exist_key, TestBinder("binderOrDefaultErrKey"))

            Assert.assertEquals(binderRequired, TestBinder("binderRequired"))
            Assert.assertEquals(binderOptional, TestBinder("binderOptional"))
            Assert.assertNull(binderOptionalErrKey)
            Assert.assertEquals(binderOrDefault, TestBinder("binderOrDefault"))
            Assert.assertEquals(binderOrDefaultErrKey, TestBinder("binderOrDefaultErrKey"))
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val sizeRequired = fragment.readSizeArgOrThrow(R.string.size_required)
            val sizeOptional = fragment.readSizeArgOrNull(R.string.size_optional)
            val sizeOptionalErrKey = fragment.readSizeArgOrNull(R.string.not_exist_key)
            val sizeOrDefault = fragment.readSizeArgOr(R.string.size_or_default, Size(0, 0))
            val sizeOrDefaultErrKey = fragment.readSizeArgOr(R.string.not_exist_key, Size(4, 4))

            val sizeFRequired = fragment.readSizeFArgOrThrow(R.string.sizeF_required)
            val sizeFOptional = fragment.readSizeFArgOrNull(R.string.sizeF_optional)
            val sizeFOptionalErrKey = fragment.readSizeFArgOrNull(R.string.not_exist_key)
            val sizeFOrDefault = fragment.readSizeFArgOr(R.string.sizeF_or_default, SizeF(0f, 0f))
            val sizeFOrDefaultErrKey = fragment.readSizeFArgOr(R.string.not_exist_key, SizeF(4f, 4f))

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