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

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import android.util.Size
import android.util.SizeF
import android.util.SparseArray
import com.github.panpf.tools4a.args.ktx.*
import com.github.panpf.tools4a.args.ktx.test.R
import org.junit.Assert

class ResTestActivity : androidx.fragment.app.FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.at_test)
        val supportFragment = ResTestSupportFragment.newInstance(intent.extras)
        supportFragmentManager.beginTransaction().replace(R.id.testAt_frame, supportFragment).commit()
    }

    val fragment: ResTestSupportFragment
        get() = (supportFragmentManager.findFragmentById(R.id.testAt_frame) as ResTestSupportFragment?)!!

    fun checkParams() {
        val activity = this

        val byteRequired = activity.readByteArgOr(R.string.byte_required, 0.toByte())
        val byteRequiredErrKey = activity.readByteArgOr(R.string.not_exist_key, 0.toByte())
        val byteArrayRequired = activity.readByteArrayArgOrThrow(R.string.byte_array_required)
        val byteArrayOptional = activity.readByteArrayArgOrNull(R.string.byte_array_optional)
        val byteArrayOptionalErrKey = activity.readByteArrayArgOrNull(R.string.not_exist_key)
        val byteArrayOrDefault = activity.readByteArrayArgOr(R.string.byte_array_or_default, byteArrayOf(0.toByte(), 0.toByte()))
        val byteArrayOrDefaultErrKey = activity.readByteArrayArgOr(R.string.not_exist_key, byteArrayOf(0.toByte(), (-1).toByte()))

        val shortRequired = activity.readShortArgOr(R.string.short_required, 0.toShort())
        val shortRequiredErrKey = activity.readShortArgOr(R.string.not_exist_key, 0.toShort())
        val shortArrayRequired = activity.readShortArrayArgOrThrow(R.string.short_array_required)
        val shortArrayOptional = activity.readShortArrayArgOrNull(R.string.short_array_optional)
        val shortArrayOptionalErrKey = activity.readShortArrayArgOrNull(R.string.not_exist_key)
        val shortArrayOrDefault = activity.readShortArrayArgOr(R.string.short_array_or_default, shortArrayOf(0.toShort(), 0.toShort()))
        val shortArrayOrDefaultErrKey = activity.readShortArrayArgOr(R.string.not_exist_key, shortArrayOf(0.toShort(), (-1).toShort()))


        val intRequired = activity.readIntArgOr(R.string.int_required, 0)
        val intRequiredErrKey = activity.readIntArgOr(R.string.not_exist_key, 0)
        val intArrayRequired = activity.readIntArrayArgOrThrow(R.string.int_array_required)
        val intArrayOptional = activity.readIntArrayArgOrNull(R.string.int_array_optional)
        val intArrayOptionalErrKey = activity.readIntArrayArgOrNull(R.string.not_exist_key)
        val intArrayOrDefault = activity.readIntArrayArgOr(R.string.int_array_or_default, intArrayOf(0, 0))
        val intArrayOrDefaultErrKey = activity.readIntArrayArgOr(R.string.not_exist_key, intArrayOf(0, -1))
        val intArrayListRequired = activity.readIntArrayListArgOrThrow(R.string.int_array_list_required)
        val intArrayListOrDefault = activity.readIntArrayListArgOr(R.string.int_array_list_or_default, arrayListOf(0, 0))
        val intArrayListOrDefaultErrKey = activity.readIntArrayListArgOr(R.string.not_exist_key, arrayListOf(0, 0))
        val intArrayListOptional = activity.readIntArrayListArgOrNull(R.string.int_array_list_optional)
        val intArrayListOptionalErrKey = activity.readIntArrayListArgOrNull(R.string.not_exist_key)

        val longRequired = activity.readLongArgOr(R.string.long_required, 0)
        val longRequiredErrKey = activity.readLongArgOr(R.string.not_exist_key, 0)
        val longArrayRequired = activity.readLongArrayArgOrThrow(R.string.long_array_required)
        val longArrayOptional = activity.readLongArrayArgOrNull(R.string.long_array_optional)
        val longArrayOptionalErrKey = activity.readLongArrayArgOrNull(R.string.not_exist_key)
        val longArrayOrDefault = activity.readLongArrayArgOr(R.string.long_array_or_default, longArrayOf(0, 0))
        val longArrayOrDefaultErrKey = activity.readLongArrayArgOr(R.string.not_exist_key, longArrayOf(0, -1))

        val floatRequired = activity.readFloatArgOr(R.string.float_required, 0f)
        val floatRequiredErrKey = activity.readFloatArgOr(R.string.not_exist_key, 0f)
        val floatArrayRequired = activity.readFloatArrayArgOrThrow(R.string.float_array_required)
        val floatArrayOptional = activity.readFloatArrayArgOrNull(R.string.float_array_optional)
        val floatArrayOptionalErrKey = activity.readFloatArrayArgOrNull(R.string.not_exist_key)
        val floatArrayOrDefault = activity.readFloatArrayArgOr(R.string.float_array_or_default, floatArrayOf(0f, 0f))
        val floatArrayOrDefaultErrKey = activity.readFloatArrayArgOr(R.string.not_exist_key, floatArrayOf(0f, -1f))

        val doubleRequired = activity.readDoubleArgOr(R.string.double_required, 0.0)
        val doubleRequiredErrKey = activity.readDoubleArgOr(R.string.not_exist_key, 0.0)
        val doubleArrayRequired = activity.readDoubleArrayArgOrThrow(R.string.double_array_required)
        val doubleArrayOptional = activity.readDoubleArrayArgOrNull(R.string.double_array_optional)
        val doubleArrayOptionalErrKey = activity.readDoubleArrayArgOrNull(R.string.not_exist_key)
        val doubleArrayOrDefault = activity.readDoubleArrayArgOr(R.string.double_array_or_default, doubleArrayOf(0.0, 0.0))
        val doubleArrayOrDefaultErrKey = activity.readDoubleArrayArgOr(R.string.not_exist_key, doubleArrayOf(0.0, -1.0))

        val booleanRequired = activity.readBooleanArgOr(R.string.boolean_required, false)
        val booleanRequiredErrKey = activity.readBooleanArgOr(R.string.not_exist_key, false)
        val booleanArrayRequired = activity.readBooleanArrayArgOrThrow(R.string.boolean_array_required)
        val booleanArrayOptional = activity.readBooleanArrayArgOrNull(R.string.boolean_array_optional)
        val booleanArrayOptionalErrKey = activity.readBooleanArrayArgOrNull(R.string.not_exist_key)
        val booleanArrayOrDefault = activity.readBooleanArrayArgOr(R.string.boolean_array_or_default, booleanArrayOf(true, false))
        val booleanArrayOrDefaultErrKey = activity.readBooleanArrayArgOr(R.string.not_exist_key, booleanArrayOf(false, true))

        val charRequired = activity.readCharArgOr(R.string.char_required, 'a')
        val charRequiredErrKey = activity.readCharArgOr(R.string.not_exist_key, 'b')
        val charArrayRequired = activity.readCharArrayArgOrThrow(R.string.char_array_required)
        val charArrayOptional = activity.readCharArrayArgOrNull(R.string.char_array_optional)
        val charArrayOptionalErrKey = activity.readCharArrayArgOrNull(R.string.not_exist_key)
        val charArrayOrDefault = activity.readCharArrayArgOr(R.string.char_array_or_default, charArrayOf('a', 'b'))
        val charArrayOrDefaultErrKey = activity.readCharArrayArgOr(R.string.not_exist_key, charArrayOf('b', 'a'))

        val stringRequired = activity.readStringArgOr(R.string.string_required, "stringRequired")
        val stringRequiredErrKey = activity.readStringArgOr(R.string.not_exist_key, "stringRequiredErrKey")
        val stringArrayRequired = activity.readStringArrayArgOrThrow(R.string.string_array_required)
        val stringArrayOptional = activity.readStringArrayArgOrNull(R.string.string_array_optional)
        val stringArrayOptionalErrKey = activity.readStringArrayArgOrNull(R.string.not_exist_key)
        val stringArrayOrDefault = activity.readStringArrayArgOr(R.string.string_array_or_default, arrayOf("array", "dft"))
        val stringArrayOrDefaultErrKey = activity.readStringArrayArgOr(R.string.not_exist_key, arrayOf("error", "erk"))
        val stringArrayListRequired = activity.readStringArrayListArgOrThrow(R.string.string_array_list_required)
        val stringArrayListOrDefault = activity.readStringArrayListArgOr(R.string.string_array_list_or_default, arrayListOf("list", "default"))
        val stringArrayListOrDefaultErrKey = activity.readStringArrayListArgOr(R.string.not_exist_key, arrayListOf("stringArrayListOrDefaultErrKey", "errKey"))
        val stringArrayListOptional = activity.readStringArrayListArgOrNull(R.string.string_array_list_optional)
        val stringArrayListOptionalErrKey = activity.readStringArrayListArgOrNull(R.string.not_exist_key)

        val charSequenceRequired = activity.readCharSequenceArgOr(R.string.char_sequence_required, "charSequenceRequired")
        val charSequenceRequiredErrKey = activity.readCharSequenceArgOr(R.string.not_exist_key, "charSequenceRequiredErrKey")
        val charSequenceArrayRequired = activity.readCharSequenceArrayArgOrThrow(R.string.char_sequence_array_required)
        val charSequenceArrayOptional = activity.readCharSequenceArrayArgOrNull(R.string.char_sequence_array_optional)
        val charSequenceArrayOptionalErrKey = activity.readCharSequenceArrayArgOrNull(R.string.not_exist_key)
        val charSequenceArrayOrDefault = activity.readCharSequenceArrayArgOr(R.string.char_sequence_array_or_default, arrayOf("array", "dft"))
        val charSequenceArrayOrDefaultErrKey = activity.readCharSequenceArrayArgOr(R.string.not_exist_key, arrayOf("error", "erk"))
        val charSequenceArrayListRequired = activity.readCharSequenceArrayListArgOrThrow(R.string.char_sequence_array_list_required)
        val charSequenceArrayListOrDefault = activity.readCharSequenceArrayListArgOr(R.string.char_sequence_array_list_or_default, arrayListOf("list", "default"))
        val charSequenceArrayListOrDefaultErrKey = activity.readCharSequenceArrayListArgOr(R.string.not_exist_key, arrayListOf("charSequenceArrayListOrDefaultErrKey", "errKey"))
        val charSequenceArrayListOptional = activity.readCharSequenceArrayListArgOrNull(R.string.char_sequence_array_list_optional)
        val charSequenceArrayListOptionalErrKey = activity.readCharSequenceArrayListArgOrNull(R.string.not_exist_key)


        val parcelableRequired = activity.readParcelableArgOr(R.string.parcelable_required, TestParcelable("required"))
        val parcelableRequiredErrKey = activity.readParcelableArgOr(R.string.not_exist_key, TestParcelable("parcelableRequiredErrKey"))
        val parcelableArrayRequired = activity.readParcelableArrayArgOrThrow<Parcelable>(R.string.parcelable_array_required)
        val parcelableArrayOptional = activity.readParcelableArrayArgOrNull<Parcelable>(R.string.parcelable_array_optional)
        val parcelableArrayOptionalErrKey = activity.readParcelableArrayArgOrNull<Parcelable>(R.string.not_exist_key)
        val parcelableArrayOrDefault = activity.readParcelableArrayArgOr(R.string.parcelable_array_or_default, arrayOf())
        val parcelableArrayOrDefaultErrKey = activity.readParcelableArrayArgOr(R.string.not_exist_key, arrayOf(TestParcelable("error"), TestParcelable("erk")))
        val parcelableArrayListRequired = activity.readParcelableArrayListArgOrThrow<Parcelable>(R.string.parcelable_array_list_required)
        val parcelableArrayListOrDefault = activity.readParcelableArrayListArgOr(R.string.parcelable_array_list_or_default, arrayListOf<Parcelable>(TestParcelable("list"), TestParcelable("default")))
        val parcelableArrayListOrDefaultErrKey = activity.readParcelableArrayListArgOr(R.string.not_exist_key, arrayListOf<Parcelable>(TestParcelable("parcelableArrayListOrDefaultErrKey"), TestParcelable("errKey")))
        val parcelableArrayListOptional = activity.readParcelableArrayListArgOrNull<Parcelable>(R.string.parcelable_array_list_optional)
        val parcelableArrayListOptionalErrKey = activity.readParcelableArrayListArgOrNull<Parcelable>(R.string.not_exist_key)

        val serializableRequired = activity.readSerializableArgOrThrow<TestSerializable>(R.string.serializable_required)
        val serializableOptional = activity.readSerializableArgOrNull<TestSerializable>(R.string.serializable_optional)
        val serializableOptionalErrKey = activity.readSerializableArgOrNull<TestSerializable>(R.string.not_exist_key)
        val serializableOrDefault = activity.readSerializableArgOr(R.string.serializable_or_default, TestSerializable("default"))
        val serializableOrDefaultErrKey = activity.readSerializableArgOr(R.string.not_exist_key, TestSerializable("errKey"))

        val bundleRequired = activity.readBundleArgOrThrow(R.string.bundle_required)
        val bundleOptional = activity.readBundleArgOrNull(R.string.bundle_optional)
        val bundleOptionalErrKey = activity.readBundleArgOrNull(R.string.not_exist_key)
        val bundleOrDefault = activity.readBundleArgOr(R.string.bundle_or_default, Bundle())
        val defaultBundle = Bundle()
        defaultBundle.putString("bundle", "bundleErrKey")
        val bundleOrDefaultErrKey = activity.readBundleArgOr(R.string.not_exist_key, defaultBundle)

        val extrasRequired = activity.readExtrasArgOrThrow()
        val extrasOptional = activity.readExtrasArgOrNull()
        val extrasDefault = activity.readExtrasArgOr(Bundle())

        Assert.assertTrue(extrasRequired.getString("extrasRequired") == "extrasRequired")
        Assert.assertTrue(extrasOptional!!.getString("extrasOptional") == "extrasOptional")
        Assert.assertTrue(extrasDefault.getString("extrasOrDefault") == "extrasOrDefault")

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

    companion object {
        fun createIntent(context: Context): Intent {
            val starter = Intent(context, ResTestActivity::class.java)
            starter.putExtra(context.getString(R.string.byte_required), 2.toByte())
            starter.putExtra(context.getString(R.string.byte_array_required), byteArrayOf(2.toByte(), (-2).toByte()))
            starter.putExtra(context.getString(R.string.byte_array_or_default), byteArrayOf(2.toByte(), (-2).toByte()))
            starter.putExtra(context.getString(R.string.byte_array_optional), byteArrayOf((-2).toByte(), 2.toByte()))


            starter.putExtra(context.getString(R.string.short_required), 3.toShort())
            starter.putExtra(context.getString(R.string.short_array_required), shortArrayOf(3.toShort(), (-3).toShort()))
            starter.putExtra(context.getString(R.string.short_array_optional), shortArrayOf((-3).toShort(), 3.toShort()))

            starter.putExtra(context.getString(R.string.int_required), 500)
            starter.putExtra(context.getString(R.string.int_array_required), intArrayOf(500, -500))
            starter.putExtra(context.getString(R.string.int_array_optional), intArrayOf(-500, 500))
            starter.putIntegerArrayListExtra(context.getString(R.string.int_array_list_required), arrayListOf(500, -500))
            starter.putIntegerArrayListExtra(context.getString(R.string.int_array_list_optional), arrayListOf(-500, 500))

            starter.putExtra(context.getString(R.string.long_required), 1000L)
            starter.putExtra(context.getString(R.string.long_array_required), longArrayOf(1000L, -1000L))
            starter.putExtra(context.getString(R.string.long_array_optional), longArrayOf(-1000L, 1000L))

            starter.putExtra(context.getString(R.string.float_required), 4f)
            starter.putExtra(context.getString(R.string.float_array_required), floatArrayOf(4f, -4f))
            starter.putExtra(context.getString(R.string.float_array_optional), floatArrayOf(-4f, 4f))


            starter.putExtra(context.getString(R.string.double_required), 6.0)
            starter.putExtra(context.getString(R.string.double_array_required), doubleArrayOf(6.0, -6.0))
            starter.putExtra(context.getString(R.string.double_array_optional), doubleArrayOf(-6.0, 6.0))


            starter.putExtra(context.getString(R.string.boolean_required), true)
            starter.putExtra(context.getString(R.string.boolean_array_required), booleanArrayOf(true, false))
            starter.putExtra(context.getString(R.string.boolean_array_optional), booleanArrayOf(false, true))


            starter.putExtra(context.getString(R.string.char_required), 'a')
            starter.putExtra(context.getString(R.string.char_array_required), charArrayOf('a', 'b'))
            starter.putExtra(context.getString(R.string.char_array_optional), charArrayOf('b', 'a'))

            starter.putExtra(context.getString(R.string.string_required), "stringRequired")
            starter.putExtra(context.getString(R.string.string_optional), "stringOptional")
            starter.putExtra(context.getString(R.string.string_array_required), arrayOf("stringRequired", "stringOptional"))
            starter.putExtra(context.getString(R.string.string_array_optional), arrayOf("stringOptional", "stringRequired"))
            starter.putStringArrayListExtra(context.getString(R.string.string_array_list_required), arrayListOf("stringRequired", "stringOptional"))
            starter.putStringArrayListExtra(context.getString(R.string.string_array_list_optional), arrayListOf("stringOptional", "stringRequired"))

            starter.putExtra(context.getString(R.string.char_sequence_required), "stringRequired")
            starter.putExtra(context.getString(R.string.char_sequence_optional), "stringOptional")
            starter.putExtra(context.getString(R.string.char_sequence_array_required), arrayOf("stringRequired", "stringOptional"))
            starter.putExtra(context.getString(R.string.char_sequence_array_optional), arrayOf("stringOptional", "stringRequired"))


            starter.putExtra(context.getString(R.string.parcelable_required), TestParcelable("parcelableRequired"))
            starter.putExtra(context.getString(R.string.parcelable_optional), TestParcelable("parcelableOptional"))
            starter.putExtra(context.getString(R.string.parcelable_array_required), arrayOf(TestParcelable("parcelableRequired"), TestParcelable("parcelableOptional")))
            starter.putExtra(context.getString(R.string.parcelable_array_optional), arrayOf(TestParcelable("parcelableOptional"), TestParcelable("parcelableRequired")))
            starter.putParcelableArrayListExtra(context.getString(R.string.parcelable_array_list_required), arrayListOf(TestParcelable("parcelableRequired"), TestParcelable("parcelableOptional")))
            starter.putParcelableArrayListExtra(context.getString(R.string.parcelable_array_list_optional), arrayListOf(TestParcelable("parcelableOptional"), TestParcelable("parcelableRequired")))


            starter.putExtra(context.getString(R.string.serializable_required), TestSerializable("serializableRequired"))
            starter.putExtra(context.getString(R.string.serializable_optional), TestSerializable("serializableOptional"))

            starter.putExtra(context.getString(R.string.byte_array_or_default), byteArrayOf(2.toByte(), (-2).toByte()))
            starter.putExtra(context.getString(R.string.short_array_or_default), shortArrayOf(3.toShort(), (-3).toShort()))
            starter.putExtra(context.getString(R.string.int_array_or_default), intArrayOf(500, -500))
            starter.putIntegerArrayListExtra(context.getString(R.string.int_array_list_or_default), arrayListOf(600, -600))
            starter.putExtra(context.getString(R.string.long_array_or_default), longArrayOf(1000L, -1000L))
            starter.putExtra(context.getString(R.string.float_array_or_default), floatArrayOf(4f, -4f))
            starter.putExtra(context.getString(R.string.double_array_or_default), doubleArrayOf(6.0, -6.0))
            starter.putExtra(context.getString(R.string.boolean_array_or_default), booleanArrayOf(true, false))
            starter.putExtra(context.getString(R.string.char_array_or_default), charArrayOf('a', 'b'))
            starter.putExtra(context.getString(R.string.char_sequence_or_default), "charSequenceOrDefault")
            starter.putExtra(context.getString(R.string.char_sequence_array_or_default), arrayOf("charSequence", "default"))


            starter.putCharSequenceArrayListExtra(context.getString(R.string.char_sequence_array_list_required), arrayListOf("charSequenceArrayListRequired", "required"))
            starter.putCharSequenceArrayListExtra(context.getString(R.string.char_sequence_array_list_optional), arrayListOf("charSequenceArrayListOptional", "optional"))
            starter.putCharSequenceArrayListExtra(context.getString(R.string.char_sequence_array_list_or_default), arrayListOf("charSequenceArrayListOrDefault", "default"))

            starter.putExtra(context.getString(R.string.string_or_default), "stringOrDefault")
            starter.putExtra(context.getString(R.string.string_array_or_default), arrayOf("stringArrayOrDefault", "default"))
            starter.putExtra(context.getString(R.string.string_array_list_or_default), arrayListOf("stringArrayListOrDefault", "default"))

            starter.putExtra(context.getString(R.string.parcelable_or_default), TestParcelable("parcelableOrDefault"))
            starter.putExtra(context.getString(R.string.parcelable_array_or_default), arrayOf(TestParcelable("parcelableArrayOrDefault"), TestParcelable("default")))
            starter.putParcelableArrayListExtra(context.getString(R.string.parcelable_array_list_or_default), arrayListOf(TestParcelable("parcelableArrayListOrDefault"), TestParcelable("default")))

            starter.putExtra(context.getString(R.string.serializable_or_default), TestSerializable("serializableOrDefault"))

            val bundleDefault = Bundle()
            bundleDefault.putString("bundle", "bundleOrDefault")
            starter.putExtra(context.getString(R.string.bundle_or_default), bundleDefault)

            val bundleRequired = Bundle()
            bundleRequired.putString("bundle", "bundleRequired")
            starter.putExtra(context.getString(R.string.bundle_required), bundleRequired)

            val bundleOptional = Bundle()
            bundleOptional.putString("bundle", "bundleOptional")
            starter.putExtra(context.getString(R.string.bundle_optional), bundleOptional)

            val b = Bundle()
            b.putString("extrasRequired", "extrasRequired")
            b.putString("extrasOptional", "extrasOptional")
            b.putString("extrasOrDefault", "extrasOrDefault")
            starter.putExtras(b)

            val args = Bundle()
            val sparseParcelableArrayRequired = SparseArray<Parcelable>()
            sparseParcelableArrayRequired.put(-1, TestParcelable("-1"))
            sparseParcelableArrayRequired.put(1, TestParcelable("1"))
            args.putSparseParcelableArray(context.getString(R.string.sparse_parcelable_array_required), sparseParcelableArrayRequired)

            val sparseParcelableArrayOptional = SparseArray<Parcelable>()
            sparseParcelableArrayOptional.put(-2, TestParcelable("-2"))
            sparseParcelableArrayOptional.put(2, TestParcelable("2"))
            args.putSparseParcelableArray(context.getString(R.string.sparse_parcelable_array_optional), sparseParcelableArrayOptional)

            val sparseParcelableArrayOrDefault = SparseArray<Parcelable>()
            sparseParcelableArrayOrDefault.put(-3, TestParcelable("-3"))
            sparseParcelableArrayOrDefault.put(3, TestParcelable("3"))
            args.putSparseParcelableArray(context.getString(R.string.sparse_parcelable_array_or_default), sparseParcelableArrayOrDefault)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                args.putBinder(context.getString(R.string.binder_required), TestBinder("binderRequired"))
                args.putBinder(context.getString(R.string.binder_optional), TestBinder("binderOptional"))
                args.putBinder(context.getString(R.string.binder_or_default), TestBinder("binderOrDefault"))
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                args.putSize(context.getString(R.string.size_required), Size(1, 1))
                args.putSize(context.getString(R.string.size_optional), Size(2, 2))
                args.putSize(context.getString(R.string.size_or_default), Size(3, 3))

                args.putSizeF(context.getString(R.string.sizeF_required), SizeF(1f, 1f))
                args.putSizeF(context.getString(R.string.sizeF_optional), SizeF(2f, 2f))
                args.putSizeF(context.getString(R.string.sizeF_or_default), SizeF(3f, 3f))
            }

            starter.putExtras(args)

            return starter
        }
    }
}