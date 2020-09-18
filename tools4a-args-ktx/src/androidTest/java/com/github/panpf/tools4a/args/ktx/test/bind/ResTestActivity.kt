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

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import com.github.panpf.tools4a.args.ktx.*
import com.github.panpf.tools4a.args.ktx.test.R
import org.junit.Assert

class ResTestActivity : androidx.fragment.app.FragmentActivity() {

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, ResTestActivity::class.java).apply {
                putExtra(context.getString(R.string.string_required), "stringRequired")
                putExtra(context.getString(R.string.string_optional), "stringOptional")
                putExtra(context.getString(R.string.string_array_required), arrayOf("stringRequired", "stringOptional"))
                putExtra(context.getString(R.string.string_array_optional), arrayOf("stringOptional", "stringRequired"))
                putStringArrayListExtra(context.getString(R.string.string_array_list_required), arrayListOf("stringRequired", "stringOptional"))
                putStringArrayListExtra(context.getString(R.string.string_array_list_optional), arrayListOf("stringOptional", "stringRequired"))

                putExtra(context.getString(R.string.byte_required), 2.toByte())
                putExtra(context.getString(R.string.byte_array_required), byteArrayOf(2.toByte(), (-2).toByte()))
                putExtra(context.getString(R.string.byte_array_optional), byteArrayOf((-2).toByte(), 2.toByte()))

                putExtra(context.getString(R.string.short_required), 3.toShort())
                putExtra(context.getString(R.string.short_array_required), shortArrayOf(3.toShort(), (-3).toShort()))
                putExtra(context.getString(R.string.short_array_optional), shortArrayOf((-3).toShort(), 3.toShort()))

                putExtra(context.getString(R.string.int_required), 500)
                putExtra(context.getString(R.string.int_array_required), intArrayOf(500, -500))
                putExtra(context.getString(R.string.int_array_optional), intArrayOf(-500, 500))
                putIntegerArrayListExtra(context.getString(R.string.int_array_list_required), arrayListOf(500, -500))
                putIntegerArrayListExtra(context.getString(R.string.int_array_list_optional), arrayListOf(-500, 500))

                putExtra(context.getString(R.string.long_required), 1000L)
                putExtra(context.getString(R.string.long_array_required), longArrayOf(1000L, -1000L))
                putExtra(context.getString(R.string.long_array_optional), longArrayOf(-1000L, 1000L))

                putExtra(context.getString(R.string.float_required), 4f)
                putExtra(context.getString(R.string.float_array_required), floatArrayOf(4f, -4f))
                putExtra(context.getString(R.string.float_array_optional), floatArrayOf(-4f, 4f))

                putExtra(context.getString(R.string.double_required), 6.toDouble())
                putExtra(context.getString(R.string.double_array_required), doubleArrayOf(6.toDouble(), (-6).toDouble()))
                putExtra(context.getString(R.string.double_array_optional), doubleArrayOf((-6).toDouble(), 6.toDouble()))

                putExtra(context.getString(R.string.boolean_required), true)
                putExtra(context.getString(R.string.boolean_array_required), booleanArrayOf(true, false))
                putExtra(context.getString(R.string.boolean_array_optional), booleanArrayOf(false, true))

                putExtra(context.getString(R.string.char_required), 'a')
                putExtra(context.getString(R.string.char_array_required), charArrayOf('a', 'b'))
                putExtra(context.getString(R.string.char_array_optional), charArrayOf('b', 'a'))


                putExtra(context.getString(R.string.char_sequence_required), "stringRequired")
                putExtra(context.getString(R.string.char_sequence_optional), "stringOptional")
                putExtra(context.getString(R.string.char_sequence_array_required), arrayOf("stringRequired", "stringOptional"))
                putExtra(context.getString(R.string.char_sequence_array_optional), arrayOf("stringOptional", "stringRequired"))

                putExtra(context.getString(R.string.parcelable_required), TestParcelable("parcelableRequired"))
                putExtra(context.getString(R.string.parcelable_optional), TestParcelable("parcelableOptional"))
                putExtra(context.getString(R.string.parcelable_array_required), arrayOf(TestParcelable("parcelableRequired"), TestParcelable("parcelableOptional")))
                putExtra(context.getString(R.string.parcelable_array_optional), arrayOf(TestParcelable("parcelableOptional"), TestParcelable("parcelableRequired")))
                putParcelableArrayListExtra(context.getString(R.string.parcelable_array_list_required), arrayListOf(TestParcelable("parcelableRequired"), TestParcelable("parcelableOptional")))
                putParcelableArrayListExtra(context.getString(R.string.parcelable_array_list_optional), arrayListOf(TestParcelable("parcelableOptional"), TestParcelable("parcelableRequired")))

                putExtra(context.getString(R.string.serializable_required), TestSerializable("serializableRequired"))
                putExtra(context.getString(R.string.serializable_optional), TestSerializable("serializableOptional"))

                putExtra(context.getString(R.string.bundle_required), Bundle().apply { putString("bundle", "bundleRequired") })
                putExtra(context.getString(R.string.bundle_optional), Bundle().apply { putString("bundle", "bundleOptional") })

                putExtra(context.getString(R.string.byte_array_or_default), byteArrayOf(2.toByte(), (-2).toByte()))
                putExtra(context.getString(R.string.short_array_or_default), shortArrayOf(3.toShort(), (-3).toShort()))
                putExtra(context.getString(R.string.int_array_or_default), intArrayOf(500, -500))
                putIntegerArrayListExtra(context.getString(R.string.int_array_list_or_default), arrayListOf(600, -600))
                putExtra(context.getString(R.string.long_array_or_default), longArrayOf(1000L, -1000L))
                putExtra(context.getString(R.string.float_array_or_default), floatArrayOf(4f, -4f))
                putExtra(context.getString(R.string.double_array_or_default), doubleArrayOf(6.toDouble(), (-6).toDouble()))
                putExtra(context.getString(R.string.boolean_array_or_default), booleanArrayOf(true, false))
                putExtra(context.getString(R.string.char_array_or_default), charArrayOf('a', 'b'))
                putExtra(context.getString(R.string.char_sequence_or_default), "charSequenceOrDefault" as CharSequence)
                putExtra(context.getString(R.string.char_sequence_array_or_default), arrayOf<CharSequence>("charSequence", "default"))

                putCharSequenceArrayListExtra(context.getString(R.string.char_sequence_array_list_required), arrayListOf("charSequenceArrayListRequired", "required"))
                putCharSequenceArrayListExtra(context.getString(R.string.char_sequence_array_list_optional), arrayListOf("charSequenceArrayListOptional", "optional"))
                putCharSequenceArrayListExtra(context.getString(R.string.char_sequence_array_list_or_default), arrayListOf("charSequenceArrayListOrDefault", "default"))

                putExtra(context.getString(R.string.string_or_default), "stringOrDefault")
                putExtra(context.getString(R.string.string_array_or_default), arrayOf("stringArrayOrDefault", "default"))
                putExtra(context.getString(R.string.string_array_list_or_default), arrayListOf("stringArrayListOrDefault", "default"))

                putExtra(context.getString(R.string.parcelable_or_default), TestParcelable("parcelableOrDefault"))
                putExtra(context.getString(R.string.parcelable_array_or_default), arrayOf(TestParcelable("parcelableArrayOrDefault"), TestParcelable("default")))
                putParcelableArrayListExtra(context.getString(R.string.parcelable_array_list_or_default), arrayListOf(TestParcelable("parcelableArrayListOrDefault"), TestParcelable("default")))

                putExtra(context.getString(R.string.serializable_or_default), TestSerializable("serializableOrDefault"))

                putExtra(context.getString(R.string.bundle_or_default), Bundle().apply { putString("bundle", "bundleOrDefault") })

            }
        }
    }

    private val stringRequired by bindStringArgOrThrow(R.string.string_required)
    private val stringOptional by bindStringArgOrNull(R.string.string_optional)
    private val stringArrayRequired by bindStringArrayArgOrThrow(R.string.string_array_required)
    private val stringArrayOptional by bindStringArrayArgOrNull(R.string.string_array_optional)
    private val stringArrayListRequired by bindStringArrayListArgOrThrow(R.string.string_array_list_required)
    private val stringArrayListOptional by bindStringArrayListArgOrNull(R.string.string_array_list_optional)

    private val byteRequired by bindByteArgOr(R.string.byte_required)
    private val byteArrayRequired by bindByteArrayArgOrThrow(R.string.byte_array_required)
    private val byteArrayOptional by bindByteArrayArgOrNull(R.string.byte_array_optional)

    private val shortRequired by bindShortArgOr(R.string.short_required)
    private val shortArrayRequired by bindShortArrayArgOrThrow(R.string.short_array_required)
    private val shortArrayOptional by bindShortArrayArgOrNull(R.string.short_array_optional)

    private val intRequired by bindIntArgOr(R.string.int_required)
    private val intArrayRequired by bindIntArrayArgOrThrow(R.string.int_array_required)
    private val intArrayOptional by bindIntArrayArgOrNull(R.string.int_array_optional)
    private val intArrayListRequired by bindIntArrayListArgOrThrow(R.string.int_array_list_required)
    private val intArrayListOptional by bindIntArrayListArgOrNull(R.string.int_array_list_optional)

    private val longRequired by bindLongArgOr(R.string.long_required)
    private val longArrayRequired by bindLongArrayArgOrThrow(R.string.long_array_required)
    private val longArrayOptional by bindLongArrayArgOrNull(R.string.long_array_optional)

    private val floatRequired by bindFloatArgOr(R.string.float_required)
    private val floatArrayRequired by bindFloatArrayArgOrThrow(R.string.float_array_required)
    private val floatArrayOptional by bindFloatArrayArgOrNull(R.string.float_array_optional)

    private val doubleRequired by bindDoubleArgOr(R.string.double_required)
    private val doubleArrayRequired by bindDoubleArrayArgOrThrow(R.string.double_array_required)
    private val doubleArrayOptional by bindDoubleArrayArgOrNull(R.string.double_array_optional)

    private val booleanRequired by bindBooleanArgOr(R.string.boolean_required)
    private val booleanArrayRequired by bindBooleanArrayArgOrThrow(R.string.boolean_array_required)
    private val booleanArrayOptional by bindBooleanArrayArgOrNull(R.string.boolean_array_optional)

    private val charRequired by bindCharArgOr(R.string.char_required)
    private val charArrayRequired by bindCharArrayArgOrThrow(R.string.char_array_required)
    private val charArrayOptional by bindCharArrayArgOrNull(R.string.char_array_optional)

    private val charSequenceRequired by bindCharSequenceArgOrThrow(R.string.char_sequence_required)
    private val charSequenceOptional by bindCharSequenceArgOrNull(R.string.char_sequence_optional)
    private val charSequenceArrayRequired by bindCharSequenceArrayArgOrThrow(R.string.char_sequence_array_required)
    private val charSequenceArrayOptional by bindCharSequenceArrayArgOrNull(R.string.char_sequence_array_optional)

    private val parcelableRequired by bindParcelableArgOrThrow<TestParcelable>(R.string.parcelable_required)
    private val parcelableOptional by bindParcelableArgOrNull<TestParcelable>(R.string.parcelable_optional)
    private val parcelableArrayRequired by bindParcelableArrayArgOrThrow<Parcelable>(R.string.parcelable_array_required)
    private val parcelableArrayOptional by bindParcelableArrayArgOrNull<Parcelable>(R.string.parcelable_array_optional)
    private val parcelableArrayListRequired by bindParcelableArrayListArgOrThrow<TestParcelable>(R.string.parcelable_array_list_required)
    private val parcelableArrayListOptional by bindParcelableArrayListArgOrNull<TestParcelable>(R.string.parcelable_array_list_optional)

    private val serializableRequired by bindSerializableArgOrThrow<TestSerializable>(R.string.serializable_required)
    private val serializableOptional by bindSerializableArgOrNull<TestSerializable>(R.string.serializable_optional)
    private val bundleRequired by bindBundleArgOrThrow(R.string.bundle_required)
    private val bundleOptional by bindBundleArgOrNull(R.string.bundle_optional)


    private val byteArrayOrDefault by bindByteArrayArgOr(R.string.byte_array_or_default, byteArrayOf())

    private val shortArrayOrDefault by bindShortArrayArgOr(R.string.short_array_or_default, shortArrayOf())

    private val intArrayOrDefault by bindIntArrayArgOr(R.string.int_array_or_default, intArrayOf())

    private val intArrayListOrDefault by bindIntArrayListArgOr(R.string.int_array_list_or_default, arrayListOf())

    private val longArrayOrDefault by bindLongArrayArgOr(R.string.long_array_or_default, longArrayOf())

    private val floatArrayOrDefault by bindFloatArrayArgOr(R.string.float_array_or_default, floatArrayOf())

    private val doubleArrayOrDefault by bindDoubleArrayArgOr(R.string.double_array_or_default, doubleArrayOf())

    private val booleanArrayOrDefault by bindBooleanArrayArgOr(R.string.boolean_array_or_default, booleanArrayOf())

    private val charArrayOrDefault by bindCharArrayArgOr(R.string.char_array_or_default, charArrayOf())

    private val charSequenceOrDefault by bindCharSequenceArgOr(R.string.char_sequence_or_default, "default")

    private val charSequenceArrayOrDefault by bindCharSequenceArrayArgOr(R.string.char_sequence_array_or_default, arrayOf())

    private val charSequenceArrayListRequired by bindCharSequenceArrayListArgOrThrow(R.string.char_sequence_array_list_required)
    private val charSequenceArrayListOptional by bindCharSequenceArrayListArgOrNull(R.string.char_sequence_array_list_optional)
    private val charSequenceArrayListOrDefault by bindCharSequenceArrayListArgOr(R.string.char_sequence_array_list_or_default, arrayListOf())

    private val stringOrDefault by bindStringArgOr(R.string.string_or_default, "")

    private val stringArrayOrDefault by bindStringArrayArgOr(R.string.string_array_or_default, arrayOf())

    private val stringArrayListOrDefault by bindStringArrayListArgOr(R.string.string_array_list_or_default, arrayListOf())

    private val parcelableOrDefault by bindParcelableArgOr(R.string.parcelable_or_default, TestParcelable("default"))

    private val parcelableArrayOrDefault by bindParcelableArrayArgOr(R.string.parcelable_array_or_default, arrayOf())

    private val parcelableArrayListOrDefault by bindParcelableArrayListArgOr(R.string.parcelable_array_list_or_default, arrayListOf())

    private val serializableOrDefault by bindSerializableArgOr(R.string.serializable_or_default, TestSerializable("default"))

    private val bundleOrDefault by bindBundleArgOr(R.string.bundle_or_default, Bundle())

    private val stringOptionalErrKey by bindStringArgOrNull(R.string.not_exist_key)
    private val stringArrayOptionalErrKey by bindStringArrayArgOrNull(R.string.not_exist_key)
    private val stringArrayListOptionalErrKey by bindStringArrayListArgOrNull(R.string.not_exist_key)
    private val booleanArrayOptionalErrKey by bindBooleanArrayArgOrNull(R.string.not_exist_key)
    private val charArrayOptionalErrKEy by bindCharArrayArgOrNull(R.string.not_exist_key)
    private val charSequenceOptionalErrKet by bindCharSequenceArgOrNull(R.string.not_exist_key)
    private val charSequenceArrayOptionalErrKey by bindCharSequenceArrayArgOrNull(R.string.not_exist_key)
    private val parcelableOptionalErrKey by bindParcelableArgOrNull<TestParcelable>(R.string.not_exist_key)
    private val parcelableArrayOptionalErrKey by bindParcelableArrayArgOrNull<Parcelable>(R.string.not_exist_key)
    private val parcelableArrayListOptionalErrKey by bindParcelableArrayListArgOrNull<TestParcelable>(R.string.not_exist_key)
    private val serializableOptionalErrKey by bindSerializableArgOrNull<TestSerializable>(R.string.not_exist_key)
    private val bundleOptionalErrKey by bindBundleArgOrNull(R.string.not_exist_key)
    private val charSequenceArrayListOptionalErrKey by bindCharSequenceArrayListArgOrNull(R.string.not_exist_key)
    private val byteArrayOptionalErrKey by bindByteArrayArgOrNull(R.string.not_exist_key)
    private val shortArrayOptionalErrKey by bindShortArrayArgOrNull(R.string.not_exist_key)
    private val intArrayOptionalErrKey by bindIntArrayArgOrNull(R.string.not_exist_key)
    private val intArrayListOptionalErrKey by bindIntArrayListArgOrNull(R.string.not_exist_key)
    private val longArrayOptionalErrKey by bindLongArrayArgOrNull(R.string.not_exist_key)
    private val floatArrayOptionalErrKey by bindFloatArrayArgOrNull(R.string.not_exist_key)
    private val doubleArrayOptionalErrKey by bindDoubleArrayArgOrNull(R.string.not_exist_key)

    fun checkParams() {
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

        Assert.assertTrue(booleanRequired)
        Assert.assertTrue(booleanArrayRequired[0] && !booleanArrayRequired[1])
        Assert.assertTrue(booleanArrayOptional?.get(0) != true
                && booleanArrayOptional?.get(1) ?: false)
        Assert.assertNull(booleanArrayOptionalErrKey)

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
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.at_test)

        val supportFragment = ResTestSupportFragment()
        supportFragment.arguments = ResTestSupportFragment.createArguments(this)
        supportFragmentManager.beginTransaction().replace(R.id.testAt_frame, supportFragment).commit()
    }

    val fragment: ResTestSupportFragment
        get() = supportFragmentManager.findFragmentById(R.id.testAt_frame) as ResTestSupportFragment
}