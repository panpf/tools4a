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

class TestActivity : androidx.fragment.app.FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.at_test)
        val supportFragment = TestSupportFragment.newInstance(intent.extras)
        supportFragmentManager.beginTransaction().replace(R.id.testAt_frame, supportFragment).commit()
    }

    val fragment: TestSupportFragment
        get() = (supportFragmentManager.findFragmentById(R.id.testAt_frame) as TestSupportFragment?)!!

    fun checkParams() {
        val activity = this

        val byteRequired = activity.readByteArgOr("byteRequired", 0.toByte())
        val byteRequiredErrKey = activity.readByteArgOr("byteRequiredErrKey", 0.toByte())
        val byteArrayRequired = activity.readByteArrayArgOrThrow("byteArrayRequired")
        val byteArrayOptional = activity.readByteArrayArgOrNull("byteArrayOptional")
        val byteArrayOptionalErrKey = activity.readByteArrayArgOrNull("byteArrayOptionalErrKey")
        val byteArrayOrDefault = activity.readByteArrayArgOr("byteArrayOrDefault", byteArrayOf(0.toByte(), 0.toByte()))
        val byteArrayOrDefaultErrKey = activity.readByteArrayArgOr("byteArrayOrDefaultErrKey", byteArrayOf(0.toByte(), (-1).toByte()))

        val shortRequired = activity.readShortArgOr("shortRequired", 0.toShort())
        val shortRequiredErrKey = activity.readShortArgOr("shortRequiredErrKey", 0.toShort())
        val shortArrayRequired = activity.readShortArrayArgOrThrow("shortArrayRequired")
        val shortArrayOptional = activity.readShortArrayArgOrNull("shortArrayOptional")
        val shortArrayOptionalErrKey = activity.readShortArrayArgOrNull("shortArrayOptionalErrKey")
        val shortArrayOrDefault = activity.readShortArrayArgOr("shortArrayOrDefault", shortArrayOf(0.toShort(), 0.toShort()))
        val shortArrayOrDefaultErrKey = activity.readShortArrayArgOr("shortArrayOrDefaultErrKey", shortArrayOf(0.toShort(), (-1).toShort()))


        val intRequired = activity.readIntArgOr("intRequired", 0)
        val intRequiredErrKey = activity.readIntArgOr("intRequiredErrKey", 0)
        val intArrayRequired = activity.readIntArrayArgOrThrow("intArrayRequired")
        val intArrayOptional = activity.readIntArrayArgOrNull("intArrayOptional")
        val intArrayOptionalErrKey = activity.readIntArrayArgOrNull("intArrayOptionalErrKey")
        val intArrayOrDefault = activity.readIntArrayArgOr("intArrayOrDefault", intArrayOf(0, 0))
        val intArrayOrDefaultErrKey = activity.readIntArrayArgOr("intArrayOrDefaultErrKey", intArrayOf(0, -1))
        val intArrayListRequired = activity.readIntArrayListArgOrThrow("intArrayListRequired")
        val intArrayListOrDefault = activity.readIntArrayListArgOr("intArrayListOrDefault", arrayListOf(0, 0))
        val intArrayListOrDefaultErrKey = activity.readIntArrayListArgOr("intArrayListOrDefaultErrKey", arrayListOf(0, 0))
        val intArrayListOptional = activity.readIntArrayListArgOrNull("intArrayListOptional")
        val intArrayListOptionalErrKey = activity.readIntArrayListArgOrNull("intArrayListOptionalErrKey")

        val longRequired = activity.readLongArgOr("longRequired", 0)
        val longRequiredErrKey = activity.readLongArgOr("longRequiredErrKey", 0)
        val longArrayRequired = activity.readLongArrayArgOrThrow("longArrayRequired")
        val longArrayOptional = activity.readLongArrayArgOrNull("longArrayOptional")
        val longArrayOptionalErrKey = activity.readLongArrayArgOrNull("longArrayOptionalErrKey")
        val longArrayOrDefault = activity.readLongArrayArgOr("longArrayOrDefault", longArrayOf(0, 0))
        val longArrayOrDefaultErrKey = activity.readLongArrayArgOr("longArrayOrDefaultErrKey", longArrayOf(0, -1))

        val floatRequired = activity.readFloatArgOr("floatRequired", 0f)
        val floatRequiredErrKey = activity.readFloatArgOr("floatRequiredErrKey", 0f)
        val floatArrayRequired = activity.readFloatArrayArgOrThrow("floatArrayRequired")
        val floatArrayOptional = activity.readFloatArrayArgOrNull("floatArrayOptional")
        val floatArrayOptionalErrKey = activity.readFloatArrayArgOrNull("DoubleArrayOptionalErrKey")
        val floatArrayOrDefault = activity.readFloatArrayArgOr("floatArrayOrDefault", floatArrayOf(0f, 0f))
        val floatArrayOrDefaultErrKey = activity.readFloatArrayArgOr("floatArrayOrDefaultErrKey", floatArrayOf(0f, -1f))

        val doubleRequired = activity.readDoubleArgOr("doubleRequired", 0.0)
        val doubleRequiredErrKey = activity.readDoubleArgOr("doubleRequiredErrKey", 0.0)
        val doubleArrayRequired = activity.readDoubleArrayArgOrThrow("doubleArrayRequired")
        val doubleArrayOptional = activity.readDoubleArrayArgOrNull("doubleArrayOptional")
        val doubleArrayOptionalErrKey = activity.readDoubleArrayArgOrNull("doubleArrayOptionalErrKey")
        val doubleArrayOrDefault = activity.readDoubleArrayArgOr("doubleArrayOrDefault", doubleArrayOf(0.0, 0.0))
        val doubleArrayOrDefaultErrKey = activity.readDoubleArrayArgOr("doubleArrayOrDefaultErrKey", doubleArrayOf(0.0, -1.0))

        val booleanRequired = activity.readBooleanArgOr("booleanRequired", false)
        val booleanRequiredErrKey = activity.readBooleanArgOr("booleanRequiredErrKey", false)
        val booleanArrayRequired = activity.readBooleanArrayArgOrThrow("booleanArrayRequired")
        val booleanArrayOptional = activity.readBooleanArrayArgOrNull("booleanArrayOptional")
        val booleanArrayOptionalErrKey = activity.readBooleanArrayArgOrNull("booleanArrayOptionalErrKey")
        val booleanArrayOrDefault = activity.readBooleanArrayArgOr("booleanArrayOrDefault", booleanArrayOf(true, false))
        val booleanArrayOrDefaultErrKey = activity.readBooleanArrayArgOr("booleanArrayOrDefaultErrKey", booleanArrayOf(false, true))

        val charRequired = activity.readCharArgOr("charRequired", 'a')
        val charRequiredErrKey = activity.readCharArgOr("charRequiredErrKey", 'b')
        val charArrayRequired = activity.readCharArrayArgOrThrow("charArrayRequired")
        val charArrayOptional = activity.readCharArrayArgOrNull("charArrayOptional")
        val charArrayOptionalErrKey = activity.readCharArrayArgOrNull("charArrayOptionalErrKey")
        val charArrayOrDefault = activity.readCharArrayArgOr("charArrayOrDefault", charArrayOf('a', 'b'))
        val charArrayOrDefaultErrKey = activity.readCharArrayArgOr("charArrayOrDefaultErrKey", charArrayOf('b', 'a'))

        val stringRequired = activity.readStringArgOr("stringRequired", "stringRequired")
        val stringRequiredErrKey = activity.readStringArgOr("stringRequiredErrKey", "stringRequiredErrKey")
        val stringArrayRequired = activity.readStringArrayArgOrThrow("stringArrayRequired")
        val stringArrayOptional = activity.readStringArrayArgOrNull("stringArrayOptional")
        val stringArrayOptionalErrKey = activity.readStringArrayArgOrNull("stringArrayOptionalErrKey")
        val stringArrayOrDefault = activity.readStringArrayArgOr("stringArrayOrDefault", arrayOf("array", "dft"))
        val stringArrayOrDefaultErrKey = activity.readStringArrayArgOr("stringArrayOrDefaultErrKey", arrayOf("error", "erk"))
        val stringArrayListRequired = activity.readStringArrayListArgOrThrow("stringArrayListRequired")
        val stringArrayListOrDefault = activity.readStringArrayListArgOr("stringArrayListOrDefault", arrayListOf("list", "default"))
        val stringArrayListOrDefaultErrKey = activity.readStringArrayListArgOr("stringArrayListOrDefaultErrKey", arrayListOf("stringArrayListOrDefaultErrKey", "errKey"))
        val stringArrayListOptional = activity.readStringArrayListArgOrNull("stringArrayListOptional")
        val stringArrayListOptionalErrKey = activity.readStringArrayListArgOrNull("stringArrayListOptionalErrKey")

        val charSequenceRequired = activity.readCharSequenceArgOr("charSequenceRequired", "charSequenceRequired")
        val charSequenceRequiredErrKey = activity.readCharSequenceArgOr("charSequenceRequiredErrKey", "charSequenceRequiredErrKey")
        val charSequenceArrayRequired = activity.readCharSequenceArrayArgOrThrow("charSequenceArrayRequired")
        val charSequenceArrayOptional = activity.readCharSequenceArrayArgOrNull("charSequenceArrayOptional")
        val charSequenceArrayOptionalErrKey = activity.readCharSequenceArrayArgOrNull("charSequenceArrayOptionalErrKey")
        val charSequenceArrayOrDefault = activity.readCharSequenceArrayArgOr("charSequenceArrayOrDefault", arrayOf("array", "dft"))
        val charSequenceArrayOrDefaultErrKey = activity.readCharSequenceArrayArgOr("charSequenceArrayOrDefaultErrKey", arrayOf("error", "erk"))
        val charSequenceArrayListRequired = activity.readCharSequenceArrayListArgOrThrow("charSequenceArrayListRequired")
        val charSequenceArrayListOrDefault = activity.readCharSequenceArrayListArgOr("charSequenceArrayListOrDefault", arrayListOf("list", "default"))
        val charSequenceArrayListOrDefaultErrKey = activity.readCharSequenceArrayListArgOr("charSequenceArrayListOrDefaultErrKey", arrayListOf("charSequenceArrayListOrDefaultErrKey", "errKey"))
        val charSequenceArrayListOptional = activity.readCharSequenceArrayListArgOrNull("charSequenceArrayListOptional")
        val charSequenceArrayListOptionalErrKey = activity.readCharSequenceArrayListArgOrNull("charSequenceArrayListOptionalErrKey")


        val parcelableRequired = activity.readParcelableArgOr("parcelableRequired", TestParcelable("required"))
        val parcelableRequiredErrKey = activity.readParcelableArgOr("parcelableRequiredErrKey", TestParcelable("parcelableRequiredErrKey"))
        val parcelableArrayRequired = activity.readParcelableArrayArgOrThrow<Parcelable>("parcelableArrayRequired")
        val parcelableArrayOptional = activity.readParcelableArrayArgOrNull<Parcelable>("parcelableArrayOptional")
        val parcelableArrayOptionalErrKey = activity.readParcelableArrayArgOrNull<Parcelable>("parcelableArrayOptionalErrKey")
        val parcelableArrayOrDefault = activity.readParcelableArrayArgOr("parcelableArrayOrDefault", arrayOf())
        val parcelableArrayOrDefaultErrKey = activity.readParcelableArrayArgOr("parcelableArrayOrDefaultErrKey", arrayOf(TestParcelable("error"), TestParcelable("erk")))
        val parcelableArrayListRequired = activity.readParcelableArrayListArgOrThrow<Parcelable>("parcelableArrayListRequired")
        val parcelableArrayListOrDefault = activity.readParcelableArrayListArgOr("parcelableArrayListOrDefault", arrayListOf<Parcelable>(TestParcelable("list"), TestParcelable("default")))
        val parcelableArrayListOrDefaultErrKey = activity.readParcelableArrayListArgOr("parcelableArrayListOrDefaultErrKey", arrayListOf<Parcelable>(TestParcelable("parcelableArrayListOrDefaultErrKey"), TestParcelable("errKey")))
        val parcelableArrayListOptional = activity.readParcelableArrayListArgOrNull<Parcelable>("parcelableArrayListOptional")
        val parcelableArrayListOptionalErrKey = activity.readParcelableArrayListArgOrNull<Parcelable>("parcelableArrayListOptionalErrKey")

        val serializableRequired = activity.readSerializableArgOrThrow<TestSerializable>("serializableRequired")
        val serializableOptional = activity.readSerializableArgOrNull<TestSerializable>("serializableOptional")
        val serializableOptionalErrKey = activity.readSerializableArgOrNull<TestSerializable>("serializableOptionalErrKey")
        val serializableOrDefault = activity.readSerializableArgOr("serializableOrDefault", TestSerializable("default"))
        val serializableOrDefaultErrKey = activity.readSerializableArgOr("serializableOrDefaultErrKey", TestSerializable("errKey"))

        val bundleRequired = activity.readBundleArgOrThrow("bundleRequired")
        val bundleOptional = activity.readBundleArgOrNull("bundleOptional")
        val bundleOptionalErrKey = activity.readBundleArgOrNull("bundleOptionalErrKey")
        val bundleOrDefault = activity.readBundleArgOr("bundleOrDefault", Bundle())
        val defaultBundle = Bundle()
        defaultBundle.putString("bundle", "bundleErrKey")
        val bundleOrDefaultErrKey = activity.readBundleArgOr("bundleOrDefaultErrKey", defaultBundle)

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
            val starter = Intent(context, TestActivity::class.java)
            starter.putExtra("byteRequired", 2.toByte())
            starter.putExtra("byteArrayRequired", byteArrayOf(2.toByte(), (-2).toByte()))
            starter.putExtra("byteArrayOrDefault", byteArrayOf(2.toByte(), (-2).toByte()))
            starter.putExtra("byteArrayOptional", byteArrayOf((-2).toByte(), 2.toByte()))


            starter.putExtra("shortRequired", 3.toShort())
            starter.putExtra("shortArrayRequired", shortArrayOf(3.toShort(), (-3).toShort()))
            starter.putExtra("shortArrayOptional", shortArrayOf((-3).toShort(), 3.toShort()))

            starter.putExtra("intRequired", 500)
            starter.putExtra("intArrayRequired", intArrayOf(500, -500))
            starter.putExtra("intArrayOptional", intArrayOf(-500, 500))
            starter.putIntegerArrayListExtra("intArrayListRequired", arrayListOf(500, -500))
            starter.putIntegerArrayListExtra("intArrayListOptional", arrayListOf(-500, 500))

            starter.putExtra("longRequired", 1000L)
            starter.putExtra("longArrayRequired", longArrayOf(1000L, -1000L))
            starter.putExtra("longArrayOptional", longArrayOf(-1000L, 1000L))

            starter.putExtra("floatRequired", 4f)
            starter.putExtra("floatArrayRequired", floatArrayOf(4f, -4f))
            starter.putExtra("floatArrayOptional", floatArrayOf(-4f, 4f))


            starter.putExtra("doubleRequired", 6.0)
            starter.putExtra("doubleArrayRequired", doubleArrayOf(6.0, -6.0))
            starter.putExtra("doubleArrayOptional", doubleArrayOf(-6.0, 6.0))


            starter.putExtra("booleanRequired", true)
            starter.putExtra("booleanArrayRequired", booleanArrayOf(true, false))
            starter.putExtra("booleanArrayOptional", booleanArrayOf(false, true))


            starter.putExtra("charRequired", 'a')
            starter.putExtra("charArrayRequired", charArrayOf('a', 'b'))
            starter.putExtra("charArrayOptional", charArrayOf('b', 'a'))

            starter.putExtra("stringRequired", "stringRequired")
            starter.putExtra("stringOptional", "stringOptional")
            starter.putExtra("stringArrayRequired", arrayOf("stringRequired", "stringOptional"))
            starter.putExtra("stringArrayOptional", arrayOf("stringOptional", "stringRequired"))
            starter.putStringArrayListExtra("stringArrayListRequired", arrayListOf("stringRequired", "stringOptional"))
            starter.putStringArrayListExtra("stringArrayListOptional", arrayListOf("stringOptional", "stringRequired"))

            starter.putExtra("charSequenceRequired", "stringRequired")
            starter.putExtra("charSequenceOptional", "stringOptional")
            starter.putExtra("charSequenceArrayRequired", arrayOf("stringRequired", "stringOptional"))
            starter.putExtra("charSequenceArrayOptional", arrayOf("stringOptional", "stringRequired"))


            starter.putExtra("parcelableRequired", TestParcelable("parcelableRequired"))
            starter.putExtra("parcelableOptional", TestParcelable("parcelableOptional"))
            starter.putExtra("parcelableArrayRequired", arrayOf(TestParcelable("parcelableRequired"), TestParcelable("parcelableOptional")))
            starter.putExtra("parcelableArrayOptional", arrayOf(TestParcelable("parcelableOptional"), TestParcelable("parcelableRequired")))
            starter.putParcelableArrayListExtra("parcelableArrayListRequired", arrayListOf(TestParcelable("parcelableRequired"), TestParcelable("parcelableOptional")))
            starter.putParcelableArrayListExtra("parcelableArrayListOptional", arrayListOf(TestParcelable("parcelableOptional"), TestParcelable("parcelableRequired")))


            starter.putExtra("serializableRequired", TestSerializable("serializableRequired"))
            starter.putExtra("serializableOptional", TestSerializable("serializableOptional"))

            starter.putExtra("byteArrayOrDefault", byteArrayOf(2.toByte(), (-2).toByte()))
            starter.putExtra("shortArrayOrDefault", shortArrayOf(3.toShort(), (-3).toShort()))
            starter.putExtra("intArrayOrDefault", intArrayOf(500, -500))
            starter.putIntegerArrayListExtra("intArrayListOrDefault", arrayListOf(600, -600))
            starter.putExtra("longArrayOrDefault", longArrayOf(1000L, -1000L))
            starter.putExtra("floatArrayOrDefault", floatArrayOf(4f, -4f))
            starter.putExtra("doubleArrayOrDefault", doubleArrayOf(6.0, -6.0))
            starter.putExtra("booleanArrayOrDefault", booleanArrayOf(true, false))
            starter.putExtra("charArrayOrDefault", charArrayOf('a', 'b'))
            starter.putExtra("charSequenceOrDefault", "charSequenceOrDefault")
            starter.putExtra("charSequenceArrayOrDefault", arrayOf("charSequence", "default"))


            starter.putCharSequenceArrayListExtra("charSequenceArrayListRequired", arrayListOf("charSequenceArrayListRequired", "required"))
            starter.putCharSequenceArrayListExtra("charSequenceArrayListOptional", arrayListOf("charSequenceArrayListOptional", "optional"))
            starter.putCharSequenceArrayListExtra("charSequenceArrayListOrDefault", arrayListOf("charSequenceArrayListOrDefault", "default"))

            starter.putExtra("stringOrDefault", "stringOrDefault")
            starter.putExtra("stringArrayOrDefault", arrayOf("stringArrayOrDefault", "default"))
            starter.putExtra("stringArrayListOrDefault", arrayListOf("stringArrayListOrDefault", "default"))

            starter.putExtra("parcelableOrDefault", TestParcelable("parcelableOrDefault"))
            starter.putExtra("parcelableArrayOrDefault", arrayOf(TestParcelable("parcelableArrayOrDefault"), TestParcelable("default")))
            starter.putParcelableArrayListExtra("parcelableArrayListOrDefault", arrayListOf(TestParcelable("parcelableArrayListOrDefault"), TestParcelable("default")))

            starter.putExtra("serializableOrDefault", TestSerializable("serializableOrDefault"))

            val bundleDefault = Bundle()
            bundleDefault.putString("bundle", "bundleOrDefault")
            starter.putExtra("bundleOrDefault", bundleDefault)

            val bundleRequired = Bundle()
            bundleRequired.putString("bundle", "bundleRequired")
            starter.putExtra("bundleRequired", bundleRequired)

            val bundleOptional = Bundle()
            bundleOptional.putString("bundle", "bundleOptional")
            starter.putExtra("bundleOptional", bundleOptional)

            val b = Bundle()
            b.putString("extrasRequired", "extrasRequired")
            b.putString("extrasOptional", "extrasOptional")
            b.putString("extrasOrDefault", "extrasOrDefault")
            starter.putExtras(b)

            val args = Bundle()
            val sparseParcelableArrayRequired = SparseArray<Parcelable>()
            sparseParcelableArrayRequired.put(-1, TestParcelable("-1"))
            sparseParcelableArrayRequired.put(1, TestParcelable("1"))
            args.putSparseParcelableArray("sparseParcelableArrayRequired", sparseParcelableArrayRequired)

            val sparseParcelableArrayOptional = SparseArray<Parcelable>()
            sparseParcelableArrayOptional.put(-2, TestParcelable("-2"))
            sparseParcelableArrayOptional.put(2, TestParcelable("2"))
            args.putSparseParcelableArray("sparseParcelableArrayOptional", sparseParcelableArrayOptional)

            val sparseParcelableArrayOrDefault = SparseArray<Parcelable>()
            sparseParcelableArrayOrDefault.put(-3, TestParcelable("-3"))
            sparseParcelableArrayOrDefault.put(3, TestParcelable("3"))
            args.putSparseParcelableArray("sparseParcelableArrayOrDefault", sparseParcelableArrayOrDefault)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                args.putBinder("binderRequired", TestBinder("binderRequired"))
                args.putBinder("binderOptional", TestBinder("binderOptional"))
                args.putBinder("binderOrDefault", TestBinder("binderOrDefault"))
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                args.putSize("sizeRequired", Size(1, 1))
                args.putSize("sizeOptional", Size(2, 2))
                args.putSize("sizeOrDefault", Size(3, 3))

                args.putSizeF("sizeFRequired", SizeF(1f, 1f))
                args.putSizeF("sizeFOptional", SizeF(2f, 2f))
                args.putSizeF("sizeFOrDefault", SizeF(3f, 3f))
            }

            starter.putExtras(args)

            return starter
        }
    }
}