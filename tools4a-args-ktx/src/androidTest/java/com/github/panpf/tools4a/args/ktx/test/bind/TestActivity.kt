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

class TestActivity : androidx.fragment.app.FragmentActivity() {

    val byteRequired by bindByteArgOr("byteRequired")
    val byteArrayRequired by bindByteArrayArgOrThrow("byteArrayRequired")
    val byteArrayOptional by bindByteArrayArgOrNull("byteArrayOptional")

    val shortRequired by bindShortArgOr("shortRequired")
    val shortArrayRequired by bindShortArrayArgOrThrow("shortArrayRequired")
    val shortArrayOptional by bindShortArrayArgOrNull("shortArrayOptional")

    val intRequired by bindIntArgOr("intRequired")
    val intArrayRequired by bindIntArrayArgOrThrow("intArrayRequired")
    val intArrayOptional by bindIntArrayArgOrNull("intArrayOptional")
    val intArrayListRequired by bindIntArrayListArgOrThrow("intArrayListRequired")
    val intArrayListOptional by bindIntArrayListArgOrNull("intArrayListOptional")

    val longRequired by bindLongArgOr("longRequired")
    val longArrayRequired by bindLongArrayArgOrThrow("longArrayRequired")
    val longArrayOptional by bindLongArrayArgOrNull("longArrayOptional")

    val floatRequired by bindFloatArgOr("floatRequired")
    val floatArrayRequired by bindFloatArrayArgOrThrow("floatArrayRequired")
    val floatArrayOptional by bindFloatArrayArgOrNull("floatArrayOptional")

    val doubleRequired by bindDoubleArgOr("doubleRequired")
    val doubleArrayRequired by bindDoubleArrayArgOrThrow("doubleArrayRequired")
    val doubleArrayOptional by bindDoubleArrayArgOrNull("doubleArrayOptional")

    val booleanRequired by bindBooleanArgOr("booleanRequired")
    val booleanArrayRequired by bindBooleanArrayArgOrThrow("booleanArrayRequired")
    val booleanArrayOptional by bindBooleanArrayArgOrNull("booleanArrayOptional")

    val charRequired by bindCharArgOr("charRequired")
    val charArrayRequired by bindCharArrayArgOrThrow("charArrayRequired")
    val charArrayOptional by bindCharArrayArgOrNull("charArrayOptional")

    val charSequenceRequired by bindCharSequenceArgOrThrow("charSequenceRequired")
    val charSequenceOptional by bindCharSequenceArgOrNull("charSequenceOptional")
    val charSequenceArrayRequired by bindCharSequenceArrayArgOrThrow("charSequenceArrayRequired")
    val charSequenceArrayOptional by bindCharSequenceArrayArgOrNull("charSequenceArrayOptional")

    val stringRequired by bindStringArgOrThrow("stringRequired")
    val stringOptional by bindStringArgOrNull("stringOptional")
    val stringArrayRequired by bindStringArrayArgOrThrow("stringArrayRequired")
    val stringArrayOptional by bindStringArrayArgOrNull("stringArrayOptional")
    val stringArrayListRequired by bindStringArrayListArgOrThrow("stringArrayListRequired")
    val stringArrayListOptional by bindStringArrayListArgOrNull("stringArrayListOptional")

    val parcelableRequired by bindParcelableArgOrThrow<TestParcelable>("parcelableRequired")
    val parcelableOptional by bindParcelableArgOrNull<TestParcelable>("parcelableOptional")
    val parcelableArrayRequired by bindParcelableArrayArgOrThrow<Parcelable>("parcelableArrayRequired")
    val parcelableArrayOptional by bindParcelableArrayArgOrNull<Parcelable>("parcelableArrayOptional")
    val parcelableArrayListRequired by bindParcelableArrayListArgOrThrow<TestParcelable>("parcelableArrayListRequired")
    val parcelableArrayListOptional by bindParcelableArrayListArgOrNull<TestParcelable>("parcelableArrayListOptional")

    val serializableRequired by bindSerializableArgOrThrow<TestSerializable>("serializableRequired")
    val serializableOptional by bindSerializableArgOrNull<TestSerializable>("serializableOptional")

    val bundleRequired by bindBundleArgOrThrow("bundleRequired")
    val bundleOptional by bindBundleArgOrNull("bundleOptional")

    val byteArrayOrDefault by bindByteArrayArgOr("byteArrayOrDefault", byteArrayOf())

    val shortArrayOrDefault by bindShortArrayArgOr("shortArrayOrDefault", shortArrayOf())

    val intArrayOrDefault by bindIntArrayArgOr("intArrayOrDefault", intArrayOf())

    val intArrayListOrDefault by bindIntArrayListArgOr("intArrayListOrDefault", arrayListOf())

    val longArrayOrDefault by bindLongArrayArgOr("longArrayOrDefault", longArrayOf())

    val floatArrayOrDefault by bindFloatArrayArgOr("floatArrayOrDefault", floatArrayOf())

    val doubleArrayOrDefault by bindDoubleArrayArgOr("doubleArrayOrDefault", doubleArrayOf())

    val booleanArrayOrDefault by bindBooleanArrayArgOr("booleanArrayOrDefault", booleanArrayOf())

    val charArrayOrDefault by bindCharArrayArgOr("charArrayOrDefault", charArrayOf())

    val charSequenceOrDefault by bindCharSequenceArgOr("charSequenceOrDefault", "default")

    val charSequenceArrayOrDefault by bindCharSequenceArrayArgOr("charSequenceArrayOrDefault", arrayOf())

    val charSequenceArrayListRequired by bindCharSequenceArrayListArgOrThrow("charSequenceArrayListRequired")
    val charSequenceArrayListOptional by bindCharSequenceArrayListArgOrNull("charSequenceArrayListOptional")
    val charSequenceArrayListOrDefault by bindCharSequenceArrayListArgOr("charSequenceArrayListOrDefault", arrayListOf())

    val stringOrDefault by bindStringArgOr("stringOrDefault", "")

    val stringArrayOrDefault by bindStringArrayArgOr("stringArrayOrDefault", arrayOf())

    val stringArrayListOrDefault by bindStringArrayListArgOr("stringArrayListOrDefault", arrayListOf())

    val parcelableOrDefault by bindParcelableArgOr("parcelableOrDefault", TestParcelable("default"))

    val parcelableArrayOrDefault by bindParcelableArrayArgOr("parcelableArrayOrDefault", arrayOf())

    val parcelableArrayListOrDefault by bindParcelableArrayListArgOr("parcelableArrayListOrDefault", arrayListOf())

    val serializableOrDefault by bindSerializableArgOr("serializableOrDefault", TestSerializable("default"))

    val bundleOrDefault by bindBundleArgOr("bundleOrDefault", Bundle())

    private val extrasRequired by bindExtrasArgOrThrow()
    private val extrasOptional by bindExtrasArgOrNull()
    private val extrasOrDefault by bindExtrasArgOr(Bundle())

    private val stringOptionalErrKey by bindStringArgOrNull("keyNotExist")
    private val stringArrayOptionalErrKey by bindStringArrayArgOrNull("keyNotExist")
    private val stringArrayListOptionalErrKey by bindStringArrayListArgOrNull("keyNotExist")
    private val booleanArrayOptionalErrKey by bindBooleanArrayArgOrNull("keyNotExist")
    private val charArrayOptionalErrKEy by bindCharArrayArgOrNull("keyNotExist")
    private val charSequenceOptionalErrKet by bindCharSequenceArgOrNull("keyNotExist")
    private val charSequenceArrayOptionalErrKey by bindCharSequenceArrayArgOrNull("keyNotExist")
    private val parcelableOptionalErrKey by bindParcelableArgOrNull<TestParcelable>("keyNotExist")
    private val parcelableArrayOptionalErrKey by bindParcelableArrayArgOrNull<Parcelable>("keyNotExist")
    private val parcelableArrayListOptionalErrKey by bindParcelableArrayListArgOrNull<TestParcelable>("keyNotExist")
    private val serializableOptionalErrKey by bindSerializableArgOrNull<TestSerializable>("keyNotExist")
    private val bundleOptionalErrKey by bindBundleArgOrNull("keyNotExist")
    private val charSequenceArrayListOptionalErrKey by bindCharSequenceArrayListArgOrNull("keyNotExist")
    private val byteArrayOptionalErrKey by bindByteArrayArgOrNull("keyNotExist")
    private val shortArrayOptionalErrKey by bindShortArrayArgOrNull("keyNotExist")
    private val intArrayOptionalErrKey by bindIntArrayArgOrNull("keyNotExist")
    private val intArrayListOptionalErrKey by bindIntArrayListArgOrNull("keyNotExist")
    private val longArrayOptionalErrKey by bindLongArrayArgOrNull("keyNotExist")
    private val floatArrayOptionalErrKey by bindFloatArrayArgOrNull("keyNotExist")
    private val doubleArrayOptionalErrKey by bindDoubleArrayArgOrNull("keyNotExist")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.at_test)

        val supportFragment = TestSupportFragment()
        supportFragment.arguments = TestSupportFragment.createArguments(this)
        supportFragmentManager.beginTransaction().replace(R.id.testAt_frame, supportFragment).commit()
    }

    val fragment: TestSupportFragment
        get() = supportFragmentManager.findFragmentById(R.id.testAt_frame) as TestSupportFragment

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

        Assert.assertTrue(extrasRequired.getString("extrasRequired") == "extrasRequired")
        Assert.assertTrue(extrasOptional?.getString("extrasOptional") == "extrasOptional")
        Assert.assertTrue(extrasOrDefault.getString("extrasOrDefault") == "extrasOrDefault")

        //-------------

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
    }


    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, TestActivity::class.java).apply {

                putExtra("byteRequired", 2.toByte())
                putExtra("byteArrayRequired", byteArrayOf(2.toByte(), (-2).toByte()))
                putExtra("byteArrayOptional", byteArrayOf((-2).toByte(), 2.toByte()))

                putExtra("shortRequired", 3.toShort())
                putExtra("shortArrayRequired", shortArrayOf(3.toShort(), (-3).toShort()))
                putExtra("shortArrayOptional", shortArrayOf((-3).toShort(), 3.toShort()))

                putExtra("intRequired", 500)
                putExtra("intArrayRequired", intArrayOf(500, -500))
                putExtra("intArrayOptional", intArrayOf(-500, 500))
                putIntegerArrayListExtra("intArrayListRequired", arrayListOf(500, -500))
                putIntegerArrayListExtra("intArrayListOptional", arrayListOf(-500, 500))

                putExtra("longRequired", 1000L)
                putExtra("longArrayRequired", longArrayOf(1000L, -1000L))
                putExtra("longArrayOptional", longArrayOf(-1000L, 1000L))

                putExtra("floatRequired", 4f)
                putExtra("floatArrayRequired", floatArrayOf(4f, -4f))
                putExtra("floatArrayOptional", floatArrayOf(-4f, 4f))

                putExtra("doubleRequired", 6.toDouble())
                putExtra("doubleArrayRequired", doubleArrayOf(6.toDouble(), (-6).toDouble()))
                putExtra("doubleArrayOptional", doubleArrayOf((-6).toDouble(), 6.toDouble()))

                putExtra("booleanRequired", true)
                putExtra("booleanArrayRequired", booleanArrayOf(true, false))
                putExtra("booleanArrayOptional", booleanArrayOf(false, true))

                putExtra("charRequired", 'a')
                putExtra("charArrayRequired", charArrayOf('a', 'b'))
                putExtra("charArrayOptional", charArrayOf('b', 'a'))

                putExtra("stringRequired", "stringRequired")
                putExtra("stringOptional", "stringOptional")
                putExtra("stringArrayRequired", arrayOf("stringRequired", "stringOptional"))
                putExtra("stringArrayOptional", arrayOf("stringOptional", "stringRequired"))
                putStringArrayListExtra("stringArrayListRequired", arrayListOf("stringRequired", "stringOptional"))
                putStringArrayListExtra("stringArrayListOptional", arrayListOf("stringOptional", "stringRequired"))

                putExtra("charSequenceRequired", "stringRequired")
                putExtra("charSequenceOptional", "stringOptional")
                putExtra("charSequenceArrayRequired", arrayOf("stringRequired", "stringOptional"))
                putExtra("charSequenceArrayOptional", arrayOf("stringOptional", "stringRequired"))

                putExtra("parcelableRequired", TestParcelable("parcelableRequired"))
                putExtra("parcelableOptional", TestParcelable("parcelableOptional"))
                putExtra("parcelableArrayRequired", arrayOf(TestParcelable("parcelableRequired"), TestParcelable("parcelableOptional")))
                putExtra("parcelableArrayOptional", arrayOf(TestParcelable("parcelableOptional"), TestParcelable("parcelableRequired")))
                putParcelableArrayListExtra("parcelableArrayListRequired", arrayListOf(TestParcelable("parcelableRequired"), TestParcelable("parcelableOptional")))
                putParcelableArrayListExtra("parcelableArrayListOptional", arrayListOf(TestParcelable("parcelableOptional"), TestParcelable("parcelableRequired")))

                putExtra("serializableRequired", TestSerializable("serializableRequired"))
                putExtra("serializableOptional", TestSerializable("serializableOptional"))

                putExtra("bundleRequired", Bundle().apply { putString("bundle", "bundleRequired") })
                putExtra("bundleOptional", Bundle().apply { putString("bundle", "bundleOptional") })

                //---
                putExtra("byteArrayOrDefault", byteArrayOf(2.toByte(), (-2).toByte()))
                putExtra("shortArrayOrDefault", shortArrayOf(3.toShort(), (-3).toShort()))
                putExtra("intArrayOrDefault", intArrayOf(500, -500))
                putIntegerArrayListExtra("intArrayListOrDefault", arrayListOf(600, -600))
                putExtra("longArrayOrDefault", longArrayOf(1000L, -1000L))
                putExtra("floatArrayOrDefault", floatArrayOf(4f, -4f))
                putExtra("doubleArrayOrDefault", doubleArrayOf(6.toDouble(), (-6).toDouble()))
                putExtra("booleanArrayOrDefault", booleanArrayOf(true, false))
                putExtra("charArrayOrDefault", charArrayOf('a', 'b'))
                putExtra("charSequenceOrDefault", "charSequenceOrDefault" as CharSequence)
                putExtra("charSequenceArrayOrDefault", arrayOf<CharSequence>("charSequence", "default"))

                putCharSequenceArrayListExtra("charSequenceArrayListRequired", arrayListOf("charSequenceArrayListRequired", "required"))
                putCharSequenceArrayListExtra("charSequenceArrayListOptional", arrayListOf("charSequenceArrayListOptional", "optional"))
                putCharSequenceArrayListExtra("charSequenceArrayListOrDefault", arrayListOf("charSequenceArrayListOrDefault", "default"))

                putExtra("stringOrDefault", "stringOrDefault")
                putExtra("stringArrayOrDefault", arrayOf("stringArrayOrDefault", "default"))
                putExtra("stringArrayListOrDefault", arrayListOf("stringArrayListOrDefault", "default"))

                putExtra("parcelableOrDefault", TestParcelable("parcelableOrDefault"))
                putExtra("parcelableArrayOrDefault", arrayOf(TestParcelable("parcelableArrayOrDefault"), TestParcelable("default")))
                putParcelableArrayListExtra("parcelableArrayListOrDefault", arrayListOf(TestParcelable("parcelableArrayListOrDefault"), TestParcelable("default")))

                putExtra("serializableOrDefault", TestSerializable("serializableOrDefault"))

                putExtra("bundleOrDefault", Bundle().apply { putString("bundle", "bundleOrDefault") })

                putExtras(Bundle().apply { putString("extrasRequired", "extrasRequired") })
                putExtras(Bundle().apply { putString("extrasOptional", "extrasOptional") })
                putExtras(Bundle().apply { putString("extrasOrDefault", "extrasOrDefault") })
            }
        }

    }
}